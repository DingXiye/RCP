package com.rengu.demo.views;

import java.util.List;

public interface ITreeElement {
	//�������ڵ�����
	public void setname(String name);
	
	//�õ����ڵ�����
	public String getname();
	//�����ӽڵ㼯��
	public void setchildren(List children);
	//�õ��ӽڵ㼯��
	public List getchildren();
	//�Ƿ�������
	public boolean hasChildren();
	//����ӽڵ�
	public void addChild(ITreeElement iTreeElement);
	

}
