package com.supportsys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the level database table.
 * 
 */
@Entity
@Table(name="level")
@NamedQuery(name="Level.findAll", query="SELECT l FROM Level l")
public class Level implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String client;

	private String levelName;

	private int levelWeight;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="levelBean")
	private List<User> users;

	public Level() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClient() {
		return this.client;
	}

	public void setClient(String client) {
		this.client = client;
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

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setLevelBean(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setLevelBean(null);

		return user;
	}

}