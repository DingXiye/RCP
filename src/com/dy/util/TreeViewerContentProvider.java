package com.dy.util;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
/**
 * ��͸��ͼ�������ṩ��
 * @author dingye
 *
 */
public class TreeViewerContentProvider implements ITreeContentProvider{
	public TreeViewerContentProvider() {
		// TODO Auto-generated constructor stub
	}
	
	//���ظ��ڵ���ӽڵ�
	public Object[] getChildren(Object parentElement){
		ITreeElement treeElement= (ITreeElement)parentElement;
		List list=treeElement.getChildren();
		
		if(list==null||list.isEmpty())
			return new Object[0];//���ؿ�����
		else 
			return list.toArray();
	}
	
	public Object getParent(Object element){
		return null;
	}
	
	//���ظ���Ԫ���Ƿ�����Ԫ��
	public boolean hasChildren(Object element){
		ITreeElement treeElement= (ITreeElement)element;
		List list=treeElement.getChildren();
		if(list==null||list.isEmpty())
			return false;
		else 
			return true;
	}
	
	//������Ϊ�趨��Ԫ��ʱ�����ز鿴������ʾ��Ԫ��
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
