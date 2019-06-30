<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>id : ${lgoin_info.m_userid }
<p>name : ${lgoin_info.m_name }
<p>tel : ${lgoin_info.m_tel }
<p><a href="<c:url value='/logout'/>">로그아웃</a>
</body>
</html>