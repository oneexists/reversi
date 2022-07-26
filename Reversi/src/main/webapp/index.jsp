<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="styles/styles.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<title> Reversi Game</title>
</head>
<body>
<div class="container">
<h1> Reversi </h1>
  <form action="<c:url value='moveDisk'/>" method="post">
  	<div class="row">
  	<div class="col-xs-6">
  		<button class="btn btn-primary" type="submit" name="quit" >New Game</button>
  	</div>
  	<div class="col-xs-6">
  		<c:if test="${pass == true && !game.over}">
  			<button type="submit" class="btn btn-primary" name="pass">Pass Move</button>
  		</c:if>    	
	   	<c:if test="${game.over}">
	   		<p>Winner: ${(game.winner == null) ? "TIE" : game.winner}</p>
	  	</c:if>
   	</div>
  	</div>
  	<br />
	<div class="game">
  	<table class="table game-table">
  		<thead>
  		<tr>
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
  			</td>
  		<c:if test="${stat.count % 8 == 0 }">
  		</tr>
  		</c:if>
  		</c:forEach>
    </table>
	</div>
    </form>
    <br />
    <div class="scoreboard">
	    <div class="row">
			<div class="col-xs-6">
				<p>${game.turnString}</p>
			</div>
			<div class="col-xs-6">
			    <p>${err}</p>
			</div>
	    </div>
	    <div class="row">
	    	<div class="col-xs-4">
	    		<p>Current Player:</p>
	    		<p>${game.currentPlayer}</p>
	    	</div>
	    	<div class="col-xs-4">
	    		<p>Current Score:</p>
	    		<p>LIGHT ${game.score[0]} - DARK ${game.score[1]}</p>
	    	</div>
	    	<div class="col-xs-4">
	    		<p>Game Over:</p>
	    		<p>${game.over}</p>
	    	</div>
	    </div>
    </div>
</div>
</body>
</html>
