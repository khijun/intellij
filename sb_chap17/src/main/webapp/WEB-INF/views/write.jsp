<%@ page import="edu.du.sb_chap17.model.Article" %>
<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("euc-kr"); %>
<html>
<head><title>�Խñ� �ۼ�</title></head>
<body>
�Խñ��� ����߽��ϴ�.
<br/>
<a href="<c:url value='/list'/>">��Ϻ���</a>
<a href="<c:url value='/read?articleId=${postedArticle.id}'/>">�Խñ� �б�</a>
</body>
</html>
