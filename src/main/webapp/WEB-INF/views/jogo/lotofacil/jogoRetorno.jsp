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
				<fmt:message key="lotofacil.menosSorteadas.description" />
			</p>
		</div>
	</header>

	<section id="about">
		<div class="container">
			<div class="form-group row">
				<div class="col-md-12">
					<div class="form-group row">
						<div class="col-md-12">
							<label>Se eu tivesse jogado as dezenas menos sorteadas desde o começo, teria ganhado em premios: ${somaMenosSorteados}</label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</tags:pageTemplate>