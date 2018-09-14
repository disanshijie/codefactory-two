package authorFactory.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Tools {

	public static String fistUppercase(String str) {
		if(str!=null && str.length()>1) {
			return str.substring(0, 1).toUpperCase()+str.substring(1);
		}else {
			return null;
		}
	}
	public static String fistLowcase(String str) {
		if(str!=null && str.length()>1) {
			return str.substring(0, 1).toLowerCase()+str.substring(1);
		}else {
			return null;
		}
	}
	/**
	 * List<args>生成 优化的参数名称
	 * @Description
	 * 返回类型 String
	 * @param type
	 * @return
	 * @注
	 */
	public static String typeName(String type) {
		String str="args";
		if(type.contains("List<")) {
			str=type.substring(type.indexOf("<")+1, type.indexOf(">"));
			str =Tools.fistLowcase(str)+"s";
		}else {
			str=Tools.fistLowcase(type);
		}
		return str;
	}
	/**
	 * java文件 类末尾指定字符串前插入内容（末尾插入，再将key插入到文件结尾）
	 * @Description
	 * 返回类型 void
	 * @param str	需要插入的内容
	 * @param fileName	文件路径
	 * @param key	字符串byte数组 eg：XMLBYTE= {'<','/','m','a','p','p','e','r','>'};
	 * @注 保证key是文件结尾的 字符串
	 */
	public static void insertNewWord2(String str, String fileName,byte[] key) {
		byte[] temp=new byte[key.length];
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
            //  byte[] b =str.getBytes() ;
            //找到key在文件中的位置
            Long skip=-1L;
            for (long i = randomAccessFile.length() - key.length; i >0; i--) {
            	randomAccessFile.seek(i);
            	int k=randomAccessFile.read(temp);
            	//System.out.println(k);
            	
            	//找到 key 符号
            	Boolean flag=true;
            	for (int j = 0; j < key.length; j++) {
					if(key[j]!=temp[j]) {
						flag=false;break;
					}
				}
            	if(flag) {
            		skip=i;
            		break;
            	}
            	
            }
            if(skip!=-1L) {
            	randomAccessFile.seek(skip);
            	randomAccessFile.write(str.getBytes()); // }后面的一切都被覆盖 长度变为 原来长度+b的长度
            	
            	//将} 再次写入	末尾写入
            	randomAccessFile.seek(randomAccessFile.length());
            	randomAccessFile.write(key);
            	
            	randomAccessFile.close();
            }else {
            	//TODO 没有找到
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	/**
	 * 文件 指定其中字符串前插入 字符串 
	 * @Description
	 * 返回类型 void
	 * @param str	需要插入的内容
	 * @param fileName	文件路径
	 * @param key	字符串byte数组 eg：XMLBYTE= {'<','/','m','a','p','p','e','r','>'};
	 * @注
	 */
	public static void insertNewWord(String str, String fileName,byte[] key) {
		byte[] temp=new byte[key.length];
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
			//  byte[] b =str.getBytes() ;
			//找到key在文件中的位置
			Long skip=-1L;
			for (long i = randomAccessFile.length() - key.length; i >0; i--) {
				randomAccessFile.seek(i);
				int k=randomAccessFile.read(temp);
				//System.out.println(k);
				
				//找到 key 符号
				Boolean flag=true;
				for (int j = 0; j < key.length; j++) {
					if(key[j]!=temp[j]) {
						flag=false;break;
					}
				}
				if(flag) {
					skip=i;
					break;
				}
				
			}
			if(skip!=-1L) {
				//直接插入
				insertNewWord(skip, str+"\n\t", fileName);
			}else {
				//TODO 没有找到
				System.out.println("没有找到字符串");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * java文件 类末尾插入方法
	 * @Description
	 * 返回类型 void
	 * @param str	字符串
	 * @param fileName	文件 路径
	 * @注
	 */
	public static void insertNewWord(String str, String fileName,byte key) {
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
			//byte[] b = str.getBytes();
			//把后面的内容往后面挪
			Long skip=0L;
			for (long i = randomAccessFile.length() - 1; i >0; i--) {
				randomAccessFile.seek(i);
				byte temp = randomAccessFile.readByte();
				//找到 key 符号
				if(temp==key) {
					skip=i;
					break;
				}
			}
			randomAccessFile.seek(skip);
			randomAccessFile.write(str.getBytes()); // }后面的一切都被覆盖 长度变为 原来长度+b的长度
			//将} 再次写入
			randomAccessFile.seek(randomAccessFile.length());
			randomAccessFile.write(key);
			
			randomAccessFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 指定位置插入
	 * @Description
	 * 返回类型 void
	 * @param skip	前面按字符串个数
	 * @param str	插入的字符串
	 * @param fileName 文件地址
	 * @注
	 */
	public static void insertNewWord(long skip, String str, String fileName) {
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
			if (skip < 0 || skip > randomAccessFile.length()) {
				return;
			}
			byte[] b = str.getBytes();
			//System.out.println(b.length);
			//System.out.println(randomAccessFile.length());
			randomAccessFile.setLength(randomAccessFile.length() + b.length);
			//把后面的内容往后面挪
			for (long i = randomAccessFile.length() - 1; i > b.length + skip - 1; i--) {
				randomAccessFile.seek(i - b.length);
				byte temp = randomAccessFile.readByte();
				//System.out.println("byte值："+temp);
				randomAccessFile.seek(i);
				randomAccessFile.writeByte(temp);
			}
			randomAccessFile.seek(skip);
			randomAccessFile.write(b);
			randomAccessFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 /**
 	* @Description:将文件或着文件夹复制到另一个文件夹中去
 	* @author sun
 	* @date  2018年6月8日 上午11:10:27
 	* @param sourceFile	源，文件夹或者文件
 	* @param dest	目标路径，必须是文件夹；如果source是文件则自动创建此文件
 	* @throws IOException
 	 */
     public static void copyFolder(File sourceFile, File dest) throws IOException{
 		File destFile=new File(dest,sourceFile.getName());
 		
 		if(sourceFile.isFile()) {
 			destFile.createNewFile();
 			copyFileByStreams(sourceFile,destFile);
 		}else if(sourceFile.isDirectory()) {
 			destFile.mkdir();
 			File[] fileArray = sourceFile.listFiles();
 			for (File file : fileArray) {
 				/*如果source是文件夹，那么dest只能是文件夹
 				 * 如果source是文件，那么dest最好是文件
 				 */
 				copyFolder(file,destFile);
 			}
 		}
 	}
     /**
      * @Description 流的形式复制文件
      * 返回类型 void
      * @param srcFile	源文件
 	 * @param newFile	目标文件
      * @throws IOException
      * @注
      */
      public static void copyFileByStreams(File srcFile, File newFile) throws IOException {
  		InputStream input = null;    
  	    OutputStream output = null;  
  	    try {
  	    	 //复制文件到指定位置
  	    	 input = new BufferedInputStream(new FileInputStream(srcFile));
  	    	 output = new BufferedOutputStream(new FileOutputStream(newFile));
  	         byte[] b = new byte[1024];
  	         Integer len = 0;
  	         while((len = input.read(b)) != -1) {
  	        	output.write(b, 0, len);
  	         }
  	           
  		}finally {
  			try {
  				input.close();
  				output.close();
  			} catch (Exception e) {
  				e.printStackTrace();
  			}
  			
  	    }
  	}

	/**
	 * 将字符串以追加的方式以制定的编码写入到文件中
	 */
	public final static boolean writeAppend(File file, String str, String encoding) {
		try (RandomAccessFile randomFile = new RandomAccessFile(file, "rw")) {
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			randomFile.write(str.getBytes(encoding));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param f
	 *            将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful.
	 */
	public static boolean delDir(File f) {
		// 判断是否是一个目录, 不是的话跳过, 直接删除; 如果是一个目录, 先将其内容清空.
		if (f.isDirectory()) {
			// 获取子文件/目录
			File[] subFiles = f.listFiles();
			// 遍历该目录
			for (File subFile : subFiles) {
				// 递归调用删除该文件: 如果这是一个空目录或文件, 一次递归就可删除. 如果这是一个非空目录, 多次
				// 递归清空其内容后再删除
				boolean success = delDir(subFile);
				if (!success) {
					return false;
				}
			}
		}
		// 删除空目录或文件
		return f.delete();
	}

	/**
	 * 以列表的方式获取文件的所有行
	 *
	 * @param file
	 *            需要出来的文件
	 * @return 包含所有行的list
	 */
	public final static List<String> lines(File file) {
		List<String> list = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * file转字符串
	 * 返回类型 String
	 * @param file
	 * @return
	 * @注
	 */
	public static String readToString(File file) {
        String encoding = "UTF-8";
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();
        }  
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            //System.err.println("The OS does not support " + encoding);  
            e.printStackTrace();
            return null;  
        }  
    }
}
