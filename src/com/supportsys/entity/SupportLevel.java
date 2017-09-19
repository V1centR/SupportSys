package com.supportsys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the supportLevels database table.
 * 
 */
@Entity
@Table(name="supportLevels")
@NamedQuery(name="SupportLevel.findAll", query="SELECT s FROM SupportLevel s")
public class SupportLevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=99)
	private String levelName;

	private int levelWeight;

	private int trust;

	//bi-directional many-to-one association to SupportUser
	@OneToMany(mappedBy="supportLevel")
	private List<SupportUser> supportUsers;

	public SupportLevel() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLevelName() {
		return this.levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getLevelWeight() {
		return this.levelWeight;
	}

	public void setLevelWeight(int levelWeight) {
		this.levelWeight = levelWeight;
	}

	public int getTrust() {
		return this.trust;
	}

	public void setTrust(int trust) {
		this.trust = trust;
	}

	public List<SupportUser> getSupportUsers() {
		return this.supportUsers;
	}

	public void setSupportUsers(List<SupportUser> supportUsers) {
		this.supportUsers = supportUsers;
	}

	public SupportUser addSupportUser(SupportUser supportUser) {
		getSupportUsers().add(supportUser);
		supportUser.setSupportLevel(this);

		return supportUser;
	}

	public SupportUser removeSupportUser(SupportUser supportUser) {
		getSupportUsers().remove(supportUser);
		supportUser.setSupportLevel(null);

		return supportUser;
	}

}