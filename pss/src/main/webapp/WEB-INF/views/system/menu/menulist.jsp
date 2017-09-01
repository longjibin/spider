<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<script>
$(document).ready(function() {
    $('#tabletree').treegrid();
});
</script>
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
					<li class="active"><a href="javascript:loadPage('menu/list');">菜单列表</a></li>
					<shiro:hasPermission name="system:menu:edit">
						<li><a href="javascript:loadPage('menu/form');">新增菜单</a></li>
					</shiro:hasPermission>
				</ul>
				<div class="tab-content">
					<div class="active tab-pane">
						<div class="box box-info">
							<!-- /.box-header -->
							<div class="box-body no-padding">
								<table id="tabletree" class="table table-hover">
									<tbody>
										<tr>
											<th>菜单名</th>
											<th>类型</th>
											<th>序号</th>
											<th>链接</th>
											<th>权限</th>
											<th>可见</th>
											<shiro:hasPermission name="system:menu:edit">
												<th>操作</th>
											</shiro:hasPermission>
											<!-- <th>操作</th> -->
										</tr>
										<c:forEach items="${list }" var="menu">
											<tr class="treegrid-${menu.id } <c:if test="${menu.pId ne 1 }">treegrid-parent-${menu.pId }</c:if>">
												<td><i class="<c:choose><c:when test="${menu.icon ne ''}">${menu.icon }</c:when><c:otherwise>fa fa-circle-o</c:otherwise></c:choose>"></i> ${menu.name }</td>
												<td>${menu.type eq 2 ? '系统菜单' : '普通菜单' }  </td>
												<td>${menu.sort }  </td>
												<td>${menu.url }  </td>
												<td>${menu.permissions }  </td>
												<td>${menu.visible eq 1 ? '显示' : '隐藏' }  </td>
												<shiro:hasPermission name="system:menu:edit">
													<td width="150px;">
														<a href="javascript:loadPage('menu/form?id=${menu.id }');"><i class="fa fa-edit"></i> 修 改</a>&nbsp;&nbsp;&nbsp;&nbsp;
														<a href="javascript:remove('menu/remove?id=${menu.id }','menu/list');"><i class="fa fa-times"></i> 删 除</a>
													</td>
												</shiro:hasPermission>
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
