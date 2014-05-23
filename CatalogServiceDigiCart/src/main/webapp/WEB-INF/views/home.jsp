<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>

<title>Library Book Maintenance</title>
</head>
<h2>Book Data Form</h2>
<a href="/LibraryBook/logout">Logout</a>
<form:form method="POST" modelAttribute="book" 	action="/LibraryBook/rest/book/create">
	<table>
		<tr>
			<td><label for="bookName">Book Name: </label></td>
			<td><form:input path="bookName" id="bookName"/></td>
			<td><form:errors path="bookName" cssclass="error"></form:errors></td>
		</tr>

		<tr>
			<td><label for="bookAuthor">Author: </label></td>
			<td><form:input path="author" id="bookAuthor"/></td>
			<td><form:errors path="author" cssclass="error"></form:errors></td>
		</tr>

		<tr>
			<td><label for="bookPrice">Price: </label></td>
			<td><form:input path="price" id="bookPrice" /></td>
			<td><form:errors path="price" cssclass="error"></form:errors></td>
		</tr>
		<tr>
			<td><label for="ratingOptions">Rating: </label></td>
			<td><form:select path="rating" id="ratingOptions">
					<form:option value="">Select Rating</form:option>
					<form:option value="POOR">poor</form:option>
					<form:option value="GOOD">good</form:option>
					<form:option value="VERYGOOD">verygood</form:option>
					<form:option value="AWESOME">awesome</form:option>
				</form:select></td>
			<td><form:errors path="rating" cssclass="error"></form:errors></td>
		</tr>

		<tr>
			<td><label for="bookISBN">ISBN: </label></td>
			<td><form:input path="ISBN" id="bookISBN"/></td>
			<td><form:errors path="ISBN" cssclass="error"></form:errors></td>
		</tr>

		<tr>
			<td><label for="bookReleaseDate">ReleaseDate: </label></td>
			<td><form:input path="releaseDate" id="bookReleaseDate"
					placeholder="dd/MM/yyyy" /></td>
			<td><form:errors path="releaseDate" cssclass="error"></form:errors></td>
		</tr>

		<tr>
			<td colspan="3"><input type="submit" value="Submit" />
		</tr>
	</table>

</form:form>

<c:if test="${not empty error}">
	<div class="message green">
		<font color="RED">${book.bookName} ${error}</font>
	</div>
</c:if>
<c:if test="${not empty success}">
	<div class="message green">
		<font color="Green">${book.bookName} ${success} </font>
	</div>
</c:if>
<form:form action="/LibraryBook/rest/books" method="GET"
	modelAttribute="bookList">
	<input type="submit" value="AllBooks">
</form:form>
<script src="https://www.paypalobjects.com/js/external/paypal-button.min.js?merchant=bikashdora-facilitator@gmail.com" 
    data-button="buynow" 
    data-name="DemoProduct" 
    data-quantity="3" 
    data-callback="http://localhost:8080/LibraryBook/home" 
    data-env="sandbox">
    </script>
   <a href="/LibraryBook/CreditCard">Credit Card Payment</a>
    
    
</body>
</html>
