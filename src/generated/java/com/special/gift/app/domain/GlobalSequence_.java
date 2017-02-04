package com.special.gift.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GlobalSequence.class)
public abstract class GlobalSequence_ {

	public static volatile SingularAttribute<GlobalSequence, Integer> sequenceInterval;
	public static volatile SingularAttribute<GlobalSequence, Long> nextValue;
	public static volatile SingularAttribute<GlobalSequence, String> sequenceId;
	public static volatile SingularAttribute<GlobalSequence, Long> currentValue;

}

