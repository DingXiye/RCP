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
		
		//����������ʾ�Ա�ǩ
		setToolTipText("dropdown menu Action");
		
		//���ð�ťͼ��
		//�������Ҳ�����IMG_ETOOL_DEF_PERSPECTIVE��;
		ImageDescriptor imgDes = WorkbenchImages.getImageDescriptor
				(IWorkbenchGraphicConstants.IMG_DTOOL_NEW_FASTVIEW);
		
		setImageDescriptor(imgDes);
		//���������˵�
		setMenuCreator(new IMenuCreator() {
			public Menu getMenu(Control parent) {
				// TODO Auto-generated method stub
				//����˵�
				Menu menu = new Menu(parent);
				//���ò˵���
				MenuItem item1 = new MenuItem(menu, SWT.NONE);
				//���ò˵����ı�����δ�˵�����ӿ�ݷ�ʽ
				item1.setText("&firstItem");
				//������ﴦ��
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
