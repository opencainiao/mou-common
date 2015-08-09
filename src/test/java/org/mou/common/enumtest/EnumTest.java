package org.mou.common.enumtest;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mou.common.EnumUtils;
import org.mou.common.JsonUtil;

public class EnumTest {
	@Test
	public void enum2OrdinalTest() {
		List<Integer> ordinal = EnumUtils.enum2Ordinal(Sex.class);

		String json = JsonUtil.getPrettyJsonStr(ordinal);

		System.out.println(json);
	}

	@Test
	public void enum2NameTest() {
		List<String> enum2Name = EnumUtils.enum2Name(Sex.class);

		String json = JsonUtil.getPrettyJsonStr(enum2Name);

		System.out.println(json);
	}

	/**
	 * 将枚举中的值转换为序号和名称的map
	 * 
	 * @param clz
	 * @return
	 */
	@Test
	public void enum2BasicMapTest() {
		Map<Integer, String> enum2BasicMap = EnumUtils.enum2BasicMap(Sex.class);

		System.out.println(enum2BasicMap);
		String json = JsonUtil.getPrettyJsonStr(enum2BasicMap);

		System.out.println(json);
	}

	@Test
	public void enumProp2ListTest() {
		List<String> enumProp2List = EnumUtils.enumProp2List(Sex.class, "code");
		System.out.println(enumProp2List);

		List<String> enumProp2List2 = EnumUtils.enumProp2List(Sex.class, "name");
		System.out.println(enumProp2List2);
	}

	/**
	 * 将枚举中的值的某个属性转换为序号和字符串列表
	 * 
	 * @param clz
	 * @param propName某个属性值
	 * @return
	 */
	@Test
	public void enumProp2OrdinalMapTest() {
		Map<Integer, String> enumProp2OrdinalMap = EnumUtils.enumProp2OrdinalMap(Sex.class, "code");
		System.out.println(enumProp2OrdinalMap);

		enumProp2OrdinalMap = EnumUtils.enumProp2OrdinalMap(Sex.class, "name");
		System.out.println(enumProp2OrdinalMap);
	}

	/**
	 * 将枚举中的值的某个属性转换为名称和字符串map
	 * 
	 * @param clz
	 * @param propName某个属性值
	 * @return
	 */
	@Test
	public void enumProp2NameMapTest() {
		Map<String, String> enumProp2OrdinalMap = EnumUtils.enumProp2NameMap(Sex.class, "code");
		System.out.println(enumProp2OrdinalMap);

		enumProp2OrdinalMap = EnumUtils.enumProp2NameMap(Sex.class, "name");
		System.out.println(enumProp2OrdinalMap);
	}

	/**
	 * 将枚举中的两个属性转换为map<br>
	 * {1=男, 0=女}
	 * 
	 * @param clz
	 * @param keyProp
	 *            要转化的key的属性名称
	 * @param valueProp
	 *            要转换的value的属性名称
	 * @return
	 */
	@Test
	public void enumProp2MapTest() {
		Map<String, String> enumProp2OrdinalMap = EnumUtils.enumProp2Map(Sex.class, "code", "name");
		System.out.println(enumProp2OrdinalMap);

	}

}
