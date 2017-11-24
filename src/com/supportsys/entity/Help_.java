package com.supportsys.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-24T19:43:42.066-0200")
@StaticMetamodel(Help.class)
public class Help_ {
	public static volatile SingularAttribute<Help, Integer> id;
	public static volatile SingularAttribute<Help, Date> dateHelp;
	public static volatile SingularAttribute<Help, String> hashSecure;
	public static volatile SingularAttribute<Help, String> helpLabel;
	public static volatile SingularAttribute<Help, String> helpTxt;
	public static volatile SingularAttribute<Help, String> solutionTxt;
	public static volatile SingularAttribute<Help, String> tags;
	public static volatile ListAttribute<Help, ChatHelp> chatHelps;
	public static volatile SingularAttribute<Help, Department> department;
	public static volatile SingularAttribute<Help, User> supportUser;
	public static volatile SingularAttribute<Help, User> user;
	public static volatile SingularAttribute<Help, Status> statusBean;
	public static volatile SingularAttribute<Help, TypeHelp> typeHelpBean;
	public static volatile ListAttribute<Help, HelpTag> helpTags;
}
