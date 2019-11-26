<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<tags:pageTemplate titulo="Jogos">

	<header class="bg-primary text-white">
		<div class="container text-center">
			<h1>
				<fmt:message key="lotofacil.dash.title" />
			</h1>
			<p class="lead">
				<fmt:message key="lotofacil.dezenasSelecionadas.description" />
			</p>
		</div>
	</header>

	<section id="about">
		<div class="container">
			<div class="form-group row">
				<div class="col-md-12">
					<div class="form-group row">
						<div class="col-md-12">
							<label>Se eu tivesse jogado desde o começo, teria ganhado em premios: ${somaSorteados}</label>
						</div>
						<div class="col-md-12">
							<label>Se eu tivesse jogado desde o começo, teria gastado (Considerando valor do jogo em R$ ${valor}): ${gasto}</label>
						</div>
						<div class="col-md-12">
						<c:if test="${lucro > 0}">
							<label style="color: #4CAF50;">Lucro: ${lucro}</label>
						</c:if>
						<c:if test="${lucro < 0}">
							<label style="color: #f44336;">Lucro: ${lucro}</label>
						</c:if>
						<c:if test="${lucro == 0}">
							<label style="color: #2196F3;">Lucro: ${lucro}</label>
						</c:if>
						</div>
					</div>
				</div>
			</div>
			<h1>Dezenas Selecionadas</h1>
			<table
				class="table table-bordered table-striped table-hover sortable">
				<tr>
					<th>Dezena</th>
					<th>X Sorteada</th>
				</tr>

				<c:forEach items="${dezenasSelecionadas}" var="selecionada">
					<tr>
						<td>${selecionada.numero}</td>
						<td>${selecionada.quantidade}</td>
					</tr>
				</c:forEach>
			</table>
			<h1>Concursos onde teriam sido premiadas</h1>
			<table
				class="table table-bordered table-striped table-hover sortable">
				<tr>
					<th>Concurso</th>
					<th>Acertos</th>
					<th>Valor</th>
					<th>Data</th>
				</tr>

				<c:forEach items="${sorteados}" var="sorteio">
					<tr>
						<td>${sorteio.concurso}</td>
						<td>${sorteio.acertos }</td>
						<td>${sorteio.valor}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${sorteio.data}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</section>
</tags:pageTemplate>