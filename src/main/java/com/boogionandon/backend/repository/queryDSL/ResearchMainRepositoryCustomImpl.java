package com.boogionandon.backend.repository.queryDSL;

import com.boogionandon.backend.domain.QBeach;
import com.boogionandon.backend.domain.QImage;
import com.boogionandon.backend.domain.QMember;
import com.boogionandon.backend.domain.QResearchMain;
import com.boogionandon.backend.domain.QResearchSub;
import com.boogionandon.backend.domain.QWorker;
import com.boogionandon.backend.domain.ResearchMain;
import com.boogionandon.backend.domain.enums.ReportStatus;
import com.boogionandon.backend.dto.PageRequestDTO;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;


@Repository
@Log4j2
@RequiredArgsConstructor
public class ResearchMainRepositoryCustomImpl implements ResearchMainRepositoryCustom{

  private final JPAQueryFactory queryFactory;


  // New 작업에 관한 메서드
  // 조사 완료탭에 들어감 -> 조사는 완료했지만 배정은 되지 않은 상태
  // super admin은 전부 다 볼 수 있어야 함
  @Override
  public Page<ResearchMain> findByStatusNeededAndSearchForSuper(String beachSearch, Pageable pageable) {

    QResearchMain researchMain = QResearchMain.researchMain;
    QResearchSub researchSub = QResearchSub.researchSub;
    QBeach beach = QBeach.beach;


    JPAQuery<ResearchMain> query = queryFactory
        .selectFrom(researchMain)
        // 이게 맞나?
        .leftJoin(researchMain.researchSubList, researchSub).fetchJoin()
        .leftJoin(researchMain.beach, beach).fetchJoin()
        .where(checkStatusNeededAndSearch(beachSearch));

    // 정렬 적용
    OrderSpecifier<?> orderSpecifier = createOrderSpecifier(pageable.getSort());
    if (orderSpecifier != null) {
      query.orderBy(orderSpecifier);
    }

    List<ResearchMain> content = query
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    log.info("content : " + content);

    long total = countByStatusNeededAndSearchForSuper(beachSearch);
    log.info("total : " + total);

    return new PageImpl<>(content, pageable, total);
  }
  // 일반 admin은 자기가 관리하는 것들만 볼 수 있음
 @Override
  public Page<ResearchMain> findByStatusNeededAndSearchForRegular(String beachSearch, Pageable pageable, Long adminId) {

    QResearchMain researchMain = QResearchMain.researchMain;
    QResearchSub researchSub = QResearchSub.researchSub;
    QBeach beach = QBeach.beach;
    QWorker researcher = QWorker.worker;
    QMember member = QMember.member;  // Member 엔티티에 대한 Q 클래스를 추가 // 상속 받기 때문인것 같음


    JPAQuery<ResearchMain> query = queryFactory
        .selectFrom(researchMain)
        // 이게 맞나?
        .leftJoin(researchMain.researchSubList, researchSub).fetchJoin()
        .leftJoin(researchMain.beach, beach).fetchJoin()
        .leftJoin(researchMain.researcher, researcher).fetchJoin()
        .innerJoin(member).on(member.id.eq(researcher.id)) // Worker와 Member를 조인
        .where(
            checkStatusNeededAndSearch(beachSearch),
            member.managerId.eq(adminId)  // member.managerId를 사용
        );

    // 정렬 적용
    OrderSpecifier<?> orderSpecifier = createOrderSpecifier(pageable.getSort());
    if (orderSpecifier != null) {
      query.orderBy(orderSpecifier);
    }

    List<ResearchMain> content = query
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    log.info("content : " + content);

    long total = countByStatusNeededAndSearchForRegular(beachSearch, adminId);
    log.info("total : " + total);

    return new PageImpl<>(content, pageable, total);
  }

  // closed 작업에 관한 메서드
  // 작업 조회 탭에 들어감 -> 조사는 완료하고 배정까지 되어 있는 상태
  @Override
  public Page<ResearchMain> findByStatusCompletedAndSearchForSuper(String beachSearch, Pageable pageable) {
    QResearchMain researchMain = QResearchMain.researchMain;
    QResearchSub researchSub = QResearchSub.researchSub;
    QBeach beach = QBeach.beach;

    JPAQuery<ResearchMain> query = queryFactory
        .selectFrom(researchMain)
        // 이게 맞나?
        .leftJoin(researchMain.researchSubList, researchSub).fetchJoin()
        .leftJoin(researchMain.beach, beach).fetchJoin()
        .where(checkStatusCompletedAndSearch(beachSearch));

    // 정렬 적용
    OrderSpecifier<?> orderSpecifier = createOrderSpecifier(pageable.getSort());
    if (orderSpecifier != null) {
      query.orderBy(orderSpecifier);
    }

    List<ResearchMain> content = query
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    log.info("content : " + content);

    long total = countByStatusCompletedAndSearchForSuper(beachSearch);
    log.info("total : " + total);

    return new PageImpl<>(content, pageable, total);
  }
  @Override
  public Page<ResearchMain> findByStatusCompletedAndSearchForRegular(String beachSearch, Pageable pageable, Long adminId) {
    QResearchMain researchMain = QResearchMain.researchMain;
    QResearchSub researchSub = QResearchSub.researchSub;
    QBeach beach = QBeach.beach;
    QWorker researcher = QWorker.worker;
    QMember member = QMember.member;  // Member 엔티티에 대한 Q 클래스를 추가 // 상속 받기 때문인것 같음

    JPAQuery<ResearchMain> query = queryFactory
        .selectFrom(researchMain)
        // 이게 맞나?
        .leftJoin(researchMain.researchSubList, researchSub).fetchJoin()
        .leftJoin(researchMain.beach, beach).fetchJoin()
        .leftJoin(researchMain.researcher, researcher).fetchJoin()
        .innerJoin(member).on(member.id.eq(researcher.id)) // Worker와 Member를 조인
        .where(
            checkStatusCompletedAndSearch(beachSearch),
            member.managerId.eq(adminId)  // member.managerId를 사용
        );

    // 정렬 적용
    OrderSpecifier<?> orderSpecifier = createOrderSpecifier(pageable.getSort());
    if (orderSpecifier != null) {
      query.orderBy(orderSpecifier);
    }

    List<ResearchMain> content = query
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    log.info("content : " + content);

    long total = countByStatusCompletedAndSearchForRegular(beachSearch, adminId);
    log.info("total : " + total);

    return new PageImpl<>(content, pageable, total);
  }


  // ----------- Needed 관련 시작 ----------------------
  // 생각해 보니 status를 if 문으로 처리하려 해도 파라미터로 비교할 게 없으니 따로 만드는게 맞는듯
  private Predicate checkStatusNeededAndSearch(String beachSearch) {
    QResearchMain researchMain = QResearchMain.researchMain;
    QBeach beach = QBeach.beach;

    BooleanExpression statusCondition = researchMain.status.eq(ReportStatus.ASSIGNMENT_NEEDED);

    if (beachSearch != null && !beachSearch.isEmpty()) {
      BooleanExpression searchCondition = beach.beachName.contains(beachSearch);

      return statusCondition.and(searchCondition);
    } else {
      return statusCondition;
    }
  }
  private long countByStatusNeededAndSearchForSuper(String beachSearch) {
    QResearchMain researchMain = QResearchMain.researchMain;

    return queryFactory
        .select(researchMain.count())
        .from(researchMain)
        .where(checkStatusNeededAndSearch(beachSearch))
        .fetchOne();
  }
  private long countByStatusNeededAndSearchForRegular(String beachSearch, Long adminId) {
    QResearchMain researchMain = QResearchMain.researchMain;
    QWorker researcher = QWorker.worker;
    QMember member = QMember.member;  // Member 엔티티에 대한 Q 클래스를 추가 // 상속 받기 때문인것 같음

    return queryFactory
        .select(researchMain.count())
        .from(researchMain)
        .leftJoin(researchMain.researcher, researcher)
        .innerJoin(member).on(member.id.eq(researcher.id))// Worker와 Member를 조인
        .where(
            checkStatusNeededAndSearch(beachSearch),
            member.managerId.eq(adminId)  // member.managerId를 사용
        )
        .fetchOne();
  }
  // ----------- Needed 관련 끝 ----------------------

  // ----------- Completed 관련 시작 ----------------------
  private long countByStatusCompletedAndSearchForSuper(String beachSearch) {
    QResearchMain researchMain = QResearchMain.researchMain;

    return queryFactory
        .select(researchMain.count())
        .from(researchMain)
        .where(checkStatusCompletedAndSearch(beachSearch))
        .fetchOne();
  }

  private long countByStatusCompletedAndSearchForRegular(String beachSearch, Long adminId) {
    QResearchMain researchMain = QResearchMain.researchMain;
    QWorker researcher = QWorker.worker;
    QMember member = QMember.member;  // Member 엔티티에 대한 Q 클래스를 추가 // 상속 받기 때문인것 같음

    return queryFactory
        .select(researchMain.count())
        .from(researchMain)
        .leftJoin(researchMain.researcher, researcher)
        .innerJoin(member).on(member.id.eq(researcher.id))// Worker와 Member를 조인
        .where(
            checkStatusCompletedAndSearch(beachSearch),
            member.managerId.eq(adminId)  // member.managerId를 사용
        )
        .fetchOne();
  }

  // 생각해 보니 status를 if 문으로 처리하려 해도 파라미터로 비교할 게 없으니 따로 만드는게 맞는듯
  private Predicate checkStatusCompletedAndSearch(String beachSearch) {
    QResearchMain researchMain = QResearchMain.researchMain;
    QBeach beach = QBeach.beach;

    BooleanExpression statusCondition = researchMain.status.eq(ReportStatus.ASSIGNMENT_COMPLETED);

    if (beachSearch != null && !beachSearch.isEmpty()) {
      BooleanExpression searchCondition = beach.beachName.contains(beachSearch);

      return statusCondition.and(searchCondition);
    } else {
      return statusCondition;
    }
  }
  // ----------- Completed 관련 끝 ----------------------

  private OrderSpecifier<?> createOrderSpecifier(Sort sort) {
    if (sort.isEmpty()) {
      return null;
    }

    for (Sort.Order order : sort) {
      PathBuilder<ResearchMain> pathBuilder = new PathBuilder<>(ResearchMain.class, "researchMain");

      return new OrderSpecifier(
          order.isAscending() ? Order.ASC : Order.DESC,
          pathBuilder.get(order.getProperty())
      );
    }

    return null;
  }




  // 작업 조회에 관한 메서드

}