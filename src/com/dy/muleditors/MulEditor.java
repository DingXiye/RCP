package com.dy.muleditors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.MultiPageEditorPart;

/**
 * 多页编辑器,起始编码页
 * @author dingye
 *
 */
public class MulEditor extends MultiPageEditorPart{
	private Text text;
	private StyledText styledText;
	private Label label;
	//为多页编辑器创建并添加新的编辑页
	@Override
	protected void createPages() {
		//自定义方法编辑页
		createPage0();
		createPage1();
		createPage2();
	}

	private void createPage2() {
		label=new Label(getContainer(), SWT.NONE);
		label.setText("标签");
		addPage(label);
		setPageText(2, "Page_2");
	}

	private void createPage1() {
		text=new Text(getContainer(), SWT.NONE);
		addPage(text);
		setPageText(1, "Page_1");
	}

	private void createPage0() {
		styledText=new StyledText(getContainer(), SWT.NONE);
		addPage(styledText);//在多页编辑器上创建并添加新页，他包含给定的空间
		setPageText(0, "Page_0");//按照给定的索引为页面设置文本标签
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
