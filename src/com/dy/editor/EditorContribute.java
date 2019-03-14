package com.dy.editor;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorActionBarContributor;
/*
 * ��һ����ӹ���̨��������ť�ķ���
 */
public class EditorContribute extends EditorActionBarContributor{

	private Action toolBarAction;
	public EditorContribute(){
		makeActions();
	}
	public void makeActions() {
		toolBarAction=new Action() {
			public void run(){//��Ӿ������
				System.out.println("ִ�е���");
			}
		};
		toolBarAction.setToolTipText("��������ť����");
		//��Ӱ�ťͼ��
		toolBarAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_TASK_TSK));
	}
	
	//��ӹ���̨�˵�����
	public void contributeToMenu(IMenuManager menuManager){
		
	}
	
	public void contirbuteToToolBar(IToolBarManager toolBarManager){
		toolBarManager.add(new Separator());//���÷ָ��
		toolBarManager.add(toolBarAction);//��ӹ�������ť
	}
	
//	class action1 extends Action{
//		public action1() {
//			setToolTipText("��������ť����");
//			setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_TASK_TSK));
//		}
//		public void run(){
//			
//			
//		}
//	}
}
