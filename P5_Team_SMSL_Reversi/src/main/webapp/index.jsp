<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<style>
.game {
	border: 5px black solid;
	width: 500px;
}
.space {
	background-color: green;
	height: 50px;
	width: 50px;
	line-height: 50px;
	text-align: center;
	vertical-align: middle;
	text-align: center;
}
.LIGHT {
	background-color:white;
	color: white;
	height: 50px;
  	width: 50px;
  	border-radius: 50%;
  	display: inline-block;
}
.DARK {
	background-color:black;
	color: black;
	height: 50px;
  	width: 50px;
  	border-radius: 50%;
  	display: inline-block;
}

.game-row {
  	display: grid;
  	grid-template-columns: 60px 60px 60px 60px 60px;
  	grid-template-rows: 30px 30px;
 	grid-auto-flow: row;
 	height: 15px;
 	background-color: green;
 	color: black;
 	text-align: center;
}

.item-a {
  grid-column: 1;
  grid-row: 1 / 8;
}

.item-h {
  grid-column: 8;
  grid-row: 1 / 8;
}


</style>


<title> Reversi Game</title>
<h1> Reversi </h1>
</head>
  <body>
  <div class="game">
  
  <section class="game-row">
  <div class="item-a">A</div>
  <div class="item-b">B</div>
  <div class="item-c">C</div>
  <div class="item-d">D</div>
  <div class="item-e">E</div>
  <div class="item-f">F</div>
  <div class="item-g">G</div>
  <div class="item-h">H</div>
</section>
  
  <form action="<c:url value='moveDisk'/>" method="post">
  	<table style="background-color:Green; width: 500px;">
  		<tr>
  		<c:forEach items="${game.disks}" var="space" varStatus="stat">
  			<c:if test="${stat.count % 8 == 1}">
  			<td>${game.getRow(stat.count)}</td>
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
  			
  			<td>
  			<c:if test="${stat.count % 8 == 0 }">
  			</tr>
  			<tr>
  			</c:if>
  		</c:forEach>
  		<td colspan="16">
  		<ul style="background-color:darkgreen; color:white;">
  		<li>Current Player: ${game.currentPlayer }</li>
  		<li>Current Score: DARK ${game.darkScore} - LIGHT ${game.lightScore}</li>
  		<li>Game Over: ${game.over}</li>
  		<c:if test="${game.over}">
  		<li>Winner: ${(game.winner == null) ? "TIE" : game.winner}</li>
  		</c:if>
  		</ul>
  		<button class="submit" name="quit" >New Game</button>
  		<c:if test="${pass && !game.over}">
  		<button class="submit" name="pass">Pass Move</button>
  		</c:if>
  		</td>
    	</tr>
    </table>
    </form>
    ${game.turnString}
    ${err}
    </div>
  </body>
</html>
