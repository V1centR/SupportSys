package com.supportsys.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the helpTags database table.
 * 
 */
@Entity
@Table(name="helpTags")
@NamedQuery(name="HelpTag.findAll", query="SELECT h FROM HelpTag h")
public class HelpTag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to Help
	@ManyToOne
	@JoinColumn(name="idHelp", nullable=false)
	private Help help;

	//bi-directional many-to-one association to Tag
	@ManyToOne
	@JoinColumn(name="idTag", nullable=false)
	private Tag tag;

	public HelpTag() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Help getHelp() {
		return this.help;
	}

	public void setHelp(Help help) {
		this.help = help;
	}

	public Tag getTag() {
		return this.tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

}