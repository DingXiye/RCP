package com.rengu.demo.views;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class NvaigatorView  extends ViewPart{
	private TreeViewer tv;
	public NvaigatorView(){
		
		
	}
//为视图添加窗口部件
	public void createPartControl(Composite parent) {
		
		//定义TreeViewer
		tv=new TreeViewer(parent,SWT.MULTI|SWT.H_SCROLL|SWT.V_SCROLL);
		//设置内容提供 器
		
		
	}

	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
