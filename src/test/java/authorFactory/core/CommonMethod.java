package authorFactory.core;

import java.io.File;
import java.io.IOException;
import java.util.List;

import authorFactory.contant.CustomConstant;

public class CommonMethod {

	/**
	 * 写之前备份文件
	 * @Description	
	 * 返回类型 void
	 * @param filePath 文件路径
	 * @param methodName 备份文件夹名称
	 * @throws IOException
	 * @注
	 */
	public static void backUpFile(String filePath,String methodName) throws IOException {
		File back=new File(CustomConstant.BACKUPBASE+methodName);
		back.mkdirs();
		
		File readme=new File(CustomConstant.BACKUPBASE+methodName+"/readme.txt");
		if(!readme.exists()) {
			readme.createNewFile();
		}else {
			//TODO 说明之前已经运行过了 是不是应该不备份 防止多次运行备份覆盖
			//return;
		}
		Tools.writeAppend(readme, filePath+"\r\n", "UTF-8");
		Tools.copyFolder(new File(filePath),back);
	}
	/**
	 * 回滚
	 * @Description
	 * 返回类型 Boolean
	 * @param methodName	文件夹位置
	 * @return
	 * @注
	 */
	public static Boolean roleBackFiles(String methodName) {
		File readme=new File(CustomConstant.BACKUPBASE+methodName+"/readme.txt");
		List<String> lines=Tools.lines(readme);
		for (String str : lines) {
			if(str!=null && str.length()>1) {
				String name=new File(str).getName();
				try {
					Tools.copyFileByStreams(new File(CustomConstant.BACKUPBASE+methodName+"/"+name), new File(str));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}; 
	
	/**
	 * 文件中添加字符串
	 * 返回类型 void
	 * @param template
	 * @param filePath
	 * @throws IOException
	 * @注
	 */
	public static void writeAppend(String tempPath,String filePath,String methodName) throws IOException {
		//template转化为字符串
		String str=Tools.readToString(new File(tempPath));
		//备份原始文件
		backUpFile(filePath,methodName);
		//写入
		//Tools.insertNewWord(str,filePath,CustomConstant.JAVABYTE);
		Tools.insertNewWord2(str,filePath,CustomConstant.JAVABYTE);
	}
	
	/**
	 * 根据模板路径和项目文件的对应关系， 将模板文件加入项目文件中
	 * 返回类型 void
	 * @param methodName 需要生成的方法名
	 * @throws IOException
	 * @注
	 */
	public static void writeInfo(String methodName) throws IOException {
		
		DataPool.getDatas(new CallBack() {
			@Override
			public void slove(String a, String b) {
				System.out.println("----------");
				System.out.println(a);
				System.out.println(b);
				try {
					CommonMethod.writeAppend(a,b,methodName);
				} catch (IOException e) {
					// TODO 回滚数据  如果执行前删除了备份数据， 回滚有效； 如果执行前没有删除备份数据，回滚可能回滚之前的数据 ，所以还是手动回滚吧
					//CommonMethod.roleBackFiles(methodName);
					e.printStackTrace();
				}
			}
		});
	}
}
