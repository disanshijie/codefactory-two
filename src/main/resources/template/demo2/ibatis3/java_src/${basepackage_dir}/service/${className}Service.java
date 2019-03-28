<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ${basepackage}.dao.${className}Dao;
import ${basepackage}.entity.${className};
import com.hkcg.framework.mybatis.PageInfo;
import com.hkcg.mobile.utils.Identities;

/**
 * ${table.sqlName}
 * @author YuLinlin
 *
 */
@Component
@Transactional(readOnly = true)
public class ${className}Service {
	@Autowired
	${className}Dao ${classNameLower}Dao;
	
	public ${className}Dao get${className}Dao(){
		return this.${classNameLower}Dao;
	}

	/**
	 * 查询分页列表（列表管理）
	 * @param searchParams
	 * @param page
	 * @return
	 */
	public List<Map> queryForManage(Map searchParams, PageInfo page) {
		searchParams.put("page", page);
		List<Map> list = ${classNameLower}Dao.query${className}PageList(searchParams);
		return list;
	}

	/*public boolean isExsit${className}code(String ${table.pkColumn.columnNameLower}, String ${className}code) {
		List<${className}> ${classNameLower}list = ${classNameLower}Dao.getBy${className}code(${classNameLower}code);
		if (StringUtils.isBlank(${table.pkColumn.columnNameLower})) {
			return ${classNameLower}list.size() > 0;
		} else {
			if (${classNameLower}list.size() > 0) {
				${className} ${classNameLower} = ${classNameLower}list.get(0);
				return !${classNameLower}.get${table.pkColumn.columnName}().equals(${table.pkColumn.columnNameLower});
			}
		}
		return false;
	}*/
	
	/**
	 * 插入
	 * @param ${classNameLower}
	 * @return
	 */
	@Transactional(readOnly = false)
	public ${className} insert(${className} ${classNameLower}) {
		${classNameLower}.set${table.pkColumn.columnName}(Identities.uuid2());
		${classNameLower}Dao.insert(${classNameLower});
		return ${classNameLower};
	}
	
	/**
	 * 更新
	 * @param ${classNameLower}
	 * @return
	 */
	@Transactional(readOnly = false)
	public ${className} update(${className} ${classNameLower}) {
		${classNameLower}Dao.update(${classNameLower});
		return ${classNameLower};
	}
	/**
	 * 删除
	 * @param ${table.pkColumn.columnNameLower}
	 */
	@Transactional(readOnly = false)
	public void delete(String ${table.pkColumn.columnNameLower}) {
		${classNameLower}Dao.delete(${table.pkColumn.columnNameLower});
	}

	/**
	 * 根据主键获取
	 * @param ${classNameLower}
	 * @return
	 */
	public ${className} getBy${table.pkColumn.columnName}(String ${table.pkColumn.columnNameLower}) {
		${className} ${classNameLower} = ${classNameLower}Dao.getById(${table.pkColumn.columnNameLower});
		return ${classNameLower};
	}

	

}
