<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Kysymys" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vaalikone</title>

<link rel="stylesheet" type="text/css" href="mycssfilesomewhere.css">
<script src="myscriptfile.js"></script>

</head>
<body>
 <div align="center">
	<h2>Vaalikone</h2>	
	<table border="3" cellpadding="3">
	<tr>
       <th>ID</th>
       <th>Kysymykset</th>
       <th>Toiminnot</th>
    </tr>
	<c:forEach var="kysymys" items="${requestScope.kysymyslista}" >
		<tr>
			<td><c:out value="${kysymys.number}" /></td>
			<td><c:out value="${kysymys.kysymys}" /></td>
			<td>
			    <a href="/luepaivittamaan?id=<c:out value='${kysymys.id}' />">Päivitä</a>
			    &nbsp;&nbsp;&nbsp;&nbsp;
			    <a href="/poista?id=<c:out value='${kysymys.id}' />">Poista</a>                     
			</td>
		</tr>
	</c:forEach>
	</table>
	<p><a href="./html/kysymyslomake.html">Lis&auml;&auml; kysymykset?</a></p>
</div> 
</body>
</html>