<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<tags:pageTemplate titulo="Math">

	<header class="bg-primary text-white">
		<div class="container text-center">
			<h1><fmt:message key="home.welcome" /></h1>
			<p class="lead"><fmt:message key="home.welcome.description" /></p>
		</div>
	</header>

	<section id="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 mx-auto">
					<h2><fmt:message key="home.about.title" /></h2>
					<p class="lead"><fmt:message key="home.about.title.description" /></p>
				</div>
			</div>
		</div>
	</section>

<%-- 	<section id="services" class="bg-light"> --%>
<!-- 		<div class="container"> -->
<!-- 			<div class="row"> -->
<!-- 				<div class="col-lg-8 mx-auto"> -->
<!-- 					<h2>Services we offer</h2> -->
<!-- 					<p class="lead">Lorem ipsum dolor sit amet, consectetur -->
<!-- 						adipisicing elit. Aut optio velit inventore, expedita quo -->
<!-- 						laboriosam possimus ea consequatur vitae, doloribus consequuntur -->
<!-- 						ex. Nemo assumenda laborum vel, labore ut velit dignissimos.</p> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<%-- 	</section> --%>

	<section id="contact">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 mx-auto">
					<h2><fmt:message key="home.about.contact" /></h2>
					<p class="lead"><fmt:message key="home.about.contact.description" /></p>
				</div>
			</div>
		</div>
	</section>

</tags:pageTemplate>



















