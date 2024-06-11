<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style.css">
<title>Procedimento</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
	</div>
	<br />
	<div align="center" class="container">
		<form action="procedimento" method="post">
			<p class="title">
				<b>Procedimento</b>
			</p>
			<table>
				<tr>
					<td><input class="input_data" type="date" id="data"
						name="data" value='<c:out value="${servico.dt_entrada }"></c:out>'></td>
					<td><input type="submit" id="botao" name="botao"
						value="Pesquisar"></td>
				</tr>
			</table>
		</form>
	</div>
	<br />
	<br />
	<div align="center">
		<c:if test="${not empty erro }">
			<H2>
				<c:out value="${erro }" />
			</H2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty saida }">
			<H3>
				<c:out value="${saida }" />
			</H3>
		</c:if>
	</div>
	<br />
	<br />
	<div class="lista-data" align="center">
		<c:if test="${not empty lista }">
			<table class="tabela_div"
				style="border-collapse: separate; border-spacing: 0px;">
				<thead>
					<tr>
						<th class="titulos-listagem">DATA ENTRADA</th>
						<th class="titulos-listagem">PROCEDIMENTO</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="s" items="${lista }">
						<tr>
							<td class="fonte-listagem" align="center"><c:out
									value="${s.dt_entrada }" /></td>
							<td class="fonte-listagem" align="center"><c:out
									value="${s.servico }" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>