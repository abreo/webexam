<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加分类</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body {
	background: rgb(254, 238, 189);
}
</style>
</head>

<body>
	<h1>添加film</h1>
	<p style="font-weight: 900; color: red">${msg }</p>
	<form action="<c:url value='/FilmServlet?method=addFilm'/>"
		method="post">
		title：    &nbsp;<input style="width: 150px; height: 20px;" type="text"
			name="title" /><br /> 
	    description：<input
			style="width: 223px; height: 20px;" type="text" name="description" /><br />
		<select style="width: 150px; height: 20px;" name="language_id">
			<c:forEach items="${languageList}" var="l">
				<option value="${l.language_id}">${l.name}</option>
			</c:forEach>
		</select> <br /> <input type="submit" value="添加film" />
	</form>
</body>
</html>
