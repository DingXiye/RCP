package com.dy.sampleEditor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.dy.action.sampleAction;
import com.dy.action.viewAction;


/**
 * ´´½¨±à¼­Æ÷Àà
 * @author dingye
 *
 */
public class SampleEditor extends EditorPart{
	private Text text;
	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

	public void addEditorToolBar(){
		IActionBars actionBars=getEditorSite().getActionBars();
		IToolBarManager itool=actionBars.getToolBarManager();
		itool.add(new viewAction());
	}
	
	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// TODO Auto-generated method stub
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		text=new Text(parent, SWT.BORDER);
		addEditorToolbar(parent);
	}

	private void addEditorToolbar(Composite parent) {
		// TODO Auto-generated method stub
		ViewForm vf=new ViewForm(parent,SWT.NONE);
		vf.setLayout(new FillLayout());
		text=new Text(vf, SWT.BORDER);
		
		ToolBar bar=new ToolBar(vf,SWT.FLAT);
		ToolBarManager barManager=new ToolBarManager(bar);
		vf.setTopLeft(bar);
		vf.setContent(text);
		barManager.add(new viewAction());
		barManager.update(true);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
