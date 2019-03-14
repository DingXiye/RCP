package com.dy.util;

import java.util.ArrayList;
import java.util.List;

import com.dy.TreeInput.EditorInputAnalysis;
import com.dy.TreeInput.editorSalary;
import com.dy.editor.editorOneInput;
/**
 * ʵ�幤��
 * @author dingye
 *
 */
public class EntityFactory {
	//���ü���
	public static List<EntityElement> TreeEntityElement() {
		//һ��Ŀ¼
		EntityElement root1=new EntityElement("Ա������","root",1);
		EntityElement root2=new EntityElement("��Ʒ����","root",1);
		
		//����Ŀ¼
		EntityElement level1=new EntityElement("Ա������","Ա������",2);
		EntityElement level2=new EntityElement("Ա��н��","Ա������",2);
		EntityElement level3=new EntityElement("��Ʒ����","��Ʒ����",2);
		EntityElement level4=new EntityElement("��Ʒ����","��Ʒ����",2);
		
		//�������
		EntityElement leaf1=new EntityElement("������ַ","��Ʒ����",3);
		EntityElement leaf2=new EntityElement("��ͳ��վ","��Ʒ����",3);
		EntityElement leaf3=new EntityElement("wap��վ","��Ʒ����",3);
		
		level1.setEditorInput(new editorOneInput());//��Ա���������һ���༭��
		level2.setEditorInput(new editorSalary());
		level3.setEditorInput(new EditorInputAnalysis());
		//��������ӵ�root1��
		root1.addChild(level1);
		root1.addChild(level2);
		
		root2.addChild(level3);
		root2.addChild(level4);
		
		
		level4.addChild(leaf1);
		level4.addChild(leaf2);
		level4.addChild(leaf3);
		
		{//������ͳһ�����з���
			ArrayList list=new ArrayList();
			list.add(root1);
			list.add(root2);
			return list;
		}
	}

}
