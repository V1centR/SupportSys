package com.supportsys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the supportScore database table.
 * 
 */
@Entity
@Table(name="supportScore")
@NamedQuery(name="SupportScore.findAll", query="SELECT s FROM SupportScore s")
public class SupportScore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private int value;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;

	public SupportScore() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
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