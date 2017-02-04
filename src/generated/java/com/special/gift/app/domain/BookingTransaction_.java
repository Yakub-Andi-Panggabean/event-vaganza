package com.special.gift.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BookingTransaction.class)
public abstract class BookingTransaction_ {

	public static volatile SingularAttribute<BookingTransaction, String> transactionId;
	public static volatile SingularAttribute<BookingTransaction, Integer> priceAll;
	public static volatile SingularAttribute<BookingTransaction, String> dateBooking;
	public static volatile SingularAttribute<BookingTransaction, Vendor> vendor;
	public static volatile SingularAttribute<BookingTransaction, String> timeBooking;
	public static volatile SingularAttribute<BookingTransaction, Character> methodPayment;
	public static volatile SingularAttribute<BookingTransaction, String> transactionDate;
	public static volatile SingularAttribute<BookingTransaction, Character> statusTransaction;
	public static volatile SingularAttribute<BookingTransaction, String> transactionTime;
	public static volatile SingularAttribute<BookingTransaction, String> eventId;
	public static volatile SingularAttribute<BookingTransaction, TransactionConfirmation> transactionConfirmation;
	public static volatile SingularAttribute<BookingTransaction, Integer> pricePayment;
	public static volatile SingularAttribute<BookingTransaction, Character> statuspayment;
	public static volatile SingularAttribute<BookingTransaction, User> user;

}

