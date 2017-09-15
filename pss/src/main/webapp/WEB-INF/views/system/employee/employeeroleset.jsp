<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<%@ include file="/WEB-INF/common/head.jsp"%>
<!-- Main content -->
<body style="background-color: #ecf0f5;">
	<section class="content">
		<div class="box box-primary">
			<!-- <div class="box-header with-border">
           		<div class="input-group input-group-sm">
 					<input type="text" class="form-control">
                   	<span class="input-group-btn">
                     	<button type="button" class="btn btn-info btn-flat">Go!</button>
                   	</span>
   				</div>
            </div> -->
			<div class="box-body box-profile" style="margin:0 auto;">
				<select id='callbacks' multiple='multiple'>
					<c:forEach items="${roles }" var="role">
						<c:if test="${role.id ne '1' }">
							<option value="${role.id }" <c:if test="${role.selected eq true }">selected="selected"</c:if>>${role.name }</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
$('#callbacks').multiSelect({
	afterSelect : function(roleId) {//选中回调
		var index;
		$.ajax({
		    url:'${ctxAdmin}/employeerole/save',
		    type:'POST', //GET
		    data:{
		    	employeeId:'${employee.id}',
		    	roleId:roleId[0]
		    },
		    async:true,    //或false,是否异步
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    beforeSend:function(xhr){
		    	index = layer.load(1, {shade: [0.1,'#fff']});//0.1透明度的白色背景
		    },
		    success:function(data,textStatus,jqXHR){
		    	layer.msg(data.msg);
		    },
		    error:function(xhr,textStatus){
		    	layer.msg(textStatus);
		    },
		    complete:function(){
		    	layer.close(index);
		    }
		});
	},
	afterDeselect : function(roleId) {//撤销回调
		var index;
		$.ajax({
		    url:'${ctxAdmin}/employeerole/remove',
		    type:'GET', //GET
		    data:{
		    	employeeId:'${employee.id}',
		    	roleId:roleId[0]
		    },
		    async:true,    //或false,是否异步
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    beforeSend:function(xhr){
		    	index = layer.load(1, {shade: [0.1,'#fff']});//0.1透明度的白色背景
		    },
		    success:function(data,textStatus,jqXHR){
		    	layer.msg(data.msg);
		    },
		    error:function(xhr,textStatus){
		    	layer.msg(textStatus);
		    },
		    complete:function(){
		    	layer.close(index);
		    }
		});
	}
});
</script>
