package test;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineContributionItem;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.wizards.AbstractExtensionWizardRegistry;
import org.eclipse.ui.wizards.IWizardCategory;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.ui.wizards.IWizardRegistry;

import com.dy.ActionFactory.util.MyActionFactory;
import com.dy.action.DropAction;
import com.dy.action.Myaction;
import com.dy.action.viewAction;

//import org.eclipse.jface.action.IToolBarManager;

/**
 * 添加工具条上的功能,使用的是直接用代码添加，在xml中也添加了一个按钮
 * 
 * @author dingye
 * 
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	private Myaction myaction;
	private Myaction navAction;
	private DropAction dropAction;
	private viewAction viewaction;
	private IWorkbenchAction exit;
	private IWorkbenchAction about;
	private IWorkbenchAction newWin;
	private IWorkbenchAction save;
	private IWorkbenchAction refa;
	private IContributionItem showviewaction;
	private IAction importAction;
	private IAction NewAction;
	// 注册插件
	protected void makeActions(IWorkbenchWindow window) {
		super.makeActions(window);
		showviewaction = ContributionItemFactory.VIEWS_SHORTLIST.create(window);
		// 定义操作对象
		importAction = MyActionFactory.My.create(window);
		NewAction = new com.dy.NewWizard.NewAction(window);
		navAction = new Myaction(window);
		myaction = new Myaction(window);
		viewaction = new viewAction(window);
		dropAction = new DropAction();// 下拉功能
		// 定义内置操作
		exit = ActionFactory.QUIT.create(window);
		register(exit);

		about = ActionFactory.ABOUT.create(window);
		ImageDescriptor descriptor = WorkbenchImages
				.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_HELP_SEARCH);
		about.setImageDescriptor(descriptor);
		register(about);

		newWin = ActionFactory.OPEN_NEW_WINDOW.create(window);
		register(newWin);

		save = ActionFactory.SAVE.create(window);
		register(save);

		refa = ActionFactory.REFRESH.create(window);
		register(refa);

//		removeDuplicateAction();//删除不需要的菜单项
	}

	@SuppressWarnings("restriction")
	private void removeDuplicateAction() {
		 AbstractExtensionWizardRegistry reg = (AbstractExtensionWizardRegistry) WorkbenchPlugin.getDefault().getNewWizardRegistry();
		IWizardCategory[] categories = reg.getRootCategory().getCategories();
		for (int i = 0; i < categories.length; i++) {
			if (categories[i].getId().equals("org.eclipse.ui.Basic")) {
				IWizardDescriptor[] wizard = categories[i].getWizards();
				IExtensionRegistry registry = Platform.getExtensionRegistry();
				IExtensionPoint point = registry
						.getExtensionPoint("org.eclipse.ui.newWizards");
				IExtension[] extensions = point.getExtensions();
				for (int j = 0; j < wizard.length; j++) {
					System.out.println(extensions[i].getLabel());
					reg.removeExtension(extensions[i],
							new Object[] { wizard[j] });
				}

			}

		}
	}

	// 将插件添加到工具栏中
	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager winmenu = new MenuManager("File");
		winmenu.add(exit);
		winmenu.add(about);
		winmenu.add(newWin);
		winmenu.add(save);
		winmenu.add(refa);
		winmenu.add(importAction);
		winmenu.add(NewAction);
		menuBar.add(winmenu);

		MenuManager windowmenu = new MenuManager("Open");
		windowmenu.add(myaction);
		windowmenu.add(viewaction);
//		windowmenu.add(testAction);
		menuBar.add(windowmenu);

	}

	/**
	 * 添加工具栏（代码添加）
	 */
	protected void fillCoolBar(ICoolBarManager coolBar) {
		// 定义工具栏按钮
		IToolBarManager toolBarManager = new ToolBarManager(coolBar.getStyle());
		coolBar.add(toolBarManager);
		toolBarManager.add(navAction);
		// toolBarManager.add(new Separator());

		IToolBarManager toolBar = new ToolBarManager(coolBar.getStyle());
		// 添加分组标识，使toolbar成为独立的动态工具栏组
		toolBar.add(new GroupMarker("GroupMarker1"));
		toolBar.add(about);
		coolBar.add(toolBar);

		// 设置dropdown动态工具栏按钮
		IToolBarManager toolbar1 = new ToolBarManager(coolBar.getStyle());
		toolbar1.add(new GroupMarker("GroupMarkr2"));
		toolbar1.add(dropAction);
		// 添加一个菜单项
		toolbar1.add(showviewaction);
		coolBar.add(toolbar1);
	}

	// 获得进度监视器
	protected void fillStatusLine(StatusLineManager statusLine) {
		super.fillStatusLine(statusLine);
		// 定义对象
		final StatusLineContributionItem contributionItem = new StatusLineContributionItem(
				"");
		// 获取进度监视器，并在状态栏显示
		statusLine.getProgressMonitor();
		// 设置状态栏文本
		contributionItem.setText("status message");
		// 将statusitem注册到statusline中
		statusLine.add(contributionItem);
	}
}