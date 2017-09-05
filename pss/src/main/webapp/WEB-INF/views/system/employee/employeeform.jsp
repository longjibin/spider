<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<section class="content-header">
	<h1>员工管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
		<li><a href="#">系统设置</a></li>
		<li class="active">员工管理</li>
	</ol>
</section>
<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="nav-tabs-custom">
				<ul class="nav nav-tabs">
					<li><a href="javascript:loadPage('employee/list','GET',null);">员工列表</a></li>
					<li class="active"><a href="javascript:loadPage('employee/form?id=${employee.id }','GET',null);">${empty employee.id?'新增':'修改' }员工</a></li>
				</ul>
				<div class="tab-content">
					<div class="active tab-pane">
						<form id="modelForm" class="form-horizontal">
							<input name="id" type="hidden" value="${employee.id }">
							<div class="form-group">
								<label for="jobNo" class="col-sm-2 col-sm-offset-1 control-label">工号</label>
								<div class="col-sm-6 input-group">
									<input id="jobNo" name="jobNo" type="text" value="${employee.jobNo }" class="form-control" placeholder="工号">
								</div>
							</div>
							<div class="form-group">
								<label for="loginName" class="col-sm-2 col-sm-offset-1 control-label">登陆账号</label>
								<div class="col-sm-6 input-group">
									<input id="loginName" name="loginName" type="text" value="${employee.loginName }" class="form-control" placeholder="登陆账号">
								</div>
							</div>
							<div class="form-group">
								<label for="loginPass" class="col-sm-2 col-sm-offset-1 control-label">登陆密码</label>
								<div class="col-sm-6 input-group">
									<input id="loginPass" name="loginPass" type="password" class="form-control" placeholder="登陆密码">
								</div>
							</div>
							<div class="form-group">
								<label for="againLoginPass" class="col-sm-2 col-sm-offset-1 control-label">确认密码</label>
								<div class="col-sm-6 input-group">
									<input id="againLoginPass" name="againLoginPass" type="password" class="form-control" placeholder="确认密码">
								</div>
							</div>
							<shiro:hasPermission name="system:employee:disable">
								<div class="form-group">
									<label for="visible" class="col-sm-2 col-sm-offset-1 control-label">启用/禁用</label>
									<div class="col-sm-6 input-group">
										<input type="radio" name="visible" class="minimal" value="1" <c:if test="${employee.status eq 1 }">checked</c:if>> 启用&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="visible" class="minimal" value="2" <c:if test="${employee.status eq 2 }">checked</c:if>> 禁用
									</div>
								</div>
							</shiro:hasPermission>
							<div class="form-group">
								<label for="remark" class="col-sm-2 col-sm-offset-1 control-label">备注</label>
								<div class="col-sm-6 input-group">
									<textarea id="remark" name="remark" class="form-control" placeholder="备注">${employee.remark }</textarea>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-9 input-group">
									<a class="btn btn-danger" onclick="getCheckedNodes();save('role/save','role/list');">保 存</a>
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
</script>
