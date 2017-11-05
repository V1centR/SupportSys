package com.supportsys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int client;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegister;

	private String description;

	private String email;

	private String idConfEmail;

	private String mobile;

	private String name;

	private String pass;

	private String phone;

	private String sname;

	//bi-directional many-to-one association to Help
	@OneToMany(mappedBy="supportUser")
	private List<Help> helps1;

	//bi-directional many-to-one association to Help
	@OneToMany(mappedBy="user")
	private List<Help> helps2;

	//bi-directional many-to-one association to SupportScore
	@OneToMany(mappedBy="user")
	private List<SupportScore> supportScores;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="dept")
	private Department department;

	//bi-directional many-to-one association to Image
	@ManyToOne
	@JoinColumn(name="avatar")
	private Image image;

	//bi-directional many-to-one association to UserGroup
	@ManyToOne
	@JoinColumn(name="group")
	private UserGroup userGroup;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClient() {
		return this.client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public Date getDataRegister() {
		return this.dataRegister;
	}

	public void setDataRegister(Date dataRegister) {
		this.dataRegister = dataRegister;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public List<Help> getHelps1() {
		return this.helps1;
	}

	public void setHelps1(List<Help> helps1) {
		this.helps1 = helps1;
	}

	public Help addHelps1(Help helps1) {
		getHelps1().add(helps1);
		helps1.setSupportUser(this);

		return helps1;
	}

	public Help removeHelps1(Help helps1) {
		getHelps1().remove(helps1);
		helps1.setSupportUser(null);

		return helps1;
	}

	public List<Help> getHelps2() {
		return this.helps2;
	}

	public void setHelps2(List<Help> helps2) {
		this.helps2 = helps2;
	}

	public Help addHelps2(Help helps2) {
		getHelps2().add(helps2);
		helps2.setUser(this);

		return helps2;
	}

	public Help removeHelps2(Help helps2) {
		getHelps2().remove(helps2);
		helps2.setUser(null);

		return helps2;
	}

	public List<SupportScore> getSupportScores() {
		return this.supportScores;
	}

	public void setSupportScores(List<SupportScore> supportScores) {
		this.supportScores = supportScores;
	}

	public SupportScore addSupportScore(SupportScore supportScore) {
		getSupportScores().add(supportScore);
		supportScore.setUser(this);

		return supportScore;
	}

	public SupportScore removeSupportScore(SupportScore supportScore) {
		getSupportScores().remove(supportScore);
		supportScore.setUser(null);

		return supportScore;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public UserGroup getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

}