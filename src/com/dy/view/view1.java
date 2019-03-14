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
 * ���͸��ͼ
 * 
 * @author dingye
 * 
 */
public class view1 extends ViewPart {
	private TreeViewer tv;// ���һ����
	private DrillDownAdapter drillDownAdapter;

	public view1() {
	}

	/**
	 * ���һ��Ŀ¼��
	 */
	@Override
	public void createPartControl(Composite parent) {
		tv = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		tv.setContentProvider(new TreeViewerContentProvider());// ���������ṩ��
		tv.setLabelProvider(new TreeViewLabelProvider());// ���ñ�ǩ�ṩ��
		tv.setInput(EntityFactory.TreeEntityElement());// ��������

		fillViewToolBarAction();// �����ͼ��������ť
		fillViewToolBarContextMenu();// �����˵�
		fillTreeViewContextMenu();// Ϊ�鿴����������Ĳ˵�
//		createContextMenu(parent);// �����һ������˵�

		hookDoubleClickAction();// ˫����ť����
	}

	/**
	 * �һ������˵�����
	 * @param parent
	 */
	private void createContextMenu(Composite parent) {
		// TODO �Զ����ɵķ������
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

	// �Զ��巽���������ͼ��������ť��С�������¼���
	private void fillViewToolBarAction() {
		IActionBars bars = getViewSite().getActionBars();
		// ���幤����
		IToolBarManager toolBar = bars.getToolBarManager();
		drillDownAdapter = new DrillDownAdapter(tv);
		drillDownAdapter.addNavigationActions(toolBar);
		toolBar.add(new Separator());// ��ӷָ��
		// toolBar.add(new ActionOne());//��Ӳ���
		toolBar.add(new ActionTwo());
	}

	// �Զ��巽������ӹ����������˵���view menu�¼���
	private void fillViewToolBarContextMenu() {
		IActionBars bars = getViewSite().getActionBars();
		IMenuManager toolMenu = bars.getMenuManager();
		toolMenu.add(new ActionOne());
		// toolMenu.add(new ActionTwo());
		toolMenu.add(new Separator());
		// Ϊ�����Ĳ˵���Ӳ���:goback��gohome��gointo
		drillDownAdapter.addNavigationActions(toolMenu);
	}

	// Ϊ���鿴����������Ĳ˵�������һ��¼���
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

	// �Զ���˫���¼�
	public void hookDoubleClickAction() {
		// ���˫���¼�
		tv.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				// ѡ�������Ԫ��
				IStructuredSelection st = (IStructuredSelection) event
						.getSelection();
				EntityElement element = (EntityElement) st.getFirstElement();// ��ȡ��һ��Ԫ��
				String editorid = null;
				IEditorInput editorInput = element.getEditorInput();
				// �ж�˫���¼���õĵ��Ƿ���Ա������
				if (element.getName().equals("Ա������")) {
					editorid = "test.editor1";
//					IWorkbenchPage workbenchPage = getViewSite().getPage();
					// ����������Ը����̳�viewpart����ʹ��
					 IWorkbenchPage workbenchPage =
					 PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					// ����Ҫ�򿪵ı༭��
					IEditorPart editorPart = workbenchPage
							.findEditor(editorInput);
					if (editorPart != null) {
						workbenchPage.bringToTop(editorPart);
					} else {
						try {
							// ��Ա���������editor������༭����plugin�е�ΨһidΪtest.editor1
							editorPart = workbenchPage.openEditor(editorInput,
									editorid);
						} catch (PartInitException e) {
							System.out.println(e);
						}
					}
				}
				if (element.getName().equals("Ա��н��")) {
					editorid = "SalaryEditor";
					IWorkbenchPage workbenchPage = getViewSite().getPage();
					// ����Ҫ�򿪵ı༭��
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
				if (element.getName().equals("��Ʒ����")) {
					editorid = "AnalysisEditor";
					IWorkbenchPage workbenchPage = getViewSite().getPage();
					// ����Ҫ�򿪵ı༭��
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

	// ������
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
