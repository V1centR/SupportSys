package com.supportsys.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-06T23:10:12.801-0300")
@StaticMetamodel(SupportScore.class)
public class SupportScore_ {
	public static volatile SingularAttribute<SupportScore, Integer> id;
	public static volatile SingularAttribute<SupportScore, Date> date;
	public static volatile SingularAttribute<SupportScore, Integer> value;
	public static volatile SingularAttribute<SupportScore, SupportUser> supportUser;
}
