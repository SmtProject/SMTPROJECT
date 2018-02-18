package com.smt.data.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAdmin is a Querydsl query type for Admin
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAdmin extends EntityPathBase<Admin> {

    private static final long serialVersionUID = -17126169L;

    public static final QAdmin admin = new QAdmin("admin");

    public final QSmtUser _super = new QSmtUser(this);

    //inherited
    public final StringPath address = _super.address;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final StringPath firstName = _super.firstName;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final StringPath lastName = _super.lastName;

    //inherited
    public final StringPath middleName = _super.middleName;

    //inherited
    public final StringPath password = _super.password;

    //inherited
    public final StringPath phone = _super.phone;

    public final EnumPath<Admin.AdminRole> role = createEnum("role", Admin.AdminRole.class);

    //inherited
    public final StringPath session = _super.session;

    //inherited
    public final EnumPath<SmtUser.SmtUserStatus> status = _super.status;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final DateTimePath<java.util.Date> updatedDate = _super.updatedDate;

    //inherited
    public final StringPath userName = _super.userName;

    public QAdmin(String variable) {
        super(Admin.class, forVariable(variable));
    }

    public QAdmin(Path<? extends Admin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdmin(PathMetadata<?> metadata) {
        super(Admin.class, metadata);
    }

}

