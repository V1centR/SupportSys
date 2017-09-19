package com.supportsys.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the clients database table.
 * 
 */
@Entity
@Table(name="clients")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=120)
	private String address;

	@Column(length=99)
	private String bairro;

	@Column(length=49)
	private String city;

	@Column(length=120)
	private String desc;

	@Column(nullable=false, length=99)
	private String email;

	private int level;

	@Column(nullable=false, length=99)
	private String name;

	@Column(nullable=false, length=13)
	private String phone;

	@Column(length=13)
	private String phoneB;

	//bi-directional many-to-one association to Image
	@ManyToOne
	@JoinColumn(name="logoImg")
	private Image image;

	//bi-directional many-to-one association to Uf
	@ManyToOne
	@JoinColumn(name="uf")
	private Uf ufBean;

	public Client() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
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

	public String getPhoneB() {
		return this.phoneB;
	}

	public void setPhoneB(String phoneB) {
		this.phoneB = phoneB;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Uf getUfBean() {
		return this.ufBean;
	}

	public void setUfBean(Uf ufBean) {
		this.ufBean = ufBean;
	}

}