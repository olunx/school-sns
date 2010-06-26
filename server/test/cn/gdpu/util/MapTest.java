package cn.gdpu.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class MapTest {

	@Test
	public void test(){
		Map map = new HashMap();
		map.put(1, "aaa");
		map.put(1, "bbb");
		System.out.println(map);
		map.remove(1);
	}
}
