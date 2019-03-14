package com.dy.editor;

import java.io.Serializable;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
/**
 * 编辑器工具类
 * @author dingye
 *
 */
public class editorOneInput implements IEditorInput,Serializable{
	public editorOneInput() {
		// TODO Auto-generated constructor stub
	}

	// 获得一个适配器
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	// 返回编辑器是否存在
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	//返回输入的图像描述
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//返回编辑器标题栏名称
	public String getName() {
		// TODO Auto-generated method stub
		return "员工档案";
	}

	//返回能够用来可以保存编辑器输入状态的对象
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "员工管理/员工档案";
	}

}
