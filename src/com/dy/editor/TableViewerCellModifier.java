package com.dy.editor;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.TableItem;
/**
 * 表单修改器
 * @author dingye
 *
 */
public class TableViewerCellModifier implements ICellModifier {
	private Viewer viewer;
	public TableViewerCellModifier(Viewer view) {
		this.viewer=view;
	}

	/**
	 * 检查给定元素的属性是否能被修改
	 */
	@Override
	public boolean canModify(Object element, String property) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 返回表格记录的属性修改的值，null则不需要修改
	 */
	@Override
	public Object getValue(Object element, String property) {
		StaffEntity entity=(StaffEntity)element;
//		System.out.println(entity.getId());
		if(property.equals("ID"))
			return null;
		else if(property.equals("Name"))
			return null;
		else if(property.equals("Male"))
			return entity.isSex()?new Boolean(true):new Boolean(false);
		else if(property.equals("Age"))
			return entity.getAge()+"";
		else if(property.equals("Phone"))
			return null;
		else if(property.equals("Department"))
			return new Integer(0);
		else if(property.equals("Relat"))
			return null;
		else if(property.equals("DateTime"))
			return null;
		else 
			return null;
	}

	/**
	 * 修改表格记录的属性值，只对male，age，department属性进行修改设定
	 */
	@Override
	public void modify(Object element, String property, Object value) {
		TableItem item=(TableItem) element;
		StaffEntity entity=(StaffEntity)item.getData();
//		System.out.println(entity.getName());
		//判断要修改的单元格是否为性别列
		if(property.equals("Male"))
			entity.setSex(((Boolean)value).booleanValue());
		else if(property.equals("Age"))
			entity.setAge((new Integer((String)value).intValue()));
		else if(property.equals("Department"))
			entity.setDepartment(editorOne.departments[((Integer)value).intValue()]);
		
		else 
			return;
		//修改更新
		viewer.refresh();
	}

}
