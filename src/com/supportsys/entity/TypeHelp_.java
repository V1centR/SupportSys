package com.supportsys.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-24T09:48:50.904-0200")
@StaticMetamodel(TypeHelp.class)
public class TypeHelp_ {
	public static volatile SingularAttribute<TypeHelp, Integer> id;
	public static volatile SingularAttribute<TypeHelp, String> desc;
	public static volatile SingularAttribute<TypeHelp, String> name;
	public static volatile ListAttribute<TypeHelp, Help> helps;
}
