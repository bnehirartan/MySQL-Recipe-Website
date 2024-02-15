package com.xadmin.recipemanagement.model;

public class Commentuser {
	
	
	
	public Commentuser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Commentuser(int commentID, String uname) {
		super();
		CommentID = commentID;
		this.uname = uname;
	}
	protected int CommentID;
	protected String uname;
	
	
	public int getCommentID() {
		return CommentID;
	}
	public void setCommentID(int commentID) {
		CommentID = commentID;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	
	


}
