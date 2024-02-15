

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
				<c:if test="${comment != null}">
					<form action="updateComment" method="post">
				</c:if>
				<c:if test="${comment == null}">
					<form action="insertComment" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${recipe != null}">
            			Edit Comment
            		</c:if>
						<c:if test="${recipe == null}">
            			Add New Comment
            		</c:if>
					</h2>
				</caption>

				<c:if test="${comment != null}">
					<input type="hidden" name="joinID" value="<c:out value='${joinnn.joinID}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>CommentID</label> <input type="number"
						value="<c:out value='${comment.CommentID}' />" class="form-control"
						name="CommentID" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label> Comment</label> <input type="text"
						value="<c:out value='${comment.comment}' />" class="form-control"
						name="comment">
				</fieldset>

				<fieldset class="form-group">
					<label> Vote</label> <input type="number"
						value="<c:out value='${joinnn.vote}' />" class="form-control"
						name="vote">
				</fieldset>
						<fieldset class="form-group">
					<label>dish id</label> <input type="number"
						value="<c:out value='${commentrecipe.id}' />" class="form-control"
						name="id">
				</fieldset>
				</fieldset>
						<fieldset class="form-group">
					<label>user name</label> <input type="text"
						value="<c:out value='${commentuser.uname}' />" class="form-control"
						name="name">
				</fieldset>
				
				
				
				
				
				




				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>