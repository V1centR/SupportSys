package com.supportsys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the typeHelp database table.
 * 
 */
@Entity
@Table(name="typeHelp")
@NamedQuery(name="TypeHelp.findAll", query="SELECT t FROM TypeHelp t")
public class TypeHelp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=45)
	private String desc;

	@Column(nullable=false, length=45)
	private String name;

	//bi-directional many-to-one association to Help
	@OneToMany(mappedBy="typeHelpBean")
	private List<Help> helps;

	public TypeHelp() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Help> getHelps() {
		return this.helps;
	}

	public void setHelps(List<Help> helps) {
		this.helps = helps;
	}

	public Help addHelp(Help help) {
		getHelps().add(help);
		help.setTypeHelpBean(this);

		return help;
	}

	public Help removeHelp(Help help) {
		getHelps().remove(help);
		help.setTypeHelpBean(null);

		return help;
	}

}