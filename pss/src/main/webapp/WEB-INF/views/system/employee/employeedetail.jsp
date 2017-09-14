<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<%@ include file="/WEB-INF/common/head.jsp"%>
<!-- Main content -->
<div style="background-color: #ecf0f5;">
	<section class="content">
		<div class="box box-primary">
			<div class="box-body box-profile">
				<img class="profile-user-img img-responsive img-circle" src="${ctxStatic }/plugins/adminlte/img/user4-128x128.jpg" alt="User profile picture">
				<h3 class="profile-username text-center">${empty employee.nickName?employee.loginName:employee.nickName }</h3>
				<ul class="list-group list-group-unbordered">
					<li class="list-group-item"><b><i class="fa fa-user-circle"></i> 工号</b> <a class="pull-right">${employee.jobNo }</a></li>
					<c:if test="${employee.loginName ne fns:getConfig('adminAccount') }">
						<li class="list-group-item"><b><i class="fa fa-address-card"></i> 身份证号</b> <a class="pull-right">${employee.idCardNo }</a></li>
						<li class="list-group-item"><b><i class="fa fa-caret-square-o-down"></i> 状态</b>
							<a class="pull-right">
								<c:if test="${employee.status eq 1 }"><span class="label label-success">正常</span></c:if>
								<c:if test="${employee.status eq 2 }"><span class="label label-success">禁用</span></c:if>
								<c:if test="${employee.status eq 3 }"><span class="label label-warning">离职</span></c:if>
							</a>
						</li>
						<li class="list-group-item"><b><i class="fa fa-calendar"></i> 入职时间</b> <a class="pull-right">${employee.entryTime }</a></li>
						<c:if test="${employee.status eq 3 }">
							<li class="list-group-item"><b><i class="fa fa-calendar"></i> 离职时间</b> <a class="pull-right">${employee.quitTime }</a></li>
						</c:if>
					</c:if>
					<li class="list-group-item"><b><i class="fa fa-user-o"></i> 角色</b>
						<p class="pull-right">
							<c:forEach items="${roles }" var="role">
								<span class="label label-success">${role.name }</span>
							</c:forEach>
		              	</p>
		  			</li>
				</ul>
				<a href="#" class="btn btn-primary btn-block"><b>Follow</b></a>
			</div>
		</div>
	</section>
</div>
