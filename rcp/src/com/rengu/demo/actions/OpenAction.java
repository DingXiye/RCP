package com.rengu.demo.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

public class OpenAction extends Action {

	private IWorkbenchWindow window;

	public OpenAction(IWorkbenchWindow window) {
		this.window = window;
		// �����ı���ݼ���
		this.setText("&Navigator@Ctrl+A+N");
		// ����������ʾ�Ա�ǩ
		setToolTipText("Open View Action");
		// ���ò˵��������ͼ��
		ImageDescriptor imdDs = WorkbenchImages
				.getImageDescriptor(IWorkbenchGraphicConstants.IMG_LCL_PIN_VIEW);
		this.setImageDescriptor(imdDs);
	}

	public void run() {
		MessageDialog.openInformation(window.getShell(), "Myrcp Plug-in",
				"Hello,Eclipse Word");
	}

}
