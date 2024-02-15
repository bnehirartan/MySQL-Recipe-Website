package com.xadmin.recipemanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.recipemanagement.model.Comment;
import com.xadmin.recipemanagement.model.Joinnn;
import com.xadmin.recipemanagement.model.Recipe;



public class RecipeDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/recipedb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Ye7Ti7k4";
	private String jdbcDriver = "com.mysql.jdbc.Driver";

	private static final String INSERT_RECIPES_SQL = "INSERT INTO recipedb.Recipes" + "  (name, recipe, ingredients) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_RECIPES_BY_ID = "select id,name,recipe,ingredients from recipedb.Recipes where id =?";
	private static final String SELECT_ALL_RECIPES = "select * from recipedb.Recipes";
	private static final String DELETE_RECIPES_SQL = "delete from recipedb.Recipes where id = ?;";
	private static final String UPDATE_RECIPES_SQL = "update recipedb.Recipes set name = ?,recipe= ?, ingredients =? where id = ?;";
	private static final String COUNT_RECIPES_SQL = "SELECT COUNT(name) AS NOR FROM recipedb.Recipes;";
	private static final String DESC_RECIPES_SQL="SELECT * FROM recipedb.Recipes ORDER BY name desc;";
	private static final String ASC_RECIPES_SQL="SELECT * FROM recipedb.Recipes ORDER BY name ASC;";
	private static final String UPDATE ="drop table recipedb.joinnn;\n"
			+ "create table recipedb.joinnn AS\n"
			+ "SELECT commentuser.uname ,Recipes.name, comment.vote ,comment.comment\n"
			+ "FROM recipedb.commentrecipe\n"
			+ "JOIN recipedb.Recipes ON commentrecipe.id = Recipes.id\n"
			+ "JOIN recipedb.comment ON commentrecipe.CommentID = comment.CommentID\n"
			+ "JOIN recipedb.commentuser ON comment.CommentID = commentuser.CommentID\n"
			+ "JOIN recipedb.user ON commentuser.uname = user.uname;\n"
			+ "ALTER TABLE  recipedb.joinnn\n"
			+ "ADD COLUMN `joinID` INT NOT NULL AUTO_INCREMENT AFTER `comment`,\n"
			+ "ADD PRIMARY KEY (`joinID`),\n"
			+ "ADD UNIQUE INDEX `joinID_UNIQUE` (`joinID` ASC) VISIBLE;\n"
			+ "";
	
	
	private static final String totalvotes_per_dish= "SELECT name, SUM(vote) AS totalVotesFROM recipedb.joinnnGROUP BY name;";
	private static final String AVGvotes_per_dish= "SELECT name, AVG(vote) AS totalVotesFROM recipedb.joinnnGROUP BY name;";
	private static final String COMMENTUSER ="INSERT INTO `recipedb`.`commentuser`"+" (`uname`, `CommentID`) VALUES "+"(?, ?)";
	private static final String COMMENTRECIPE=  "INSERT INTO `recipedb`.`commentrecipe`"+" ( `CommentID`, `id`) VALUES "+"(?, ?)";
	
	public RecipeDAO() {
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

	public void insertRecipes(Recipe recipe) throws SQLException {
		System.out.println(INSERT_RECIPES_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECIPES_SQL)) {
			preparedStatement.setString(1, recipe.getName());
			preparedStatement.setString(2, recipe.getRecipe());
			preparedStatement.setString(3, recipe.getIngredients());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	
	
	
	
	
	
	
	
	
	
	public Recipe selectRecipe(int id) {
		Recipe recipe = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECIPES_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String recipe1 = rs.getString("recipe");
				String ingredients = rs.getString("ingredients");
				recipe = new Recipe(id, name, recipe1, ingredients);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return recipe;
	}
	
	
	
	public int RecipeCount () {
		int count=0;
		try (Connection connection = getConnection();
				
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(COUNT_RECIPES_SQL);){
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				 count = rs.getInt("NOR");
				 System.out.println("salam");
			}
		
	System.out.println(count);
	
	} catch (SQLException e) {
		printSQLException(e);
	}
		return count;
	}
	
	

	
	
	
	
	
	public List<Recipe> selectAllRecipesAsc() {
	    List<Recipe> recipes = new ArrayList<>();
	    try (Connection connection = getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(ASC_RECIPES_SQL);) {
	        System.out.println(preparedStatement);

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
	            String recipe = rs.getString("recipe");
	            String ingredients = rs.getString("ingredients");
	            recipes.add(new Recipe(id, name, recipe, ingredients));
	        }
	    } catch (SQLException e) {
	        printSQLException(e);
	    }
	    return recipes;
	}

	
	public void updateeComment() {
	    try (Connection connection = getConnection();
	         Statement statement = connection.createStatement();) {
	        
	        // Execute the SQL statements
	        statement.executeUpdate("DROP TABLE IF EXISTS recipedb.joinnn;");
	        statement.executeUpdate("CREATE TABLE recipedb.joinnn AS SELECT commentuser.uname, Recipes.name, comment.vote, comment.comment " +
	                               "FROM recipedb.commentrecipe " +
	                               "JOIN recipedb.Recipes ON commentrecipe.id = Recipes.id " +
	                               "JOIN recipedb.comment ON commentrecipe.CommentID = comment.CommentID " +
	                               "JOIN recipedb.commentuser ON comment.CommentID = commentuser.CommentID " +
	                               "JOIN recipedb.user ON commentuser.uname = user.uname;");
	        statement.executeUpdate("ALTER TABLE recipedb.joinnn " +
	                               "ADD COLUMN `joinID` INT NOT NULL AUTO_INCREMENT AFTER `comment`, " +
	                               "ADD PRIMARY KEY (`joinID`), " +
	                               "ADD UNIQUE INDEX `joinID_UNIQUE` (`joinID` ASC) VISIBLE;");
	    } catch (SQLException e) {
	        printSQLException(e);
	    }
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<Recipe> selectAllRecipesDesc() {
	    List<Recipe> recipes = new ArrayList<>();
	    try (Connection connection = getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(DESC_RECIPES_SQL);) {
	        System.out.println(preparedStatement);

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
	            String recipe = rs.getString("recipe");
	            String ingredients = rs.getString("ingredients");
	            recipes.add(new Recipe(id, name, recipe, ingredients));
	        }
	    } catch (SQLException e) {
	        printSQLException(e);
	    }
	    return recipes;
	}

	
	
	
	
	
	
	
	public List<Recipe> selectAllRecipes() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Recipe> recipes = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RECIPES);) {
			System.out.println(preparedStatement);
			
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String recipe = rs.getString("recipe");
				String ingredients = rs.getString("ingredients");
				recipes.add(new Recipe(id, name, recipe, ingredients));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return recipes;
	}

	public boolean deleteRecipe(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_RECIPES_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateRecipe(Recipe recipe) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPES_SQL);) {
			System.out.println("updated Recipes:"+statement);
			statement.setString(1, recipe.getName());
			statement.setString(2, recipe.getRecipe());
			statement.setString(3, recipe.getIngredients());
			statement.setInt(4, recipe.getId());

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