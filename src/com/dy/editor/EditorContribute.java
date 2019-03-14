package com.dy.editor;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorActionBarContributor;
/*
 * 第一种添加工作台工具栏按钮的方法
 */
public class EditorContribute extends EditorActionBarContributor{

	private Action toolBarAction;
	public EditorContribute(){
		makeActions();
	}
	public void makeActions() {
		toolBarAction=new Action() {
			public void run(){//添加具体操作
				System.out.println("执行到了");
			}
		};
		toolBarAction.setToolTipText("工具栏按钮操作");
		//添加按钮图表
		toolBarAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_TASK_TSK));
	}
	
	//添加工具台菜单方法
	public void contributeToMenu(IMenuManager menuManager){
		
	}
	
	public void contirbuteToToolBar(IToolBarManager toolBarManager){
		toolBarManager.add(new Separator());//设置分割符
		toolBarManager.add(toolBarAction);//添加工具栏按钮
	}
	
//	class action1 extends Action{
//		public action1() {
//			setToolTipText("工具栏按钮操作");
//			setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_TASK_TSK));
//		}
//		public void run(){
//			
//			
//		}
//	}
}
