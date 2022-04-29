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
	<form method="post" action="modify.do?no=${modReq.articleNumber}">
		<p>
			제목: <input type="text" name="title" value="${modReq.title}" />
			<c:if test="${errors.title }">제목을 입력하세요.</c:if>
		</p>
		<p>
			내용 :
			<textarea name="content" rows="5" cols="30">${modReq.content}</textarea>
		</p>
		<input type="submit" value="글 수정" />

	</form>
</body>
</html>