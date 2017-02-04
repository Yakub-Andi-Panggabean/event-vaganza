package com.special.gift.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> userAddress;
	public static volatile SingularAttribute<User, String> handphone;
	public static volatile SingularAttribute<User, String> phone;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, Character> status;
	public static volatile SingularAttribute<User, String> email;
	public static volatile ListAttribute<User, Vendor> vendor;
	public static volatile SingularAttribute<User, String> userId;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> userZip;

}

