package com.dy.view;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.part.NullEditorInput;
import org.eclipse.ui.part.ViewPart;

import com.dy.TreeInput.InputOne;
import com.dy.TreeInput.InputTwo;
/**
 * ��ҳ�༭������ͼ
 * @author dingye
 *
 */
public class view3 extends ViewPart{
	private Composite top=null;
	private Button button=null;
//	private Button button1=null;
//	private Button button2=null;
	public view3() {
		// TODO Auto-generated constructor stub
	}
	String editorid;
//	InputOne editorInput;
	@Override
	public void createPartControl(Composite parent) {
		top=new Composite(parent, SWT.NONE);
		top.setLayout(new GridLayout());
		button=new Button(top, SWT.NONE);
		button.setText("�򿪶�ҳ�༭��");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event){
				try{
					getViewSite().getWorkbenchWindow().getActivePage().openEditor(new NullEditorInput(), "test.editor2");
				}catch(Exception e){
					System.out.println(e);
				}
			}
		});
		
//		button1=new Button(top, SWT.NONE);
//		button1.setText("����һ");
//		button1.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent event){
//				editorInput=new InputOne();
//				editorid = "treeview";
//				IWorkbenchPage workbenchPage = getViewSite().getPage();
//				IEditorPart editorPart = workbenchPage.findEditor(new InputOne());
//				// ����Ҫ�򿪵ı༭��
//				System.out.println("input"+editorInput.getName());
//				if (editorPart != null) {
//					System.out.println("-------------");
//					workbenchPage.bringToTop(editorPart);
//				} else {
//					try {
//						//��editor������༭����plugin�е�ΨһidΪtest.editor1
//						editorPart = workbenchPage.openEditor(editorInput, editorid);
//					} catch (PartInitException e) {
//						System.out.println(e);
//					}
//				}
//			}
//		});
//		button2=new Button(top, SWT.NONE);
//		button2.setText("������");
//		button2.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent event){
//				try{
//					getViewSite().getWorkbenchWindow().getActivePage().openEditor(new InputTwo(), "treeview");
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
//		});
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
