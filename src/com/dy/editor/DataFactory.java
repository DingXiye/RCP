package com.dy.editor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * �������ݹ���
 * @author dingye
 *
 */
public class DataFactory {

	public static List getFactoryData() {
		List list=new ArrayList();
		list.add(new StaffEntity(1, "��", true, 22, 1234567, "�г���", "����",new Date()));
		list.add(new StaffEntity(2, "��", false, 27, 123451, "����", "���", new Date()));
		list.add(new StaffEntity(3, "��", true, 23, 123234, "����", "�¶�", new Date()));
		list.add(new StaffEntity(4, "��", false, 29, 1245451, "����", "�Զ�", new Date()));
		return list;
	}

}
