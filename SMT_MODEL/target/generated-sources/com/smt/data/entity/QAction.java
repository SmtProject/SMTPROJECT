package com.smt.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAction is a Querydsl query type for Action
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAction extends EntityPathBase<Action> {

    private static final long serialVersionUID = -531626082L;

    public static final QAction action = new QAction("action");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath isEnable = createBoolean("isEnable");

    public final EnumPath<smt.model.tools.ActionEnum> name = createEnum("name", smt.model.tools.ActionEnum.class);

    public final EnumPath<smt.model.tools.Role> smtRole = createEnum("smtRole", smt.model.tools.Role.class);

    public QAction(String variable) {
        super(Action.class, forVariable(variable));
    }

    public QAction(Path<? extends Action> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAction(PathMetadata<?> metadata) {
        super(Action.class, metadata);
    }

}

