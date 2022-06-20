<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="styles/styles.css">

<title> Reversi Game</title>
</head>
<body>
<h1> Reversi </h1>
<div class="game">
  <form action="<c:url value='moveDisk'/>" method="post">
  	<table class="game-table">
  		<thead>
  		<tr class="table-row">
  			<th></th>
  			<th>A</th>
  			<th>B</th>
  			<th>C</th>
  			<th>D</th>
  			<th>E</th>
  			<th>F</th>
  			<th>G</th>
  			<th>H</th>
  		</tr>
  		</thead>
  		<tr>
  		<c:forEach items="${game.disks}" var="space" varStatus="stat">
  		<c:if test="${stat.count % 8 == 1}">
  			<td class="table-row">${game.getRow(stat.count)}</td>
  		</c:if>
  			<td> 
  			<c:choose>
  			<c:when test="${empty space }">
  				<button type="submit" name="loc" class="space" value="${stat.index }"></button>
  			</c:when>
  			<c:otherwise>
 				<span class="space ${space }"></span>
  			</c:otherwise>
  			</c:choose>		
  			</td>
  		<c:if test="${stat.count % 8 == 0 }">
  		</tr>
  		
  		</c:if>
  		</c:forEach>
    </table>
    <br>
    <p>${game.turnString}</p>
    <p>${err}</p>
    <table class="game-table">
    <tr>
    	<td>Current Player: </td>
    	<td>${game.currentPlayer}</td>
    </tr>
    <tr>
    	<td>Current Score: </td>
    	<td>LIGHT ${game.score[0]} - DARK ${game.score[1]}</td>
	</tr>    
	<tr>
    	<td>Game Over: </td>
    	<td>${game.over}</td>
	</tr> 
  	<c:if test="${game.over}">
  	<tr>
  		<td>Winner: </td>
  		<td>${(game.winner == null) ? "TIE" : game.winner}</td>
  	</tr>
  	</c:if>
  	<tr>
  		<td><button class="submit" name="quit" >New Game</button></td>
  		<td>
  		<c:if test="${pass == true}">
  			<button class="submit" name="pass">Pass Move</button>
  		</c:if>
  		</td>
  	</tr>
    </table>
    </form>
    </div>
  </body>
</html>
