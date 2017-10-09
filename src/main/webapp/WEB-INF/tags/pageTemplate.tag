<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="titulo" required="true"%>
<%@ attribute name="bodyClass" required="false"%>
<%@ attribute name="extraScripts" fragment="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="pt">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>${titulo}</title>

<c:url value="/resources" var="resourcesPath" />
<c:url value="/jogo" var="jogoPath" />
<c:url value="/" var="homePath" />

<!-- Bootstrap core CSS -->
<link href="${resourcesPath}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${resourcesPath}/css/scrolling-nav.css" rel="stylesheet">

</head>
<body id="page-top">

	<%@include file="/WEB-INF/views/cabecalho.jsp"%>

	<jsp:doBody />

	<%@include file="/WEB-INF/views/rodape.jsp"%>

	<jsp:invoke fragment="extraScripts" />


</body>
</html>








