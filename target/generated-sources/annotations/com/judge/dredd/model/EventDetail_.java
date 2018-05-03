package com.judge.dredd.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EventDetail.class)
public abstract class EventDetail_ {

	public static volatile SingularAttribute<EventDetail, Date> endDate;
	public static volatile SingularAttribute<EventDetail, String> eventName;
	public static volatile SingularAttribute<EventDetail, Long> id;
	public static volatile SingularAttribute<EventDetail, Date> startDate;

}

