package com.dy.editor;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
/**
 * 点击排序事件
 * @author dingye
 *
 */
public class SortProvider extends ViewerSorter {
	private int sort;

	public void Sorter(int sort) {//点击一次sort为1，再次点击为-1
		this.sort = sort;
		System.out.println(sort);
	}
	
	//排序器核心代码，返回值为一个整数
	public int compare(Viewer viewer, Object object1, Object object2) {
		StaffEntity s1 = (StaffEntity) object1;
		StaffEntity s2 = (StaffEntity) object2;
//		System.out.println("sort"+sort);
		switch (sort) {
		case 1: {
			Integer id1 = Integer.valueOf(s1.getId());
			Integer id2 = Integer.valueOf(s2.getId());
			int descid = id1.compareTo(id2);
			return descid;
		}
		case -1: {
			Integer id1 = Integer.valueOf(s1.getId());
			Integer id2 = Integer.valueOf(s2.getId());
			int ascid = id2.compareTo(id1);
			return ascid;
		}
		case 2: {
			String name1 = s1.getName();
			String name2 = s2.getName();
			int descname = name1.compareTo(name2);
			return descname;
		}
		case -2: {
			String name1 = s1.getName();
			String name2 = s2.getName();
			int ascname = name2.compareTo(name1);
			return ascname;
		}
		case 3: {
			Integer age1 = Integer.valueOf(s1.getAge());
			Integer age2 = Integer.valueOf(s2.getAge());
			int descAge = age1.compareTo(age2);
			return descAge;
		}

		case -3: {
			Integer age1 = Integer.valueOf(s1.getAge());
			Integer age2 = Integer.valueOf(s2.getAge());
			int ascAge = age2.compareTo(age1);
			return ascAge;
		}
		}
		return 0;
	}
}
