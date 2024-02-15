package com.xadmin.recipemanagement.model;

/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class Joinnn {
	protected int joinID;
	protected int vote;
	protected String comment;
	protected String name;
	protected String uname;
	
	public Joinnn() {
		super();
	}

	public Joinnn(String uname, String name, int vote, String comment, int joinID) {
		super();
		this.joinID = joinID;
		this.vote = vote;
		this.comment = comment;
		this.name = name;
		this.uname = uname;
	}

	public int getJoinID() {
		return joinID;
	}

	public void setJoinID(int joinID) {
		this.joinID = joinID;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
	
	


	
	
	}
