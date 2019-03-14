package com.dy.muleditors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.MultiPageEditorPart;

/**
 * ��ҳ�༭��,��ʼ����ҳ
 * @author dingye
 *
 */
public class MulEditor extends MultiPageEditorPart{
	private Text text;
	private StyledText styledText;
	private Label label;
	//Ϊ��ҳ�༭������������µı༭ҳ
	@Override
	protected void createPages() {
		//�Զ��巽���༭ҳ
		createPage0();
		createPage1();
		createPage2();
	}

	private void createPage2() {
		label=new Label(getContainer(), SWT.NONE);
		label.setText("��ǩ");
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
		addPage(styledText);//�ڶ�ҳ�༭���ϴ����������ҳ�������������Ŀռ�
		setPageText(0, "Page_0");//���ո���������Ϊҳ�������ı���ǩ
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
