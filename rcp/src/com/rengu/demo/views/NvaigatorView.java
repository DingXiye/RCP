package com.rengu.demo.views;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class NvaigatorView  extends ViewPart{
	private TreeViewer tv;
	public NvaigatorView(){
		
		
	}
//Ϊ��ͼ��Ӵ��ڲ���
	public void createPartControl(Composite parent) {
		
		//����TreeViewer
		tv=new TreeViewer(parent,SWT.MULTI|SWT.H_SCROLL|SWT.V_SCROLL);
		//���������ṩ ��
		
		
	}

	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
