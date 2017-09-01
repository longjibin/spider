<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>角色管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
		<li><a href="#">系统设置</a></li>
		<li class="active">角色管理</li>
	</ol>
</section>
<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="nav-tabs-custom">
				<ul class="nav nav-tabs">
					<li class="active"><a href="javascript:loadPage('role/list');">角色列表</a></li>
					<li><a href="javascript:loadPage('role/form');">新增角色</a></li>
				</ul>
				<div class="tab-content">
					<div class="active tab-pane">
						<div class="box box-info">
							<!-- /.box-header -->
							<div class="box-body no-padding">
								<table id="tabletree" class="table table-hover">
									<tbody>
										<tr>
											<th width="50px;">序号</th>
											<th>角色名</th>
											<th>操作</th>
										</tr>
										<c:forEach items="${list }" var="role" varStatus="s">
											<tr>
												<td>${s.count }</td>
												<td>${role.name }</td>
												<td width="150px;">
													<a href="javascript:loadPage('role/form?id=${role.id }');"><i class="fa fa-edit"></i> 修 改</a>&nbsp;&nbsp;&nbsp;&nbsp;
													<a href="javascript:remove('role/remove?id=${role.id }','role/list');"><i class="fa fa-times"></i> 删 除</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /.box-body -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
