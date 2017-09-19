package com.supportsys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the scoreUsers database table.
 * 
 */
@Entity
@Table(name="scoreUsers")
@NamedQuery(name="ScoreUser.findAll", query="SELECT s FROM ScoreUser s")
public class ScoreUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date data;

	@Column(nullable=false)
	private int value;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser", nullable=false)
	private User user;

	public ScoreUser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}