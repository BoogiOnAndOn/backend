package com.boogionandon.backend.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClean is a Querydsl query type for Clean
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClean extends EntityPathBase<Clean> {

    private static final long serialVersionUID = -235321689L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClean clean = new QClean("clean");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QBeach beach;

    public final NumberPath<Double> beachLength = createNumber("beachLength", Double.class);

    public final DateTimePath<java.time.LocalDateTime> cleanDateTime = createDateTime("cleanDateTime", java.time.LocalDateTime.class);

    public final QWorker cleaner;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Double> endLatitude = createNumber("endLatitude", Double.class);

    public final NumberPath<Double> endLongitude = createNumber("endLongitude", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Image, QImage> images = this.<Image, QImage>createList("images", Image.class, QImage.class, PathInits.DIRECT2);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final EnumPath<com.boogionandon.backend.domain.enums.TrashType> mainTrashType = createEnum("mainTrashType", com.boogionandon.backend.domain.enums.TrashType.class);

    public final StringPath members = createString("members");

    public final NumberPath<Integer> realTrashAmount = createNumber("realTrashAmount", Integer.class);

    public final StringPath specialNote = createString("specialNote");

    public final NumberPath<Double> startLatitude = createNumber("startLatitude", Double.class);

    public final NumberPath<Double> startLongitude = createNumber("startLongitude", Double.class);

    public final EnumPath<com.boogionandon.backend.domain.enums.ReportStatus> status = createEnum("status", com.boogionandon.backend.domain.enums.ReportStatus.class);

    public final StringPath weather = createString("weather");

    public QClean(String variable) {
        this(Clean.class, forVariable(variable), INITS);
    }

    public QClean(Path<? extends Clean> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClean(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClean(PathMetadata metadata, PathInits inits) {
        this(Clean.class, metadata, inits);
    }

    public QClean(Class<? extends Clean> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.beach = inits.isInitialized("beach") ? new QBeach(forProperty("beach")) : null;
        this.cleaner = inits.isInitialized("cleaner") ? new QWorker(forProperty("cleaner")) : null;
    }

}

