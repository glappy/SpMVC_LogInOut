<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>

<style>
	fieldset{
		width:70%;
		margin:20px auto;
	}
	legend{
		font-size:12pt;
		font-weight:bold;
		color:#3d65ff;
	}
	.in-label{
		display:inline-block;
		width:20%;
		float:left;
		text-align:right;
		margin-right:5px;
		padding:8px;
	}
	.in-box-border{
		display:inline-block;
		width:70%;
	}
	.in-box{
		padding:8px;
		margin:3px;
		display:inline-block;
		width:70%;
		border:1px solid #ccc;
	}
	.in-box:hover{
		background-color:#ddd;
		border:1px solid blue;
	}
	input:invalid{
		background-color:#ffdddd;
		border:2px solid red;
	}
	#userid_error{
		width:70%;
		color:red;
		font-weight:bold;
	}
	.userid_label{
		display:none;
	}
	.in-box-error{
		display:inline-block;
		margin-left:20px;
		font-size:12px;
		color:red;
	}
	.in-file-box{
		border:2px solid blue;
	}
	.in-file-box h3{
		text-align:center;
	}
</style>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 	<form enctype="multipart/form-data" action="${rootPath }/bbs/write_tag" method="POST">
 	
 	</form>
</body>
</html>