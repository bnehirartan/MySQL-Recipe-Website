package com.xadmin.recipemanagement.model;

public class Commentrecipe {
	
	
	
	
	public Commentrecipe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Commentrecipe(int commentID, int id) {
		super();
		CommentID = commentID;
		this.id = id;
	}
	protected int CommentID;
	protected int id;
	public int getCommentID() {
		return CommentID;
	}
	public void setCommentID(int commentID) {
		CommentID = commentID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
