package com.nmw.pss.module.oa.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nmw.pss.common.constant.HttpConstant;

/**
 * 模型控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/model")
public class ModelController {
	private static final Logger LOGGER=LoggerFactory.getLogger(ModelController.class);

	@Autowired
	private RepositoryService repositoryService;
	
	/**
	 * 模型列表
	 * @return
	 */
	@RequestMapping(value="list")
	public String menuList(Model model){
		List<org.activiti.engine.repository.Model> list =  repositoryService.createModelQuery().orderByCreateTime().desc().listPage(0, 10);
		model.addAttribute("list", list);
		return "oa/model/modellist";
	}
	
	@RequestMapping(value="form")
	public String menuForm(String modelId, Model model){
		return "oa/model/modelform";
	}
	
	@ResponseBody
	@RequestMapping(value = "create", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> create(String name, String key, String description) {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, StringUtils.defaultString(description));
			org.activiti.engine.repository.Model newModel = repositoryService.newModel();
			newModel.setMetaInfo(modelObjectNode.toString());
			newModel.setName(name);
			newModel.setKey(key);
			repositoryService.saveModel(newModel);
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.put("stencilset", stencilSetNode);
			repositoryService.addModelEditorSource(newModel.getId(), editorNode.toString().getBytes("utf-8"));
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_200);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, HttpConstant.HTTP_MSG_200);
			resultMap.put(HttpConstant.HTTP_DATA_KEY, "modeler.html?modelId=" + newModel.getId());
		} catch (Exception e) {
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_500);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, "操作失败:"+e.getMessage());
			LOGGER.warn("模型保存失败", e);
		}
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value="remove",method=RequestMethod.GET)
	public Map<String, Object> menuRemove(String modelId){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			repositoryService.deleteModel(modelId);
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_200);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, HttpConstant.HTTP_MSG_200);
		} catch (Exception e) {
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_500);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, "操作失败:"+e.getMessage());
			LOGGER.warn("模型删除失败", e);
		}
		return resultMap;
	}
}
