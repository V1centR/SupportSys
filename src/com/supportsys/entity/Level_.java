package com.supportsys.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-16T21:46:52.118-0200")
@StaticMetamodel(Level.class)
public class Level_ {
	public static volatile SingularAttribute<Level, Integer> id;
	public static volatile SingularAttribute<Level, String> client;
	public static volatile SingularAttribute<Level, String> levelName;
	public static volatile SingularAttribute<Level, Integer> levelWeight;
	public static volatile ListAttribute<Level, User> users;
}
