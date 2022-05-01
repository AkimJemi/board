<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function autoInsert(num, url, pageNo){
		alert(pageNo);
		var url2 ='write.do?title=1&content=1&freepass=1&num='+num +'&pageNo='+pageNo;
	    let f = document.createElement('form');
	    f.setAttribute('method', 'post');
	    f.setAttribute('action', url2);
	    document.body.appendChild(f);
	    f.submit();
}

</script>
</head>
<body>
	<table border='1' width="700">
		<tr>
			<td colspan="4"><a href="write.do">[게시글 쓰기]</a><a href="<%=request.getContextPath()%>/index.jsp">[ 목록으로 ]</a>
			<a href="javascript:void(0)" onclick="autoInsert(5, '<%=request.getContextPath()%>', '${articlePage.currentPage }')">[ 자동 추가5 ]</a>
			<a href="javascript:void(0)"  onclick="autoInsert(10, '<%=request.getContextPath()%>', '${articlePage.currentPage }')">[ 자동 추가10 ]</a>
			<a href="javascript:void(0)" onclick="autoInsert(15, '<%=request.getContextPath()%>', '${articlePage.currentPage }')">[ 자동 추가15 ]</a>
		</tr>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>조회수</td>
		</tr>
		<c:if test="${articlePage.hasNoArticles() }">
			<tr>
				<td>${article.number }</td>
			</tr>
		</c:if>
		<c:forEach var="article" items="${articlePage.content }">
			<tr>
				<td><a
					href="read.do?no=${article.number }&pageNo=${articlePage.currentPage}">${article.number }
				</a></td>
				<td><c:out value="${article.title }" /></td>
				<td>${article.readCount }</td>
				<td>${article.writer.name }</td>
			</tr>
		</c:forEach>
		<c:if test="${articlePage.hasArticles()}">
			<tr>
				<td colspan="4"><c:if test="${articlePage.startPage>5 }">
						<a href="list.do?pageNo=${articlePage.startPage -5 }"> [이전]</a>
					</c:if> <c:forEach var="pNo" begin="${articlePage.startPage }"
						end="${articlePage.endPage }">
						<a href="list.do?pageNo=${ pNo}">[${pNo }]</a>
					</c:forEach> <c:if test="${articlePage.endPage <articlePage.totalPages }">
						<a href="list.do?pageNo=${articlePage.startPage +5 }">[다음]</a>
					</c:if></td>
			</tr>
		</c:if>
	</table>
</body>
</html>