<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage_app}.database.dao;

import ${basepackage_app}.entity.${className};
import java.util.List;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

/**
 * 访问${table.sqlName}
 * @author Steven
 *
 */
public class ${className}Dao extends BaseDao{
	
	public ${className}Dao() {
		super();
	}
	
	/**
	 * 插入
	 * @param ${classNameLower}
	 */
	public void insert(${className} ${classNameLower}) {
		try {
			dbUtils.save(${classNameLower});
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量插入
	 * @param ${classNameLower}List
	 */
	public void insert(List<${className}> ${classNameLower}List) {
		try {
			dbUtils.saveAll(${classNameLower}List);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 清空表
	 */
	public void clearTable(){

		try {
			dbUtils.delete(${className}.class, WhereBuilder.b("pkid", "!=", "1"));
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除
	 * @param pkid
	 */
	public void deleteByPkid(String pkid){
		
		try {
			dbUtils.delete(${className}.class, WhereBuilder.b("pkid", "=", pkid));
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除
	 */
	public void delete(${className} ${classNameLower}){
		try {
			dbUtils.delete(${classNameLower});
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新
	 * @param ${classNameLower}
	 * @param updateColumnNames
	 */
	public boolean update(${className} ${classNameLower}, String... updateColumnNames){
		try {
			dbUtils.update(${classNameLower}, updateColumnNames);
			return true;
		} catch (DbException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 更新
	 */
	public void update(${className} ${classNameLower}){
		try {
			dbUtils.update(${classNameLower});
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据pkid查询
	 * @param pkid
	 */
	public ${className} get${className}ByPkid(String pkid){
		try {
			List<${className}> ${classNameLower}List = dbUtils.findAll(Selector.from(
					${className}.class).where("pkid", "=", pkid));
			if (${classNameLower}List.size() > 0) {
				return ${classNameLower}List.get(0);
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询所有
	 */
	public List<${className}> getAll(){
		try {
			List<${className}> ${classNameLower}List = dbUtils.findAll(${className}.class);
			return ${classNameLower}List;
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据工单id查询
	 * @param orderpkid
	 */
	public ${className} get${className}ByOrderpkid(String orderpkid){
		
		try {
			List<${className}> ${classNameLower}List = dbUtils.findAll(Selector.from(
					${className}.class).where("WORKORDERPKID", "=", orderpkid));
			if (${classNameLower}List.size() > 0) {
				return ${classNameLower}List.get(0);
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据工单id查询列表
	 * @param orderpkid
	 */
	public List<${className}> get${className}List(String orderpkid){
		
		try {
			List<${className}> ${classNameLower}List = dbUtils.findAll(Selector.from(
					${className}.class).where("WORKORDERPKID", "=", orderpkid));
				return ${classNameLower}List;
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}
}

