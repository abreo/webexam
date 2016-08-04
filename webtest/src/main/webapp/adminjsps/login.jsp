<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>管理员登录页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<h1>管理员登录页面</h1>
	<hr />
	<p style="color: red; font-weight: 900">${msg }</p>
	<form action="<c:url value='/UserServlet'/>" method="post"
		target="_top">
		<input type="hidden" name="method" value="login" /> 
			<table>
			<tr>
				<td colspan="2" class="tdWhiteLine"></td>
			</tr>
			<tr>
				<td colspan="2" class="tdWhiteLine"></td>
			</tr>
			<tr>
				<td class="tdFormLabel" width="40%">用户名：</td>
				<td class="tdFormControl">
					<input type="text" name="username" value="${form.username}" />
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel">密码:</td>
				<td class="tdFormControl">
					<input type="password" name="password" value="${form.password}" />
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel"></td>
				<td class="tdFormControl"><input type="submit" value="登录" /></td>
			</tr>
		</table>
			
	</form>
</body>
</html>
