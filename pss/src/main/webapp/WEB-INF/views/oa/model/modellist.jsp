<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>角色管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
		<li><a href="#">办公中心</a></li>
		<li><a href="#">模型管理</a></li>
		<li class="active">模型列表</li>
	</ol>
</section>
<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="nav-tabs-custom">
				<ul class="nav nav-tabs">
					<li class="active"><a href="javascript:loadPage('model/list','GET',null);">模型列表</a></li>
					<shiro:hasPermission name="oa:model:edit">
						<li><a href="javascript:loadPage('model/form','GET',null);">模型设计</a></li>
					</shiro:hasPermission>
				</ul>
				<div class="tab-content">
					<div class="active tab-pane">
						<div class="box box-info">
							<div class="box-body no-padding">
								<table id="tabletree" class="table table-hover">
									<tbody>
										<tr>
											<th width="50px;">序号</th>
											<th>模型id</th>
											<th>名称</th>
											<th>操作</th>
										</tr>
										<c:forEach items="${list }" var="model" varStatus="s">
											<tr>
												<td>${s.count }</td>
												<td>${model.id }</td>
												<td>${model.name }</td>
												<td width="150px;">
													<shiro:hasPermission name="oa:model:edit">
														<a href="${ctx }/modeler.html?modelId=${model.id}" target="_blank"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
													</shiro:hasPermission>
													<shiro:hasPermission name="oa:model:delete">
														<a href="javascript:remove('model/remove?modelId=${model.id }','model/list');"><i class="fa fa-edit"></i> 删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
													</shiro:hasPermission>
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
