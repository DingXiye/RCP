package com.dy.util;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
/**
 * 树透视图的内容提供器
 * @author dingye
 *
 */
public class TreeViewerContentProvider implements ITreeContentProvider{
	public TreeViewerContentProvider() {
		// TODO Auto-generated constructor stub
	}
	
	//返回父节点的子节点
	public Object[] getChildren(Object parentElement){
		ITreeElement treeElement= (ITreeElement)parentElement;
		List list=treeElement.getChildren();
		
		if(list==null||list.isEmpty())
			return new Object[0];//返回空数组
		else 
			return list.toArray();
	}
	
	public Object getParent(Object element){
		return null;
	}
	
	//返回给定元素是否有子元素
	public boolean hasChildren(Object element){
		ITreeElement treeElement= (ITreeElement)element;
		List list=treeElement.getChildren();
		if(list==null||list.isEmpty())
			return false;
		else 
			return true;
	}
	
	//当输入为设定的元素时，返回查看器中显示的元素
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof List){
			List list=(List)inputElement;
			return list.toArray();
		}else{
			return new Object[0];
		}
	}
	
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}
}
