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
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private int client;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dataRegister;

	@Column(length=199)
	private String description;

	@Column(nullable=false, length=120)
	private String email;

	@Column(length=64)
	private String idConfEmail;

	@Column(length=12)
	private String mobile;

	@Column(nullable=false, length=49)
	private String name;

	@Column(nullable=false, length=12)
	private String pass;

	@Column(length=12)
	private String phone;

	@Column(nullable=false, length=99)
	private String sname;

	//bi-directional many-to-one association to Help
	@OneToMany(mappedBy="user")
	private List<Help> helps;

	//bi-directional many-to-one association to ScoreUser
	@OneToMany(mappedBy="user")
	private List<ScoreUser> scoreUsers;

	//bi-directional many-to-one association to Image
	@ManyToOne
	@JoinColumn(name="avatar")
	private Image image;

	//bi-directional many-to-one association to Level
	@ManyToOne
	@JoinColumn(name="level", nullable=false)
	private Level levelBean;

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

	public List<Help> getHelps() {
		return this.helps;
	}

	public void setHelps(List<Help> helps) {
		this.helps = helps;
	}

	public Help addHelp(Help help) {
		getHelps().add(help);
		help.setUser(this);

		return help;
	}

	public Help removeHelp(Help help) {
		getHelps().remove(help);
		help.setUser(null);

		return help;
	}

	public List<ScoreUser> getScoreUsers() {
		return this.scoreUsers;
	}

	public void setScoreUsers(List<ScoreUser> scoreUsers) {
		this.scoreUsers = scoreUsers;
	}

	public ScoreUser addScoreUser(ScoreUser scoreUser) {
		getScoreUsers().add(scoreUser);
		scoreUser.setUser(this);

		return scoreUser;
	}

	public ScoreUser removeScoreUser(ScoreUser scoreUser) {
		getScoreUsers().remove(scoreUser);
		scoreUser.setUser(null);

		return scoreUser;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Level getLevelBean() {
		return this.levelBean;
	}

	public void setLevelBean(Level levelBean) {
		this.levelBean = levelBean;
	}

}