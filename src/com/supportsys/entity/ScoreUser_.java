package com.supportsys.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-16T21:46:52.120-0200")
@StaticMetamodel(ScoreUser.class)
public class ScoreUser_ {
	public static volatile SingularAttribute<ScoreUser, Integer> id;
	public static volatile SingularAttribute<ScoreUser, Date> data;
	public static volatile SingularAttribute<ScoreUser, Integer> value;
	public static volatile SingularAttribute<ScoreUser, User> user;
}
