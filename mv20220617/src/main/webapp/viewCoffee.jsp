<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coffee View</title>
</head>
<body>
<table border="1"  width="50%">
 <c:forEach var="c"   items="${coffees}">                
        <tr>
           <td><c:out value="${c.cofName}"/></td>
           <td><c:out value="${c.sales}"/></td>
           <td><c:out value="${c.total}"/></td>
           <td><c:out value="${c.price}"/></td>
        </tr>
 </c:forEach>
 </table>
</body>
</html>