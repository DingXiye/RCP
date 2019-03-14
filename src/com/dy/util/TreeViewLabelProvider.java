package com.dy.util;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
/**
 * ���ı�ǩ�ṩ��
 * @author dingye
 *
 */
public class TreeViewLabelProvider implements ILabelProvider {

	public TreeViewLabelProvider() {}
	//���ø�����ͼƬ
	public Image getImage(Object element) {
		//ϵͳ�Դ��Ķ���ͼ��
		String image1=ISharedImages.IMG_OBJ_FOLDER;
		String image2=ISharedImages.IMG_OBJ_FILE;
		String image3=ISharedImages.IMG_DEF_VIEW;
		String image4=ISharedImages.IMG_TOOL_COPY;
		
		//��ȡÿ�����Ľڵ�id
		Integer id=((EntityElement)element).getId();
		
		if(id==1)//һ��Ŀ¼���ýڵ�ͼ��
			return PlatformUI.getWorkbench().getSharedImages().getImage(image1);
		
		if(id==2)//����Ŀ¼���ýڵ�ͼ��
			return PlatformUI.getWorkbench().getSharedImages().getImage(image2);
		if(id==3){
			return PlatformUI.getWorkbench().getSharedImages().getImage(image3);
		}
		else
			return PlatformUI.getWorkbench().getSharedImages().getImage(image4);
	}

	//���ؽڵ���ı�
	public String getText(Object element) {
		ITreeElement treeElement=(ITreeElement)element;
		return treeElement.getName();
	}
	public void addListener(ILabelProviderListener listener) {
		
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}


}
