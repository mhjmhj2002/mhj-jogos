<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<tags:pageTemplate titulo="Math">

	<header class="bg-primary text-white">
		<div class="container text-center">
			<h1>Contato</h1>
			<p class="lead">Descrição</p>
		</div>
	</header>

	<section id="about">
		<div class="container">
			<form:form action="${s:mvcUrl('CC#contatar').build() }" method="POST"
				commandName="contato">
				<div class="form-group">
					<div class="col-md-12">
						<div class="form-group row">
							<div class="col-md-12">
								<label>Nome</label>
							</div>
							<div class="col-md-12">
								<form:input path="nome" />
								<form:errors path="nome" />
							</div>
							<div class="col-md-12">
								<label>Email</label>
							</div>
							<div class="col-md-12">
								<form:input type="email" path="email" />
								<form:errors path="email" />
							</div>
							<div class="col-md-12">
								<label>Mensagem</label>
							</div>
							<div class="col-md-12">
								<form:textarea path="mensagem" cssClass="form-control"
									maxlength="50" />
								<form:errors path="mensagem" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">
						<div class="form-group row">
							<div class="col-md-12">
								<button type="submit" class="btn btn-primary">Enviar</button>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</section>
</tags:pageTemplate>