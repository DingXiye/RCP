package com.dy.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import com.dy.view.view2;
/**
 * 通过扩展点添加视图工具和菜单栏
 * 1、在xml中创建扩展点
 * 2、创建操作类
 * @author dingye
 *
 */
public class ViewActionDelegate implements IViewActionDelegate{
	private view2 view2;
	
	public ViewActionDelegate() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run(IAction action) {
		MessageDialog.openInformation(null, "Message", "this is viewActionDelegate");
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IViewPart view) {
		this.view2=(view2)view;
		
	}

}
