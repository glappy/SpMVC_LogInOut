<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width", initial-scale=1">
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	.drag_area{
		width:70%;
		height:200px;
		border:1px solid blue;
		margin:0 auto;
		text-align:center;
	}
	.drag_area:hover{
		cursor:pointer;
		background-color:#ccc;
	}
	#img-containger{
		display:flex;
	}
	#images{
		flex:0 0 auto;
		margin:5px auto;
		display:flex;
		aligne-items:center;
		justify-content:center;
		border:1px solid blue;
	}
	.img-box{
		flex:0 0 100px;
		height:100px;
		margin:10px;
		border-radius:6px;
	}
	h2{
		text-align:center;
	}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(function(){
	$(".drag_area").on("dragover",function(e){
		$('h3').text('파일을 내려놓으세요')
		return false
	})
	$(".drag_area").on("drop",function(e){
		$('h3').text('파일 등록하는 중')
		let files=e.originalEvent.dataTransfer.files
		let fData=new FormData();
		for(let i = 0;i < files.length;i++){
			fData.append('files',files[i])
		}
		$.ajax({
			url:"<c:url value='/file/files'/>",
			method:"POST",
			data:fData,
			dataType:"JSON",
			processData:false,
			contentType:false,
			success:function(result){
				if(result==null){
					$("h3").text('파일업로드오류')
				}else{
					for(let i=0;i<result.length;i++){
						$("#images").append(
								$("<img/>",{
									src:"<c:url value='/files/'/>"+result[i]
									class:'img-box'
								})
						)
					}
					$('h3').text(파일업로드성공)
				}
			}
			,error:function(){
				alert('서버와통신오류')
			}
		})
		return false;
	})
})
</script>
</head>
<body>
	<h2>다중 파일 Drag and Drop</h2>
		<article>
			<div class="drag_area">
			<h3>업로드할 파일 드래그</h3>
			</div>
		</article>
		<article id="img-container">
			<div id="images"></div>
		</article>
</body>
</html>