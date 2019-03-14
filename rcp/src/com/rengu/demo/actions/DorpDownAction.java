package com.rengu.demo.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

public class DorpDownAction  extends Action{

	public DorpDownAction(){
		
		super("DropDown",Action.AS_DROP_DOWN_MENU);
		
		//工具栏上提示性标签
		setToolTipText("dropdown menu Action");
		
		//设置按钮图标
		//此属性找不到（IMG_ETOOL_DEF_PERSPECTIVE）;
		ImageDescriptor imgDes = WorkbenchImages.getImageDescriptor
				(IWorkbenchGraphicConstants.IMG_DTOOL_NEW_FASTVIEW);
		
		setImageDescriptor(imgDes);
		//设置下拉菜单
		setMenuCreator(new IMenuCreator() {
			public Menu getMenu(Control parent) {
				// TODO Auto-generated method stub
				//定义菜单
				Menu menu = new Menu(parent);
				//设置菜单项
				MenuItem item1 = new MenuItem(menu, SWT.NONE);
				//设置菜单项文本，并未菜单项添加快捷方式
				item1.setText("&firstItem");
				//添加事物处理
				item1.addSelectionListener(new SelectionAdapter() {
					public void widgeSelected(SelectionEvent e){
						MessageDialog.openInformation(new Shell(Display.getDefault()),
						"DropDown Dialog", "This is FirstItem DropDown MessAges!");
					}
				});
				MenuItem item2=new MenuItem(menu, SWT.NONE);
				item2.setText("&SecondItem");
				MenuItem item3=new MenuItem(menu, SWT.NONE);
				item3.setText("&ThirdItem");
				MenuItem item4=new MenuItem(menu, SWT.NONE);
				item4.setText("&FourItem");
				return menu;
			}
			
			public Menu getMenu(Menu parent) {
				// TODO Auto-generated method stub
				return null;
			}
			public void dispose() {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
	}
}
