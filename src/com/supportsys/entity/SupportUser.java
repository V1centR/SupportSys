package com.supportsys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the supportUsers database table.
 * 
 */
@Entity
@Table(name="supportUsers")
@NamedQuery(name="SupportUser.findAll", query="SELECT s FROM SupportUser s")
public class SupportUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String corpMobile;

	private String desc;

	private String email;

	private String idConfEmail;

	private String name;

	private String pass;

	private String setorPhone;

	private String snome;

	//bi-directional many-to-one association to Help
	@OneToMany(mappedBy="supportUser")
	private List<Help> helps;

	//bi-directional many-to-one association to SupportScore
	@OneToMany(mappedBy="supportUser")
	private List<SupportScore> supportScores;

	//bi-directional many-to-one association to SupportLevel
	@ManyToOne
	@JoinColumn(name="level")
	private SupportLevel supportLevel;

	//bi-directional many-to-one association to Image
	@ManyToOne
	@JoinColumn(name="avatar")
	private Image image;

	public SupportUser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorpMobile() {
		return this.corpMobile;
	}

	public void setCorpMobile(String corpMobile) {
		this.corpMobile = corpMobile;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdConfEmail() {
		return this.idConfEmail;
	}

	public void setIdConfEmail(String idConfEmail) {
		this.idConfEmail = idConfEmail;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getSetorPhone() {
		return this.setorPhone;
	}

	public void setSetorPhone(String setorPhone) {
		this.setorPhone = setorPhone;
	}

	public String getSnome() {
		return this.snome;
	}

	public void setSnome(String snome) {
		this.snome = snome;
	}

	public List<Help> getHelps() {
		return this.helps;
	}

	public void setHelps(List<Help> helps) {
		this.helps = helps;
	}

	public Help addHelp(Help help) {
		getHelps().add(help);
		help.setSupportUser(this);

		return help;
	}

	public Help removeHelp(Help help) {
		getHelps().remove(help);
		help.setSupportUser(null);

		return help;
	}

	public List<SupportScore> getSupportScores() {
		return this.supportScores;
	}

	public void setSupportScores(List<SupportScore> supportScores) {
		this.supportScores = supportScores;
	}

	public SupportScore addSupportScore(SupportScore supportScore) {
		getSupportScores().add(supportScore);
		supportScore.setSupportUser(this);

		return supportScore;
	}

	public SupportScore removeSupportScore(SupportScore supportScore) {
		getSupportScores().remove(supportScore);
		supportScore.setSupportUser(null);

		return supportScore;
	}

	public SupportLevel getSupportLevel() {
		return this.supportLevel;
	}

	public void setSupportLevel(SupportLevel supportLevel) {
		this.supportLevel = supportLevel;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}