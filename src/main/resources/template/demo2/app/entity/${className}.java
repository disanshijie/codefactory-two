<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage_app}.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.stech.lib.utils.DateConvertUtils;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ${table.sqlName}
 * @author Steven
 *
 */
@Table(name = "${table.sqlName}")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ${className} extends EntityBase {
	//alias
	public static final String TABLE_ALIAS = "${table.sqlName}";
	
	//columns START
	<#list table.columns as column>
	@Column(name = "${column.sqlName}")
	private ${column.javaType} ${column.columnNameLower};
	</#list>
	//columns END

	<@generateConstructor className/>
	<@generateJavaColumns/>


	public int hashCode() {
		return new HashCodeBuilder().append(this.${table.pkColumn.columnNameLower}).toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ${className} == false) return false;
		if(this == obj) return true;
		${className} other = (${className})obj;
		return new EqualsBuilder()
			<#list table.pkColumns as column>
			.append(get${column.columnName}(),other.get${column.columnName}())
			</#list>
			.isEquals();
	}
}

<#macro generateJavaColumns>
	<#list table.columns as column>
		<#if column.isDateTimeColumn>
	@JsonIgnore
	public String get${column.columnName}String() {
		return DateConvertUtils.dateTimeToString(get${column.columnName}());
	}
	
		</#if>	
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	</#list>
</#macro>


