package com.supportsys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the uf database table.
 * 
 */
@Entity
@Table(name="uf")
@NamedQuery(name="Uf.findAll", query="SELECT u FROM Uf u")
public class Uf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=2)
	private String sign;

	//bi-directional many-to-one association to Client
	@OneToMany(mappedBy="ufBean")
	private List<Client> clients;

	public Uf() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSign() {
		return this.sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public List<Client> getClients() {
		return this.clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Client addClient(Client client) {
		getClients().add(client);
		client.setUfBean(this);

		return client;
	}

	public Client removeClient(Client client) {
		getClients().remove(client);
		client.setUfBean(null);

		return client;
	}

}