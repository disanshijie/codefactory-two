<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkcg.framework.JsonResponse;
import com.hkcg.framework.Servlets;
import com.hkcg.framework.mybatis.PageInfo;
import com.hkcg.framework.mybatis.PageResponse;

import ${basepackage}.entity.${className};
import ${basepackage}.service.${className}Service;

/**
 * ${table.sqlName}
 * @author YuLinlin
 * 
 */
@Controller
@RequestMapping(value = "/${namespace}/${classNameLower}")
public class ${className}Controller {
	@Autowired
	private ${className}Service ${classNameLower}Service;

	
	/**
	 * 进入${classNameLower}List.jsp页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "${classNameLower}List", method = RequestMethod.GET)
	public String ${classNameLower}List() {
		return "${namespace}/${classNameLower}/${classNameLower}List";
	}
	/**
	 * 进入新建界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "createForm", method = RequestMethod.GET)
	public String createForm(String deptid, Model model) {
		${className} ${classNameLower} = new ${className}();
		model.addAttribute("${classNameLower}", ${classNameLower});
		return "${namespace}/${classNameLower}/${classNameLower}EditForm";
	}
	
	/**
	 * 进入修改界面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "editForm/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") String id, Model model) {
		if (StringUtils.isNotBlank(id)) {
			${className} ${classNameLower} = ${classNameLower}Service.getBy${table.pkColumn.columnName}(id);
			model.addAttribute("${classNameLower}", ${classNameLower});
		}
		return "${namespace}/${classNameLower}/${classNameLower}EditForm";
	}
	
	/**
	 * 获取查询管理列表 
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "queryForManage")
	@ResponseBody
	public PageResponse queryForManage(int page, int rows,HttpServletRequest request) {
		Map searchParams = Servlets.getParametersStartingWith(request,
				"search_");
		PageInfo pageInfo = new PageInfo(page, rows);
		List<Map> list = ${classNameLower}Service.queryForManage(searchParams, pageInfo);
		PageResponse pageResponse = new PageResponse(list, pageInfo);
		return pageResponse;
	}

	

	/**
	 * 删除
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse delete(String id, HttpServletRequest request,
			HttpServletResponse response) {
		JsonResponse jsonResponse = new JsonResponse(true, null, null);
		try {
			${classNameLower}Service.delete(id);

		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMessage(e.getMessage());
		}
		return jsonResponse;
	}

	/**
	 * 保存
	 * @param ${classNameLower}
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse save(${className} ${classNameLower}, HttpServletRequest request,
			HttpServletResponse response) {
		JsonResponse jsonResponse = new JsonResponse(true, null, null);
		try {

			if (StringUtils.isBlank(${classNameLower}.get${table.pkColumn.columnName}())) {
				${classNameLower} = ${classNameLower}Service.insert(${classNameLower});
			} else {
				${className} old${className} = ${classNameLower}Service.getBy${table.pkColumn.columnName}(${classNameLower}.get${table.pkColumn.columnName}());
				<#list table.columns as column>
				old${className}.set${column.columnName}(${classNameLower}.get${column.columnName}());
				</#list>
				${classNameLower} = ${classNameLower}Service.update(old${className});
			}
			jsonResponse.setData(${classNameLower});
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMessage(e.getMessage());
		}
		return jsonResponse;
	}

	/**
	 * 检查部门编码是否唯一
	 * 
	 * @param deptid
	 * @param deptcode
	 * @param request
	 * @param response
	 * @return
	 */
	/*@RequestMapping(value = "isUnique${className}code", method = RequestMethod.POST)
	@ResponseBody
	public ValidResponse isUnique${className}code(@RequestParam("${classNameLower}id") String ${classNameLower}id,
			@RequestParam("param") String ${classNameLower}code, HttpServletRequest request,
			HttpServletResponse response) {
		ValidResponse validResponse = new ValidResponse(true, null);
		try {
			boolean isExsit = ${classNameLower}Service.isExsit${className}code(${classNameLower}id, ${classNameLower}code);
			if (isExsit) {
				validResponse.setStatus(false);
				validResponse.setInfo("${className}编码[" + ${classNameLower}code + "]已经存在");
			} else {
				validResponse.setStatus(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			validResponse.setStatus(false);
			validResponse.setInfo(e.getMessage());
		}
		return validResponse;
	}*/

	

	

}
