package com.dy.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

/**
 * 添加下拉菜单
 * @author dingye
 *
 */
public class DropAction extends Action{
	public DropAction() {
		super("DropDown",Action.AS_DROP_DOWN_MENU);
		setToolTipText("dropdown menu");//提示信息
		//设置按钮图标
		ImageDescriptor descriptor=WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_DTOOL_NEW_FASTVIEW);
		setImageDescriptor(descriptor);
		//设置下拉菜单
		setMenuCreator(new IMenuCreator() {
			@Override
			public Menu getMenu(Menu parent) {
				return null;
			}
			
			@Override
			public Menu getMenu(Control parent) {
				Menu menu=new Menu(parent);
				//设置菜单项
				MenuItem item1=new MenuItem(menu,SWT.NONE);
				item1.setText("&FirstItem");
				item1.addSelectionListener(new SelectionAdapter(){
					public void widgetSelected(SelectionEvent event){
						MessageDialog.openInformation(new Shell(Display.getDefault()), "DropDown Dialog","This is FirstItem DropDown Message");
					}
				});
				
				MenuItem item2=new MenuItem(menu,SWT.NONE);
				item2.setText("&SecondItem");
				
				MenuItem item3=new MenuItem(menu,SWT.NONE);
				item3.setText("&ThirdItem");

				MenuItem item4=new MenuItem(menu,SWT.NONE);
				item4.setText("&FourItem");
				return menu;
			}
			
			@Override
			public void dispose() {
				// TODO Auto-generated method stub
			}
		});
	}
	
	public void run(){
		MessageDialog.openInformation(new Shell(Display.getDefault()), "DropDown Dialog", "DropDown Message!!");
	}
}
