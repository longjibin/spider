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
					<li><a href="javascript:loadPage('menu/form');">新增菜单</a></li>
				</ul>
				<div class="tab-content">
					<div class="active tab-pane">
						<div class="box box-info">
							<!-- /.box-header -->
							<div class="box-body no-padding">
								<table id="tabletree" class="table">
									<tbody>
										<tr>
											<th>name</th>
										</tr>
										<c:forEach items="${list }" var="menu">
											<tr class="treegrid-${menu.id } <c:if test="${menu.id ne 1 }">treegrid-parent-${menu.pId }</c:if>">
												<td>${menu.name }</td>
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
