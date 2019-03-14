package rcp;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.StatusLineContributionItem;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

import com.rengu.demo.actions.DorpDownAction;
import com.rengu.demo.actions.OpenAction;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private IWorkbenchAction iExitAction;
	private IWorkbenchAction iAboutAction;
	private IWorkbenchAction iNewWindowAction;
	private IWorkbenchAction iSaveAction;
	private IWorkbenchAction iRefAction;
	private OpenAction openAction;
	private DorpDownAction iDorpDownAction;
	

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	openAction=new OpenAction(window);
    	iDorpDownAction =new DorpDownAction();
    	
    	iExitAction=ActionFactory.QUIT.create(window);
    	register(iExitAction);
    	
    	iRefAction =ActionFactory.REFRESH.create(window);
    	register(iRefAction);
    	
        iSaveAction =ActionFactory.SAVE.create(window);
        register(iSaveAction);
        
        iAboutAction=ActionFactory.ABOUT.create(window);
        register(iAboutAction);
        
        iNewWindowAction=ActionFactory.OPEN_NEW_WINDOW.create(window);
        register(iNewWindowAction);
        
      //Ϊ���ò�����Ӱ�ťͼ��
        ImageDescriptor imgDs = WorkbenchImages.getImageDescriptor
        (IWorkbenchGraphicConstants.IMG_DTOOL_NEW_FASTVIEW);
        iAboutAction.setImageDescriptor(imgDs);
        register(iAboutAction);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	
    	// ����Menumanager���󣬼�����һ��window����һ���˵���
    	MenuManager windowMenu = new MenuManager("&Window",IWorkbenchActionConstants.M_WINDOW);
    	
    	// ����"show view"�˵�������˵���
    	MenuManager showViewMenu = new MenuManager("&Show View", "show view");
    			
    	MenuManager fileNew=new MenuManager("&File",IWorkbenchActionConstants.M_FILE);
    	
    	MenuManager helpNew=new MenuManager("&Help",IWorkbenchActionConstants.M_HELP);
    	
    
    	showViewMenu.add(openAction);
    	
    	windowMenu.add(showViewMenu);
    	
    	menuBar.add(windowMenu);
    	
    	menuBar.add(fileNew);
    	
    	menuBar.add(helpNew);
    	
    	fileNew.add(iNewWindowAction);
    	
    	fileNew.add(iSaveAction);
    	
    	fileNew.add(new Separator());
    	
    	fileNew.add(iRefAction);
    	
    	helpNew.add(iAboutAction);
    	
    	fileNew.add(iDorpDownAction);
    	
    	fileNew.add(iExitAction);
    	
    	
    }
    //�˷������״̬��
    protected void  fillCooBar(ICoolBarManager iCoolBarManagerss){
    	
    	// IToolBarManager toobar = new IToolBarManager(iCoolBarManagerss.getStyle());
    	// iCoolBarManagerss.add(toobar);
    	// toobar.add(iAboutAction);
    	iAboutAction.setMenuCreator(new IMenuCreator() {
			
			public Menu getMenu(Menu parent) {
				Menu menu = new Menu(parent);
				for (int i = 0; i < 5; i++) {
					MenuItem menuitem = new  MenuItem(menu, SWT.CHECK);
					menuitem.setText("MenuItem"+i);
				}
				return menu;
			}
			
			public Menu getMenu(Control parent) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public void dispose() {
				// TODO Auto-generated method stub
				
			}
		});
    }
    //��ý��ȼ�����
    protected void fillStatusLine( IStatusLineManager statusLine){
    	super.fillStatusLine(statusLine);
    	//�������
    	 final StatusLineContributionItem statusitem = new StatusLineContributionItem("");
    	 //��ȡ��ʾ
    	 statusLine.getProgressMonitor();
    	 //����״̬���ı�
    	 statusitem.setText("Status Messages");
    	 //��statusitemע�ᵽstatusLine
    	 statusLine.add(statusitem);
    }
    
}
