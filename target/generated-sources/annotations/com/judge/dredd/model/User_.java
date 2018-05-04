package com.judge.dredd.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Date> tokenExpiry;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, Integer> userType;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, String> token;

}

