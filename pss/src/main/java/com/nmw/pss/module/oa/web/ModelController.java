package com.nmw.pss.module.oa.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 模型控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/model")
public class ModelController {

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
	
	@RequestMapping(value = "create", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public void create(@RequestParam("name") String name, @RequestParam("key") String key,
			@RequestParam(value = "description", required = false) String description, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION,
					org.apache.commons.lang3.StringUtils.defaultString(description));
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
			response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + newModel.getId());
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
