package com.dy.editor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.part.EditorPart;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.dy.util.EntityElement;
import com.dy.util.EntityFactory;
import com.dy.util.TreeViewLabelProvider;
import com.dy.util.TreeViewerContentProvider;

public class SalaryEditor extends EditorPart {
	private Tree tree;
	// private List<EntityElement> list = new ArrayList<EntityElement>();//
	// �ӹ����е���
	private List<EntityElement> templist = new ArrayList<EntityElement>();
	private TreeViewer treeView;
	private String SelectName;

	private boolean flag = false;
	private List<EntityElement> repealList = new ArrayList<EntityElement>();// ������Ƹ�ֵ

	public SalaryEditor() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);
		this.setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		treeView = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		treeView.setContentProvider(new TreeViewerContentProvider());// ���������ṩ��
		treeView.setLabelProvider(new TreeViewLabelProvider());// ���ñ�ǩ�ṩ��
		treeView.setInput(null);// ��������
		tree = treeView.getTree();
		// list = EntityFactory.TreeEntityElement();
		// templist = null;
		tree.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem item = (TreeItem) e.item;
				SelectName = item.getText();
				System.out.println(e.toString());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}
		});

		createContextMenu(parent);// �һ������˵�����
	}

	/**
	 * �һ������˵�����
	 * 
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
				manager.add(new Add());
				manager.add(new AddChild());
				manager.add(new Delete());
				manager.add(new Update());
				manager.add(new Save());
				manager.add(new Repeal());// ����
				manager.add(new SelectAll());
				manager.add(new Import());
			}
		});
		Menu menu = mgr.createContextMenu(treeView.getControl());
		treeView.getControl().setMenu(menu);
		getSite().registerContextMenu(mgr, treeView);
	}

	// �༭���е���ɾ���ڵ�
	public class Delete extends Action {
		public Delete() {
			setText("Delete");
			setToolTipText("Delete Node");
			ImageDescriptor imageDesc = WorkbenchImages
					.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_NEW_PAGE);
			setHoverImageDescriptor(imageDesc);
		}

		public void run() {
			if (templist.size() > 0) {
				boolean delete = MessageDialog.openQuestion(null, "Warning",
						"ȷ��ɾ����");
				if (delete) {
					flag = true;
					try {
						repealList = deepCopy(templist);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					DeleteNode(templist);
					treeView.setContentProvider(new TreeViewerContentProvider());// ���������ṩ��
					treeView.setLabelProvider(new TreeViewLabelProvider());// ���ñ�ǩ�ṩ��
					treeView.setInput(templist);// ��������
				} else
					return;
			}
		}

		public List<EntityElement> DeleteNode(List<EntityElement> processList) {
			for (int i = 0; i < processList.size(); i++) {
				if (processList.get(i).getName().equals(SelectName)) {
					processList.remove(i);
					break;
				} else {
					if (processList.get(i).hasChildren()) {
						DeleteNode(processList.get(i).getChildren());
					}
				}
			}
			return templist;
		}
	}

	// ���ͬ���ڵ�
	public class Add extends Action {
		public Add() {
			setText("Add Brother");
			setToolTipText("Add Node");
			ImageDescriptor imageDesc = WorkbenchImages
					.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_NEW_PAGE);
			setHoverImageDescriptor(imageDesc);
		}

		public void run() {
			String input = JOptionPane.showInputDialog(null, "���ͬ���ڵ�",
					"������ڵ�����", JOptionPane.PLAIN_MESSAGE);
			try {
				if (!input.equals("") && input != null) {
					flag = true;
					try {
						repealList = deepCopy(templist);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					templist = addList(input, templist);
					treeView.setContentProvider(new TreeViewerContentProvider());// ���������ṩ��
					treeView.setLabelProvider(new TreeViewLabelProvider());// ���ñ�ǩ�ṩ��
					treeView.setInput(templist);// ��������
				} else {
					MessageDialog.openInformation(null, "��ʾ��Ϣ", "������ڵ�����");
					run();
				}
			} catch (NullPointerException e) {
				return;
			}
		}

		public List<EntityElement> addList(String input,
				List<EntityElement> list) {
			if (list.size() == 0) {
				list.add(new EntityElement(input, "root", 1));
				return list;
			}
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getName());
				if (list.get(i).getName().equals(SelectName)) {
					list.add(new EntityElement(input,
							list.get(i).getParentId(), list.get(i).getId()));
					break;
				} else {
					if (list.get(i).hasChildren()) {
						addList(input, list.get(i).getChildren());
					}
				}
			}
			return list;
		}
	}

	// ����ӽڵ�
	public class AddChild extends Action {
		public AddChild() {
			setText("Add Child");
			setToolTipText("Add Node");
			ImageDescriptor imageDesc = WorkbenchImages
					.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_NEW_PAGE);
			setHoverImageDescriptor(imageDesc);
		}

		public void run() {
			if (templist.size() > 0) {
				String input = JOptionPane.showInputDialog(null, "���Ҷ�ӽڵ�",
						"������ڵ�����", JOptionPane.PLAIN_MESSAGE);
				System.out.println(input);
				try {
					if (!input.equals("") && input != null) {
						flag = true;
						try {
							repealList = deepCopy(templist);
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						templist = addList(input, templist);
						treeView.setContentProvider(new TreeViewerContentProvider());// ���������ṩ��
						treeView.setLabelProvider(new TreeViewLabelProvider());// ���ñ�ǩ�ṩ��
						treeView.setInput(templist);// ��������
					} else {
						MessageDialog.openInformation(null, "��ʾ��Ϣ", "������ڵ�����");
						run();
					}
				} catch (NullPointerException e) {
					return;
				}
			}
		}

		public List<EntityElement> addList(String input,
				List<EntityElement> list) {
			if (list.size() == 0) {
				list.add(new EntityElement(input, "root", 1));
				return list;
			}
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getName().equals(SelectName)) {
					list.get(i).getChildren().add(new EntityElement(input,list.get(i).getName(),list.get(i).getId() + 1));
					break;
				} else {
					if (list.get(i).hasChildren()) {
						addList(input, list.get(i).getChildren());
					}
				}
			}
			return list;
		}
	}

	// ���£�ʵ����������
	public class Update extends Action {
		public Update() {
			setText("Update");
			setToolTipText("Update Node");
			ImageDescriptor imageDesc = WorkbenchImages
					.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_NEW_PAGE);
			setHoverImageDescriptor(imageDesc);
		}

		public void run() {
			if (templist.size() > 0) {
				String input = JOptionPane.showInputDialog(null, "������",
						"������ڵ�����", JOptionPane.PLAIN_MESSAGE);
				System.out.println(input);
				try {
					if (!input.equals("") && input != null) {
						flag = true;
						try {
							repealList = deepCopy(templist);
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						templist = update(input, templist);
						treeView.setContentProvider(new TreeViewerContentProvider());// ���������ṩ��
						treeView.setLabelProvider(new TreeViewLabelProvider());// ���ñ�ǩ�ṩ��
						treeView.setInput(templist);// ��������

					} else {
						MessageDialog.openInformation(null, "��ʾ��Ϣ", "������ڵ�����");
						run();
					}
				} catch (NullPointerException e) {
					return;
				}
			}
		}

		public List<EntityElement> update(String input, List<EntityElement> list) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getName().equals(SelectName)) {
					list.get(i).setName(input);
					break;
				} else {
					if (list.get(i).hasChildren()) {
						update(input, list.get(i).getChildren());
					}
				}
			}
			return list;
		}
	}

	// ����
	public class Save extends Action {
		public Save() {
			setText("Save");
			setToolTipText("Save Node");
			ImageDescriptor imageDesc = WorkbenchImages
					.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_NEW_PAGE);
			setHoverImageDescriptor(imageDesc);
		}

		public void run() {
			FileDialog fileDialog = new FileDialog(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(), SWT.SAVE);
			fileDialog.setFilterNames(new String[] { "Text Files",
					"Both Files", "All Files" });
			fileDialog.setFilterExtensions(new String[] { "*.xml", "*.*" });
			String filename = fileDialog.open();
			System.out.println(filename);
			try {
				// ��һ��������DOM��
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document document = db.newDocument();
				document.setXmlStandalone(true);
				// �ڶ������������ṹ
				// �����ڵ�
				Element Tree = document.createElement("Tree");
				List<EntityElement> input = new ArrayList<EntityElement>();
				input = getEntity(templist, input);
				for (EntityElement element : input) {
					Element node = document.createElement("node");
					Element id = document.createElement("id");
					Element name = document.createElement("name");
					Element parentId = document.createElement("parentId");
					// ���ýڵ�����ԡ�����
					id.setTextContent("" + element.getId());
					name.setTextContent(element.getName());
					parentId.setTextContent(element.getParentId());
					// ���ӽڵ���ӵ����ڵ�
					node.appendChild(id);
					node.appendChild(name);
					node.appendChild(parentId);
					Tree.appendChild(node);
				}
				document.appendChild(Tree);
				// �������������ṹ����xml�ļ���
				TransformerFactory tff = TransformerFactory.newInstance();
				Transformer tf = tff.newTransformer();
				tf.setOutputProperty(OutputKeys.INDENT, "yes");// �ڵ㻻��
				tf.transform(new DOMSource(document),
						new StreamResult(filename));
			} catch (Exception e) {
				e.printStackTrace();
				MessageDialog.openError(null, "��ʾ��Ϣ", "����ʧ��");
			}
			MessageDialog.openInformation(null, "��ʾ��Ϣ", "����ɹ�");
		}
	}

	// ����
	public class Repeal extends Action {
		public Repeal() {
			setText("Repeal");
			setToolTipText("Repeal Node");
			ImageDescriptor imageDesc = WorkbenchImages
					.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_NEW_PAGE);
			setHoverImageDescriptor(imageDesc);
		}

		public void run() {
			if (flag) {
				treeView.setContentProvider(new TreeViewerContentProvider());// ���������ṩ��
				treeView.setLabelProvider(new TreeViewLabelProvider());// ���ñ�ǩ�ṩ��
				treeView.setInput(repealList);// ��������
				templist = repealList;
			}
			flag = false;
		}
	}

	public class Import extends Action {
		public Import() {
			setText("Import");
			setToolTipText("Import Node");
			ImageDescriptor imageDesc = WorkbenchImages
					.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_NEW_PAGE);
			setHoverImageDescriptor(imageDesc);
		}

		public void run() {
			FileDialog fileDialog = new FileDialog(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(), SWT.OPEN);
			String filepath = fileDialog.open();// �򿪶Ի��򲢷����û�ѡ���Ŀ¼
			System.out.println(filepath);
			try {
				List<EntityElement> lists = new ArrayList<EntityElement>();
				List<EntityElement> source = getAll(filepath);
				for (EntityElement element : source) {
					if (element.getId() == 1) {
						System.out.println();
						lists.add(element);
					}
				}
				templist = create(source, lists, 2);
				treeView.setContentProvider(new TreeViewerContentProvider());// ���������ṩ��
				treeView.setLabelProvider(new TreeViewLabelProvider());// ���ñ�ǩ�ṩ��
				treeView.setInput(templist);// ��������
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public List<EntityElement> create(List<EntityElement> source,
				List<EntityElement> dest, Integer level)
				throws ClassNotFoundException, IOException {
			for (int index = 1; index < source.size(); index++) {
				if (source.get(index).getId() == level) {
					for (int i = 0; i < dest.size(); i++) {
						EntityElement root = dest.get(i);
						if (source.get(index).getParentId()
								.equals(root.getName())) {
							root.addChild(source.get(index));
							break;
						}
					}
				}
			}
			level = level + 1;
			if (level < 10)
				for (int i = 0; i < dest.size(); i++) {
					create(source, dest.get(i).getChildren(), level);
				}
			return dest;
		}

		public List<EntityElement> getAll(String fileName) throws Exception {
			// ������ URI �����ݽ���Ϊһ�� XML �ĵ�,������Document����
			DocumentBuilder db = null;
			List<EntityElement> entitys;
			DocumentBuilderFactory dbFactory;
			dbFactory = DocumentBuilderFactory.newInstance();
			db = dbFactory.newDocumentBuilder();
			Document document = db.parse(fileName);
			// ���ĵ�˳�򷵻ذ������ĵ����Ҿ��и���������Ƶ����� Element �� NodeList
			NodeList entityList = document.getElementsByTagName("node");
			entitys = new ArrayList<EntityElement>();
			// ��������
			for (int i = 0; i < entityList.getLength(); i++) {
				EntityElement element = new EntityElement();
				// ��ȡ��i�����
				org.w3c.dom.Node node = entityList.item(i);
				// ��ȡ�����ӽڵ�,������Test���͵Ļ���
				NodeList cList = node.getChildNodes();
				// ��һ��ʵ����������Լ�������
				ArrayList<String> contents = new ArrayList<>();
				for (int j = 1; j < cList.getLength(); j += 2) {
					org.w3c.dom.Node cNode = cList.item(j);
					String content = cNode.getFirstChild().getTextContent();
					contents.add(content);
				}

				element.setId(Integer.parseInt(contents.get(0)));
				element.setName(contents.get(1));
				element.setParentId(contents.get(2));
				entitys.add(element);
			}

			return entitys;

		}
	}

	public class SelectAll extends Action {
		public SelectAll() {
			setText("SelectAll");
			setToolTipText("SelectAll Node");
			ImageDescriptor imageDesc = WorkbenchImages
					.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_NEW_PAGE);
			setHoverImageDescriptor(imageDesc);
		}

		public void run() {
			tree.selectAll();
		}
	}

	// ��ȡ����ʵ��
	public List<EntityElement> getEntity(List<EntityElement> list,
			List<EntityElement> output) {
		for (int i = 0; i < list.size(); i++) {
			output.add(list.get(i));
			if (list.get(i).hasChildren()) {
				getEntity(list.get(i).getChildren(), output);
			}
		}
		return output;
	}

	// ���
	public List<EntityElement> deepCopy(List<EntityElement> src)
			throws IOException, ClassNotFoundException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(src);
		ByteArrayInputStream byteIn = new ByteArrayInputStream(
				byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		List<EntityElement> dest = (List<EntityElement>) in.readObject();
		return dest;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}
}
