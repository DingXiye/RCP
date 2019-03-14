package com.dy.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.IEditorInput;

public class EntityElement implements ITreeElement,Serializable,Cloneable{
	private String name;
	private Integer id;//记录节点的层数
	private String parentId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	private IEditorInput editorInput;
//	private EntityElement 
	private List<ITreeElement> childNode=new ArrayList<ITreeElement>();//子节点
	public List<ITreeElement> getChildNode() {
		return childNode;
	}

	public void setChildNode(List<ITreeElement> childNode) {
		this.childNode = childNode;
	}

	public EntityElement() {
		// TODO Auto-generated constructor stub
	}
	

	public EntityElement(String name, String parentId,Integer id) {
		super();
		this.name = name;
		this.parentId = parentId;
		this.id=id;
	}

	public void setName(String name) {
		this.name=name;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public void setChildren(List children) {
		this.childNode=children;
	}

	public List getChildren() {
		return childNode;
	}

	public boolean hasChildren() {
		if(childNode.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void addChild(ITreeElement treeElement) {
		// TODO Auto-generated method stub
		childNode.add(treeElement);
	}

	@Override
	public IEditorInput getEditorInput() {
		// TODO Auto-generated method stub
		return editorInput;
	}

	@Override
	public void setEditorInput(IEditorInput editorInput) {
		// TODO Auto-generated method stub
		this.editorInput=editorInput;
	}
	
	public Object clone(){
		EntityElement el=null;
		try{
			el=(EntityElement) super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return el;
	}
}
