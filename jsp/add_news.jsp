<%@ page language="java" contentType="text/html; charset=utf-8"
	import="by.htp.les.bean.UserLoginationInfo, java.util.ArrayList,java.util.List,by.htp.les.bean.News"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<fmt:setLocale value="${sessionScope.local }" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.title" var="title" />
<fmt:message bundle="${loc}" key="local.welcome" var="welcome" />
<fmt:message bundle="${loc}" key="local.admin" var="admin" />
<fmt:message bundle="${loc}" key="local.logout" var="logout" />
<fmt:message bundle="${loc}" key="local.add.news" var="add_news" />
<fmt:message bundle="${loc}" key="local.add.news.title" var="news_title" />
<fmt:message bundle="${loc}" key="local.brief" var="brief" />
<fmt:message bundle="${loc}" key="local.content" var="content" />
<fmt:message bundle="${loc}" key="local.date" var="date" />
<fmt:message bundle="${loc}" key="local.date.show" var="date_show" />
<fmt:message bundle="${loc}" key="local.date.format" var="date_format" />
<fmt:message bundle="${loc}" key="local.save" var="save" />
<fmt:message bundle="${loc}" key="local.cancel" var="cancel" />


<title>add news</title>
<style>
body {
	background: #1abc9c;
}

* {
	box-sizing: border-box;
}

.column {
	float: left;
	padding: 10px;
	height: 600px;
}

.left {
	width: 75%;
}

.right {
	width: 25%;
}

.row:after {
	content: "";
	display: table;
	clear: both;
}

input {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type=submit] {
	width: 100%;
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=submit]:hover {
	background-color: #45a049;
}

div.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 40px;
}
</style>
</head>
<body>
	 <div style="font-family: Segoe Script, sans-serif; text-align: center; color: white;">

		<h1>
			<big><big><strong><c:out value="${title}" /> </strong></big></big>
		</h1>
	
	</div>
	<div>
	  
	<c:url var = "ru" value="/local">
						<c:param name="localpage" value="goToAddNewsPage" />
						<c:param name="lang" value="ru" />
						
					</c:url>
					
					<c:url var = "en" value="/local">
						<c:param name="localpage" value="goToAddNewsPage" />
						<c:param name="lang" value="en" />
						
					</c:url>
					<c:url var = "back" value="/goToMainPage">
						
					</c:url>
					<a href="${back}" style="margin-left: 20px;"><img src="resources/back.png" /></a>
					<a href="${ru}" style="float: right; margin-right: 40px;"><img src="resources/ru.png" /></a>
					<a href="${en}" style="float: right; margin-right: 20px;"><img src="resources/eng.png" /></a>
	
	
	
	</div>	
	<br />
	<div class="column left"
		style="background-color: rgba(240, 240, 240, 1)">


		<div class="container">
			<h3><c:out value="${add_news}"/></h3>
		
			<form:form action="saveNews" modelAttribute="news" method="POST">
		<table>
			<tr>
				<td>${news_title}</td>
				<td><form:input path="title" /></td>
				<td><form:errors path="title" cssClass="error" /></td>
			</tr>
			<tr>
				<td>${brief}</td>
				<td><form:input path="brief" /></td>
				<td><form:errors path="brief" cssClass="error" /></td>
			</tr>
			<tr>
				<td>${content}</td>
				<td><form:input path="content" /></td>
				<td><form:errors path="content" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td>${date}</td>
				<td><form:input path="data" placeholder="dd/MM/yyyy"/></td>
				<td><form:errors path="data" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td colspan="3"><input type="submit" value="${save}" class="save" ></td>
			</tr>
		</table>

	</form:form>
	
			</font>
		</div>
	</div>
	<div class="column right" style="background-color: rgb(220, 220, 220);">
		<div class="container">
			<c:if test="${(sessionScope.role eq \"admin\")}">
				<c:if test="${message != null}">
					<h1 align="center">${welcome}</h1>
					<h1 align="center">	${admin}
						<c:out value="${message}" />
					</h1>
				</c:if>
			</c:if>
			<c:if test="${(sessionScope.role eq \"user\")}">
				<c:if test="${message != null}">
					<h1 align="center">${welcome}</h1>
					<h1 align="center">
						<c:out value="${message}" />
					</h1>
				</c:if>
			</c:if>
			<br />
			<c:url var = "log" value="/logout">	</c:url> 
			<a href="${log}">${logout}</a>
		</div>
	</div>





</body>
</html>
