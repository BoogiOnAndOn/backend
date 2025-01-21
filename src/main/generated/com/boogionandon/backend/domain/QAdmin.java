package com.boogionandon.backend.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAdmin is a Querydsl query type for Admin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdmin extends EntityPathBase<Admin> {

    private static final long serialVersionUID = -237399123L;

    public static final QAdmin admin = new QAdmin("admin");

    public final QMember _super = new QMember(this);

    //inherited
    public final StringPath address = _super.address;

    //inherited
    public final StringPath addressDetail = _super.addressDetail;

    public final ListPath<String, StringPath> assignmentAreaList = this.<String, StringPath>createList("assignmentAreaList", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath contact = createString("contact");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final BooleanPath delFlag = _super.delFlag;

    public final StringPath department = createString("department");

    //inherited
    public final StringPath email = _super.email;

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

    public final StringPath position = createString("position");

    //inherited
    public final StringPath username = _super.username;

    public final StringPath workCity = createString("workCity");

    public final StringPath workPlace = createString("workPlace");

    public QAdmin(String variable) {
        super(Admin.class, forVariable(variable));
    }

    public QAdmin(Path<? extends Admin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdmin(PathMetadata metadata) {
        super(Admin.class, metadata);
    }

}

