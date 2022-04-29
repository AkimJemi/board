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

	<form method="post" action="login.do">
		<c:if test="${errors.idOrPwNotMatch }">	
아이디 암호가 일치하지 않습니다
</c:if>
		<p>
			아이디: <input type="text" name="id" value="${param.id }" />
			<c:if test="${errors.id }">ID를 입력하세요.</c:if>
		</p>

		<p>
			암호 : <input type="password" name="password"
				value="${param.password }" />
			<c:if test="${errors.password }">암호를 입력하세요.</c:if>
		</p>
		<input type="submit" value="로그인" />

	</form>

</body>
</html>