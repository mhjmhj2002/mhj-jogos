<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script> --%>

<script type="text/javascript" src="<c:url value='/resources/vendor/jquery/jquery.min.js'/>"></script>

<script type="text/javascript">

	$(document).ready(function() {
		console.log("ready");
	});

// 	$(function(){
		
// 		$('#n1').check(function() {
// 			console.log("check n1");
// 		});

// 	});
	
	function onCheck(check) {
		if(maxChecks()){
			check.checked = false;
			alert("Máximo 18!");
			return;
		}
		addQtdeDezenas();
		validarCheck(check,"l"+check.id);
	}
	
	function validarCheck(check, id){
		var label = document.getElementById(id);
		if (check.checked) {
			var numero = id.substring(2, 4);
			label.textContent = numero;
		} else {
			label.textContent = '';
		}
// 		label.hidden = !check.checked;
	}
	
	function maxChecks() {
		var sum = 0;
		for (var i = 1; i < 26; i++) {
			var check = document.getElementById("n"+i);
// 			console.log("check: " + check.id + ", checked: " + check.checked);
			if (check.checked) {
				sum++;
			}
		}
		return sum > 18;
	}
	
	function addQtdeDezenas() {
		var sum = 0;
		for (var i = 1; i < 26; i++) {
			var check = document.getElementById("n"+i);
			if(check == null) {
				return;
			}
// 			console.log("check: " + check.id + ", checked: " + check.checked);
			if (check.checked) {
				sum++;
			}
		}
		var qtdDezenas = document.getElementById("qtdDezenas");
		qtdDezenas.textContent = sum;
	}
	
	function cleanPage(){
		location.reload();
	}
</script>

<tags:pageTemplate titulo="Jogos">

	<header class="bg-primary text-white">
		<div class="container text-center">
			<h1>
				<fmt:message key="lotofacil.dash.title" />
			</h1>
			<p class="lead">
				<fmt:message key="lotofacil.sorteio.description" />
			</p>
		</div>
	</header>

	<section id="about">
		<div class="container">
			<form:form action="${s:mvcUrl('LFC#jogar').build() }" method="post" modelAttribute="jogo">
				<div class="form-group">
						<div class="form-group row">
							<div class="col-md-5">
								  <form:checkbox path="dezenas" id="n1" name="n1" value="01" onclick="onCheck(this)"/>01
								  <form:checkbox path="dezenas" id="n2" name="n2" value="02" onclick="onCheck(this)"/>02
								  <form:checkbox path="dezenas" id="n3" name="n3" value="03" onclick="onCheck(this)"/>03
								  <form:checkbox path="dezenas" id="n4" name="n4" value="04" onclick="onCheck(this)"/>04
								  <form:checkbox path="dezenas" id="n5" name="n5" value="05" onclick="onCheck(this)"/>05
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-5">
								  <form:checkbox path="dezenas" id="n6" name="n6" value="06" onclick="onCheck(this)"/>06
								  <form:checkbox path="dezenas" id="n7" name="n7" value="07" onclick="onCheck(this)"/>07
								  <form:checkbox path="dezenas" id="n8" name="n8" value="08" onclick="onCheck(this)"/>08
								  <form:checkbox path="dezenas" id="n9" name="n9" value="09" onclick="onCheck(this)"/>09
								  <form:checkbox path="dezenas" id="n10" name="n10" value="10" onclick="onCheck(this)"/>10
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-5">
								  <form:checkbox path="dezenas" id="n11" name="n11" value="11" onclick="onCheck(this)"/>11
								  <form:checkbox path="dezenas" id="n12" name="n12" value="12" onclick="onCheck(this)"/>12
								  <form:checkbox path="dezenas" id="n13" name="n13" value="13" onclick="onCheck(this)"/>13
								  <form:checkbox path="dezenas" id="n14" name="n14" value="14" onclick="onCheck(this)"/>14
								  <form:checkbox path="dezenas" id="n15" name="n15" value="15" onclick="onCheck(this)"/>15
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-5">
								  <form:checkbox path="dezenas" id="n16" name="n16" value="16" onclick="onCheck(this)"/>16
								  <form:checkbox path="dezenas" id="n17" name="n17" value="17" onclick="onCheck(this)"/>17
								  <form:checkbox path="dezenas" id="n18" name="n18" value="18" onclick="onCheck(this)"/>18
								  <form:checkbox path="dezenas" id="n19" name="n19" value="19" onclick="onCheck(this)"/>19
								  <form:checkbox path="dezenas" id="n20" name="n20" value="20" onclick="onCheck(this)"/>20
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-5">
								  <form:checkbox path="dezenas" id="n21" name="n21" value="21" onclick="onCheck(this)"/>21
								  <form:checkbox path="dezenas" id="n22" name="n22" value="22" onclick="onCheck(this)"/>22
								  <form:checkbox path="dezenas" id="n23" name="n23" value="23" onclick="onCheck(this)"/>23
								  <form:checkbox path="dezenas" id="n24" name="n24" value="24" onclick="onCheck(this)"/>24
								  <form:checkbox path="dezenas" id="n25" name="n25" value="25" onclick="onCheck(this)"/>25
							</div>
						</div>

						<div class="form-group row">
							<div class="col-md-5">
							<label>Quantidade de dezenas: </label>						  
						  	<label id="qtdDezenas">0</label>
						  	</div>	
						</div>
				</div>
				<button type="submit" class="btn btn-primary">Jogar</button>
			</form:form>
		</div>
	</section>
</tags:pageTemplate>