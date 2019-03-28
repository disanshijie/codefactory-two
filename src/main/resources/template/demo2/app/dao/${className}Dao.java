<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage_app}.common.db.dao;

import ${basepackage_app}.entity.${className};
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.SqlInfoBuilder;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import java.util.List;

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
			db.save(${classNameLower});
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
			db.save(${classNameLower}List);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 清空表
	 */
	public void clearTable(){

		try {
			db.delete(${className}.class, WhereBuilder.b("PKID", "!=", "1"));
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据PKID删除
	 * @param pkid
	 */
	public void deleteByPkid(String pkid){
		
		try {
			db.delete(${className}.class, WhereBuilder.b("PKID", "=", pkid));
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除
	 */
	public void delete(${className} ${classNameLower}){
		try {
			db.delete(${classNameLower});
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
			db.update(${classNameLower}, updateColumnNames);
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
			KeyValue[] karr = {};
            List<KeyValue> keyValues = SqlInfoBuilder.entity2KeyValueList(db.getTable(${className}.class), ${classNameLower});
            db.update(${className}.class, WhereBuilder.b("PKID", "=", ${classNameLower}.getPkid()), keyValues.toArray(karr));
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有
	 */
	public List<${className}> getAll(){
		try {
			List<${className}> ${classNameLower}List = db.findAll(${className}.class);
			return ${classNameLower}List;
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据PKID查询
	 * @param pkid
	 */
	public ${className} get${className}ByPkid(String pkid){
		try {
			return db.selector(${className}.class).where("PKID", "=", pkid).findFirst();
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	 /**
     * 根据指定列查询（等值查询）
     * @param columnName    列名（全大写）
     * @param value         查询value
     * @return
     */
    public List<${className}> getBy(String columnName, String value){
        try {
            return db.selector(${className}.class).where(columnName, "=", value).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }
}

