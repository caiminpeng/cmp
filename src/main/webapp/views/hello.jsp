<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="<c:url value='/views/jquery-1.11.1.min.js'/>"></script>
<script type="text/javascript">
function onc(){
	var a=$("#hh").val();
	alert(a);
}

</script>

<body>
<button onclick="onc()">添加</button>
    <h1>Hello World!</h1>
    <input type="text" id="hh" value="123"> 
</body>
</html>