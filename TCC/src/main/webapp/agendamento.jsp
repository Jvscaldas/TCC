<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style.css">
<title>Agendamento</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
	</div>
	<br />
	<div align="center" class="container">
		<form action="agendamento" method="post">
			<br /> <br /> <br />
			<h1 class="title">
				<b>Agendamento</b>
			</h1>
			<table>
				<tr>
					<td colspan="3"><input class="input_data_id" type="text"
						id="cpf" name="cpf" placeholder="CPF"
						value='<c:out value="${cliente.cpf }"></c:out>'></td>
					<td><input type="submit" id="botao" name="botao"
						value="Buscar"></td>
				</tr>
				<tr>
					<td colspan="4"><input class="input_data" type="text"
						id="nome" name="nome" placeholder="Nome"
						value='<c:out value="${cliente.nome }"></c:out>'></td>
				</tr>
				<tr>
					<td colspan="4"><input class="input_data" type="text"
						id="email" name="email" placeholder="Email"
						value='<c:out value="${cliente.email }"></c:out>'></td>
				</tr>
				<tr>
					<td colspan="4"><input class="input_data" type="text"
						id="telefone" name="telefone" placeholder="Telefone"
						value='<c:out value="${cliente.telefone }"></c:out>'></td>
				</tr>
				<tr>
					<td><input type="submit" id="botao" name="botao"
						value="Inserir"></td>
					<td><input type="submit" id="botao" name="botao"
						value="Atualizar"></td>
					<td><input type="submit" id="botao" name="botao"
						value="Excluir"></td>
				</tr>
			</table>
		</form>
	</div>
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
</body>
</html>