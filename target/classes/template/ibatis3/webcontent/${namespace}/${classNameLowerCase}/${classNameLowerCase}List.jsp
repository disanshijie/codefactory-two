<%@page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/context/mytags.jsp"%>
<#include "/macro.include"/> 
<#include "/custom.include"/> 
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<!DOCTYPE html>
<html lang="zh-CN">
<t:head easyuiEnabled="true" jqueryEnabled="true" showLoading="false"
	validformEnabled="true" title="${table.tableAlias}管理" bootstrapEnabled="false"
	lhgdialogEnabled="true">
	<script type="text/javascript">
		$(function(){
			$('#${classNameLower}List_datagrid').datagrid();
			
		});
		//新增${table.tableAlias}
		function add() {
			$.dialog({
				title : '新增${table.tableAlias}',
				content : 'url:${namespace}/${classNameLower}/createForm',
				width : 600,
				height : 400,
				lock : true,
				okVal : "保存",
				cancelVal : "取消",
				ok : function() {
					var iframe = this.iframe.contentWindow;
					var _this = this;
					iframe.save(function(data, obj) {
						if (data.success) {
							$.dialog.tips('保存成功');
							_this.close();
							reloadList();
						} else {
							$.messager.alert('系统错误',data.message, 'error');
						}
					});
					return false;
				},
				cancel : function() {

				}
			});
		}

		//删除${table.tableAlias}
		function del(id) {

			$.messager.confirm('删除', '确认删除该${table.tableAlias}?', function(r) {
				if (r) {
					$.ajax({
						async : false,
						cache : false,
						type : 'GET',
						url : '/${namespace}/${classNameLower}/delete?id=' + id,// 请求的action路径
						success : function(data) {
							if (data.success) {
								$.dialog.tips('删除成功');
								reloadList();
							} else {
								$.messager.alert('系统错误',data.message, 'error');
							}
						}
					});
				}
			});
		}

		//修改${table.tableAlias}
		function edit(id) {
			$.dialog({
				id:'',
				title : '修改${table.tableAlias}',
				content : 'url:${namespace}/${classNameLower}/editForm/' + id,
				width : 600,
				height : 400,
				lock : true,
				okVal : "保存",
				cancelVal : "取消",
				ok : function() {
					var iframe = this.iframe.contentWindow;
					var _this = this;
					iframe.save(function(data, obj) {
						if (data.success) {
							$.dialog.tips('保存成功');
							_this.close();
							reloadList();

						} else {
							$.messager.alert('系统错误',data.message, 'error');
						}
					});
					return false;
				},
				cancel : function() {

				}
			});
		}

		//重新加载
		function reloadList() {
			$('#${classNameLower}List_datagrid').datagrid('reload');
		}
		
		//查询
		function ${classNameLower}Search() {
			var queryParams = $('#${classNameLower}List_datagrid').datagrid(
					'options').queryParams;
			$('#${classNameLower}Searchform').find('*').each(function() {
				queryParams[$(this).attr('name')] = $(this).val();
			});
			$('#${classNameLower}List_datagrid').datagrid('reload');
		}

		//重置查询条件
		function ${classNameLower}SearchReset() {
			$("#${classNameLower}Searchform").find(":input").val("");
			${classNameLower}Search();
		}
		
		//格式化操作
		function optFormat(value, rec, index) {
			var href = '';
			href += "[<a href='javascript:void(0)' onclick=edit('"
					+ rec.${table.pkColumn.sqlName}
					+ "')>修改</a>]";
			href += "[<a href='javascript:void(0)' onclick=del('"
					+ rec.${table.pkColumn.sqlName}
					+ "')>删除</a>]";

			return href;
		}
	</script>
</t:head>
<body class="easyui-layout" style="overflow-y: hidden;" scroll="no">
<!-- 搜索条件 -->
	<div id="${classNameLower}Searchform" class="searchFormTable" region="north" border="false">
		<table>
			<#list table.columns as column>
			<tr>
				<td class="formLabel">${column.columnAlias}:</td>
				<td class="formValue"><input type="text"
					name="search_${column.columnNameLower}" /></td>
			</tr>
			</#list>
			<tr>
				<td><a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="${classNameLower}Search()">查询</a><a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-reload" onclick="${classNameLower}SearchReset()">重置</a></td>

			</tr>
		</table>
	</div>
	<!-- 中间-->
	<div id="mainPanle" region="center" style="overflow: hidden;" border="false">
		<div  id="${classNameLower}_toolbar" style="height: 25px;" class="datagrid-toolbar">
			<span style="float: left;"><a href="javascript:void(0)"
				class="easyui-linkbutton" plain="true" icon="icon-add"
				onclick="add()">新增</a></span>
		</div>
		
		<table id="${classNameLower}List_datagrid" title="${classNameLower}列表" border="false"
			border="false" url="${namespace}/${classNameLower}/queryForManage" fit="true" idField="PARAMID"
			method="POST" pagination="true" pageSize="20" pagePosition="bottom"
			showFooter="false" rownumbers="true" fitColumns="false"
			toolbar="#${classNameLower}_toolbar" singleSelect="true">
			<thead>
				<tr>
					<th
						data-options="field:'opt',width:60,formatter:optFormat,align:'center'">操作</th>
					<#list table.columns as column>
					<th data-options="field:'${column.columnAlias}',width:150">${column.columnAlias}</th>					
					</#list>
				</tr>
			</thead>
			
		</table>
	</div>



</body>
</html>

