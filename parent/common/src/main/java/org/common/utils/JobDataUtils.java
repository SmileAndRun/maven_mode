package org.common.utils;

import java.util.HashMap;
import java.util.List;






import org.common.model.QrtzJobData;

import com.alibaba.fastjson.JSONObject;

public class JobDataUtils {
	public static Object translate(String jobClass,Object obj){
		JSONObject jsonObject = null;
		HashMap<String, Integer[]> map = null;
		if(jobClass.equals("com.bdcom.server.core.quartz.model.BarrageJob")){
			@SuppressWarnings("unchecked")
			List<QrtzJobData> list = (List<QrtzJobData>)obj;
			String[] time = new String[list.size()];
			Integer[] value = new Integer[list.size()];
			for(int i=0;i<list.size();i++){
				time[i] = TimeUtils.get(null).format(list.get(i).getEXCUTETIME());
				value[i] = Integer.parseInt(list.get(i).getJOBDATA());
			}
			jsonObject = new JSONObject();
			jsonObject.put("title", "countBulletScreenTask");
			jsonObject.put("time", time);
			jsonObject.put("yTitle", "number");
			map = new HashMap<String,Integer[]>();
			map.put("barrage", value);
			jsonObject.put("datas", map);
		}
		return jsonObject;
	}
}
