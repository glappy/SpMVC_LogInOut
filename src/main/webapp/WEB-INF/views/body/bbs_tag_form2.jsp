<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>

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
		font-weihgt:bold;
	}
	.userid_label{
		display:none;
	}
	.in-box-error{
		display:inline-block;
		margin-left:20px;
		font-size:12pt;
		color:red;
	}
	.in-file-box{
		border:2px solid blue;
	}
	.in-file-box h3{
		text-align:center;
	}
</style>

<form:form enctype="multipart/form-data" action="${rootPath }/bbs/wrtie_tag" 
			modelAttribute="bbsVO">
	<fieldset>
		<legend>게시판</legend>
			<label class="in-label">회원ID</label>
				<div class="in-box-border">
					<form:input class="in-box" readonly="true" palceholder="회원ID를 입력하세요" 
								id="b_userid" path="b_userid"/><br/>
					<form:errors path="b_userid" class="in-box-error"/>
				</div>
				
			<label class="in-label" for="b_date">작성일자</label>
				<div class="in-box-border">
					<form:input class="in-box" id="b_date" path="b_date"/><br/>
					<form:errors path="b_date" class="in-box-error"/>
				</div>

			<label class="in-label" for="b_time">작성시각</label>
				<div class="in-box-border">
					<form:input class="in-box" id="b_time" path="b_time"/><br/>
					<form:errors path="b_time" class="in-box-order"/>
				</div>

			<label class="in-label" for="b_title">제목</label>
				<div class="in-box-border">
					<form:input class="in-box" id="b_title" path="b_title"/><br/>
					<form:errors path="b_title"/>
				</div>

			<label class="in-label" for="b_content">내용</label>
				<div class="in-box-border">
					<form:textarea type="text" class="in-box" id="b_content" 
									path="b_content" rows="5"/><br/>
					<form:errors path="b_content" class="in-box-order"/>
				</div>

			<label class="in-label"></label>
				<input type="file" id="b_file" name="b_file">
				<hr/>

			<label class="in-label"></label>
				<button id="btn-join-1" type="submit">저장</button>
	</fieldset>
</form:form>