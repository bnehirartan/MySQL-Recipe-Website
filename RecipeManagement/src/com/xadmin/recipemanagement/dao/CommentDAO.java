package com.xadmin.recipemanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.recipemanagement.model.Comment;
import com.xadmin.recipemanagement.model.Commentrecipe;
import com.xadmin.recipemanagement.model.Commentuser;
import com.xadmin.recipemanagement.model.Joinnn;



public class CommentDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/recipedb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Ye7Ti7k4";
	private String jdbcDriver = "com.mysql.jdbc.Driver";

	
		
	private static final String Insert_User_Query = "INSERT INTO recipedb.commentuser"+"(uname,CommentID) VALUES "+"(?,?);";
	private static final String Insert_COMMENT_Query= "INSERT INTO recipedb.comment"+ "(CommentID, vote, comment) VALUES "+"(?, ?, ?);";
	private static final String Insert_COMMENT_RecipeQuery = "INSERT INTO recipedb.commentrecipe "+"(id, CommentID) VALUES "+"((SELECT id FROM recipedb.Recipes WHERE name = ?), ?);";

	
	
	
	private static final String SELECT_COMMENT_BY_ID = "select uname,name,vote,commment from recipedb.joinnn where joinID =?";
	private static final String SELECT_ALL_COMMENTS = "SELECT * FROM recipedb.joinnn;";
	private static final String DELETE_COMMENTS_SQL = "delete from recipedb.joinnn where joinID = ?;";
	private static final String UPDATE_JOINNN_SQL = "update recipedb.joinnn set uname = ?,name= ?, vote =? , comment =? where joinID = ?;";
	private static final String UPDATE_COMMENTS_SQL="update recipedb.comment set CommentID = ?,vote= ?, comment= ?;";
	
	public CommentDAO() {
		super();
	}
	
	
	
	

	protected Connection getConnection() {
		Connection connection =null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	
	
	
	
	
	
	public void insertCommentuser(Commentuser Commentuser) throws SQLException {
		System.out.println(Insert_User_Query);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(Insert_User_Query)) {
			preparedStatement.setString(1, Commentuser.getUname());
			preparedStatement.setInt(2, Commentuser.getCommentID());
				System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	
	public void insertComments(Comment comment) throws SQLException {
		System.out.println(Insert_COMMENT_Query);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(Insert_COMMENT_Query)) {
			preparedStatement.setInt(1, comment.getCommentID());
			preparedStatement.setInt(2, comment.getVote());
			preparedStatement.setString(3, comment.getComment());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public void insertCommentrecipe(Commentrecipe commentrecipe) throws SQLException {
		System.out.println(Insert_COMMENT_RecipeQuery);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(Insert_COMMENT_RecipeQuery)) {
			preparedStatement.setInt(1, commentrecipe.getCommentID());
			preparedStatement.setInt(2, commentrecipe.getId());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public Joinnn selectJoinnn(int joinID) {
		Joinnn joinnn = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMENT_BY_ID);) {
			preparedStatement.setInt(1, joinID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String uname = rs.getString("uname");
				String name = rs.getString("name");
				int vote = rs.getInt("vote");
				String comment1 = rs.getString("comment");
				joinnn = new Joinnn(uname, name, vote, comment1,joinID);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return joinnn;
	}

	
	

	
	
	
	
	
	
	public List<Joinnn> selectAllComments() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Joinnn> joinnn = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COMMENTS);) {
			System.out.println(preparedStatement);
			
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String uname = rs.getString("uname");
				String name = rs.getString("name");
				int vote = rs.getInt("vote");
				String comment1 = rs.getString("comment");
				int joinID = rs.getInt("joinID");
				joinnn.add(new Joinnn(uname, name, vote, comment1,joinID));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return joinnn;
	}

	public boolean deleteComment(int joinID) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_COMMENTS_SQL);) {
			statement.setInt(1, joinID);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateJoinnn(Joinnn joinnn) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_JOINNN_SQL);) {
			System.out.println("updated Comments:"+statement);
			statement.setString(1, joinnn.getUname());
			statement.setString(2, joinnn.getName());
			statement.setInt(3, joinnn.getVote());
			statement.setString(4, joinnn.getComment());
			statement.setInt(5, joinnn.getJoinID());
			

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	
	
	
	
	
	public boolean updateComment(Comment comment) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_COMMENTS_SQL);) {
			System.out.println("updated Comments:"+statement);
			statement.setInt(1, comment.getCommentID());
			statement.setInt(2, comment.getVote());
			statement.setString(3, comment.getComment());
			

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}