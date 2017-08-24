<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Log in</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<%@ include file="/WEB-INF/common/head.jsp"%>
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
			
			$('#signupForm').validate( {
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
