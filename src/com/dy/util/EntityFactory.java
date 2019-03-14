package com.dy.util;

import java.util.ArrayList;
import java.util.List;

import com.dy.TreeInput.EditorInputAnalysis;
import com.dy.TreeInput.editorSalary;
import com.dy.editor.editorOneInput;
/**
 * 实体工厂
 * @author dingye
 *
 */
public class EntityFactory {
	//设置集合
	public static List<EntityElement> TreeEntityElement() {
		//一级目录
		EntityElement root1=new EntityElement("员工管理","root",1);
		EntityElement root2=new EntityElement("产品管理","root",1);
		
		//二级目录
		EntityElement level1=new EntityElement("员工档案","员工管理",2);
		EntityElement level2=new EntityElement("员工薪资","员工管理",2);
		EntityElement level3=new EntityElement("产品分析","产品管理",2);
		EntityElement level4=new EntityElement("产品报价","产品管理",2);
		
		//三级层次
		EntityElement leaf1=new EntityElement("短信网址","产品报价",3);
		EntityElement leaf2=new EntityElement("传统网站","产品报价",3);
		EntityElement leaf3=new EntityElement("wap网站","产品报价",3);
		
		level1.setEditorInput(new editorOneInput());//给员工档案添加一个编辑器
		level2.setEditorInput(new editorSalary());
		level3.setEditorInput(new EditorInputAnalysis());
		//将二级添加到root1中
		root1.addChild(level1);
		root1.addChild(level2);
		
		root2.addChild(level3);
		root2.addChild(level4);
		
		
		level4.addChild(leaf1);
		level4.addChild(leaf2);
		level4.addChild(leaf3);
		
		{//设置在统一集合中返回
			ArrayList list=new ArrayList();
			list.add(root1);
			list.add(root2);
			return list;
		}
	}

}
