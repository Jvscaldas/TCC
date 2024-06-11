<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style.css">
<title>Serviço</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
	</div>
	<br />
	<div align="center" class="container">
		<form action="servico" method="post">
			<br /> <br /> <br />
			<p class="title">
				<b>Serviços</b>
			</p>
			<table>
				<tr>
					<td colspan="3"><input class="input_data_id" type="text"
						id="placa" name="placa" placeholder="Placa"
						value='<c:out value="${servico.placa }"></c:out>'></td>
					<td><input type="submit" id="botao" name="botao"
						value="Buscar"></td>
				</tr>
				<tr>
					<td colspan="4"><input class="input_data" type="text"
						id="servico" name="servico" placeholder="Serviço"
						value='<c:out value="${servico.servico }"></c:out>'></td>
				</tr>
				<tr>
					<td colspan="4"><input class="input_data" type="text"
						id="dt_entrada" name="dt_entrada" placeholder="Data Entrada"
						value='<c:out value="${servico.dt_entrada }"></c:out>'></td>
				</tr>
				<tr>
					<td colspan="4"><input class="input_data" type="text"
						id="dt_retirada" name="dt_retirada" placeholder="Data Retirada"
						value='<c:out value="${servico.dt_retirada }"></c:out>'></td>
				</tr>
				<tr>
					<td colspan="4"><select class="combo_box" id="cliente"
						name="cliente">
							<option value="0">Selecione um Cliente</option>
							<c:if test="${not empty clientes }">
								<c:forEach items="${clientes }" var="c">
									<c:if test="${not empty cliente }">
										<c:if test="${c.cpf eq cliente.cpf }">
											<option value="${c.cpf }" selected="selected">
												<c:out value="${c.nome }">
												</c:out></option>
										</c:if>
									</c:if>
									<c:if test="${(empty cliente) || (c.cpf ne cliente.cpf) }">
										<option value="${c.cpf }">
											<c:out value="${c.nome }"></c:out>
										</option>
									</c:if>
								</c:forEach>
							</c:if>
					</select></td>
				</tr>
				<tr>
					<td colspan="4"><input class="input_data" type="text"
						id="status" name="status" placeholder="Status"
						value='<c:out value="${servico.status }"></c:out>'></td>
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
	<div align="center">
		<c:if test="${not empty erro }">
			<h2>
				<c:out value="${erro }" />
			</h2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty saida }">
			<h3>
				<c:out value="${saida }" />
			</h3>
		</c:if>
	</div>
</body>
</html>