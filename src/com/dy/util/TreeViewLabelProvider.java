package com.dy.util;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
/**
 * 树的标签提供器
 * @author dingye
 *
 */
public class TreeViewLabelProvider implements ILabelProvider {

	public TreeViewLabelProvider() {}
	//设置各级的图片
	public Image getImage(Object element) {
		//系统自带的定义图表
		String image1=ISharedImages.IMG_OBJ_FOLDER;
		String image2=ISharedImages.IMG_OBJ_FILE;
		String image3=ISharedImages.IMG_DEF_VIEW;
		String image4=ISharedImages.IMG_TOOL_COPY;
		
		//获取每个树的节点id
		Integer id=((EntityElement)element).getId();
		
		if(id==1)//一级目录设置节点图标
			return PlatformUI.getWorkbench().getSharedImages().getImage(image1);
		
		if(id==2)//二级目录设置节点图标
			return PlatformUI.getWorkbench().getSharedImages().getImage(image2);
		if(id==3){
			return PlatformUI.getWorkbench().getSharedImages().getImage(image3);
		}
		else
			return PlatformUI.getWorkbench().getSharedImages().getImage(image4);
	}

	//返回节点的文本
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
