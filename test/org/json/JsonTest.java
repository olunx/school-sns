package org.json;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

public class JsonTest {

	@Test
	public void getJson() {
		/*
"文学" : {
	"key" : 1, 
	"defaultvalue" : 2, 
	"values": {
		"语文" : 2,
		"数学" : 3
	}
},
"体育" : {
	"key" : 4, 	"defaultvalue" : 5, 
	"values" : {
		"足球" : 5,
		"篮球" : 6
	}
}

		 * */
		Map map=new HashMap();
		Map map2=new HashMap();
		Map map3=new HashMap();
		map3.put("数学", 3);
		map3.put("语文", 2);
		map2.put("values", map3);
		map2.put("defaultvalue",2);
		map2.put("key",1);
		map.put("文学", map2);
		System.out.println(JSONObject.fromObject(map));
	}
}
