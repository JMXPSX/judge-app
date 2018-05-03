package com.judge.dredd.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Score.class)
public abstract class Score_ {

	public static volatile SingularAttribute<Score, Integer> eventId;
	public static volatile SingularAttribute<Score, Integer> score;
	public static volatile SingularAttribute<Score, Long> id;
	public static volatile SingularAttribute<Score, Boolean> isFinal;
	public static volatile SingularAttribute<Score, Integer> userId;
	public static volatile SingularAttribute<Score, Boolean> isDone;
	public static volatile SingularAttribute<Score, Integer> entryId;

}

