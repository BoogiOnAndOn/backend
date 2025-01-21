package com.boogionandon.backend.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBeach is a Querydsl query type for Beach
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBeach extends EntityPathBase<Beach> {

    private static final long serialVersionUID = -236457535L;

    public static final QBeach beach = new QBeach("beach");

    public final StringPath beachName = createString("beachName");

    public final StringPath dongEub = createString("dongEub");

    public final StringPath guGun = createString("guGun");

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final StringPath si = createString("si");

    public final StringPath workplace = createString("workplace");

    public QBeach(String variable) {
        super(Beach.class, forVariable(variable));
    }

    public QBeach(Path<? extends Beach> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBeach(PathMetadata metadata) {
        super(Beach.class, metadata);
    }

}

