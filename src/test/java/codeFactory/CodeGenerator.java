package codeFactory;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.provider.db.sql.SqlFactory;
import cn.org.rapid_framework.generator.provider.db.sql.model.Sql;

public class CodeGenerator {
	public static GeneratorFacade g = new GeneratorFacade();
	
	public static void main(String[] args) throws Exception {
		// 模板地址
		String templatePath = "D:\\sunjinchao\\data\\eclipse\\WorkSpace\\Space4\\codefactory-two\\src\\main\\resources\\template/demo2";
		
		g.getGenerator().addTemplateRootDir(templatePath);
		// 删除生成器的输出目录//
		//g.deleteOutRootDir();
		// 通过数据库表生成文件
		//g.generateByTable("tb_user");
		
		//g.deleteByTable("tb_user");
		//g.generateByTable("tb_user");

		test_generate_by_sql();
		// 自动搜索数据库中的所有表并生成文件,template为模板的根目录
		// g.generateByAllTable();
		// 按table名字删除文件
		// g.deleteByTable("table_name", "template");
	}
	
	/*
	public void test_generate_by_sql() throws Exception {
        Sql sql = new SqlFactory().parseSql("select * from user_info where username=#username# and password=#password#");  //同时支持 #param# $param$ #{param} ${param} :param 几种占位符
        sql.setTableSqlName("user_info");
        sql.setMultiplicity("many");  //many or one,用于控制查询结果是one,many
        sql.setOperation("findByUsernameAndPassword"); 
        sql.setRemarks("根据用户名及密码进行查询");
        new GeneratorFacade().generateBySql(sql, "generator/test/for_test_select_sql");
	}
	*/
	
	public static void test_generate_by_sql() throws Exception {
        Sql sql = new SqlFactory().parseSql("select * from tb_user where username=#username# and password=#password#");  //同时支持 #param# $param$ #{param} ${param} :param 几种占位符
       
       // sql.setParameterClass("tb_user");
       // sql.setTableSqlName("user_info");
        
        sql.setMultiplicity("many");  //many or one,用于控制查询结果是one,many
        sql.setOperation("findByUsernameAndPassword"); 
        sql.setRemarks("根据用户名及密码进行查询");
        g.generateBySql(sql);
       // g.generateBySql(sql, "generator/test/for_test_select_sql");
	}
}