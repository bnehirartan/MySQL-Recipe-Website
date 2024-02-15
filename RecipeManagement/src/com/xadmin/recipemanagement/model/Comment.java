package com.xadmin.recipemanagement.model;

public class Comment {
	protected int CommentID;
	protected int vote;
	protected String comment;
	
	
	
	
	
	public Comment() {
		super();
		
	}
	public Comment(int commentID, int vote, String comment) {
		super();
		CommentID = commentID;
		this.vote = vote;
		this.comment = comment;
	}
	public int getCommentID() {
		return CommentID;
	}
	public void setCommentID(int commentID) {
		CommentID = commentID;
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
	
}
