package com.supportsys.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-05T13:01:20.456-0200")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, Integer> client;
	public static volatile SingularAttribute<User, Date> dataRegister;
	public static volatile SingularAttribute<User, String> description;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> idConfEmail;
	public static volatile SingularAttribute<User, String> mobile;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> pass;
	public static volatile SingularAttribute<User, String> phone;
	public static volatile SingularAttribute<User, String> sname;
	public static volatile ListAttribute<User, Help> helps1;
	public static volatile ListAttribute<User, Help> helps2;
	public static volatile ListAttribute<User, SupportScore> supportScores;
	public static volatile SingularAttribute<User, Department> department;
	public static volatile SingularAttribute<User, Image> image;
	public static volatile SingularAttribute<User, UserGroup> userGroup;
}
