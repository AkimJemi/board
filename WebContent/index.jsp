<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<u:isLogin>
${authUser.name }님 안녕하세요
<a href="logout.do">[로그아웃하기]</a>
		<a href="changePwd.do">[암호 변경하기]</a>
		<a href="article/list.do">[게시판 목록]</a>
	</u:isLogin>
	<u:notLogin>
		<a href="join.do">[회원가입하기]</a>
		<a href="login.do">[로그인하기]</a>
	</u:notLogin>
</body>
</html>