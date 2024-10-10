<%@ page import="edu.du.sb_chap17.model.Article" %>
<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("euc-kr"); %>
<html>
<head><title>게시글 작성</title></head>
<body>
게시글을 등록했습니다.
<br/>
<a href="<c:url value='/list'/>">목록보기</a>
<a href="<c:url value='/read?articleId=${postedArticle.id}'/>">게시글 읽기</a>
</body>
</html>
