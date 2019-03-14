package com.dy.util;

import java.util.List;

import org.eclipse.ui.IEditorInput;

public interface ITreeElement {
	//设置树节点名称
	public void setName(String name);
	
	//得到节点名称
	public String getName();
	
	//设置与子节点集合
	public void setChildren(List children);
	
	//得到子节点集合
	public List getChildren();
	
	//是否有子孙
	public boolean hasChildren();
	
	//添加子节点
	public void addChild(ITreeElement treeElement);
	
	public IEditorInput getEditorInput();
	
	public void setEditorInput(IEditorInput editorInput);
}
