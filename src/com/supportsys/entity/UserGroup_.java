package com.supportsys.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-05T13:01:20.459-0200")
@StaticMetamodel(UserGroup.class)
public class UserGroup_ {
	public static volatile SingularAttribute<UserGroup, Integer> id;
	public static volatile SingularAttribute<UserGroup, String> description;
	public static volatile SingularAttribute<UserGroup, String> name;
	public static volatile SingularAttribute<UserGroup, Integer> score;
	public static volatile ListAttribute<UserGroup, User> users;
}
