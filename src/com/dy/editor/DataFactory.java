package com.dy.editor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 创建数据工厂
 * @author dingye
 *
 */
public class DataFactory {

	public static List getFactoryData() {
		List list=new ArrayList();
		list.add(new StaffEntity(1, "王", true, 22, 1234567, "市场部", "王二",new Date()));
		list.add(new StaffEntity(2, "李", false, 27, 123451, "商务部", "李二", new Date()));
		list.add(new StaffEntity(3, "章", true, 23, 123234, "财务部", "章二", new Date()));
		list.add(new StaffEntity(4, "赵", false, 29, 1245451, "商务部", "赵二", new Date()));
		return list;
	}

}
