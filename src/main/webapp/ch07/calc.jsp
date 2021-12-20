<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="calc" class="ch07.Calculator" scope="application">
	<jsp:setProperty property="*" name="calc"/>
</jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계산기 - useBean 결과</title>
</head>
<body>
	<h2>계산결과-useBean</h2>
	<hr>
	결과: <%=calc.calc() %>
</body>
</html>