package com.supportsys.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-24T09:48:50.867-0200")
@StaticMetamodel(Image.class)
public class Image_ {
	public static volatile SingularAttribute<Image, Integer> id;
	public static volatile SingularAttribute<Image, String> ext;
	public static volatile SingularAttribute<Image, String> imgName;
	public static volatile ListAttribute<Image, Client> clients;
	public static volatile ListAttribute<Image, SupportUser> supportUsers;
	public static volatile ListAttribute<Image, User> users;
}
