package com.special.gift.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TransactionConfirmation.class)
public abstract class TransactionConfirmation_ {

	public static volatile SingularAttribute<TransactionConfirmation, String> transactionId;
	public static volatile SingularAttribute<TransactionConfirmation, BookingTransaction> transaction;
	public static volatile SingularAttribute<TransactionConfirmation, Character> status;

}

