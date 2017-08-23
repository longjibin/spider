<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>AdminLTE 2 | User Profile</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="${ctx }/static/plugins/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="${ctx }/static/plugins/awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="${ctx }/static/plugins/ionicons/css/ionicons.min.css">

<!-- Theme style -->
<link rel="stylesheet" href="${ctx }/static/plugins/adminlte/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="${ctx }/static/plugins/adminlte/css/skins/_all-skins.min.css">

<!-- jQuery 2.2.3 -->
<script src="${ctx }/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${ctx }/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="${ctx }/static/plugins/fastclick/fastclick.js"></script>
</head>
<body>
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>菜单管理</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
			<li><a href="#">系统设置</a></li>
			<li class="active">菜单管理</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">

		<div class="row">
			<div class="col-md-12">
				<div class="nav-tabs-custom">
					<ul class="nav nav-tabs">
						<li class="active"><a href="${ctx }/admin/menu/list">菜单列表</a></li>
						<li><a href="${ctx }/admin/menu/form">新增菜单</a></li>
					</ul>
					<div class="tab-content">
						<div class="active tab-pane" id="activity">
							菜单列表
						</div>
					</div>
				</div>
			</div>
		</div>

	</section>

	<!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
	<div class="control-sidebar-bg"></div>
</body>
</html>
