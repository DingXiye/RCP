package com.dy.view;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.part.ViewPart;

import com.dy.sampleEditor.SampleEditorInput;
/**
 * sampleview1 ”Õº
 * @author dingye
 *
 */
public class view2 extends ViewPart{
	private List list;
	public view2() {
		// TODO Auto-generated constructor stub
	}
	
	public void createPartControl(Composite parent){
		list=new List(parent,SWT.None);
		list.setItems(new String[]{"sampleview","sampleeditor","muleditor"});
		hookClickAction();//œÏ”¶sampleeditor
	}
	
	private void hookClickAction() {
		list.addMouseListener(new MouseAdapter() {
			SampleEditorInput sampleEditorInput=new SampleEditorInput();
			IWorkbenchPage iWorkbenchPage=getViewSite().getPage();
			public void mouseDoubleClick(MouseEvent e){
				IEditorInput editorInput=null;
				String editorID=null;
				if(list.getSelectionIndex()==1){
					editorInput=sampleEditorInput;
					editorID="editor3";
					//≤È’“±‡º≠∆˜
					IEditorPart editorPart=iWorkbenchPage.findEditor(editorInput);
					if(editorPart!=null){
						iWorkbenchPage.bringToTop(editorPart);
					}else
						try{
							editorPart=iWorkbenchPage.openEditor(editorInput, editorID);
						}catch(Exception ex){
							System.out.println(ex);
						}
				}
			}
		});
	}

	public void setFocus(){
		
	}
}
