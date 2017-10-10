<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
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
			<form:form action="${s:mvcUrl('LFC#gravar').build() }" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label>Arquivo</label> 
					<input name=sorteio type="file" class="form-control" />
				</div>
				<button type="submit" class="btn btn-primary">Gravar</button>
			</form:form>
		</div>
	</section>
</tags:pageTemplate>