<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${param.name }님, 회원가입에 성공했습니다.

<input type="button" onClick="location.href= '<%=request.getContextPath()%>/index.jsp'" value="목록으로 돌아가기"/>
</body>
</html>