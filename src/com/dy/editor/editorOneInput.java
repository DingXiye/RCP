package com.dy.editor;

import java.io.Serializable;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
/**
 * �༭��������
 * @author dingye
 *
 */
public class editorOneInput implements IEditorInput,Serializable{
	public editorOneInput() {
		// TODO Auto-generated constructor stub
	}

	// ���һ��������
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	// ���ر༭���Ƿ����
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	//���������ͼ������
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//���ر༭������������
	public String getName() {
		// TODO Auto-generated method stub
		return "Ա������";
	}

	//�����ܹ��������Ա���༭������״̬�Ķ���
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "Ա������/Ա������";
	}

}
