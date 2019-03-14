package com.dy.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

/**
 * �����༭��
 * 
 * @author dingye
 * 
 */
public class editorOne extends EditorPart {
	private TableViewer tv;
	private Table table;
	private boolean sort = true;// ��������ʽ
	private IsSexFilter isSexFilter;// ����Ů��

	public static String[] departments = new String[] { "������", "������", "���²�",
			"�г���", "����", "������" };

	// �˷���������༭����Ӵ��ڲ���
	public void createPartControl(Composite parent) {

		//�ڶ�����ӹ���̨��������ť�ķ���
//		IActionBars bars = getEditorSite().getActionBars();
//		IToolBarManager iToolBar = bars.getToolBarManager();
//		iToolBar.add(new Separator());
//		iToolBar.add(new deleteAction());
//		iToolBar.add(new filterAction());

		// ����ViewForm����
		ViewForm form = new ViewForm(parent, SWT.NONE);
		// ��Ʋ��ַ�ʽΪ������ʽ
		form.setLayout(new FillLayout());
		createTableViewer(form);
		// createTableViewer(parent);// �����Զ��巽����������鿴��
		tv.setLabelProvider(new TableViewLabelProvider());// ����ǩ�ṩ��
		tv.setContentProvider(new TableContentProvider());// ��������ṩ��
		tv.setInput(DataFactory.getFactoryData());// ���д������

		tv.setSorter(new SortProvider());// ��������ṩ��,ʵ��������

		createCellModifier();// ��������޸���

		// �����������
		isSexFilter = new IsSexFilter();
		// ���幤��������
		ToolBar bar = new ToolBar(form, SWT.FLAT);
		// ���幤����������
		ToolBarManager barManager = new ToolBarManager(bar);
		// ���ƹ�������ViewForm����������ʾ
		form.setTopLeft(bar);
		// ���ñ��鿴����λ��
		form.setContent(tv.getControl());
		// ��ӹ�������ť����
		barManager.add(new filterAction());
		barManager.add(new deleteAction());
		// ���¹����������������򹤾�����ť������ʾ
		barManager.update(true);
	}

	private void createCellModifier() {
		CellEditor[] cellEditor = new CellEditor[8];// ��Ԫ��༭��������8�б༭����
		cellEditor[0] = null;
		cellEditor[1] = null;
		cellEditor[2] = new CheckboxCellEditor(table);// Ϊ��3�����ñ༭��

		cellEditor[3] = new TextCellEditor(table);// Ϊ��4�����ñ༭��
		cellEditor[4] = null;
		cellEditor[5] = new ComboBoxCellEditor(table, departments,
				SWT.READ_ONLY);// Ϊ��6�����ñ༭��

		cellEditor[6] = null;
		cellEditor[7] = null;

		// ��������������
		tv.setColumnProperties(new String[] { "ID", "Name", "Male", "Age",
				"Phone", "Department", "Relat", "DateTime" });

		// Ϊ�����������ı���Ԫ��༭��������������Ϊ��ֵ��
		Text text1 = (Text) cellEditor[3].getControl();
		text1.addVerifyListener(new VerifyListener() {

			@Override
			public void verifyText(VerifyEvent e) {
				boolean textValue = ("0123456789".indexOf(e.text) >= 0);
				e.doit = textValue;
			}
		});
		// ���ñ��Ԫ�޸�
		tv.setCellModifier(new TableViewerCellModifier(tv));
		// ���õ�Ԫ��༭��
		tv.setCellEditors(cellEditor);
	}

	private void createTableViewer(Composite parent) {
		tv = new TableViewer(parent, SWT.MULTI | SWT.FULL_SELECTION);
		table = tv.getTable();
		TableColumn c1 = new TableColumn(table, SWT.LEFT);// ���������
		c1.setText("���");
		c1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				sort = !sort;
				((SortProvider) tv.getSorter()).Sorter(sort ? -1 : 1);
				tv.refresh();
			}
		});
		c1.setWidth(90);

		TableColumn c2 = new TableColumn(table, SWT.LEFT);
		c2.setText("����");
		c2.setWidth(90);
		c2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				sort = !sort;
				((SortProvider) tv.getSorter()).Sorter(sort ? -2 : 2);
				tv.refresh();
			}
		});

		TableColumn c3 = new TableColumn(table, SWT.LEFT);
		c3.setText("�Ա�");
		c3.setWidth(90);

		TableColumn c4 = new TableColumn(table, SWT.LEFT);
		c4.setText("����");
		c4.setWidth(90);
		c4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				sort = !sort;
				((SortProvider) tv.getSorter()).Sorter(sort ? -3 : 3);
				tv.refresh();
			}
		});

		TableColumn c5 = new TableColumn(table, SWT.LEFT);
		c5.setText("��ϵ�绰");
		c5.setWidth(90);

		TableColumn c6 = new TableColumn(table, SWT.LEFT);
		c6.setText("���ڲ���");
		c6.setWidth(90);

		TableColumn c7 = new TableColumn(table, SWT.LEFT);
		c7.setText("����");
		c7.setWidth(90);

		TableColumn c8 = new TableColumn(table, SWT.LEFT);
		c8.setText("�Ǽ�ʱ��");
		c8.setWidth(90);

		table.setHeaderVisible(true);// ���ñ�ͷ�ɼ�
		table.setLinesVisible(true);// ���ñ���пɼ�

	}

	class filterAction extends Action {
		public filterAction() {
			setToolTipText("���˵�ŮԱ����Ϣ");// ��ʾ�����
			setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
					.getImageDescriptor(ISharedImages.IMG_OPEN_MARKER));
		}

		// Ϊ�����ӹ��˲���
		public void run() {
			tv.addFilter(isSexFilter);
		}
	}

	class deleteAction extends Action {
		public deleteAction() {
			setToolTipText("ɾ��");
			setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
					.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		}

		public void run() {

			table.remove(table.getSelectionIndices());
		}

	}

	// ����༭����
	public void doSave(IProgressMonitor monitor) {

	}

	// ���Ϊ
	public void doSaveAs() {

	}

	// ��ʼ���༭��
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);
		this.setInput(input);

	}

	// ���ϴα���������󣬷��ر༭�����Ƿ��ٴα��޸�
	public boolean isDirty() {

		return false;
	}

	// �Ƿ�֧�����Ϊ
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public editorOne() {
		// TODO Auto-generated constructor stub
	}

}
