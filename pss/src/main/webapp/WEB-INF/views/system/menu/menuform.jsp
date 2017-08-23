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
<!-- bootstrap datepicker -->
<link rel="stylesheet" href="${ctx }/static/plugins/datepicker/datepicker3.css">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet" href="${ctx }/static/plugins/iCheck/all.css">
<!-- Bootstrap time Picker -->
<link rel="stylesheet" href="${ctx }/static/plugins/timepicker/bootstrap-timepicker.min.css">
<!-- Select2 -->
<link rel="stylesheet" href="${ctx }/static/plugins/select2/select2.min.css">

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
<!-- Select2 -->
<script src="${ctx }/static/plugins/select2/select2.full.min.js"></script>
<!-- bootstrap datepicker -->
<script src="${ctx }/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- iCheck -->
<script src="${ctx }/static/plugins/iCheck/icheck.min.js"></script>
<!-- AdminLTE App -->
<script src="${ctx }/static/plugins/adminlte/js/app.min.js"></script>
<script>
$(function() {
	//Initialize Select2 Elements
	$(".select2").select2();

	//Date picker
	$('#datepicker').datepicker({
		autoclose : true
	});

	//iCheck for checkbox and radio inputs
	$('input[type="checkbox"].minimal, input[type="radio"].minimal')
			.iCheck({
				checkboxClass : 'icheckbox_minimal-blue',
				radioClass : 'iradio_minimal-blue'
			});
	//Red color scheme for iCheck
	$('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
				checkboxClass : 'icheckbox_minimal-red',
				radioClass : 'iradio_minimal-red'
			});
	
	//Flat red color scheme for iCheck
    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
      checkboxClass: 'icheckbox_flat-green',
      radioClass: 'iradio_flat-green'
    });

});
</script>
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
							<!-- SELECT2 EXAMPLE -->
							<!-- <div class="box box-default">
								<div class="box-header with-border">
									<h3 class="box-title">Select2</h3>
					
									<div class="box-tools pull-right">
										<button type="button" class="btn btn-box-tool"
											data-widget="collapse">
											<i class="fa fa-minus"></i>
										</button>
										<button type="button" class="btn btn-box-tool"
											data-widget="remove">
											<i class="fa fa-remove"></i>
										</button>
									</div>
								</div>
								/.box-header
								<div class="box-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label>Minimal</label> <select class="form-control select2"
													style="width: 100%;">
													<option selected="selected">Alabama</option>
													<option>Alaska</option>
													<option>California</option>
													<option>Delaware</option>
													<option>Tennessee</option>
													<option>Texas</option>
													<option>Washington</option>
												</select>
											</div>
											/.form-group
											<div class="form-group">
												<label>Disabled</label> <select class="form-control select2"
													disabled="disabled" style="width: 100%;">
													<option selected="selected">Alabama</option>
													<option>Alaska</option>
													<option>California</option>
													<option>Delaware</option>
													<option>Tennessee</option>
													<option>Texas</option>
													<option>Washington</option>
												</select>
											</div>
											/.form-group
										</div>
										/.col
										<div class="col-md-6">
											<div class="form-group">
												<label>Multiple</label> <select class="form-control select2"
													multiple="multiple" data-placeholder="Select a State"
													style="width: 100%;">
													<option>Alabama</option>
													<option>Alaska</option>
													<option>California</option>
													<option>Delaware</option>
													<option>Tennessee</option>
													<option>Texas</option>
													<option>Washington</option>
												</select>
											</div>
											/.form-group
											<div class="form-group">
												<label>Disabled Result</label> <select
													class="form-control select2" style="width: 100%;">
													<option selected="selected">Alabama</option>
													<option>Alaska</option>
													<option disabled="disabled">California (disabled)</option>
													<option>Delaware</option>
													<option>Tennessee</option>
													<option>Texas</option>
													<option>Washington</option>
												</select>
											</div>
											/.form-group
										</div>
										/.col
									</div>
									/.row
								</div>
								/.box-body
								<div class="box-footer">
									Visit <a href="https://select2.github.io/">Select2
										documentation</a> for more examples and information about the plugin.
								</div>
							</div> -->
							<!-- /.box -->
					
							<div class="row">
								<!-- /.col (left) -->
								<div class="col-md-12">
									<div class="box box-primary">
										<div class="box-header">
											<h3 class="box-title">Date picker</h3>
										</div>
										<div class="box-body">
											<!-- Date -->
											<div class="form-group">
												<label>Date:</label>
					
												<div class="input-group date">
													<div class="input-group-addon">
														<i class="fa fa-calendar"></i>
													</div>
													<input type="text" class="form-control pull-right"
														id="datepicker">
												</div>
												<!-- /.input group -->
											</div>
											<!-- /.form group -->
					
										</div>
										<!-- /.box-body -->
									</div>
									<!-- /.box -->
					
									<!-- iCheck -->
									<div class="box box-success">
										<div class="box-header">
											<h3 class="box-title">iCheck - Checkbox &amp; Radio Inputs</h3>
										</div>
										<div class="box-body">
											<!-- Minimal style -->
					
											<!-- checkbox -->
											<div class="form-group">
												<label> <input type="checkbox" class="minimal" checked>
												</label> <label> <input type="checkbox" class="minimal">
												</label> <label> <input type="checkbox" class="minimal" disabled>
													Minimal skin checkbox
												</label>
											</div>
					
											<!-- radio -->
											<div class="form-group">
												<label> <input type="radio" name="r1" class="minimal"
													checked>
												</label> <label> <input type="radio" name="r1" class="minimal">
												</label> <label> <input type="radio" name="r1" class="minimal"
													disabled> Minimal skin radio
												</label>
											</div>
					
											<!-- Minimal red style -->
					
											<!-- checkbox -->
											<div class="form-group">
												<label> <input type="checkbox" class="minimal-red"
													checked>
												</label> <label> <input type="checkbox" class="minimal-red">
												</label> <label> <input type="checkbox" class="minimal-red"
													disabled> Minimal red skin checkbox
												</label>
											</div>
					
											<!-- radio -->
											<div class="form-group">
												<label> <input type="radio" name="r2"
													class="minimal-red" checked>
												</label> <label> <input type="radio" name="r2"
													class="minimal-red">
												</label> <label> <input type="radio" name="r2"
													class="minimal-red" disabled> Minimal red skin radio
												</label>
											</div>
					
											<!-- Minimal red style -->
					
											<!-- checkbox -->
											<div class="form-group">
												<label> <input type="checkbox" class="flat-red" checked>
												</label> <label> <input type="checkbox" class="flat-red">
												</label> <label> <input type="checkbox" class="flat-red"
													disabled> Flat green skin checkbox
												</label>
											</div>
					
											<!-- radio -->
											<div class="form-group">
												<label> <input type="radio" name="r3" class="flat-red"
													checked>
												</label> <label> <input type="radio" name="r3" class="flat-red">
												</label> <label> <input type="radio" name="r3" class="flat-red"
													disabled> Flat green skin radio
												</label>
											</div>
										</div>
										<!-- /.box-body -->
										<div class="box-footer">
											Many more skins available. <a href="http://fronteed.com/iCheck/">Documentation</a>
										</div>
									</div>
									<!-- /.box -->
								</div>
								<!-- /.col (right) -->
							</div>
							<!-- /.row -->
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
