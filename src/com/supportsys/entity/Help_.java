package com.supportsys.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-06T23:10:12.780-0300")
@StaticMetamodel(Help.class)
public class Help_ {
	public static volatile SingularAttribute<Help, Integer> id;
	public static volatile SingularAttribute<Help, Date> dateHelp;
	public static volatile SingularAttribute<Help, String> helpLabel;
	public static volatile SingularAttribute<Help, String> helpTxt;
	public static volatile SingularAttribute<Help, String> tags;
	public static volatile ListAttribute<Help, ChatHelp> chatHelps;
	public static volatile SingularAttribute<Help, Department> department;
	public static volatile SingularAttribute<Help, SupportUser> supportUser;
	public static volatile SingularAttribute<Help, User> user;
	public static volatile SingularAttribute<Help, Status> statusBean;
	public static volatile SingularAttribute<Help, TypeHelp> typeHelpBean;
	public static volatile ListAttribute<Help, HelpTag> helpTags;
}
