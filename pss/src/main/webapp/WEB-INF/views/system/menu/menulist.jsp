<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
					<div class="active tab-pane" id="activity">
						菜单列表
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
