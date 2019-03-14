package com.dy.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
/**
 * ��һ���˵�����ť����Open��ʹ��
 * @author dingye
 *
 */
public class Myaction extends Action{
	private IWorkbenchWindow window; //����̨����
	public Myaction(IWorkbenchWindow window){
		this.window=window;
		this.setText("&Navigtor@Ctrl+Alt+N");
		setToolTipText("open view");
		ImageDescriptor imag=WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_LCL_PIN_VIEW);
		this.setImageDescriptor(imag);
		
	}
	public void run(){
		MessageDialog.openInformation(window.getShell(), "Message", "This is Navigtor");
	}
	
}
