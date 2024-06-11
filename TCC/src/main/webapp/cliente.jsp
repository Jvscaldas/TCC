<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style.css">
<title>Cliente</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
	</div>
	<br />
	<div align="center" class="container">
		<form action="cliente" method="post">
			<br /> <br /> <br />
			<p class="title">
				<b>Clientes</b>
			</p>
			<input type="submit" id="botao" name="botao" value="Listar">
		</form>
	</div>
	<div class="lista-cadastros" align="center">
		<c:if test="${not empty clientes }">
			<table class= "tabela_div" style="border-collapse: separate; border-spacing: 0px; widht: 100%">
				<thead>
					<tr>
						<th class="titulos-listagem"><b>CPF</b></th>
						<th class="titulos-listagem"><b>NOME</b></th>
						<th class="titulos-listagem"><b>EMAIL</b></th>
						<th class="titulos-listagem"><b>TELEFONE</b></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${clientes }" var="c">
						<tr>
							<td class="fonte-listagem" align="center"><c:out
									value="${c.cpf }" /></td>
							<td class="fonte-listagem" align="center"><c:out
									value="${c.nome }" /></td>
							<td class="fonte-listagem" align="center"><c:out
									value="${c.email }" /></td>
							<td class="fonte-listagem" align="center"><c:out
									value="${c.telefone }" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>