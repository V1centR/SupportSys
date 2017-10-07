package com.supportsys.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-06T23:10:12.777-0300")
@StaticMetamodel(Department.class)
public class Department_ {
	public static volatile SingularAttribute<Department, Integer> id;
	public static volatile SingularAttribute<Department, String> desc;
	public static volatile SingularAttribute<Department, String> name;
	public static volatile SingularAttribute<Department, String> phone;
	public static volatile SingularAttribute<Department, Client> clientBean;
	public static volatile ListAttribute<Department, Help> helps;
}
