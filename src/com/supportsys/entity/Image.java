package com.supportsys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the images database table.
 * 
 */
@Entity
@Table(name="images")
@NamedQuery(name="Image.findAll", query="SELECT i FROM Image i")
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=4)
	private String ext;

	@Column(nullable=false, length=32)
	private String imgName;

	//bi-directional many-to-one association to Client
	@OneToMany(mappedBy="image")
	private List<Client> clients;

	//bi-directional many-to-one association to SupportUser
	@OneToMany(mappedBy="image")
	private List<SupportUser> supportUsers;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="image")
	private List<User> users;

	public Image() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExt() {
		return this.ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getImgName() {
		return this.imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public List<Client> getClients() {
		return this.clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Client addClient(Client client) {
		getClients().add(client);
		client.setImage(this);

		return client;
	}

	public Client removeClient(Client client) {
		getClients().remove(client);
		client.setImage(null);

		return client;
	}

	public List<SupportUser> getSupportUsers() {
		return this.supportUsers;
	}

	public void setSupportUsers(List<SupportUser> supportUsers) {
		this.supportUsers = supportUsers;
	}

	public SupportUser addSupportUser(SupportUser supportUser) {
		getSupportUsers().add(supportUser);
		supportUser.setImage(this);

		return supportUser;
	}

	public SupportUser removeSupportUser(SupportUser supportUser) {
		getSupportUsers().remove(supportUser);
		supportUser.setImage(null);

		return supportUser;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setImage(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setImage(null);

		return user;
	}

}