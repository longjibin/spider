<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!-- Content Header (Page header) -->
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
					<li class="active"><a href="javascript:loadPage('employee/list');">员工列表</a></li>
					<li><a href="javascript:loadPage('employee/form');">新增员工</a></li>
				</ul>
				<div class="tab-content">
					<div class="active tab-pane">
						<div class="box box-info">
							<div class="box-header with-border">
				           		<h3 class="box-title">员工信息</h3>
				            </div>
							<!-- /.box-header -->
							<div class="box-body">
				              	<div class="row">
				              		<form action="#" method="post">
				              			<div class="col-xs-4">
					                		<label>工号</label>
					                  		<div class="input-group">
								            	<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
								                <input type="email" class="form-control" placeholder="工号">
							              	</div>
					                	</div>
						               	<div class="col-xs-4">
						                	<label>账号</label>
						                  	<div class="input-group">
								            	<span class="input-group-addon"><i class="fa fa-user"></i></span>
								                <input type="email" class="form-control" placeholder="账号">
							              	</div>
						                </div>
						                <div class="col-xs-4">
						                	<label>手机</label>
						                  	<div class="input-group">
								            	<span class="input-group-addon"><i class="fa fa-mobile"></i></span>
								                <input type="email" class="form-control" placeholder="手机">
							              	</div>
						                </div>
				              		</form>
				              	</div>
				              	<br>
				              	<div class="row">
			                		<div class="col-xs-4">
				                		<button type="button" class="btn btn-block btn-info btn-flat"> 查 询 </button>
					                </div>
				              	</div>
				            </div>
							<!-- /.box-body -->
						</div>
						
						<div class="box box-info">
				            <!-- /.box-header -->
				            <div class="box-body">
				            	<table id="tabletree" class="table table-hover">
									<tbody>
										<tr>
											<th width="50px;">序号</th>
											<th>头像</th>
											<th>工号</th>
											<th>登录账号</th>
											<th>昵称</th>
											<th>登录账号</th>
											<th>手机号</th>
											<th>状态</th>
											<th>操作</th>
										</tr>
										<c:forEach items="${list }" var="employee" varStatus="s">
											<tr>
												<td>${s.count }</td>
												<td>${employee.headPic }</td>
												<td>${employee.jobNo }</td>
												<td>${employee.loginName }</td>
												<td>${employee.nickName }</td>
												<td>${employee.loginName }</td>
												<td>${employee.phone }</td>
												<td>${employee.status }</td>
												<td width="150px;">
													<a href="javascript:loadPage('employee/form?id=${employee.id }');"><i class="fa fa-edit"></i> 修 改</a>&nbsp;&nbsp;&nbsp;&nbsp;
													<a href="javascript:remove('employee/remove?id=${employee.id }','employee/list');"><i class="fa fa-times"></i> 删 除</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
				            </div>
				            <!-- /.box-body -->
				            <div class="box-footer clearfix">
				              	<ul class="pagination pagination-sm no-margin pull-right">
				                	<li><a href="#">«</a></li>
				                	<li><a href="#">1</a></li>
				               	 	<li><a href="#">2</a></li>
				                	<li><a href="#">3</a></li>
				                	<li><a href="#">»</a></li>
				              	</ul>
				            </div>
				    	</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
