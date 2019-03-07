<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>编辑</title>
</head>
<body>
    <c:if test="${!empty error}">
        <font color="red"><c:out value="${error}" /></font>
    </c:if>
    <form action="<c:url value="/sys/update"/>" method="post">
    <input type="hidden" name="id" value="${users.id }">
        用户名： <input type="text" name="name" value="${users.name }"> <br /> 密&nbsp;&nbsp;&nbsp;码： <input
            type="password" name="password" value="${users.password }"> <br /> <input type="submit"
            value="编辑"> <input type="reset" value="重置">
    </form>
   
</body>
</html>