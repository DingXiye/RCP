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
        
      //为内置操作添加按钮图标
        ImageDescriptor imgDs = WorkbenchImages.getImageDescriptor
        (IWorkbenchGraphicConstants.IMG_DTOOL_NEW_FASTVIEW);
        iAboutAction.setImageDescriptor(imgDs);
        register(iAboutAction);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	
    	// 定义Menumanager对象，即定义一个window对象（一级菜单）
    	MenuManager windowMenu = new MenuManager("&Window",IWorkbenchActionConstants.M_WINDOW);
    	
    	// 定义"show view"菜单项（二级菜单）
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
    //此方法添加状态栏
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
    //获得进度监视器
    protected void fillStatusLine( IStatusLineManager statusLine){
    	super.fillStatusLine(statusLine);
    	//定义对象
    	 final StatusLineContributionItem statusitem = new StatusLineContributionItem("");
    	 //获取显示
    	 statusLine.getProgressMonitor();
    	 //设置状态栏文本
    	 statusitem.setText("Status Messages");
    	 //将statusitem注册到statusLine
    	 statusLine.add(statusitem);
    }
    
}
