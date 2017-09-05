<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
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
					<li><a href="javascript:loadPage('menu/list','GET',null);">菜单列表</a></li>
					<shiro:hasPermission name="system:menu:edit">
						<li class="active"><a href="javascript:loadPage('menu/form?id=${menu.id }','GET',null);">${empty menu.id?'新增':'修改' }菜单</a></li>
					</shiro:hasPermission>
				</ul>
				<div class="tab-content">
					<div class="active tab-pane">
						<form id="modelForm" class="form-horizontal">
							<input name="id" type="hidden" value="${menu.id }">
							<div class="form-group">
								<label for="pName" class="col-sm-2 col-sm-offset-1 control-label">上级菜单</label>
								<div class="col-sm-6 input-group">
									<input id="pName" name="pName" type="text" value="${menu.parent.name }" class="form-control" readonly="readonly" onclick="menuTree();">
									<input id="pId" name="pId" type="hidden" value="${menu.parent.id }">
									<div class="input-group-addon" onclick="menuTree();">
										<i class="fa fa-plus"></i>
				                  	</div>
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-2 col-sm-offset-1 control-label">名称</label>
								<div class="col-sm-6 input-group">
									<input id="name" name="name" type="text" value="${menu.name }" class="form-control" placeholder="菜单名">
								</div>
							</div>
							<div class="form-group">
								<label for="url" class="col-sm-2 col-sm-offset-1 control-label">链接</label>
								<div class="col-sm-6 input-group">
									<input id="url" name="url" type="text" value="${menu.url }" class="form-control" placeholder="链接">
								</div>
							</div>
							<div class="form-group">
								<label for="icon" class="col-sm-2 col-sm-offset-1 control-label">图标</label>
								<div class="col-sm-6 input-group">
									<input id="icon" name="icon" type="text" value="${menu.icon }" class="form-control" placeholder="图标" readonly="readonly" onclick="menuIcons();">
									<div class="input-group-addon" onclick="menuIcons();">
										<i class="fa fa-star"></i>
				                  	</div>
								</div>
							</div>
							<div class="form-group">
								<label for="sort" class="col-sm-2 col-sm-offset-1 control-label">序号</label>
								<div class="col-sm-6 input-group">
									<input id="sort" name="sort" type="number" value="${menu.sort }" class="form-control" placeholder="序号">
								</div>
							</div>
							<div class="form-group">
								<label for="permissions" class="col-sm-2 col-sm-offset-1 control-label">权限标识</label>
								<div class="col-sm-6 input-group">
									<input id="permissions" name="permissions" type="text" value="${menu.permissions }" class="form-control" placeholder="权限标识">
								</div>
							</div>
							<div class="form-group">
								<label for="visible" class="col-sm-2 col-sm-offset-1 control-label">可见</label>
								<div class="col-sm-6 input-group">
									<input type="radio" name="visible" class="minimal" value="1" <c:if test="${menu.visible eq 1 }">checked</c:if>> 可见&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="visible" class="minimal" value="2" <c:if test="${menu.visible eq 2 }">checked</c:if>> 隐藏
								</div>
							</div>
							<div class="form-group">
								<label for="type" class="col-sm-2 col-sm-offset-1 control-label">菜单类型</label>
								<div class="col-sm-6 input-group">
					            	<input type="radio" name="type" class="minimal" value="1" <c:if test="${menu.type eq 1 }">checked</c:if>> 普通&nbsp;&nbsp;&nbsp;&nbsp;
					            	<input type="radio" name="type" class="minimal" value="2" <c:if test="${menu.type eq 2 }">checked</c:if>> 系统
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
									<a class="btn btn-danger" onclick="save('/menu/save','/menu/list');">保 存</a>
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

/**
 * 菜单树
 */
function menuTree(){
	layer.open({
		type: 2,
		title: '选择菜单',
		shadeClose: true,
		shade: 0.5,
		area: ['380px', '60%'],
		content: '${ctxAdmin}/menu/tree',
		btn: ['确定', '取消'],
	  	yes: function(index, layero){
	  		var iframeWin = window[layero.find('iframe')[0]['name']];
	  		var node=iframeWin.getSelectedNode();
	  		if(node!=undefined){
	  			$('#pName').val(node.name);
	  			$('#pId').val(node.id);
	  			layer.close(index);
	  		}
	  	},
	  	btn2: function(index, layero){
	    	layer.close(index);
	  	}
	});
}

/**
 * 菜单图标
 */
function menuIcons() {
	layer.open({
		type: 2,
		title: '选择菜单',
		shadeClose: true,
		shade: 0.5,
		area: ['1200px', '90%'],
		content: '${ctxAdmin}/menu/icon',
		btn: ['确定', '取消'],
	  	yes: function(index, layero){
	  		alert(123);
	  	},
	  	btn2: function(index, layero){
	    	layer.close(index);
	  	}
	});
}
</script>
