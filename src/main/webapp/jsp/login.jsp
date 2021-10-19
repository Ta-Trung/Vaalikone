<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kirjautuminen</title>
</head>
<body>
	<div align="center">
		<h1>Vaalikone</h1>
		<h2>Ylläpito kirjautuminen</h2>
		<form id="formlogin" action="/vahvistatunnus" method="post">
			<table>
				<tr>
					<td>Ylläpitäjän nimi:</td>
					<td><input type="text" name="yllapitajannimi" id="yllapitajannimi" size="20"></td>
				</tr>
				<tr>
					<td>Salasana:</td>
					<td><input type="text" name="salasana" id="salasana" size="20"></td>
				</tr>
				<tr>
					<td>
						<button type="submit">Kirjaudu</button>
					</td>					
				</tr>
			</table>
		</form>
	</div>

</body>
</html>