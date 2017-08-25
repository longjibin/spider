<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<%@ include file="/WEB-INF/common/head.jsp"%>
<script>
var setting = {
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
function getCheckedNode() {
	var treeObj=$.fn.zTree.getZTreeObj('treeDemo');
	var nodes=treeObj.getSelectedNodes();
	if(nodes.length==0){
		layer.msg('当前未选中');
		return;
	}
	if(nodes.length!=1){
		layer.msg('不允许选中多个');
		return;
	}
	return nodes[0];
}
</script>
<div class="content_wrap">
	<ul id="treeDemo" class="ztree"></ul>
</div>
