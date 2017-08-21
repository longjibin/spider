<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Log in</title>
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
<!-- iCheck -->
<link rel="stylesheet" href="${ctx }/static/plugins/iCheck/square/blue.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="JavaScript:void(0);"><b>Admin</b>LTE</a>
		</div>
		<div class="login-box-body">
			<form id="signupForm">
				<div class="form-group has-feedback">
					<input id="loginName" name="loginName" type="text" class="form-control" placeholder="账 号">
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input id="loginPass" name="loginPass" type="password" class="form-control" placeholder="密 码">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<div class="checkbox icheck">
						<label> <input type="checkbox"> 记 住 我 </label>
					</div>
				</div>
				<div class="form-group has-feedback">
					<button type="submit" class="btn btn-primary btn-block btn-flat">登 录</button>
				</div>
			</form>

			<a href="#">忘 记 密 码</a><br>

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<!-- jQuery 2.2.3 -->
	<script src="${ctx }/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="${ctx }/static/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script src="${ctx }/static/plugins/iCheck/icheck.min.js"></script>
	<!-- jquery-validation -->
	<script src="${ctx }/static/plugins/jquery-validation/jquery.validate.min.js"></script>
	<!-- layer -->
	<script src="${ctx }/static/plugins/layer/layer.js"></script>
	<script>
		$.validator.setDefaults({
			submitHandler: function () {
				var index;
				$.ajax({
				    url:'${ctx}/login',
				    type:'POST', //GET
				    async:true,    //或false,是否异步
				    data:{
				    	loginName:$('#loginName').val(),
				    	loginPass:$('#loginPass').val()
				    },
				    timeout:5000,    //超时时间
				    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
				    beforeSend:function(xhr){
				    	index = layer.load(1, {shade: [0.1,'#fff']});//0.1透明度的白色背景
				    },
				    success:function(data,textStatus,jqXHR){
				    	if(data.code==200){
				    		//跳转到主页面
					    	window.location.href='${ctx}/admin';
				    	}else{
				    		layer.msg(data.msg);
				    	}
				    },
				    error:function(xhr,textStatus){
				    	layer.msg(textStatus);
				    },
				    complete:function(){
				    	layer.close(index);
				    }
				})
			}
		});
		
		$(function() {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});
			
			$( "#signupForm" ).validate( {
				rules: {
					loginName: 'required',
					loginPass: 'required'
				},
				messages: {
					loginName: {
						required: "请输入账号"
					},
					loginPass: {
						required: "请输入密码"
					}
				},
				errorPlacement: function ( error, element ) {
				}
			} );
		});
	</script>
</body>
</html>
