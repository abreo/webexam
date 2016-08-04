<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>分类列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {background: rgb(254,238,189);}
	table {font-family: 宋体; font-size: 11pt; border-color: rgb(78,78,78);  width: 60%;}
	#th {background: rgb(78,78,78);}
</style>
  </head>
  
  <body>
    <h2 style="text-align: center;">film列表</h2>
    <table align="center" border="1" cellpadding="0" cellspacing="0">
    	<tr id="th" bordercolor="rgb(78,78,78)">
    		<th>film_id</th>
    	    <th>title</th>
    	    <th>description</th>
    	    <th>language</th>
    		<th>option</th>
    		<th>option</th>
    	</tr>
    <c:forEach items="${filmList}" var="f">
    	<tr bordercolor="rgb(78,78,78)">
    		<td>${f.film_id}</td>
    		<td>${f.title}</td>
    		<td>${f.description}</td>    		
    		<td>${f.name}</td>    		
    		<td>
    		  <a href="<c:url value='/FilmServlet?method=editerFilm&fid=${f.film_id}'/>">编辑</a>
    		</td> 
    		<td> 
    		  <a onclick="return confirm('你真的要删除吗？')" href="<c:url value='/FilmServlet?method=deleteFilm&fid=${f.film_id}'/>">删除</a>
    		</td>
    	</tr>
   </c:forEach>
    </table>
  </body>
</html>
 