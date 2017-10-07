package com.supportsys.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-06T23:10:12.772-0300")
@StaticMetamodel(Client.class)
public class Client_ {
	public static volatile SingularAttribute<Client, Integer> id;
	public static volatile SingularAttribute<Client, String> address;
	public static volatile SingularAttribute<Client, String> bairro;
	public static volatile SingularAttribute<Client, String> city;
	public static volatile SingularAttribute<Client, String> desc;
	public static volatile SingularAttribute<Client, String> email;
	public static volatile SingularAttribute<Client, Integer> level;
	public static volatile SingularAttribute<Client, String> name;
	public static volatile SingularAttribute<Client, String> phone;
	public static volatile SingularAttribute<Client, String> phoneB;
	public static volatile SingularAttribute<Client, Image> image;
	public static volatile SingularAttribute<Client, Uf> ufBean;
	public static volatile ListAttribute<Client, Department> departments;
}
