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
							<label>Último concurso cadastrado: ${numero}</label>
						</div>
						<div class="col-md-12">
							<label>Data: <fmt:formatDate pattern="dd/MM/yyyy"
								value="${data}" /></label>
						</div>
						<div class="col-md-12">
							<label>Dezenas Sorteadas: ${dezenas}</label>
						</div>
						<div class="col-md-12">
							<label>Dezena Mais Sorteada: ${dezenaMaisSorteada} - ${vezesMaisSorteada} vezes.</label>
						</div>
						<div class="col-md-12">
							<label>Dezena Menos Sorteada: ${dezenaMenosSorteada} - ${vezesMenosSorteada} vezes.</label>
						</div>
					</div>
				</div>
			</div>	
			<h1>Opções:</h1>
			<div class="form-group row">
				<div class="col-md-12">
					<div class="form-group row">
						<div class="col-md-12">
							<a href="${homePath}lotofacil/maisSorteadas">- Dezenas mais sorteadas</a>
						</div>
						<div class="col-md-12">
							<a href="${homePath}lotofacil/menosSorteadas">- Dezenas menos sorteadas</a>
						</div>
						<div class="col-md-12">
							<a href="${homePath}lotofacil/jogo">- Escolher Jogo</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</tags:pageTemplate>