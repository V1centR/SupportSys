package com.supportsys.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the help database table.
 * 
 */
@Entity
@Table(name="help")
@NamedQuery(name="Help.findAll", query="SELECT h FROM Help h")
public class Help implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateHelp;

	private String hashSecure;

	private String helpLabel;

	private String helpTxt;

	private String solutionTxt;

	private String tags;

	//bi-directional many-to-one association to ChatHelp
	@OneToMany(mappedBy="help")
	private List<ChatHelp> chatHelps;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="dept")
	private Department department;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="supportUserId")
	private User supportUser;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="status")
	private Status statusBean;

	//bi-directional many-to-one association to TypeHelp
	@ManyToOne
	@JoinColumn(name="typeHelp")
	private TypeHelp typeHelpBean;

	//bi-directional many-to-one association to HelpTag
	@OneToMany(mappedBy="help")
	private List<HelpTag> helpTags;

	public Help() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateHelp() {
		return this.dateHelp;
	}

	public void setDateHelp(Date dateHelp) {
		this.dateHelp = dateHelp;
	}

	public String getHashSecure() {
		return this.hashSecure;
	}

	public void setHashSecure(String hashSecure) {
		this.hashSecure = hashSecure;
	}

	public String getHelpLabel() {
		return this.helpLabel;
	}

	public void setHelpLabel(String helpLabel) {
		this.helpLabel = helpLabel;
	}

	public String getHelpTxt() {
		return this.helpTxt;
	}

	public void setHelpTxt(String helpTxt) {
		this.helpTxt = helpTxt;
	}

	public String getSolutionTxt() {
		return this.solutionTxt;
	}

	public void setSolutionTxt(String solutionTxt) {
		this.solutionTxt = solutionTxt;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public List<ChatHelp> getChatHelps() {
		return this.chatHelps;
	}

	public void setChatHelps(List<ChatHelp> chatHelps) {
		this.chatHelps = chatHelps;
	}

	public ChatHelp addChatHelp(ChatHelp chatHelp) {
		getChatHelps().add(chatHelp);
		chatHelp.setHelp(this);

		return chatHelp;
	}

	public ChatHelp removeChatHelp(ChatHelp chatHelp) {
		getChatHelps().remove(chatHelp);
		chatHelp.setHelp(null);

		return chatHelp;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getSupportUser() {
		return this.supportUser;
	}

	public void setSupportUser(User supportUser) {
		this.supportUser = supportUser;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatusBean() {
		return this.statusBean;
	}

	public void setStatusBean(Status statusBean) {
		this.statusBean = statusBean;
	}

	public TypeHelp getTypeHelpBean() {
		return this.typeHelpBean;
	}

	public void setTypeHelpBean(TypeHelp typeHelpBean) {
		this.typeHelpBean = typeHelpBean;
	}

	public List<HelpTag> getHelpTags() {
		return this.helpTags;
	}

	public void setHelpTags(List<HelpTag> helpTags) {
		this.helpTags = helpTags;
	}

	public HelpTag addHelpTag(HelpTag helpTag) {
		getHelpTags().add(helpTag);
		helpTag.setHelp(this);

		return helpTag;
	}

	public HelpTag removeHelpTag(HelpTag helpTag) {
		getHelpTags().remove(helpTag);
		helpTag.setHelp(null);

		return helpTag;
	}

}