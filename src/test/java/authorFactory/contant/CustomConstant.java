package authorFactory.contant;

import java.util.HashMap;
import java.util.Map;

public class CustomConstant {
	//一般java文件}结尾
	public static byte[] JAVABYTE= {'}'};
	//自定义位置 	//rapid-generator-node-sun
	//public static byte[] JAVABYTE={'/','/','r','a','p','i','d','-','g','e','n','e','r','a','t','o','r','-','n','o','d','e','-','s','u','n'};
	
	//xml文件 </mapper>结尾
	public static byte[] XMLBYTE= {'<','/','m','a','p','p','e','r','>'};
	
	//spring bean中的命名规则
	/*
	public static String App="App";
	public static String Service="Service";
	public static String Dao="Dao";
	*/
	//备份文件路径
	public static String BACKUPBASE="D:/sunjinchao/data/eclipse/WorkSpace/Space3/sunbo/autojavaStructure/";
	//原始模板文件路径
	public static String TEMPLATE_BASEPATH_RAW="C:/Users/admin/git/codefactory/template/mytemplate/";
	
	
	//生成后的模板文件路径
	public static String TEMPLATE_BASEPATH="D:/sunjinchao/data/eclipse/WorkSpace/Space4/codefactory-two/generator-output/";
	//control
	public static String template_basepath_control=TEMPLATE_BASEPATH+"control-";
	//app
	public static String template_basepath_app=TEMPLATE_BASEPATH+"app-";
	//service
	public static String template_basepath_service=TEMPLATE_BASEPATH;//+"service/";
	//dao
	public static String template_basepath_dao=TEMPLATE_BASEPATH;//+"dao/";
	//xml
	public static String template_basepath_xml=TEMPLATE_BASEPATH;//+"xml/";
	
	//方法基本型
	/**
	 * 添加
	 */
	public static String[] method_add= {"add.java","add-impl.java"};
	/**
	 * 更新
	 */
	public static String method_update="";
	
	/**
	 * 查找，单实体类
	 */
	public static String method_search_single="";
	
	/**
	 * 查找，List实体类
	 */
	public static String method_search_model1="";
	
	/**
	 * 查找，List<Map>类
	 */
	public static String method_search_model2="";
	
	
}
