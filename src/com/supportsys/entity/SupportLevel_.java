package com.supportsys.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-16T21:46:52.123-0200")
@StaticMetamodel(SupportLevel.class)
public class SupportLevel_ {
	public static volatile SingularAttribute<SupportLevel, Integer> id;
	public static volatile SingularAttribute<SupportLevel, String> levelName;
	public static volatile SingularAttribute<SupportLevel, Integer> levelWeight;
	public static volatile SingularAttribute<SupportLevel, Integer> trust;
	public static volatile ListAttribute<SupportLevel, SupportUser> supportUsers;
}
