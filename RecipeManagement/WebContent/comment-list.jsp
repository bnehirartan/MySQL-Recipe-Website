<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Comment Management Application - Comment List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: blue">
        <div>
            <a href="http://localhost:8080/RecipeManagement/list" class="navbar-brand">Comment Management Application</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/listComment"
                   class="nav-link">Comments</a></li>
        </ul>
    </nav>
</header>

<br>
<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Comments</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/showEditCommentForm" class="btn btn-success">Add New comment</a>
				<a href="<%=request.getContextPath()%>/up" class="btn btn-success">Update comment</a>
			</div>
			
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>

                <th>User Name</th>
                <th>Recipe Name</th>
                <th>Vote</th>
                <th>Comment</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="comment" items="${listComment}">
                <tr>
                    <td><c:out value="${comment.uname}" /></td>
                    <td><c:out value="${comment.name}" /></td>
                    <td><c:out value="${comment.vote}" /></td>
                    <td><c:out value="${comment.comment}" /></td>
                    <td>
                        <a href="showEditCommentForm?joinID=<c:out value='${comment.joinID}' />">Edit Comment</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteComment?joinID=<c:out value='${comment.joinID}' />">Delete Comment</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
