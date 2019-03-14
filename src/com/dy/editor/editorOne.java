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
 * 创建编辑器
 * 
 * @author dingye
 * 
 */
public class editorOne extends EditorPart {
	private TableViewer tv;
	private Table table;
	private boolean sort = true;// 设置排序方式
	private IsSexFilter isSexFilter;// 过滤女生

	public static String[] departments = new String[] { "行政部", "财政部", "人事部",
			"市场部", "商务部", "技术部" };

	// 此方法用来向编辑器添加窗口部件
	public void createPartControl(Composite parent) {

		//第二种添加工作台工具栏按钮的方法
//		IActionBars bars = getEditorSite().getActionBars();
//		IToolBarManager iToolBar = bars.getToolBarManager();
//		iToolBar.add(new Separator());
//		iToolBar.add(new deleteAction());
//		iToolBar.add(new filterAction());

		// 定义ViewForm容器
		ViewForm form = new ViewForm(parent, SWT.NONE);
		// 设计布局方式为充满方式
		form.setLayout(new FillLayout());
		createTableViewer(form);
		// createTableViewer(parent);// 调用自定义方法，建立表查看器
		tv.setLabelProvider(new TableViewLabelProvider());// 表格标签提供器
		tv.setContentProvider(new TableContentProvider());// 表格内容提供器
		tv.setInput(DataFactory.getFactoryData());// 表格写入数据

		tv.setSorter(new SortProvider());// 表格排序提供器,实现排序功能

		createCellModifier();// 建立表格修改器

		// 定义表格过滤器
		isSexFilter = new IsSexFilter();
		// 定义工具栏对象
		ToolBar bar = new ToolBar(form, SWT.FLAT);
		// 定义工具栏管理器
		ToolBarManager barManager = new ToolBarManager(bar);
		// 控制工具栏在ViewForm容器顶端显示
		form.setTopLeft(bar);
		// 设置表格查看器的位置
		form.setContent(tv.getControl());
		// 添加工具栏按钮操作
		barManager.add(new filterAction());
		barManager.add(new deleteAction());
		// 更新工具栏管理器，否则工具栏按钮不能显示
		barManager.update(true);
	}

	private void createCellModifier() {
		CellEditor[] cellEditor = new CellEditor[8];// 单元格编辑器，设置8列编辑对象
		cellEditor[0] = null;
		cellEditor[1] = null;
		cellEditor[2] = new CheckboxCellEditor(table);// 为第3列设置编辑器

		cellEditor[3] = new TextCellEditor(table);// 为第4列设置编辑器
		cellEditor[4] = null;
		cellEditor[5] = new ComboBoxCellEditor(table, departments,
				SWT.READ_ONLY);// 为第6列设置编辑器

		cellEditor[6] = null;
		cellEditor[7] = null;

		// 设置列属性名称
		tv.setColumnProperties(new String[] { "ID", "Name", "Male", "Age",
				"Phone", "Department", "Relat", "DateTime" });

		// 为第四列设置文本单元格编辑器，将输入设置为数值型
		Text text1 = (Text) cellEditor[3].getControl();
		text1.addVerifyListener(new VerifyListener() {

			@Override
			public void verifyText(VerifyEvent e) {
				boolean textValue = ("0123456789".indexOf(e.text) >= 0);
				e.doit = textValue;
			}
		});
		// 设置表格单元修改
		tv.setCellModifier(new TableViewerCellModifier(tv));
		// 设置单元格编辑器
		tv.setCellEditors(cellEditor);
	}

	private void createTableViewer(Composite parent) {
		tv = new TableViewer(parent, SWT.MULTI | SWT.FULL_SELECTION);
		table = tv.getTable();
		TableColumn c1 = new TableColumn(table, SWT.LEFT);// 定义表格的列
		c1.setText("编号");
		c1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				sort = !sort;
				((SortProvider) tv.getSorter()).Sorter(sort ? -1 : 1);
				tv.refresh();
			}
		});
		c1.setWidth(90);

		TableColumn c2 = new TableColumn(table, SWT.LEFT);
		c2.setText("姓名");
		c2.setWidth(90);
		c2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				sort = !sort;
				((SortProvider) tv.getSorter()).Sorter(sort ? -2 : 2);
				tv.refresh();
			}
		});

		TableColumn c3 = new TableColumn(table, SWT.LEFT);
		c3.setText("性别");
		c3.setWidth(90);

		TableColumn c4 = new TableColumn(table, SWT.LEFT);
		c4.setText("年龄");
		c4.setWidth(90);
		c4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				sort = !sort;
				((SortProvider) tv.getSorter()).Sorter(sort ? -3 : 3);
				tv.refresh();
			}
		});

		TableColumn c5 = new TableColumn(table, SWT.LEFT);
		c5.setText("联系电话");
		c5.setWidth(90);

		TableColumn c6 = new TableColumn(table, SWT.LEFT);
		c6.setText("所在部门");
		c6.setWidth(90);

		TableColumn c7 = new TableColumn(table, SWT.LEFT);
		c7.setText("亲属");
		c7.setWidth(90);

		TableColumn c8 = new TableColumn(table, SWT.LEFT);
		c8.setText("登记时间");
		c8.setWidth(90);

		table.setHeaderVisible(true);// 设置表头可见
		table.setLinesVisible(true);// 设置表格行可见

	}

	class filterAction extends Action {
		public filterAction() {
			setToolTipText("过滤掉女员工信息");// 提示性语句
			setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
					.getImageDescriptor(ISharedImages.IMG_OPEN_MARKER));
		}

		// 为表格添加过滤操作
		public void run() {
			tv.addFilter(isSexFilter);
		}
	}

	class deleteAction extends Action {
		public deleteAction() {
			setToolTipText("删除");
			setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
					.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		}

		public void run() {

			table.remove(table.getSelectionIndices());
		}

	}

	// 保存编辑内容
	public void doSave(IProgressMonitor monitor) {

	}

	// 另存为
	public void doSaveAs() {

	}

	// 初始化编辑器
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);
		this.setInput(input);

	}

	// 在上次被保存操作后，返回编辑内容是否再次被修改
	public boolean isDirty() {

		return false;
	}

	// 是否支持另存为
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
