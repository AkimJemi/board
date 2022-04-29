<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="join.do">
		<p>
			아이디: <input type="text" name="id" value="${param.id }" />
			<c:if test="${errors.id }">ID를 입력하세요.</c:if>
			<c:if test="${errors.duplicateId }">이미 사용중인 아이디입니다.</c:if>
		</p>
		<p>
			이름 : <input type="text" name="name" value="${param.name }" />
			<c:if test="${errors.name }">이름를 입력하세요.</c:if>
		</p>
		<p>
			암호 : <input type="password" name="password"
				value="${param.password }" />
			<c:if test="${errors.password }">암호를 입력하세요.</c:if>
		</p>
		<p>
			확인: <input type="password" name="confirmPassword"
				value="${param.confirmPassword }" />
			<c:if test="${errors.confirmPassword }">확인를 입력하세요.</c:if>
			<c:if test="${errors.notMatch }">암호 불일치</c:if>
		</p>
		<input type="submit" value="가입" />
	</form>


</body>
</html>