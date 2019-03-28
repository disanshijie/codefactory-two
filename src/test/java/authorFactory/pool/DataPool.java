package authorFactory.pool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存储池，全局
 * @url
 * @Description
 * @author admin
 * @date 2018年9月17日
 * @version V1.0
 * @说明
 */
public class DataPool {
	//数据池
	public static List<Map<String,String>> listMaps=new ArrayList<Map<String,String>>();
	
	/**
	 * 添加数据，去重
	 */
	public static void add(String key,String value) {
		Map<String,String> map=new HashMap<>();
		map.put(key, value);
		listMaps.add(map);
	}
	/**
	 * 根据key删除数据
	 */
	public static Integer delete(String key) {
		if(key!=null)
		for (Map<String, String> map : listMaps) {
            for (Map.Entry<String, String> m : map.entrySet()) {
        		if(key.equals(m.getKey())) {
        			listMaps.remove(m);
        			return 1;
        		}
            }
        }
		return 0;
	}
	/**
	 * 遍历数据
	 */
	public static void getDatas(CallBack call) {
		for (Map<String, String> map : listMaps) {
            for (Map.Entry<String, String> m : map.entrySet()) {
            	 call.slove(m.getKey(),m.getValue());
                //System.out.print(m.getKey() + "    ");
                //System.out.println(m.getValue());
            }
        }
	}
}

