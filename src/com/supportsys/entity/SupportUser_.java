package com.supportsys.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-24T09:48:50.896-0200")
@StaticMetamodel(SupportUser.class)
public class SupportUser_ {
	public static volatile SingularAttribute<SupportUser, Integer> id;
	public static volatile SingularAttribute<SupportUser, String> corpMobile;
	public static volatile SingularAttribute<SupportUser, String> desc;
	public static volatile SingularAttribute<SupportUser, String> email;
	public static volatile SingularAttribute<SupportUser, String> idConfEmail;
	public static volatile SingularAttribute<SupportUser, String> name;
	public static volatile SingularAttribute<SupportUser, String> pass;
	public static volatile SingularAttribute<SupportUser, String> setorPhone;
	public static volatile SingularAttribute<SupportUser, String> snome;
	public static volatile ListAttribute<SupportUser, Help> helps;
	public static volatile ListAttribute<SupportUser, SupportScore> supportScores;
	public static volatile SingularAttribute<SupportUser, SupportLevel> supportLevel;
	public static volatile SingularAttribute<SupportUser, Image> image;
}
