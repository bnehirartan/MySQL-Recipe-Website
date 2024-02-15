package com.xadmin.recipemanagement.web;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.recipemanagement.dao.CommentDAO;
import com.xadmin.recipemanagement.model.Comment;
import com.xadmin.recipemanagement.model.Commentrecipe;
import com.xadmin.recipemanagement.model.Commentuser;
import com.xadmin.recipemanagement.model.Joinnn;




@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentDAO commentDAO;
	
	public void init() {
		commentDAO = new CommentDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/showNewFormm":
				showNewCommentForm(request, response);
		
				break;
			case "/insertComment":
				insertComment(request, response);
				
				
				break;
			case "/deleteComment":
				deleteComment(request, response);
				break;
			case "/showEditForm":
				showEditForm(request, response);
				break;
		
		 		
			case "/updateComment":
				updateComment(request, response);
				break;
				
				
			default:
				listComment(request, response);
				

			break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}


	
	

	
	
	
	
	
	
	
	private void listComment(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		List<Joinnn> listComment = commentDAO.selectAllComments();
		request.setAttribute("listComment", listComment);
		RequestDispatcher dispatcher = request.getRequestDispatcher("comment-list.jsp");
		dispatcher.forward(request, response);
	}
	
	
	
	
	
	private void showNewCommentForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("comment-form.jsp");
		dispatcher.forward(request, response);
	}

	
	
	

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int joinnnID = Integer.parseInt(request.getParameter("joinnnID"));
		Joinnn existingComment = commentDAO.selectJoinnn(joinnnID);
		RequestDispatcher dispatcher = request.getRequestDispatcher("comment-form.jsp");
		request.setAttribute("comment", existingComment);
		dispatcher.forward(request, response);

	}

	
	
	
	
	
	
	
	
	
	private void insertComment(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int CommentID = Integer.parseInt(request.getParameter("CommentID"));
		int vote = Integer.parseInt(request.getParameter("vote"));
		String comment = request.getParameter("comment");
		Comment newComment = new Comment(CommentID, vote, comment);
		commentDAO.insertComments(newComment);
		response.sendRedirect("comment-form.jsp");
	}
	private void insertCommentrecipe(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int CommentID = Integer.parseInt(request.getParameter("CommentID"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		Commentrecipe newCommentrecipe = new Commentrecipe(CommentID, id);
		commentDAO.insertCommentrecipe(newCommentrecipe);
		response.sendRedirect("comment-form.jsp");
	}
	private void insertCommentuser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int CommentID = Integer.parseInt(request.getParameter("CommentID"));
	
		String uname = request.getParameter("uname");
		Commentuser newCommentuser = new Commentuser(CommentID, uname);
		commentDAO.insertCommentuser(newCommentuser);
		response.sendRedirect("comment-form.jsp");
	
	}
	
	
	
	
	
	
	
	
	

	private void updateComment(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int CommentID = Integer.parseInt(request.getParameter("CommentID"));
		int vote = Integer.parseInt(request.getParameter("vote"));
		String comment = request.getParameter("comment");

		Comment book = new Comment(CommentID, vote, comment);
		commentDAO.updateComment(book);
		response.sendRedirect("comment-form.jsp");
	}

	private void deleteComment(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int joinnnID = Integer.parseInt(request.getParameter("joinnnID"));
		commentDAO.deleteComment(joinnnID);
		response.sendRedirect("comment-list.jsp");

	}

}