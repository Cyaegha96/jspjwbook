<%@page import="org.apache.tomcat.util.buf.StringCache"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp 연습</title>
</head>
<body>
	<h3>
	1. JSP 주석
	<!-- HTML 주석: 화면에서 안보이지만 소스 보기에서 보인다. -->
	<%-- jsp 주석 : 화면과 소스보기 둘 다 보이지 않는다. --%>
	</h3>
	
	<%!
	String[] members = {"김길동", "홍길동", "김사랑", "박사랑" } ;
	int num1 = 10;
	int calc(int num2){
		return num1 + num2;
	}
	%>
	
	<h3>
	2. calc(10)의 실행결과:
	<%= calc(10) %>
	</h3>
	
	<h3>
	3. inclue: hello.jsp
	</h3>
	<%@ include file="../hello.jsp" %>
	
	<h3>
	4. 스크립트(배열데이터 출력)
	</h3>
	<ul>
		<%
			for (String name: members){
		%>
		<li><%=name %></li>
		<%
			}
		%>
	</ul>
	
</body>
</html>