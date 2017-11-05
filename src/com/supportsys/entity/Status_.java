package com.supportsys.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-05T13:01:20.438-0200")
@StaticMetamodel(Status.class)
public class Status_ {
	public static volatile SingularAttribute<Status, Integer> id;
	public static volatile SingularAttribute<Status, String> name;
	public static volatile ListAttribute<Status, Help> helps;
}
