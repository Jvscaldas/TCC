<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style.css">
<title>Controle</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
	</div>
	<br />
	<div align="center" class="container">
		<form action="controle" method="post">
			<br /> <br /> <br />
			<p class="title">
				<b>Agendamentos</b>
			</p>
			<input type="submit" id="botao" name="botao" value="Controle">
		</form>
	</div>
	<div class="lista-cadastros" align="center">
		<c:if test="${not empty servicos }">
			<table class= "tabela_div" style="border-collapse: separate; border-spacing: 0px;">
				<thead>
					<tr>
						<th class="titulos-listagem"><b>CLIENTE</b></th>
						<th class="titulos-listagem"><b>PLACA</b></th>
						<th class="titulos-listagem"><b>PROCEDIMENTOS</b></th>
						<th class="titulos-listagem"><b>PRAZO</b></th>
						<th class="titulos-listagem"><b>STATUS</b></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${servicos }" var="s">
						<tr>
							<td class="fonte-listagem" align="center"><c:out
									value="${s.cliente.nome }" /></td>
							<td class="fonte-listagem" align="center"><c:out
									value="${s.placa }" /></td>
							<td class="fonte-listagem" align="center"><c:out
									value="${s.servico }" /></td>
							<td class="fonte-listagem" align="center"><c:out
									value="${s.dt_retirada }" /></td>
							<td class="fonte-listagem" align="center"><c:out
									value="${s.status }" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>