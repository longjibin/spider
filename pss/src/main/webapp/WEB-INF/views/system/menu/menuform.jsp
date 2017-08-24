<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
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
					<li><a href="javascript:loadPage('menu/list');">菜单列表</a></li>
					<li class="active"><a href="javascript:loadPage('menu/form');">添加菜单</a></li>
				</ul>
				<div class="tab-content">
					<div class="active tab-pane">
						<form class="form-horizontal">
							<div class="form-group">
								<label for="inputName" class="col-sm-2 col-sm-offset-1 control-label">上级菜单</label>
								<div class="col-sm-6 input-group">
									<input id="parentName" name="parentName" type="email" class="form-control" id="inputName" disabled="disabled">
									<div class="input-group-addon">
										<a href="javascript:sb();"><i class="fa fa-plus"></i></a>
				                  	</div>
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-2 col-sm-offset-1 control-label">名称</label>
								<div class="col-sm-6 input-group">
									<input id="name" name="name" type="text" class="form-control" placeholder="菜单名">
								</div>
							</div>
							<div class="form-group">
								<label for="url" class="col-sm-2 col-sm-offset-1 control-label">链接</label>
								<div class="col-sm-6 input-group">
									<input id="url" name="url" type="text" class="form-control" placeholder="链接">
								</div>
							</div>
							<div class="form-group">
								<label for="icon" class="col-sm-2 col-sm-offset-1 control-label">图标</label>
								<div class="col-sm-6 input-group">
									<input id="icon" name="icon" type="text" class="form-control" placeholder="图标">
								</div>
							</div>
							<div class="form-group">
								<label for="sort" class="col-sm-2 col-sm-offset-1 control-label">排序</label>
								<div class="col-sm-6 input-group">
									<input id="sort" name="sort" type="number" class="form-control" placeholder="序号">
								</div>
							</div>
							<div class="form-group">
								<label for="permissions" class="col-sm-2 col-sm-offset-1 control-label">权限标识</label>
								<div class="col-sm-6 input-group">
									<input id="permissions" name="permissions" type="text" class="form-control" placeholder="权限标识">
								</div>
							</div>
							<div class="form-group">
								<label for="visible" class="col-sm-2 col-sm-offset-1 control-label">可见</label>
								<div class="col-sm-6 input-group">
					                  <input type="radio" name="visible" class="minimal" checked> 可见&nbsp;&nbsp;&nbsp;&nbsp;
					                  <input type="radio" name="visible" class="minimal"> 隐藏
								</div>
							</div>
							<div class="form-group">
								<label for="type" class="col-sm-2 col-sm-offset-1 control-label">菜单类型</label>
								<div class="col-sm-6 input-group">
					                  <input type="radio" name="type" class="minimal" checked> 普通&nbsp;&nbsp;&nbsp;&nbsp;
					                  <input type="radio" name="type" class="minimal"> 系统
								</div>
							</div>
							<div class="form-group">
								<label for="inputExperience" class="col-sm-2 col-sm-offset-1 control-label">备注</label>
								<div class="col-sm-6 input-group">
									<textarea class="form-control" id="inputExperience" placeholder="Experience"></textarea>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-9 input-group">
									<button type="submit" class="btn btn-danger">保 存</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<script>
//iCheck for checkbox and radio inputs
$('input[type="radio"].minimal').iCheck({
  checkboxClass: 'icheckbox_minimal-blue',
  radioClass: 'iradio_minimal-blue'
});

function sb(){
	layer.open({
		type: 2,
		title: 'layer mobile页',
		shadeClose: true,
		shade: 0.8,
		area: ['380px', '90%'],
		content: '${ctxAdmin}/menu/list'
	});
}
</script>
