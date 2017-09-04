<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
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
					<li><a href="javascript:loadPage('role/list');">角色列表</a></li>
					<li class="active"><a href="javascript:loadPage('role/form?id=${role.id }');">${empty role.id?'新增':'修改' }角色</a></li>
				</ul>
				<div class="tab-content">
					<div class="active tab-pane">
						<form id="modelForm" class="form-horizontal">
							<input name="id" type="hidden" value="${role.id }">
							<div class="form-group">
								<label for="name" class="col-sm-2 col-sm-offset-1 control-label">名称</label>
								<div class="col-sm-6 input-group">
									<input id="name" name="name" type="text" value="${role.name }" class="form-control" placeholder="角色名">
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-2 col-sm-offset-1 control-label">权限设置</label>
								<div class="col-sm-6 input-group">
									<ul id="treeDemo" class="ztree"></ul>
									<input id="treeNodes" name="treeNodes" type="hidden" value="">
								</div>
							</div>
							<div class="form-group">
								<label for="remark" class="col-sm-2 col-sm-offset-1 control-label">备注</label>
								<div class="col-sm-6 input-group">
									<textarea id="remark" name="remark" class="form-control" placeholder="备注"></textarea>
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
var setting = {
	check: {
        enable: true,
        chkboxType : { "Y" : "p", "N" : "p" }
    },
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: 0
		}				
	}
};
 			
var zNodes=${treeNodes};

$(function(){
	var treeObj=$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	treeObj.expandAll(true);
});

/**
 * 获取当前选中的节点对象
 */
function getCheckedNodes() {
	var checkedIds=new Array();
	var treeObj=$.fn.zTree.getZTreeObj('treeDemo');
	var nodes=treeObj.getCheckedNodes(true);
	for (var i = 0; i < nodes.length; i++) {
		checkedIds.push(nodes[i].id);
	}
	$('#treeNodes').val(checkedIds);
}
</script>
