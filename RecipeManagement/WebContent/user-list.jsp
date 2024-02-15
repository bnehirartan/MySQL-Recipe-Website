<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Recipe Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>




	<h1>RecipeCount: <%= request.getAttribute("COUNT") %></h1>
	
	
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
		<a href="<%=request.getContextPath()%>/ASC" class="btn btn-success">ASC</a>
		<a href="<%=request.getContextPath()%>/DESC" class="btn btn-success">DESC</a>
		<a href="<%=request.getContextPath()%>/CommentServlet" class="btn btn-success">COMMENT</a>	
			
			
			
			
			
				<a href="http://localhost:8080/RecipeManagement/list" class="navbar-brand"> Recipe
					Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Recipe</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Recipes</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Recipe</a>
			</div>
			
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						
						<th>Recipe Name</th>
						<th>Recipe</th>
						<th>Ingredients</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="recipe" items="${listRecipe}">

						<tr>
						
							<td><c:out value="${recipe.name}" /></td>
							<td><c:out value="${recipe.recipe}" /></td>
							<td><c:out value="${recipe.ingredients}" /></td>
							<td><a href="edit?id=<c:out value='${recipe.id}' />">Edit Recipe</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${recipe.id}' />">Delete Recipe</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>