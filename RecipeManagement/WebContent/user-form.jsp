

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

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="http://localhost:8080/RecipeManagement/list" class="navbar-brand"> Recipe Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Recipes</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${recipe != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${recipe == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${recipe != null}">
            			Edit Recipe
            		</c:if>
						<c:if test="${recipe == null}">
            			Add New Recipe
            		</c:if>
					</h2>
				</caption>

				<c:if test="${recipe != null}">
					<input type="hidden" name="id" value="<c:out value='${recipe.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Recipe Name</label> <input type="text"
						value="<c:out value='${recipe.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label> Recipe</label> <input type="text"
						value="<c:out value='${recipe.recipe}' />" class="form-control"
						name="recipe">
				</fieldset>

				<fieldset class="form-group">
					<label> Ingredients</label> <input type="text"
						value="<c:out value='${recipe.ingredients}' />" class="form-control"
						name="ingredients">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>