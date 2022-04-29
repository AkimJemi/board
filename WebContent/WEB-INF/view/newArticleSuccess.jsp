<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<br>
${ ctxPath = pageContext.request.contextPath }
<a href="${ctxPath}/article/list.do">[ 게시글 목록 보기 ]</a>
<a href="${ctxPath}/article/read.do?no=${newArticleNo}">[ 게시글 내용보기 ]</a>
</body>
</html>