package com.special.gift.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Invoice.class)
public abstract class Invoice_ {

	public static volatile SingularAttribute<Invoice, Integer> price;
	public static volatile SingularAttribute<Invoice, String> eventPlace;
	public static volatile SingularAttribute<Invoice, String> dateBooking;
	public static volatile SingularAttribute<Invoice, BookingTransaction> transaction;
	public static volatile SingularAttribute<Invoice, String> decorationType;
	public static volatile SingularAttribute<Invoice, String> timeBooking;
	public static volatile SingularAttribute<Invoice, String> nameBooking;
	public static volatile SingularAttribute<Invoice, String> eventStyle;
	public static volatile SingularAttribute<Invoice, String> eventType;
	public static volatile SingularAttribute<Invoice, String> invoiceNo;
	public static volatile SingularAttribute<Invoice, Integer> totalPrice;
	public static volatile SingularAttribute<Invoice, String> addressBooking;

}

