<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
게시글 수정 완료
<br>
<input type="hidden" value=" [ ${ ctxPath = pageContext.request.contextPath } ]"/>
<a href="${ctxPath}/article/list.do">[ 게시글 목록 보기 ]</a>
<a href="${ctxPath}/article/read.do?no=${modReq.articleNumber}">[ 게시글 내용보기 ]</a>

</body>
</html>