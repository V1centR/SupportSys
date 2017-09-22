package com.supportsys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tags database table.
 * 
 */
@Entity
@Table(name="tags")
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to HelpTag
	@OneToMany(mappedBy="tag")
	private List<HelpTag> helpTags;

	public Tag() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<HelpTag> getHelpTags() {
		return this.helpTags;
	}

	public void setHelpTags(List<HelpTag> helpTags) {
		this.helpTags = helpTags;
	}

	public HelpTag addHelpTag(HelpTag helpTag) {
		getHelpTags().add(helpTag);
		helpTag.setTag(this);

		return helpTag;
	}

	public HelpTag removeHelpTag(HelpTag helpTag) {
		getHelpTags().remove(helpTag);
		helpTag.setTag(null);

		return helpTag;
	}

}