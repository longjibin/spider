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
					<li><a href="javascript:loadPage('menu/list');">菜单列表</a></li>
					<li class="active"><a href="javascript:loadPage('menu/form');">添加菜单</a></li>
				</ul>
				<div class="tab-content">
					<div class="active tab-pane">
						<form id="modelForm" class="form-horizontal">
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
									<a class="btn btn-danger" onclick="save('/menu/save','https://www.baidu.com');">保 存</a>
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
	  		var node=iframeWin.getCheckedNode();
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

/**
 * 保存菜单
 */
function save(url,redirectUrl) {
	var index;
	$.ajax({
	    url:'${ctxAdmin}/'+url,
	    type:'POST', //GET
	    data:$('#modelForm').serialize(),
	    timeout:5000,    //超时时间
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    beforeSend:function(xhr){
	    	index = layer.load(1, {shade: [0.1,'#fff']});//0.1透明度的白色背景
	    },
	    success:function(data,textStatus,jqXHR){
	    	if(data.code==200){
	    		layer.msg(data.msg);
	    		setTimeout(function(){
	    			window.location.href='${ctxAdmin}'+redirectUrl;
	    		},2000);
	    	}else{
	    		layer.msg(data.msg);
	    	}
	    },
	    error:function(xhr,textStatus){
	    	layer.msg(textStatus);
	    },
	    complete:function(){
	    	layer.close(index);
	    }
	})
}
</script>
