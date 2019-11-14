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
<!-- 								  <label for="n01">01</label> -->
<!-- 								  <input type="checkbox" id="n2" name="n2" onclick="onCheck(this)"> -->
<!-- 								  <label for="n02">02</label> -->
<!-- 								  <input type="checkbox" id="n3" name="n3" onclick="onCheck(this)"> -->
<!-- 								  <label for="n03">03</label> -->
<!-- 								  <input type="checkbox" id="n4" name="n4" onclick="onCheck(this)"> -->
<!-- 								  <label for="n04">04</label> -->
<!-- 								  <input type="checkbox" id="n5" name="n5" onclick="onCheck(this)"> -->
<!-- 								  <label for="n05">05</label> -->
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-5">
								  <form:checkbox path="dezenas" id="n6" name="n6" value="06" onclick="onCheck(this)"/>06
								  <form:checkbox path="dezenas" id="n7" name="n7" value="07" onclick="onCheck(this)"/>07
								  <form:checkbox path="dezenas" id="n8" name="n8" value="08" onclick="onCheck(this)"/>08
								  <form:checkbox path="dezenas" id="n9" name="n9" value="09" onclick="onCheck(this)"/>09
								  <form:checkbox path="dezenas" id="n10" name="n10" value="10" onclick="onCheck(this)"/>10
<!-- 								  <input type="checkbox" id="n6" name="n6" onclick="onCheck(this)"> -->
<!-- 								  <label for="n06">06</label> -->
<!-- 								  <input type="checkbox" id="n7" name="n7" onclick="onCheck(this)"> -->
<!-- 								  <label for="n07">07</label> -->
<!-- 								  <input type="checkbox" id="n8" name="n8" onclick="onCheck(this)"> -->
<!-- 								  <label for="n08">08</label> -->
<!-- 								  <input type="checkbox" id="n9" name="n9" onclick="onCheck(this)"> -->
<!-- 								  <label for="n09">09</label> -->
<!-- 								  <input type="checkbox" id="n10" name="n10" onclick="onCheck(this)"> -->
<!-- 								  <label for="n10">10</label> -->
							</div>
						</div>

			<div class="form-group row">
				<div class="col-md-5">
				<label>Quantidade de dezenas: </label>						  
			  	<label id="qtdDezenas">0</label>
			  	</div>	
			</div>
				</div>
			<div>	
				<label>Selecionados: </label>						  
			  	<label id="ln1"></label>							  
			    <form:label id="ln2"></form:label>							  
			    <form:label id="ln3"></form:label>							  
			    <form:label id="ln4"></form:label>							  
			    <form:label id="ln5"></form:label>							  
			    <form:label id="ln6"></form:label>							  
			    <form:label id="ln7"></form:label>							  
			    <form:label id="ln8"></form:label>							  
			    <form:label id="ln9"></form:label>							  
			    <form:label id="ln10></form:label>							  
			    <form:label id="ln11></form:label>							  
			    <form:label id="ln12></form:label>							  
			    <form:label id="ln13></form:label>							  
			    <form:label id="ln14></form:label>							  
			    <form:label id="ln15></form:label>							  
			    <form:label id="ln16></form:label>							  
			    <form:label id="ln17></form:label>							  
			    <form:label id="ln18></form:label>							  
			    <form:label id="ln19></form:label>							  
			    <form:label id="ln20></form:label>							  
			    <form:label id="ln21></form:label>							  
			    <form:label id="ln22></form:label>							  
			    <form:label id="ln23></form:label>							  
			    <form:label id="ln24></form:label>						  
			    <form:label id="ln25>25</form:label>
			</div>
				<button type="submit" class="btn btn-primary">Jogar</button>
			</form:form>
		</div>
	</section>
</tags:pageTemplate>