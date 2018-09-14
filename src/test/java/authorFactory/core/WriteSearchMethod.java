package authorFactory.core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import authorFactory.contant.CustomConstant;
import cn.org.rapid_framework.generator.Generator.GeneratorModel;
import cn.org.rapid_framework.generator.GeneratorFacade;

public class WriteSearchMethod {
		public static String[] template_name=null;

		//public static String BASEPATH="D:/opt/eyou/src/com/eyou/trader/";
		public static String BASEPATH="D:/sunjinchao/data/eclipse/WorkSpace/Space3/TokenKnows/src/main/java/com/sun/tokenknows/modules/";
		//java 文件后缀名
		public static String suffixaction="action";
		public static String suffixapp="app";
		public static String suffixservice="service";
		public static String suffixdao="dao";
		
		/*********************************文件/路径结构********************************************/
		//进入到的包名	article
		public static String PACKAGENAME="article";
		//目标路径  bao名+文件后缀名
		public static String classpath=BASEPATH+PACKAGENAME+"/";
		//类基本名称	构成文件名/bean名	User
		public static String CLASSNAME="Article";
		/**********************************文件位置*******************************************/
		//xml位置
		public static String sa="D:/sunjinchao/data/eclipse/WorkSpace/Space3/TokenKnows/src/main/resources/mybatis/sqlMappers/";
		//public static String IBATISXMLPATH=sa+"ArticleExtendMapper.xml";
		
		
		/**********************************生成方法规则*******************************************/
		//SpingBean类名
		public static String classNameLower="article";
		//返回类型
		public static String RETURNTYPE="List<Article>";
		//参数类型
		public static String[][] PARAMS= {{"Article","article"}};
		//描述
		public static String REMARK="获取侧栏文件列表";
		//方法名称 
		public static String METHODNAME="findEffectArticleOfSideaaaaaa";
		
		
		public static void main(String[] args) throws Exception {
			
			template_name=CustomConstant.method_add;
			/*
			//清除备份文件夹中原来的文件内容
			 */
			//Tools.delDir(new File(CustomConstant.BACKUPBASE+METHODNAME));
			
			//生成模板文件
			initTemplate();
			//添加str
			//writeHandle("1100");
			
			//回滚数据
			CommonMethod.roleBackFiles(METHODNAME);
		}
		
		public static Map<String,Object> paramsHandle() throws Exception {
			Map<String,Object> maps=new HashMap<>();
			
			maps.put("remark", REMARK);
			maps.put("classNameLower", classNameLower);
			maps.put("methodName", METHODNAME);
			maps.put("methodParams", PARAMS);
			maps.put("returnType", RETURNTYPE);
			return maps;
		}
		
		/**
		 * 
		 */
		public static void writeHandle(String rule) throws IOException {
			String[] rules=rule.split("");
			//处理路径
			/*
			 */
			if("1".equals(rules[0])) {
				String control_filePath=classpath+suffixaction+"/"+CLASSNAME+Tools.fistUppercase(suffixaction)+".java";
				DataPool.add(CustomConstant.template_basepath_control+template_name[0], control_filePath);
			}
			/*
			 */
			if("1".equals(rules[1])) {
				String app_filePath=classpath+suffixapp+"/impl/"+CLASSNAME+Tools.fistUppercase(suffixapp)+".java";
				String app_implFilePath=classpath+suffixapp+"/I"+CLASSNAME+Tools.fistUppercase(suffixapp)+".java";
				DataPool.add(CustomConstant.template_basepath_app+template_name[0], app_implFilePath);
				DataPool.add(CustomConstant.template_basepath_app+template_name[1], app_filePath);
			}
			/*
			 */
			if("1".equals(rules[2])) {
				String service_filePath=classpath+suffixservice+"/impl/"+CLASSNAME+Tools.fistUppercase(suffixservice)+".java";
				String service_implFilePath=classpath+suffixservice+"/I"+CLASSNAME+Tools.fistUppercase(suffixservice)+".java";
				DataPool.add(CustomConstant.template_basepath_service+template_name[0], service_filePath);
				DataPool.add(CustomConstant.template_basepath_service+template_name[1], service_implFilePath);
			}
			
			/*
			 */
			//String name="dao";
			if("1".equals(rules[3])) {
				String dao_filePath=classpath+suffixdao+"/impl/"+CLASSNAME+Tools.fistUppercase(suffixdao)+".java";
				String dao_implFilePath=classpath+suffixdao+"/I"+CLASSNAME+Tools.fistUppercase(suffixdao)+".java";
				DataPool.add(CustomConstant.template_basepath_dao+template_name[0], dao_filePath);
				DataPool.add(CustomConstant.template_basepath_dao+template_name[1], dao_implFilePath);
			}
			
			CommonMethod.writeInfo(METHODNAME);
		}
		
		
		//TODO 位置不确定
		public static void writeStrutsXml() throws IOException {
			String str="";
			str +="\n<action name=\"searchClubById\" class=\"clubAction\" method=\"searchClubById\">";
			str +="\n\t<result name=\"success\" type=\"json\">";
			str +="\n\t\t<param name=\"root\">returnObj</param>";
			str +="\n\t</result>";
			str +="\n\t<result name=\"nopermission\">/common/error/403.htm</result>";
			str +="\n\t<result name=\"error\">/common/error/500.htm</result>";
			str +="\n</action>";
		}

		/**
		 * 自动生成模板
		 * 返回类型 void
		 * @throws Exception
		 * @注
		 */
		public static void initTemplate() throws Exception {
			GeneratorFacade g = new GeneratorFacade();
			// 模板地址
			g.getGenerator().addTemplateRootDir(CustomConstant.TEMPLATE_BASEPATH_RAW);
			// 自定义生成的参数
			GeneratorModel gm=new GeneratorModel();
			gm.templateModel.putAll(paramsHandle());
			//gm.filePathModel.put(key, value);
			//gm.templateModel.put("methodName", "addArticleAll");
			g.deleteBy(gm);
			g.generateBy(gm);
		}
	}
