<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>Library Book Details</title>
</head>
</body>
<h2>Books Stored</h2>
	 <div class="message blue">
		<h3><font color="Green">All Books Stored</font></h3>
		<table border="1">
			<tbody>
				<tr>
					<td>Book Name</td>
					<td>Author</td>
					<td>ISBN</td>
					<td>Price</td>
					<td>Rating</td>
					<td>Release Date</td>
				</tr>
				<c:if test="${not empty bookList}">
					<c:forEach var="book" items="${bookList}">
					<tr>
						<td>${book.bookName}</td>
						<td>${book.author}</td>
						<td>${book.ISBN}</td>
						<td>${book.price}</td>
						<td>${book.rating}</td>
						<td>${book.releaseDate}</td>
					</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	<a href="/LibraryBook/bookForm">Book Data Form</a>
</body>
</html>
