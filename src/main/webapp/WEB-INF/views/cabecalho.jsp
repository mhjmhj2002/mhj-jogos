<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
	id="mainNav">
	<div class="container">
		<a class="navbar-brand js-scroll-trigger" href="${homePath}#page-top"><fmt:message
				key="header.home" /></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="#about"><fmt:message key="header.about" /></a></li>
				<!--             <li class="nav-item"> -->
				<!--               <a class="nav-link js-scroll-trigger" href="#services">Services</a> -->
				<!--             </li> -->
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="${homePath}#contact"><fmt:message key="header.contact" /></a>
				</li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownPortfolio" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> <fmt:message
							key="header.operations" />
				</a>
					<div class="dropdown-menu dropdown-menu-right"
						aria-labelledby="navbarDropdownPortfolio">
						<a class="dropdown-item" href="${homePath}lotofacil"><fmt:message
								key="header.operations.1" /></a> <a class="dropdown-item"
							href="portfolio-2-col.html"><fmt:message
								key="header.operations.2" /></a> <a class="dropdown-item"
							href="portfolio-3-col.html"><fmt:message
								key="header.operations.3" /></a> <a class="dropdown-item"
							href="portfolio-4-col.html"><fmt:message
								key="header.operations.4" /></a> <a class="dropdown-item"
							href="portfolio-item.html"><fmt:message
								key="header.operations.5" /></a>
					</div></li>
			</ul>
		</div>
	</div>
</nav>
