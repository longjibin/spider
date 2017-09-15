<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<script>
/**
 * 分页查询员工
 */
function submit(pageNow) {
	var pageCount=${page.pageCount};
	if(pageCount!=0){
		if(pageNow>0 && pageNow<=pageCount){
			$('#pageNow').val(pageNow);
			var data=$('#modelForm').serialize();
			loadPage('employee/list', 'POST', data);
		}else{
			layer.msg('不可请求的页号');
		}
	}else{
		if(pageNow==1){
			$('#pageNow').val(pageNow);
			var data=$('#modelForm').serialize();
			loadPage('employee/list', 'POST', data);
		}else{
			layer.msg('不可请求的页号');
		}
	}
}

/**
 * 查看员工详情
 */
function view(id) {
	layer.open({
		type: 2,
	  	title: '员工详情',
	  	shadeClose: true,
	  	shade: 0.8,
	  	area: ['500px', '70%'],
	  	content: '${ctxAdmin }/employee/view?id='+id
	}); 
}

/**
 * 角色分配
 */
function roleSet(id) {
	layer.open({
		type: 2,
	  	title: '角色分配',
	  	shadeClose: true,
	  	shade: 0.8,
	  	area: ['500px', '70%'],
	  	content: '${ctxAdmin }/employee/employeeroleset?id='+id
	});
}
</script>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>员工管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
		<li><a href="#">系统设置</a></li>
		<li class="active">员工管理</li>
	</ol>
</section>
<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="nav-tabs-custom">
				<ul class="nav nav-tabs">
					<li class="active"><a href="javascript:loadPage('employee/list','POST',null);">员工列表</a></li>
					<shiro:hasPermission name="system:employee:edit">
						<li><a href="javascript:loadPage('employee/form','GET',null);">新增员工</a></li>
					</shiro:hasPermission>
				</ul>
				<div class="tab-content">
					<div class="active tab-pane">
						<form id="modelForm" method="post" class="form-horizontal">
							<div class="box box-info">
								<div class="box-header with-border">
					           		<h3 class="box-title">查询条件</h3>
					            </div>
								<!-- /.box-header -->
								<div class="box-body">
					              	<div class="row">
				              			<div class="form-group col-xs-4">
						                  	<label class="col-sm-3 control-label">工号</label>
						                  	<div class="col-sm-9">
						                  		<div class="input-group">
									            	<span class="input-group-addon"><i class="fa fa-user-circle"></i></span>
									                <input name="jobNo" value="${employee.jobNo }" type="text" class="form-control" placeholder="工号">
								              	</div>
						                  	</div>
						                </div>
						                
						                <div class="form-group col-xs-4">
						                  	<label class="col-sm-3 control-label">账号</label>
						                  	<div class="col-sm-9">
						                  		<div class="input-group">
									            	<span class="input-group-addon"><i class="fa fa-user"></i></span>
									                <input name="loginName" value="${employee.loginName }" type="text" class="form-control" placeholder="账号">
								              	</div>
						                  	</div>
						                </div>
						                
						                <div class="form-group col-xs-4">
						                  	<label class="col-sm-3 control-label">手机</label>
						                  	<div class="col-sm-9">
						                  		<div class="input-group">
									            	<span class="input-group-addon"><i class="fa fa-mobile"></i></span>
									                <input name="phone" value="${employee.phone }" type="text" class="form-control" placeholder="手机">
								              	</div>
						                  	</div>
						                </div>
						                
						                <div class="form-group col-xs-4">
						                	<label class="col-sm-3 control-label"></label>
						                	<div class="col-sm-9">
						                  		<div class="input-group">
									                <a href="javascript:submit(1);" class="btn btn-block btn-info btn-flat"> 查 询 </a>
								              	</div>
						                  	</div>
						                </div>
					              	</div>
					            </div>
								<!-- /.box-body -->
							</div>
							
							<div class="box box-info">
								<div class="box-header with-border">
					           		<h3 class="box-title">查询结果</h3>
					            </div>
					            <!-- /.box-header -->
					            <div class="box-body">
					            	<table id="tabletree" class="table table-hover">
										<tbody>
											<tr>
												<th>头像</th>
												<th>工号</th>
												<th>登录账号</th>
												<th>昵称</th>
												<th>手机号</th>
												<th>状态</th>
												<th>操作</th>
											</tr>
											<c:choose>
												<c:when test="${not empty page.records }">
													<c:forEach items="${page.records }" var="employee">
														<c:choose>
															<c:when test="${employee.loginName ne fns:getConfig('adminAccount') }">
																<tr>
																	<td>
																		<div class="user-panel">
																			<div class="image">
																	          	<img src="${ctxStatic }/plugins/adminlte/img/user2-160x160.jpg" class="img-circle" alt="User Image">
																	        </div>
																		</div>
		        													</td>
																	<td>${employee.jobNo }</td>
																	<td>${employee.loginName }</td>
																	<td>${employee.nickName }</td>
																	<td>${employee.phone }</td>
																	<td>
																		<c:if test="${employee.status eq 1 }">正常</c:if>
																		<c:if test="${employee.status eq 2 }">禁用</c:if>
																		<c:if test="${employee.status eq 3 }">离职</c:if>
																	</td>
																	<td>
																		<shiro:hasPermission name="system:employee:view">
																			<a href="javascript:view('${employee.id }');"><i class="fa fa-edit"></i> 查 看</a>&nbsp;&nbsp;&nbsp;&nbsp;
																		</shiro:hasPermission>
																		<shiro:hasPermission name="system:employee:edit">
																			<a href="javascript:loadPage('employee/form?id=${employee.id }');"><i class="fa fa-edit"></i> 修 改</a>&nbsp;&nbsp;&nbsp;&nbsp;
																		</shiro:hasPermission>
																		<shiro:hasPermission name="system:employee:roleset">
																			<a href="javascript:roleSet('${employee.id }');"><i class="fa fa-edit"></i> 角色分配</a>&nbsp;&nbsp;&nbsp;&nbsp;
																		</shiro:hasPermission>
																		<shiro:hasPermission name="system:employee:delete">
																			<a href="javascript:remove('employee/remove?id=${employee.id }','employee/list');"><i class="fa fa-edit"></i> 删 除</a>&nbsp;&nbsp;&nbsp;&nbsp;
																		</shiro:hasPermission>
																	</td>
																</tr>
															</c:when>
															<c:otherwise>
																<c:if test="${employee.loginName eq current.loginName }">
																	<tr>
																		<td>
																			<div class="user-panel">
																				<div class="image">
																		          	<img src="${ctxStatic }/plugins/adminlte/img/user2-160x160.jpg" class="img-circle" alt="User Image">
																		        </div>
																			</div>
			        													</td>
																		<td>${employee.jobNo }</td>
																		<td>${employee.loginName }</td>
																		<td>${employee.nickName }</td>
																		<td>${employee.phone }</td>
																		<td>
																			<c:if test="${employee.status eq 1 }">正常</c:if>
																			<c:if test="${employee.status eq 2 }">禁用</c:if>
																			<c:if test="${employee.status eq 3 }">离职</c:if>
																		</td>
																		<td>
																			<shiro:hasPermission name="system:employee:view">
																				<a href="javascript:view('${employee.id }');"><i class="fa fa-edit"></i> 查 看</a>&nbsp;&nbsp;&nbsp;&nbsp;
																			</shiro:hasPermission>
																			<shiro:hasPermission name="system:employee:edit">
																				<a href="javascript:loadPage('employee/form?id=${employee.id }');"><i class="fa fa-edit"></i> 修 改</a>&nbsp;&nbsp;&nbsp;&nbsp;
																			</shiro:hasPermission>
																		</td>
																	</tr>
																</c:if>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<tr>
														<td colspan="7">无满足条件的数据！</td>
													</tr>
												</c:otherwise>
											</c:choose>
										</tbody>
									</table>
					            </div>
					            <!-- /.box-body -->
					            <div class="box-footer clearfix">
					            	<input id="pageNow" name="pageNow" type="hidden" value="${page.pageNow }">					            
					            	<ul class="pagination pagination-sm no-margin pull-right">
						           		<li><a href="javascript:submit(${page.preNum });">«</a></li>
						           		<c:forEach begin="${page.startNum }" end="${page.endNum }" varStatus="s">
						           			<li><a href="javascript:submit(${s.index });">${s.index }</a></li>
						           		</c:forEach>
						                <li><a href="javascript:submit(${page.nextNum });">»</a></li>
						                <li><a href="javascript:void(0);">${page.recordCount }条记录</a></li>
						                <li><a href="javascript:void(0);">共${page.pageCount }页</a></li>
						      		</ul>
					            </div>
					    	</div>
				    	</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
