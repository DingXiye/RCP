package com.dy.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
/**
 * 添加编辑器工具栏按钮和菜单
 * @author dingye
 *
 */
public class sampleAction implements IEditorActionDelegate{

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		MessageDialog.openInformation(null, "Hello", "This is Editor action");
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		// TODO Auto-generated method stub
		
	}
	
}
