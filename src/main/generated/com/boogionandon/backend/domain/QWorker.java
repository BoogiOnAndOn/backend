package com.boogionandon.backend.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWorker is a Querydsl query type for Worker
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWorker extends EntityPathBase<Worker> {

    private static final long serialVersionUID = 1870712544L;

    public static final QWorker worker = new QWorker("worker");

    public final QMember _super = new QMember(this);

    //inherited
    public final StringPath address = _super.address;

    //inherited
    public final StringPath addressDetail = _super.addressDetail;

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final BooleanPath delFlag = _super.delFlag;

    //inherited
    public final StringPath email = _super.email;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    //inherited
    public final NumberPath<Long> managerId = _super.managerId;

    //inherited
    public final ListPath<com.boogionandon.backend.domain.enums.MemberType, EnumPath<com.boogionandon.backend.domain.enums.MemberType>> memberRoleList = _super.memberRoleList;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final StringPath password = _super.password;

    //inherited
    public final StringPath phone = _super.phone;

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    //inherited
    public final StringPath username = _super.username;

    public final NumberPath<Double> vehicleCapacity = createNumber("vehicleCapacity", Double.class);

    public QWorker(String variable) {
        super(Worker.class, forVariable(variable));
    }

    public QWorker(Path<? extends Worker> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorker(PathMetadata metadata) {
        super(Worker.class, metadata);
    }

}

