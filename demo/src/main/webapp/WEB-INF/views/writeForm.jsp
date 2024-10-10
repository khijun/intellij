<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<br><p>

<table width="500" cellpadding="0" cellspacing="0" border="1">
    <c:choose>
        <c:when test="${dto.id == null}"><form action="write" method="post"></c:when>
        <c:otherwise>
            <form action="update" method="post">
            <input type="hidden" name = "id" value='${dto.id}'>
        </c:otherwise>
    </c:choose>

    <tr>
        <td > 작성자 </td>
        <td> <input type="text" name="writer" size = "100" value = "${dto.writer}"> </td>
    </tr>
    <tr>
        <td> 제목 </td>
        <td> <input type="text" name="title" size = "100" value = "${dto.title}"></td>
    </tr>
    <tr>
        <td> 내용 </td>
        <td> <input type="text" name="content" size = "100" value = "${dto.content}"> </td>
    </tr>
    <tr >
        <td colspan="2"> <input type="submit" value="입력">
              &nbsp;&nbsp; <a href="list">목록보기</a></td>
    </tr>
    </form>
</table>
</body>
</html>