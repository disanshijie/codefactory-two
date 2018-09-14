<%@page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<#include "/macro.include"/> 
<#include "/custom.include"/> 
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<%@include file="/WEB-INF/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<t:head easyuiEnabled="true" jqueryEnabled="true" showLoading="false"
	validformEnabled="true" title="${table.tableAlias}编辑" bootstrapEnabled="false"
	lhgdialogEnabled="false">
	<script type="text/javascript">
		var ${classNameLower}_editform;
		$(function() {
			${classNameLower}_editform = $("#${classNameLower}_editform").Validform({
				tiptype : 4,
				label : ".label",
				showAllError : false,
				ajaxPost : true,
				postonce : true,
				beforeSubmit : function(curform) {
					$.messager.progress({});
				},
				callback : function(data) {
					$.messager.progress('close');

				}
			});

		});

		function save(callbakcFn) {
			${classNameLower}_editform.config({
				ajaxpost : {
					success : function(data, obj) {
						callbakcFn(data, obj);
					}
				}
			});
			$('#${classNameLower}_editform').submit();
		}

		
	</script>
</t:head>
<body style="padding: 5px">
	<form id="${classNameLower}_editform" method="post" action="${namespace}/${classNameLower}/save">
		<input type="hidden" name="${table.pkColumn.sqlName}" value="${classNameLower}.${table.pkColumn.sqlName}">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="inputFormTable">
			<#list table.columns as column>
			<tr>
				<td class="formLabel">${column.columnAlias}:</td>
				<td class="formValue"><input type="text"
					name="${column.columnNameLower}" value="{${classNameLower}.${column.columnNameLower}}"
					data-options="required:true" datatype="s2-30" nullmsg="请输入${column.columnAlias}！"
					errormsg="${column.columnAlias}至少2个字符,最多30个字符！" /></td>

			</tr>
			</#list>

		</table>

	</form>
</body>
</html>