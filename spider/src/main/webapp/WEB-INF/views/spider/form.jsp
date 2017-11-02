<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!-- Bootstrap core CSS -->
<link href="${ctx }/static/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="${ctx }/static/jquery.min.js"></script>
<script src="${ctx }/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<form>
			<div class="form-group">
				<label for="url">URL</label> 
				<input type="text" class="form-control" id="url" placeholder="url">
			</div>
			<div class="form-group">
				<label for="threadNum">并发数</label>
				<select id="threadNum" class="form-control">
					<option>1</option>
				  	<option>2</option>
				  	<option>3</option>
				</select>
			</div>
		</form>
	</div>
</body>
</html>