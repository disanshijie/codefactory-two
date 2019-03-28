<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ngcs.entity.Axxm;
import com.ngcs.entity.Jz;
import com.ngcs.service.IAxxmService;
import com.ngcs.service.IJzService;

/**
<#include "/java_description.include">
 */
@Controller
public class ${className}Controller {


	@Resource
	I${classNameLower}Service ${classNameLower}Service;
	
	
	/** 
	 * 主页  集成分页 插件 PageHelper
	 * 
	 * param pageNumber:页数    pageSize:每页显示行数
	 **/
	@RequestMapping("${classNameLower}_index.action")
	public String list(Model model, Integer pageNumber, Integer pageSize) {
		if(pageNumber==null||pageNumber==0){
			pageNumber = 1;
		}
		if(pageSize ==null){
			pageSize = 10;
		}
		//爱心榜查询
		List<Jz> jzs = JzService.findBangDan();
		//开始分页   参数:页码, 每页显示数量
        PageHelper.startPage(pageNumber,10);
        List<${className}> ${classNameLower}_lists = ${classNameLower}Service.findAll(null);
        PageInfo<${className}> ${classNameLower}s = new PageInfo<${className}>(${classNameLower}_lists);
		model.addAttribute("page",${classNameLower}s);
	    model.addAttribute("bangdan", jzs);
		return "/page/${classNameLower}/index.jsp";
	}
	
	
	/** 
	 * to 后台管理
	 **/
	@RequestMapping("/to${classNameLower}AdminPage.action")
	public String toAdminPage(Model model, Integer pageNumber, Integer pageSize) {
		if(pageNumber==null||pageNumber==0){
			pageNumber = 1;
		}
		if(pageSize ==null){
			pageSize = 10;
		}
		//开始分页   参数:页码, 每页显示数量
        PageHelper.startPage(pageNumber,10);
        List< ${classNameLower}>  ${classNameLower}_lists = ${classNameLower}Service.findAll(null);
        PageInfo<${classNameLower}> ${classNameLower}s = new PageInfo<${classNameLower}>( ${classNameLower}_lists);
		model.addAttribute("page", ${classNameLower}s);
		return "/page/admin/${classNameLower}/article-list.jsp";
	}
	
	/** 
	 * 查看对象
	 **/
	@RequestMapping("${classNameLower}_get.action")
	public String get(Integer id, Model model){
		 //爱心榜查询
		 List<Jz> jzs = JzService.findBangDan();
		 ${className} pojo = ${classNameLower}Service.findById(id);
		 
         model.addAttribute("pojo", pojo);
         model.addAttribute("bangdan",jzs);
		 return "/page/${classNameLower}/get.jsp";
	}
	
	/** 
	 * 进入新增页面
	 **/
	@RequestMapping("${classNameLower}_toinsert.action")
	public String toinsert_admin() {
		return "/page/admin/${classNameLower}/add-article.jsp";
	}
	
	/** 
	 * 保存新增对象
	 **/
	@RequestMapping("${classNameLower}_insert.action")
	public String insert(${className} ${classNameLower}) {
        Date date = new Date();		
        ${classNameLower}.setDate(date);
        ${classNameLower}Service.save(${classNameLower});
		return "redirect:to${classNameLower}AdminPage.action";
	}
	
	/**
	 * 进入更新页面
	 **/
	@RequestMapping("${classNameLower}_toupdate.action")
	public String toupdate(Integer id, Model model) {
		${className} po = ${classNameLower}Service.findById(id);
		model.addAttribute("po",po);
		return "/page/admin/${classNameLower}/update-article.jsp";
	}
	
	/**
	 * 保存更新对象
	 **/
	@RequestMapping("${classNameLower}_update.action")
	public String update(${classNameLower} ${classNameLower}, Model model) {
		Date date = new Date();		
		${classNameLower}.setDate(date);
		${classNameLower}Service.update(${classNameLower});
		return "redirect:${classNameLower}_toupdate.action?id="+${classNameLower}.getId();
	}
	
	/**
	 *删除对象
	 **/
	@RequestMapping("${classNameLower}_delete.action")
	public String delete_admin(${classNameLower} ${classNameLower}) {
		${classNameLower}Service.deleteById(${classNameLower}.getId());
		return "redirect:to${classNameLower}AdminPage.action";
	}
	
}
