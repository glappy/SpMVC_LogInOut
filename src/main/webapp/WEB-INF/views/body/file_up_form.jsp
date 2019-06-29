<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.drag_area{
	width:70%;
	height:200px;
	border:1px solid blue;
	margin 0 auto;
	text-align:center;
}
.drag_area:hover{
	cursor:pointer;
	background-color:#ccc;
}
</style>

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(function(){
	$(".drag_area").on("dragover",function(e){
		$('h3').text('파일을 내려놓으세요')
		return false
	})
	$('.drag_area').on('drop',function(e){
		$('h3').text('파일 등록 중')
		let files=e.oroginalEvent.dataTransfer.files
		let fData=new FormData();
		fData.append('file',files[0])
		
		$.ajax({
			url:"<c:url value='/file/file'/>",
			method:"POST",
			data:fData,
			processData:false,
			contentType:false,
			success:function(result){
				alert(result)
			}
			,error:function(){
				alert('서버와 통신 오류')
			}
		})
		return false;
	})
})
</script>
</head>

<body>
	<div>
		<p>
		<p>
		<div class="drag_area">
		<h3>파일업로드</h3>
		</div>
		<div id="image"></div>
	</div>
</body>
</html>