<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<tags:pageTemplate titulo="Math">

	<header class="bg-primary text-white">
		<div class="container text-center">
			<h1>
				<fmt:message key="lotofacil.dash.title" />
			</h1>
			<p class="lead">
				<fmt:message key="lotofacil.dash.description" />
			</p>
		</div>
	</header>

	<section id="about">
		<div class="container">
			<div class="form-group row">
				<div class="col-md-12">
					<div class="form-group row">
						<div class="col-md-12">
							<label>Total Premios: ${somaMaisSorteados}</label>
						</div>
						<div class="col-md-12">
							<label>Total Gastos: ${gasto}</label>
						</div>
						<div class="col-md-12">
							<label>Lucro: ${lucro}</label>
						</div>
					</div>
				</div>
			</div>
			<h1>Dezenas Mais Sorteadas</h1>
			<table
				class="table table-bordered table-striped table-hover sortable">
				<tr>
					<th>Dezena</th>
					<th>X Sorteada</th>
				</tr>

				<c:forEach items="${dezenasMaisSorteadas}" var="sorteada">
					<tr>
						<td>${sorteada.numero}</td>
						<td>${sorteada.quantidade}</td>
					</tr>
				</c:forEach>
			</table>
			<h1>Detalhe</h1>
			<table
				class="table table-bordered table-striped table-hover sortable">
				<tr>
					<th>Concurso</th>
					<th>Acertos</th>
					<th>Valor</th>
					<th>Data</th>
				</tr>

				<c:forEach items="${maisSorteados}" var="sorteio">
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