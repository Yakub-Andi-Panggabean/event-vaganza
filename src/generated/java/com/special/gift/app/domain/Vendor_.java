package com.special.gift.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Vendor.class)
public abstract class Vendor_ {

	public static volatile SingularAttribute<Vendor, String> handphone;
	public static volatile SingularAttribute<Vendor, String> phone;
	public static volatile SingularAttribute<Vendor, String> desc;
	public static volatile ListAttribute<Vendor, PackageVendor> packageVendor;
	public static volatile SingularAttribute<Vendor, String> email;
	public static volatile SingularAttribute<Vendor, String> address;
	public static volatile SingularAttribute<Vendor, String> name;
	public static volatile SingularAttribute<Vendor, String> pic;
	public static volatile SingularAttribute<Vendor, VendorId> vendorId;
	public static volatile SingularAttribute<Vendor, User> user;

}

