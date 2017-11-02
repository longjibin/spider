<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="${ctx }/static/favicon.ico">

<title>test</title>

<!-- Bootstrap core CSS -->
<link href="${ctx }/static/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${ctx }/static/jumbotron-narrow.css" rel="stylesheet">

<script src="${ctx }/static/jquery.min.js"></script>
<script src="${ctx }/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="${ctx }/static/layer-v3.1.0/layer.js"></script>
<script type="text/javascript">
$(function(){
	$('.list-group-item').click(function() {
		layer.open({
			type: 2,
			title: 'layer mobile页',
			shadeClose: false,
			shade: 0.8,
			area: ['380px', '50%'],
			content: '${ctx}/spider/form?spiderId='+$(this).attr('id')
		}); 
	});
});
</script>
</head>

<body>
	<div class="container">
		<div class="header clearfix">
			<h3 class="text-muted">Spider</h3>
		</div>

		<div class="row marketing">
			<div class="list-group">
				<c:forEach items="${spiders }" var="spider">
					<button id="${spider.id }" class="list-group-item">techweb</button>
				</c:forEach>
			</div>
		</div>
		
		<footer class="footer">
			<p>&copy; 2016 Company, Inc.</p>
		</footer>
	</div>
	<!-- /container -->
</body>
</html>
