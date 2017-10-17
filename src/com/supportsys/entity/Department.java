package com.supportsys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the department database table.
 * 
 */
@Entity
@Table(name="department")
@NamedQuery(name="Department.findAll", query="SELECT d FROM Department d")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String desc;

	private String name;

	private String phone;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="client")
	private Client clientBean;

	//bi-directional many-to-one association to Help
	@OneToMany(mappedBy="department")
	private List<Help> helps;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="department")
	private List<User> users;

	public Department() {
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Client getClientBean() {
		return this.clientBean;
	}

	public void setClientBean(Client clientBean) {
		this.clientBean = clientBean;
	}

	public List<Help> getHelps() {
		return this.helps;
	}

	public void setHelps(List<Help> helps) {
		this.helps = helps;
	}

	public Help addHelp(Help help) {
		getHelps().add(help);
		help.setDepartment(this);

		return help;
	}

	public Help removeHelp(Help help) {
		getHelps().remove(help);
		help.setDepartment(null);

		return help;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setDepartment(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setDepartment(null);

		return user;
	}

}