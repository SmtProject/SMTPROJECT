package com.smt.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTeachingGrades is a Querydsl query type for TeachingGrades
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTeachingGrades extends EntityPathBase<TeachingGrades> {

    private static final long serialVersionUID = 1512905361L;

    public static final QTeachingGrades teachingGrades = new QTeachingGrades("teachingGrades");

    public final EnumPath<smt.model.tools.GradesEnum> grade = createEnum("grade", smt.model.tools.GradesEnum.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> teacherId = createNumber("teacherId", Integer.class);

    public QTeachingGrades(String variable) {
        super(TeachingGrades.class, forVariable(variable));
    }

    public QTeachingGrades(Path<? extends TeachingGrades> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeachingGrades(PathMetadata<?> metadata) {
        super(TeachingGrades.class, metadata);
    }

}

