package com.supportsys.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-24T09:48:50.900-0200")
@StaticMetamodel(Tag.class)
public class Tag_ {
	public static volatile SingularAttribute<Tag, Integer> id;
	public static volatile SingularAttribute<Tag, String> name;
	public static volatile ListAttribute<Tag, HelpTag> helpTags;
}
