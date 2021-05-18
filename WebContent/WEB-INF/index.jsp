<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Sivusto on katsottavissa http://softwareservice.fi:8080/jasminpuuntiheys/puun-tiheys -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Puun tiheyslaskuri</title>
	
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Krub&display=swap"
		rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/jasminpuuntiheys/demo.css">
	<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
</head>

<body>
	<div class="salainen">
		<div class="salainensisalto">
			<input id="palaa" type="submit" value="Palaa">
			<span>Anna salasana pro-versioon (= 31)</span>
			<form action="salainen" method="post">
				<input type="text" name="salasana" required>
				<input type="submit" value="Jatka">
			</form>
		</div>
	</div>
	
	<h1>&#127794;&#128296; Puun tiheyslaskuri &#128296;&#127794;</h1>

	<form method="post">
		<span>Korkeus (mm):</span> <input name="korkeus" type="text" required placeholder="..." ${ korkeustaytto }/>
		<span>Leveys (mm):</span> <input name="leveys" type="text" required placeholder="..." ${ leveystaytto }/>
		<span>Pituus (mm):</span> <input name="pituus" type="text" required placeholder="..." ${ pituustaytto }/>
		<span>Paino (g):</span> <input name="paino" type="text" required placeholder="..." ${ painotaytto }/>
		<input type="submit" value="Laske" /><a id="pro">Pro-versioon</a>
	</form>
	
	<span class="selite">Tiheys: ${tiheystaytto}</span>

	<script>
		$("#pro").click(function() {
			$(".salainen").fadeIn("500");
		});
		
		$("#palaa").click(function() {
			$(".salainen").fadeOut("500");
		});
	</script>
</body>
</html>