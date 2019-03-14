package com.dy.editor;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
/**
 * 标签提供器
 * @author dingye
 *
 */
public class TableViewLabelProvider implements ITableLabelProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}
	
	//获取表格中的元素
	public String getColumnText(Object element, int columnIndex) {
		StaffEntity entity=(StaffEntity)element;
		switch (columnIndex) {
		case 0:
			return ""+entity.getId();
		case 1:
			return entity.getName();
		case 2:
			return entity.isSex()? "男":"女";
		case 3:
			return ""+entity.getAge();
		case 4:
			return ""+entity.getPhone();
		case 5: 
			return entity.getDepartment();
		case 6:
			return entity.getRelatPeople();
		case 7:
			return entity.getCreateDate().toLocaleString();
		}
		return null;
	}

	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}
}
