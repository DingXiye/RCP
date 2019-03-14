package com.dy.view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

import com.dy.util.EntityElement;
import com.dy.util.EntityFactory;
import com.dy.util.TreeViewLabelProvider;
import com.dy.util.TreeViewerContentProvider;

/**
 * 添加透视图
 * 
 * @author dingye
 * 
 */
public class view1 extends ViewPart {
	private TreeViewer tv;// 添加一个树
	private DrillDownAdapter drillDownAdapter;

	public view1() {
	}

	/**
	 * 添加一棵目录树
	 */
	@Override
	public void createPartControl(Composite parent) {
		tv = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		tv.setContentProvider(new TreeViewerContentProvider());// 设置内容提供器
		tv.setLabelProvider(new TreeViewLabelProvider());// 设置标签提供器
		tv.setInput(EntityFactory.TreeEntityElement());// 读入数据

		fillViewToolBarAction();// 添加视图工具栏按钮
		fillViewToolBarContextMenu();// 下拉菜单
		fillTreeViewContextMenu();// 为查看器添加上下文菜单
//		createContextMenu(parent);// 创建右击弹出菜单

		hookDoubleClickAction();// 双击按钮操作
	}

	/**
	 * 右击弹出菜单设置
	 * @param parent
	 */
	private void createContextMenu(Composite parent) {
		// TODO 自动生成的方法存根
		MenuManager mgr = new MenuManager();
		mgr.setRemoveAllWhenShown(true);
		mgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				fillContextMenu(manager);
			}

			private void fillContextMenu(IMenuManager manager) {
				// TODO Auto-generated method stub
				manager.add(new ActionThree());
			}
		});
		Menu menu = mgr.createContextMenu(tv.getControl());
		tv.getControl().setMenu(menu);
		getSite().registerContextMenu(mgr, tv);
	}

	
	
	@Override
	public void setFocus() {

	}

	// 自定义方法，添加视图工具栏按钮（小工具栏事件）
	private void fillViewToolBarAction() {
		IActionBars bars = getViewSite().getActionBars();
		// 定义工具栏
		IToolBarManager toolBar = bars.getToolBarManager();
		drillDownAdapter = new DrillDownAdapter(tv);
		drillDownAdapter.addNavigationActions(toolBar);
		toolBar.add(new Separator());// 添加分割符
		// toolBar.add(new ActionOne());//添加操作
		toolBar.add(new ActionTwo());
	}

	// 自定义方法，添加工具栏下拉菜单（view menu事件）
	private void fillViewToolBarContextMenu() {
		IActionBars bars = getViewSite().getActionBars();
		IMenuManager toolMenu = bars.getMenuManager();
		toolMenu.add(new ActionOne());
		// toolMenu.add(new ActionTwo());
		toolMenu.add(new Separator());
		// 为上下文菜单添加操作:goback、gohome、gointo
		drillDownAdapter.addNavigationActions(toolMenu);
	}

	// 为树查看器添加上下文菜单（鼠标右击事件）
	private void fillTreeViewContextMenu() {
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(tv.getControl());
		tv.getControl().setMenu(menu);
		menuManager.add(new ActionOne());
		menuManager.add(new ActionTwo());
		menuManager.add(new Separator());
		menuManager.add(ActionFactory.NEW.create(PlatformUI.getWorkbench().getActiveWorkbenchWindow()));
		drillDownAdapter.addNavigationActions(menuManager);
	}

	// 自定义双击事件
	public void hookDoubleClickAction() {
		// 鼠标双击事件
		tv.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				// 选择包含的元素
				IStructuredSelection st = (IStructuredSelection) event
						.getSelection();
				EntityElement element = (EntityElement) st.getFirstElement();// 获取第一个元素
				String editorid = null;
				IEditorInput editorInput = element.getEditorInput();
				// 判断双击事件获得的点是否是员工档案
				if (element.getName().equals("员工档案")) {
					editorid = "test.editor1";
//					IWorkbenchPage workbenchPage = getViewSite().getPage();
					// 下面这个可以给不继承viewpart的类使用
					 IWorkbenchPage workbenchPage =
					 PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					// 查找要打开的编辑器
					IEditorPart editorPart = workbenchPage
							.findEditor(editorInput);
					if (editorPart != null) {
						workbenchPage.bringToTop(editorPart);
					} else {
						try {
							// 打开员工档案这个editor，这个编辑器在plugin中的唯一id为test.editor1
							editorPart = workbenchPage.openEditor(editorInput,
									editorid);
						} catch (PartInitException e) {
							System.out.println(e);
						}
					}
				}
				if (element.getName().equals("员工薪资")) {
					editorid = "SalaryEditor";
					IWorkbenchPage workbenchPage = getViewSite().getPage();
					// 查找要打开的编辑器
					IEditorPart editorPart = workbenchPage
							.findEditor(editorInput);
					if (editorPart != null) {
						workbenchPage.bringToTop(editorPart);
					} else {
						try {
							editorPart = workbenchPage.openEditor(editorInput,
									editorid);
							FileOutputStream fs = new FileOutputStream(
									"C:\\Users\\dingye\\Desktop\\"
											+ editorInput.getName() + ".txt");
							ObjectOutputStream os = new ObjectOutputStream(fs);
							os.writeObject(EntityFactory.TreeEntityElement());
							os.close();
						} catch (PartInitException | IOException e) {
							System.out.println(e);
						}
					}
				}
				if (element.getName().equals("产品分析")) {
					editorid = "AnalysisEditor";
					IWorkbenchPage workbenchPage = getViewSite().getPage();
					// 查找要打开的编辑器
					IEditorPart editorPart = workbenchPage
							.findEditor(editorInput);
					if (editorPart != null) {
						workbenchPage.bringToTop(editorPart);
					} else {
						try {
							editorPart = workbenchPage.openEditor(editorInput,
									editorid);
						} catch (PartInitException e) {
							System.out.println(e);
						}
					}
				}
			}
		});

	}

	// 操作类
	class ActionOne extends Action {
		public ActionOne() {
			setText("ActionOne");
			setToolTipText("ActionOne tooltip");
			ImageDescriptor imageDesc = WorkbenchImages
					.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_NEW_PAGE);
			setHoverImageDescriptor(imageDesc);
		}

		public void run() {
			MessageDialog
					.openInformation(null, "Message", "this is action one");
		}
	}

	class ActionTwo extends Action {
		public ActionTwo() {
			setText("ActionTwo");
			setToolTipText("ActionTwo tooltip");
			ImageDescriptor imageDesc = WorkbenchImages
					.getImageDescriptor(IWorkbenchGraphicConstants.IMG_OBJ_THEME_CATEGORY);
			setHoverImageDescriptor(imageDesc);
		}

		public void run() {
			MessageDialog
					.openInformation(null, "Message", "this is action two");
		}
	}
	class ActionThree extends Action {
		public ActionThree() {
			setText("ActionThree");
			setToolTipText("ActionThree tooltip");
			ImageDescriptor imageDesc = WorkbenchImages
					.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_NEW_PAGE);
			setHoverImageDescriptor(imageDesc);
		}

		public void run() {
			MessageDialog
					.openInformation(null, "Message", "this is action three");
		}
	}
}
