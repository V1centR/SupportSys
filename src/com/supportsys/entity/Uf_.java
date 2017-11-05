package com.supportsys.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-05T13:01:20.452-0200")
@StaticMetamodel(Uf.class)
public class Uf_ {
	public static volatile SingularAttribute<Uf, Integer> id;
	public static volatile SingularAttribute<Uf, String> sign;
	public static volatile ListAttribute<Uf, Client> clients;
}
