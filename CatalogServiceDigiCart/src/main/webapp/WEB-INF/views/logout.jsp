<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 
<html>
    <body>


<%-- session object example --%>
<strong>User Session ID</strong>:<%=session.getId() %><br><br>
<%session.invalidate(); %><br><br>
 You are now logged out!!
<a href="/LibraryBook/bookForm">Book Data Form</a>
   </body>
</html>