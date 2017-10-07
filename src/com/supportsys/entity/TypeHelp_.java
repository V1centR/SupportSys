package com.supportsys.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-06T23:10:12.815-0300")
@StaticMetamodel(TypeHelp.class)
public class TypeHelp_ {
	public static volatile SingularAttribute<TypeHelp, Integer> id;
	public static volatile SingularAttribute<TypeHelp, String> desc;
	public static volatile SingularAttribute<TypeHelp, String> name;
	public static volatile ListAttribute<TypeHelp, Help> helps;
}
