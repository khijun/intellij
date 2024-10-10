<%@ page contentType="text/html; charset=euc-kr" %>
<% request.setCharacterEncoding("euc-kr"); %>
<jsp:setProperty name="deleteRequest" property="*" />
<%
	String viewPage = null;
	try {
		DeleteArticleService.getInstance().deleteArticle(deleteRequest);
		viewPage = "/delete_success.jsp";
	} catch (Exception ex) {
		request.setAttribute("deleteException", ex);
		viewPage = "/delete_error.jsp";
	}
%>
<jsp:forward page="<%= viewPage %>" />
