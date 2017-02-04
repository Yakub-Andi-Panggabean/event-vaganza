package com.special.gift.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PackageVendor.class)
public abstract class PackageVendor_ {

	public static volatile SingularAttribute<PackageVendor, Integer> packageCapacity;
	public static volatile SingularAttribute<PackageVendor, String> packageName;
	public static volatile SingularAttribute<PackageVendor, Integer> packagePrice;
	public static volatile SingularAttribute<PackageVendor, Vendor> vendor;
	public static volatile SingularAttribute<PackageVendor, Integer> minimumPayment;
	public static volatile SingularAttribute<PackageVendor, String> packageId;
	public static volatile SingularAttribute<PackageVendor, String> packageStyle;
	public static volatile SingularAttribute<PackageVendor, String> packagePromo;
	public static volatile SingularAttribute<PackageVendor, String> packageCategory;
	public static volatile SingularAttribute<PackageVendor, String> timePackage;
	public static volatile SingularAttribute<PackageVendor, Integer> discountRate;
	public static volatile SingularAttribute<PackageVendor, String> packageDesc;
	public static volatile SingularAttribute<PackageVendor, String> packageImg;

}

