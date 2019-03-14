package com.rengu.demo.views;

import java.util.List;

public interface ITreeElement {
	//设置树节点名称
	public void setname(String name);
	
	//得到树节点名称
	public String getname();
	//设置子节点集合
	public void setchildren(List children);
	//得到子节点集合
	public List getchildren();
	//是否有子孙
	public boolean hasChildren();
	//添加子节点
	public void addChild(ITreeElement iTreeElement);
	

}
