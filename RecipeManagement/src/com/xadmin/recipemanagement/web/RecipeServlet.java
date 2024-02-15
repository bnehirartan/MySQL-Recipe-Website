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
import com.xadmin.recipemanagement.dao.RecipeDAO;
import com.xadmin.recipemanagement.model.Comment;
import com.xadmin.recipemanagement.model.Commentrecipe;
import com.xadmin.recipemanagement.model.Commentuser;
import com.xadmin.recipemanagement.model.Joinnn;
import com.xadmin.recipemanagement.model.Recipe;



@WebServlet("/")
public class RecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecipeDAO recipeDAO;
	private CommentDAO commentDAO;
	public void init() {
		recipeDAO = new RecipeDAO();
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
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertRecipe(request, response);
				break;
			case "/delete":
				deleteRecipe(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/ASC":
				listRecipeASC(request, response);
				break;
			case "/DESC":
			listRecipeDESC(request, response);
				break;
			case "/listComment":
				listComment(request, response);
					break;
			case "/showNewCommentForm":
				showNewCommentForm(request, response);
					break;	
			case "/showEditCommentForm":
				showNewCommentForm(request, response);
					break;	
			case "/insertComment":
				insertComment(request, response);
				insertCommentrecipe(request, response);
				insertCommentuser(request, response);
					break;	
			case "/updateComment":
				updateComment(request, response);
					break;	
					
			case "/deleteComment":
				updateComment(request, response);
					break;	
			case "/up":
				update(request, response);
					break;			
					
						
				
				
				
				
				
				
				
			
				
			case "/update":
				updateRecipe(request, response);
				break;
				
				
			default:
				listRecipe(request, response);
				

			break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void showCount(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		int count = recipeDAO.RecipeCount();
		request.setAttribute("COUNT", count);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.include(request, response);
		;
	}

	
	
	
	
	
	
	
	
	
	private void listRecipe(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		showCount(request, response);
		List<Recipe> listRecipe = recipeDAO.selectAllRecipes();
		request.setAttribute("listRecipe", listRecipe);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}
	

	private void update(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		recipeDAO.updateeComment();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}
	
	
	
	
	
	
	private void listRecipeASC(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		showCount(request, response);
		List<Recipe> listRecipeASC = recipeDAO.selectAllRecipesAsc();
		request.setAttribute("listRecipeASC", listRecipeASC);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ASC.jsp");
		dispatcher.forward(request, response);
	}
	private void listRecipeDESC(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		showCount(request, response);
		List<Recipe> listRecipeDESC = recipeDAO.selectAllRecipesDesc();
		request.setAttribute("listRecipeDESC", listRecipeDESC);
		RequestDispatcher dispatcher = request.getRequestDispatcher("DESC.jsp");
		dispatcher.forward(request, response);
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

	
	
	
	
	
	
	
	
	
	
	private void showEditCommentForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int joinnnID = Integer.parseInt(request.getParameter("joinID"));
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
		
	}
	private void insertCommentrecipe(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int CommentID = Integer.parseInt(request.getParameter("CommentID"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		Commentrecipe newCommentrecipe = new Commentrecipe(CommentID, id);
		commentDAO.insertCommentrecipe(newCommentrecipe);
		
	}
	private void insertCommentuser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int CommentID = Integer.parseInt(request.getParameter("CommentID"));
		String uname = request.getParameter("uname");
		Commentuser newCommentuser = new Commentuser(CommentID, uname);
		commentDAO.insertCommentuser(newCommentuser);
		response.sendRedirect("listComment");
	
	}
	
	
	
	
	private void updateComment(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int CommentID = Integer.parseInt(request.getParameter("CommentID"));
		int vote = Integer.parseInt(request.getParameter("vote"));
		String comment = request.getParameter("comment");

		Comment book = new Comment(CommentID, vote, comment);
		commentDAO.updateComment(book);
		response.sendRedirect("comment-list.jsp");
	}

	private void deleteComment(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int joinnnID = Integer.parseInt(request.getParameter("joinnnID"));
		commentDAO.deleteComment(joinnnID);
		response.sendRedirect("comment-list.jsp");

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Recipe existingRecipe = recipeDAO.selectRecipe (id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("recipe", existingRecipe);
		dispatcher.forward(request, response);

	}

	private void insertRecipe(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String recipe = request.getParameter("recipe");
		String ingredients = request.getParameter("ingredients");
		Recipe newRecipe = new Recipe(name, recipe, ingredients);
		recipeDAO.insertRecipes(newRecipe);
		response.sendRedirect("list");
	}

	private void updateRecipe(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String recipe = request.getParameter("recipe");
		String ingredients = request.getParameter("ingredients");

		Recipe book = new Recipe(id, name, recipe, ingredients);
		recipeDAO.updateRecipe(book);
		response.sendRedirect("list");
	}

	private void deleteRecipe(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		recipeDAO.deleteRecipe(id);
		response.sendRedirect("list");

	}

}