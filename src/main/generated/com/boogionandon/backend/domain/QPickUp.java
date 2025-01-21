package com.boogionandon.backend.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPickUp is a Querydsl query type for PickUp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPickUp extends EntityPathBase<PickUp> {

    private static final long serialVersionUID = 1664319998L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPickUp pickUp = new QPickUp("pickUp");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Image, QImage> images = this.<Image, QImage>createList("images", Image.class, QImage.class, PathInits.DIRECT2);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final EnumPath<com.boogionandon.backend.domain.enums.TrashType> mainTrashType = createEnum("mainTrashType", com.boogionandon.backend.domain.enums.TrashType.class);

    public final StringPath pickUpPlace = createString("pickUpPlace");

    public final NumberPath<Integer> realTrashAmount = createNumber("realTrashAmount", Integer.class);

    public final EnumPath<com.boogionandon.backend.domain.enums.ReportStatus> status = createEnum("status", com.boogionandon.backend.domain.enums.ReportStatus.class);

    public final DateTimePath<java.time.LocalDateTime> submitDateTime = createDateTime("submitDateTime", java.time.LocalDateTime.class);

    public final QWorker submitter;

    public QPickUp(String variable) {
        this(PickUp.class, forVariable(variable), INITS);
    }

    public QPickUp(Path<? extends PickUp> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPickUp(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPickUp(PathMetadata metadata, PathInits inits) {
        this(PickUp.class, metadata, inits);
    }

    public QPickUp(Class<? extends PickUp> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.submitter = inits.isInitialized("submitter") ? new QWorker(forProperty("submitter")) : null;
    }

}

