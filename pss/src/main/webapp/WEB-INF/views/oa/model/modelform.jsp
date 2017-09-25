<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<section class="content-header">
	<h1>角色管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
		<li><a href="#">办公中心</a></li>
		<li class="#">模型管理</li>
		<li class="active">模型设计</li>
	</ol>
</section>
<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="nav-tabs-custom">
				<ul class="nav nav-tabs">
					<li><a href="javascript:loadPage('model/list','GET',null);">模型列表</a></li>
					<li class="active"><a href="javascript:loadPage('model/form','GET',null);">模型设计</a></li>
				</ul>
				<div class="tab-content">
					<div class="active tab-pane">
						<form id="modelForm" class="form-horizontal">
							<div class="form-group">
								<label for="name" class="col-sm-2 col-sm-offset-1 control-label">模型名称</label>
								<div class="col-sm-6 input-group">
									<input name="name" type="text" class="form-control" placeholder="模型名称">
								</div>
							</div>
							<div class="form-group">
								<label for="key" class="col-sm-2 col-sm-offset-1 control-label">模型key</label>
								<div class="col-sm-6 input-group">
									<input name="key" type="text" class="form-control" placeholder="模型key">
								</div>
							</div>
							<div class="form-group">
								<label for="description" class="col-sm-2 col-sm-offset-1 control-label">描述</label>
								<div class="col-sm-6 input-group">
									<textarea name="description" class="form-control" placeholder="描述"></textarea>
								</div>
							</div>
							<div class="box-footer">
								<div class="col-sm-offset-3 col-sm-3">
									<button type="submit" class="btn btn-primary">保 存</button>
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
$.validator.setDefaults({
	submitHandler: function () {
		var index;
		$.ajax({
		    url:'${ctxAdmin}/model/create',
		    type:'POST', //GET
		    data:$('#modelForm').serialize(),
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    beforeSend:function(xhr){
		    	index = layer.load(1, {shade: [0.1,'#fff']});//0.1透明度的白色背景
		    },
		    success:function(data,textStatus,jqXHR){
		    	if(data.code==200){
		    		var tmp=window.open("about:blank","","fullscreen=1") 
					tmp.moveTo(0,0); 
					tmp.resizeTo(screen.width+20,screen.height); 
					tmp.focus(); 
					tmp.location='${ctx}/'+data.data;
					
		    		loadPage('model/list','GET',null);
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
});

$(function() {
	$('#modelForm').validate( {
		rules: {
			name: 'required',
			key: 'required'
		},
		messages: {
			name: {
				required: "请输入模型名称"
			},
			key: {
				required: "请输入模型key"
			}
		},
		errorPlacement: function ( error, element ) {
		}
	} );
});
</script>
