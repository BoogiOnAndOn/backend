package com.boogionandon.backend.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResearchSub is a Querydsl query type for ResearchSub
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResearchSub extends EntityPathBase<ResearchSub> {

    private static final long serialVersionUID = 683650179L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResearchSub researchSub = new QResearchSub("researchSub");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath beachNameWithIndex = createString("beachNameWithIndex");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Double> endLatitude = createNumber("endLatitude", Double.class);

    public final NumberPath<Double> endLongitude = createNumber("endLongitude", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final EnumPath<com.boogionandon.backend.domain.enums.TrashType> mainTrashType = createEnum("mainTrashType", com.boogionandon.backend.domain.enums.TrashType.class);

    public final QResearchMain research;

    public final NumberPath<Double> researchLength = createNumber("researchLength", Double.class);

    public final NumberPath<Double> startLatitude = createNumber("startLatitude", Double.class);

    public final NumberPath<Double> startLongitude = createNumber("startLongitude", Double.class);

    public QResearchSub(String variable) {
        this(ResearchSub.class, forVariable(variable), INITS);
    }

    public QResearchSub(Path<? extends ResearchSub> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResearchSub(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResearchSub(PathMetadata metadata, PathInits inits) {
        this(ResearchSub.class, metadata, inits);
    }

    public QResearchSub(Class<? extends ResearchSub> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.research = inits.isInitialized("research") ? new QResearchMain(forProperty("research"), inits.get("research")) : null;
    }

}

