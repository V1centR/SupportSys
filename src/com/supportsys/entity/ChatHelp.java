package com.supportsys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the chatHelps database table.
 * 
 */
@Entity
@Table(name="chatHelps")
@NamedQuery(name="ChatHelp.findAll", query="SELECT c FROM ChatHelp c")
public class ChatHelp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private int idUserPost;

	private String txt;

	//bi-directional many-to-one association to Help
	@ManyToOne
	@JoinColumn(name="idHelp")
	private Help help;

	public ChatHelp() {
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

	public int getIdUserPost() {
		return this.idUserPost;
	}

	public void setIdUserPost(int idUserPost) {
		this.idUserPost = idUserPost;
	}

	public String getTxt() {
		return this.txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public Help getHelp() {
		return this.help;
	}

	public void setHelp(Help help) {
		this.help = help;
	}

}