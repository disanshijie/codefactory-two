<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;


/**
<#include "/java_description.include">
 */
@Repository
public class ${className}DaoImpl extends BaseDaoImpl<${className}> implements I${className}Dao {

	/**
	 * mybatis配置文件命名空间
	 */
    public ${className}DaoImpl(){
		setNs("${className}Mapper");
	}
	
	/** 
	 * 创建${className}
	 **/
	public void save(${className} ${classNameLower}) {
	   Assert.notNull(${classNameLower},"'${classNameLower}' must be not null");
	   this.insert(${classNameLower}); //注意调BaseDaoImpl的insert方法
	}
	
	/** 
	 * 更新${className}
	 **/	
    public void update(${className} ${classNameLower}) {
        Assert.notNull(${classNameLower},"'${classNameLower}' must be not null");
        this.updateObj(${classNameLower});
    }	
    
	/** 
	 * 删除${className}
	 **/
    public void deleteById(${table.idColumn.javaType} id) {
    	Assert.notNull(id,"'deleteById(id)' id must be not null");
        this.delete(id);
    }
    
	/** 
	 * 查询id ${className}
	 **/    
    public ${className} findById(${table.idColumn.javaType} id) {
    	Assert.notNull(id,"'findById(id)' id must be not null");
        return this.get(id);
    }
    
	/** 
	 * 查询所有${className}
	 **/    
    public List<${className}> findAll(Map paraMap) {
    	List<${className}> list = this.find(null);
        return list;
    }
  
}
