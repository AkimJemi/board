<%@page import="jdbc.connection.ConnectionProvider"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%-- 
<c:if test="${errors.name}">이름을 입력하세요 </c:if> --%>
<body>
<%
try (Connection conn = ConnectionProvider.getConnection()) {
	out.println("커넥션 연결 성공함");
} catch (SQLException ex) {
	out.println("커넥션 연결 실패 : " + ex.getMessage());
	application.log("커넥션 연걸 실패", ex);
}
%>
</body>
</html>