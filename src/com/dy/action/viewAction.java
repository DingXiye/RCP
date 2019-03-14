package com.dy.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
/**
 * ���� ͨ����������ͼ�������Ͳ˵�
 * 1������������viewAction
 * 2���޸�view��
 * ����ͼ��ť��ӵ�Open��
 * @author dingye
 *
 */
public class viewAction extends Action{
	private final IWorkbenchWindow window;
	private final String viewid="view1";
	
	public viewAction(IWorkbenchWindow window) {
		this.window=window;
		this.setText("&view1@Ctrl+Alt+S");
		setToolTipText("open view1");
		ImageDescriptor imag=WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_LCL_PIN_VIEW);
		this.setImageDescriptor(imag);
	}
	
	public viewAction(){
		window=null;
		this.setText("&view1@Ctrl+Alt+S");
		setToolTipText("open view1");
		ImageDescriptor imag=WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_LCL_PIN_VIEW);
		this.setImageDescriptor(imag);
	}
	
	
	public void run(){
		if(window!=null){
			try{
				System.out.println("spoint");
				window.getActivePage().showView(viewid);
				System.out.println("epoint");
			}catch(PartInitException e){
				MessageDialog.openError(window.getShell(), "Error", "Error opening view"+e.getMessage());
			}
		}else {
			MessageDialog.openInformation(window.getShell(), "Warning", "Please Input Data");
		}
	}
}
