package com.dy.util;

import java.util.List;

import org.eclipse.ui.IEditorInput;

public interface ITreeElement {
	//�������ڵ�����
	public void setName(String name);
	
	//�õ��ڵ�����
	public String getName();
	
	//�������ӽڵ㼯��
	public void setChildren(List children);
	
	//�õ��ӽڵ㼯��
	public List getChildren();
	
	//�Ƿ�������
	public boolean hasChildren();
	
	//����ӽڵ�
	public void addChild(ITreeElement treeElement);
	
	public IEditorInput getEditorInput();
	
	public void setEditorInput(IEditorInput editorInput);
}
