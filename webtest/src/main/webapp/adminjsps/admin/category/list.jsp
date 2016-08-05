<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
body {
	background: rgb(254, 238, 189);
}

table {
	font-family: 宋体;
	font-size: 11pt;
	border-color: rgb(78, 78, 78);
	width: 60%;
}

#th {
	background: rgb(78, 78, 78);
}
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
		<c:forEach items="${pb.beanList}" var="f">
			<tr bordercolor="rgb(78,78,78)">
				<td>${f.film_id}</td>
				<td>${f.title}</td>
				<td>${f.description}</td>
				<td>${f.name}</td>
				<td><a
					href="<c:url value='/FilmServlet?method=editerFilm&fid=${f.film_id}'/>">编辑</a>
				</td>
				<td><a onclick="return confirm('你真的要删除吗？')"
					href="<c:url value='/FilmServlet?method=deleteFilm&fid=${f.film_id}'/>">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>



	<br />
	<!-- 给出分页相关的连接 -->
	<center>
		第${pb.pc}页/共${pb.tp}页 <a href="${pb.url}&pc=1">首页</a>
		<c:if test="${pb.pc>1}">
			<a href="${pb.url}&pc=${pb.pc-1}">上一页</a>
		</c:if>

		<c:choose>
			<%-- 如果总页数不大于10页，那么把所有的页数都显示出来 --%>
			<c:when test="${pb.tp<=10}">
				<c:set var="begin" value="1" />
				<c:set var="end" value="${pb.tp}" />
			</c:when>
			<c:otherwise>
				<%-- 当总页数大于10时--%>
				<c:set var="begin" value="${pb.pc-5}" />
				<c:set var="end" value="${pb.pc+4}" />
				<%-- 头溢出 --%>
				<c:if test="${begin<1}">
					<c:set var="begin" value="1" />
					<c:set var="end" value="10" />
				</c:if>
				<c:if test="${end>pb.tp}">
					<c:set var="begin" value="${pb.tp-9}" />
					<c:set var="end" value="${pb.tp}" />
				</c:if>
			</c:otherwise>
		</c:choose>

		<c:forEach var="i" begin="${begin}" end="${end}">
			<c:choose>
				<c:when test="${i eq pb.pc}">
          [${i}]
        </c:when>
				<c:otherwise>
					<a href="${pb.url}&pc=${i}">[${i}]</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>


		<c:if test="${pb.pc<pb.tp}">
			<a href="${pb.url}&pc=${pb.pc+1}">下一页</a>
		</c:if>
		<a href="${pb.url}&pc=${pb.tp}">尾页</a>
	</center>


</body>
</html>
