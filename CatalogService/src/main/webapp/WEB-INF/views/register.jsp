<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>

<title>Library Book Maintenance</title>
</head>
<h2>Register new User</h2>

<form:form method="POST" modelAttribute="user" action="/LibraryBook/rest/user/registerUser">
	<table>
		<tr>
			<td><label for="userName">User Name </label></td>
			<td><form:input path="userId" id="userName" /></td>
			<td><form:errors path="userId" cssclass="error"></form:errors></td>
		</tr>

		<tr>
			<td><label for="password">Password </label></td>
			<td><form:input path="password" id="password" type='password' /></td>
			<td><form:errors path="password" cssclass="error"></form:errors></td>
		</tr>

		<tr>
			<td>Select User Level</td>
			<td>
			<input type="checkbox" name="userlevel" value="ROLE_ADMIN" />Admin
			<input type="checkbox" name="userlevel" value="ROLE_MODERATOR" />Moderator 
			<input type="checkbox" name="userlevel" value="ROLE_USER" checked="checked" />User
			</td>
		</tr>
		<tr>
			<td colspan="3"><input type="submit" value="Submit" />
		</tr>
	</table>
</form:form>
<a href="/LibraryBook/login">Go to Login page</a>
<c:if test="${not empty success}">
	<div class="message green">
		<font color="Green">${user.userId} ${success} </font>
	</div>
</c:if>	
<c:if test="${not empty error}">
	<div class="message red">
		<font color="RED">${user.userId} ${error}</font>
	</div>
</c:if>
</body>
</html>
