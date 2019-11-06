<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

// 	jQuery(document).ready(function() {
// 		console.log("ready jogo");
// 	});

// 	$(function(){
		
// 		$('#n1').check(function() {
// 			console.log("check n1");
// 		});

// 	});
	
	function onCheck1(check) {
		console.log("onCheck: " + check.checked);
		console.log("onCheck: " + check.value);
		console.log("teste: " + $('#l1').text);
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
					<div class="col-md-12">
						<div class="form-group row">
							<div class="col-md-3">
								  <input type="checkbox" id="n1" name="n1" onclick="onCheck1(this)">
								  <label for="n1">01</label>
								  <input type="checkbox" id="n2" name="n2" onclick="onCheck2(this)">
								  <label for="n2">02</label>
								  <input type="checkbox" id="n3" name="n3" onclick="onCheck3(this)">
								  <label for="n3">03</label>
								  <input type="checkbox" id="n4" name="n4" onclick="onCheck4(this)">
								  <label for="n4">04</label>
								  <input type="checkbox" id="n5" name="n5" onclick="onCheck5(this)">
								  <label for="n5">05</label>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<div class="col-md-3">
								  <input type="checkbox" id="n6" name="n6" onclick="onCheck6(this)">
								  <label for="n6">06</label>
								  <input type="checkbox" id="n7" name="n7" onclick="onCheck7(this)">
								  <label for="n7">07</label>
								  <input type="checkbox" id="n8" name="n8" onclick="onCheck8(this)">
								  <label for="n8">08</label>
								  <input type="checkbox" id="n9" name="n9" onclick="onCheck9(this)">
								  <label for="n9">09</label>
								  <input type="checkbox" id="n10" name="n10" onclick="onCheck10(this)">
								  <label for="n10">10</label>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<div class="col-md-3">
								  <input type="checkbox" id="n11" name="n11" onclick="onCheck11(this)">
								  <label for="n11">11</label>
								  <input type="checkbox" id="n12" name="n12" onclick="onCheck12(this)">
								  <label for="n12">12</label>
								  <input type="checkbox" id="n13" name="n13" onclick="onCheck13(this)">
								  <label for="n13">13</label>
								  <input type="checkbox" id="n14" name="n14" onclick="onCheck14(this)">
								  <label for="n14">14</label>
								  <input type="checkbox" id="n15" name="n15" onclick="onCheck15(this)">
								  <label for="n15">15</label>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<div class="col-md-3">
								  <input type="checkbox" id="n16" name="n16" onclick="onCheck16(this)">
								  <label for="n16">16</label>
								  <input type="checkbox" id="n17" name="n17" onclick="onCheck17(this)">
								  <label for="n17">17</label>
								  <input type="checkbox" id="n18" name="n18" onclick="onCheck18(this)">
								  <label for="n18">18</label>
								  <input type="checkbox" id="n19" name="n19" onclick="onCheck19(this)">
								  <label for="n19">19</label>
								  <input type="checkbox" id="n20" name="n20" onclick="onCheck20(this)">
								  <label for="n20">20</label>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<div class="col-md-3">
								  <input type="checkbox" id="n21" name="n21" onclick="onCheck21(this)">
								  <label for="n21">21</label>
								  <input type="checkbox" id="n22" name="n22" onclick="onCheck22(this)">
								  <label for="n22">22</label>
								  <input type="checkbox" id="n23" name="n23" onclick="onCheck23(this)">
								  <label for="n23">23</label>
								  <input type="checkbox" id="n24" name="n24" onclick="onCheck24(this)">
								  <label for="n24">24</label>
								  <input type="checkbox" id="n25" name="n25" onclick="onCheck25(this)">
								  <label for="n25">25</label>
							</div>
						</div>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">Jogar</button>
			</form:form>
			<div>	
				<label>Selecionados: </label>						  
			  	<label id="l1" hidden="true">1</label>							  
			    <label id="l2" hidden="true">2</label>							  
			    <label id="l3" hidden="true">3</label>							  
			    <label id="l4" hidden="true">4</label>							  
			    <label id="l5" hidden="true">5</label>							  
			    <label id="l6" hidden="true">6</label>							  
			    <label id="l7" hidden="true">7</label>							  
			    <label id="l8" hidden="true">8</label>							  
			    <label id="l9" hidden="true">9</label>							  
			    <label id="l10" hidden="true">10</label>							  
			    <label id="l11" hidden="true">11</label>							  
			    <label id="l12" hidden="true">12</label>							  
			    <label id="l13" hidden="true">13</label>							  
			    <label id="l14" hidden="true">14</label>							  
			    <label id="l15" hidden="true">15</label>							  
			    <label id="l16" hidden="true">16</label>							  
			    <label id="l17" hidden="true">17</label>							  
			    <label id="l18" hidden="true">18</label>							  
			    <label id="l19" hidden="true">19</label>							  
			    <label id="l20" hidden="true">20</label>							  
			    <label id="l21" hidden="true">21</label>							  
			    <label id="l22" hidden="true">22</label>							  
			    <label id="l23" hidden="true">23</label>							  
			    <label id="l24" hidden="true">24</label>						  
			    <label id="l25" hidden="true">25</label>
			</div>
		</div>
	</section>
</tags:pageTemplate>