package com.boogionandon.backend.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResearchMain is a Querydsl query type for ResearchMain
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResearchMain extends EntityPathBase<ResearchMain> {

    private static final long serialVersionUID = -281878570L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResearchMain researchMain = new QResearchMain("researchMain");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QBeach beach;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Integer> expectedTrashAmount = createNumber("expectedTrashAmount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Image, QImage> images = this.<Image, QImage>createList("images", Image.class, QImage.class, PathInits.DIRECT2);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath members = createString("members");

    public final DateTimePath<java.time.LocalDateTime> reportTime = createDateTime("reportTime", java.time.LocalDateTime.class);

    public final QWorker researcher;

    public final ListPath<ResearchSub, QResearchSub> researchSubList = this.<ResearchSub, QResearchSub>createList("researchSubList", ResearchSub.class, QResearchSub.class, PathInits.DIRECT2);

    public final StringPath specialNote = createString("specialNote");

    public final EnumPath<com.boogionandon.backend.domain.enums.ReportStatus> status = createEnum("status", com.boogionandon.backend.domain.enums.ReportStatus.class);

    public final NumberPath<Double> totalBeachLength = createNumber("totalBeachLength", Double.class);

    public final StringPath weather = createString("weather");

    public QResearchMain(String variable) {
        this(ResearchMain.class, forVariable(variable), INITS);
    }

    public QResearchMain(Path<? extends ResearchMain> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResearchMain(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResearchMain(PathMetadata metadata, PathInits inits) {
        this(ResearchMain.class, metadata, inits);
    }

    public QResearchMain(Class<? extends ResearchMain> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.beach = inits.isInitialized("beach") ? new QBeach(forProperty("beach")) : null;
        this.researcher = inits.isInitialized("researcher") ? new QWorker(forProperty("researcher")) : null;
    }

}

