<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Puun tiheyslaskuri</title>

	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Krub&display=swap"
		rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/jasminpuuntiheys/demo.css">
	<script src="/jasminpuuntiheys/palikkascript.js">

	</script>
</head>

<body>
	<h1>&#127794;&#128296; Puun tiheyslaskuri <span class="pro">PRO</span> &#128296;&#127794;</h1>

	<form method="post">
		<span>Korkeus (mm):</span> <input name="korkeus" type="text" required placeholder="..." ${ korkeustaytto }/>
		<span>Leveys (mm):</span> <input name="leveys" type="text" required placeholder="..." ${ leveystaytto }/>
		<span>Pituus (mm):</span> <input name="pituus" type="text" required placeholder="..." ${ pituustaytto }/>
		<span>Paino (g):</span> <input name="paino" type="text" required placeholder="..." ${ painotaytto }/>
		<span>Grain:</span> <input name="grain" type="text" placeholder="..." ${ graintaytto }/>
		<input type="submit" value="Laske" />
	</form>
	
	<span class="selite">Tiheys: ${tiheystaytto}</span>

	<table>
		<tr>
			<th>ID</th>
			<th>TIHEYS<br>(kg/m<sup>3</sup>)</th>
			<th>KORKEUS<br>(mm)</th>
			<th>LEVEYS<br>(mm)</th>
			<th>PAINO<br>(g)</th>
			<th>PITUUS<br>(mm)</th>
			<th>GRAIN</th>
			<th>POISTA?</th>
		</tr>

		<c:forEach items="${tuotteet}" var="tuote">
			<tr id="rivi-${ tuote.getId() }">
				<td>${ tuote.getId() }</td>
				<td>${ tuote.getTiheys() }</td>
				<td>${ tuote.getKorkeus() }</td>
				<td>${ tuote.getLeveys() }</td>
				<td>${ tuote.getPaino() }</td>
				<td>${ tuote.getPituus() }</td>
				<td>${ tuote.getGrain() }</td>
				<td onclick="poistaPalikka(${ tuote.getId() });">&#10006;</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>