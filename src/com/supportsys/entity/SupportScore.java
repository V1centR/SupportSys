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
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date date;

	@Column(nullable=false)
	private int value;

	//bi-directional many-to-one association to SupportUser
	@ManyToOne
	@JoinColumn(name="idUser", nullable=false)
	private SupportUser supportUser;

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

	public SupportUser getSupportUser() {
		return this.supportUser;
	}

	public void setSupportUser(SupportUser supportUser) {
		this.supportUser = supportUser;
	}

}