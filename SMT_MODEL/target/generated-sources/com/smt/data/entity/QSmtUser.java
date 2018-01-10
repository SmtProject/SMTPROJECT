package com.smt.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSmtUser is a Querydsl query type for SmtUser
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSmtUser extends EntityPathBase<SmtUser> {

    private static final long serialVersionUID = -219642915L;

    public static final QSmtUser smtUser = new QSmtUser("smtUser");

    public final StringPath address = createString("address");

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath middleName = createString("middleName");

    public final StringPath password = createString("password");

    public final NumberPath<Integer> phone = createNumber("phone", Integer.class);

    public final StringPath role = createString("role");

    public final StringPath userName = createString("userName");

    public QSmtUser(String variable) {
        super(SmtUser.class, forVariable(variable));
    }

    public QSmtUser(Path<? extends SmtUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSmtUser(PathMetadata<?> metadata) {
        super(SmtUser.class, metadata);
    }

}

