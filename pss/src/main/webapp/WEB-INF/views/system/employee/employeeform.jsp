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
						<form id="modelForm" action="${ctxAdmin }/employee/save" method="post" class="form-horizontal">
							<input name="id" type="hidden" value="${employee.id }">
							<c:if test="${not empty employee.id}">
								<div class="form-group">
									<label for="jobNo" class="col-sm-2 col-sm-offset-1 control-label">工号</label>
									<div class="col-sm-6 input-group">
										<input id="jobNo" name="jobNo" type="text" value="${employee.jobNo }" class="form-control" placeholder="工号" readonly="readonly">
									</div>
								</div>
							</c:if>
							<div class="form-group">
								<label for="loginName" class="col-sm-2 col-sm-offset-1 control-label">登陆账号</label>
								<div class="col-sm-6 input-group">
									<input id="loginName" name="loginName" type="text" value="${employee.loginName }" class="form-control" placeholder="登陆账号" <c:if test="${not empty employee.id}">readonly="readonly"</c:if>>
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
							<c:if test="${employee.loginName ne fns:getConfig('adminAccount') }">
								<div class="form-group">
									<label for="idCardNo" class="col-sm-2 col-sm-offset-1 control-label">身份证号</label>
									<div class="col-sm-6 input-group">
										<input id="idCardNo" name="idCardNo" type="text" value="${employee.idCardNo }" class="form-control" placeholder="身份证号">
									</div>
								</div>
								<div class="form-group">
					                <label class="col-sm-2 col-sm-offset-1 control-label">入职时间</label>
					                <div class="col-sm-6 input-group date">
					                  	<div class="input-group-addon">
					                    	<i class="fa fa-calendar"></i>
					                  	</div>
					                  	<input id="entryTime" name="entryTime" type="text" class="form-control pull-right datepicker">
					                </div>
				              	</div>
				              	<shiro:hasPermission name="system:employee:disable">
									<div class="form-group">
						                <label for="status" class="col-sm-2 col-sm-offset-1 control-label">状态</label>
						                <div class="col-sm-6 input-group">
							                <select id="status" name="status" class="form-control" onchange="toggle(this);">
							                  	<option value="1" <c:if test="${employee.status eq 1 }">selected="selected"</c:if>>启用</option>
							                  	<option value="2" <c:if test="${employee.status eq 2 }">selected="selected"</c:if>>禁用</option>
							                  	<option value="3" <c:if test="${employee.status eq 3 }">selected="selected"</c:if>>离职</option>
							                </select>
						                </div>
					              	</div>
								</shiro:hasPermission>
				              	<script type="text/javascript">
									var status=${employee.status };
									if(status!=3){
										$('#quitTimeDiv').hide();
									}
								</script>
								<div id="quitTimeDiv" class="form-group">
					                <label for="quitTime" class="col-sm-2 col-sm-offset-1 control-label">离职时间</label>
					                <div class="col-sm-6 input-group date">
					                  	<div class="input-group-addon">
					                    	<i class="fa fa-calendar"></i>
					                  	</div>
					                  	<input id="quitTime" name="quitTime" type="text" class="form-control pull-right datepicker">
					                </div>
				              	</div>
							</c:if>
							<div class="form-group">
				                <label class="col-sm-2 col-sm-offset-1 control-label">角色分配</label>
				                <div class="col-sm-6 input-group">
				                	<select name="roleIds" class="form-control select2" multiple="multiple" data-placeholder="Select a State" style="width: 100%;" <c:if test="${employee.loginName eq fns:getConfig('adminAccount') }">disabled="disabled"</c:if>>
				                  		<c:forEach items="${roles }" var="role">
				                  			<option value="${role.id }" <c:if test="${role.selected eq true }">selected="selected"</c:if> <c:if test="${role.id eq 1 and fns:getConfig('adminAccount') ne employee.loginName}">disabled="disabled"</c:if>>${role.name }</option>
				                  		</c:forEach>
				                	</select>
								</div>
			              	</div>
							<div class="form-group">
								<label for="remark" class="col-sm-2 col-sm-offset-1 control-label">备注</label>
								<div class="col-sm-6 input-group">
									<textarea id="remark" name="remark" class="form-control" placeholder="备注">${employee.remark }</textarea>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-9 input-group">
									<a class="btn btn-danger" onclick="save('employee/save','employee/list');">保 存</a>
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
$(function () {
	//Initialize Select2 Elements
    $(".select2").select2();
	
	//iCheck for checkbox and radio inputs
	$('input[type="radio"].minimal').iCheck({
		checkboxClass: 'icheckbox_minimal-blue',
		radioClass: 'iradio_minimal-blue'
	});

	//Date picker
	$('.datepicker').datepicker({
	  	autoclose: true,
	  	todayHighlight: true,
	    language:"zh-CN",
	    format:"yyyy-mm-dd"
	});
});

function toggle(obj) {
	if($(obj).val()==3){
		$('#quitTimeDiv').show();
	}else{
		$('#quitTimeDiv').hide();
	}
}
</script>
