package com.boogionandon.backend.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1575028220L;

    public static final QMember member = new QMember("member1");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath address = createString("address");

    public final StringPath addressDetail = createString("addressDetail");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final BooleanPath delFlag = createBoolean("delFlag");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Long> managerId = createNumber("managerId", Long.class);

    public final ListPath<com.boogionandon.backend.domain.enums.MemberType, EnumPath<com.boogionandon.backend.domain.enums.MemberType>> memberRoleList = this.<com.boogionandon.backend.domain.enums.MemberType, EnumPath<com.boogionandon.backend.domain.enums.MemberType>>createList("memberRoleList", com.boogionandon.backend.domain.enums.MemberType.class, EnumPath.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath username = createString("username");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

