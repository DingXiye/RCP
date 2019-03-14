package com.dy.wizardpage;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IResourceActionFilter;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

public class FirstWizardPage extends WizardPage {
	private ISelection selection;
	private Text containerText;
	private Text fileText;
	private String name;

	public FirstWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle("Editor File");
		setDescription("This wizard creates a new file with *.jsp");
		// 还可以在这里设定页面图像：setImageDescription
		setPageComplete(false);
		this.selection = selection;
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		Label label = new Label(composite, SWT.NULL);
		label.setText("&Container:");
		containerText = new Text(composite, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		 containerText.setLayoutData(gd);
		 containerText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				dialogChanged();
				
			}
		 });
		 
		 Button button=new Button(composite, SWT.PUSH);
		 button.setText("Browse");
		 button.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				handleBrowse();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		 
		 label=new Label(composite,SWT.NULL);
		 label.setText("&File name:");
		 fileText=new Text(composite, SWT.BORDER|SWT.SINGLE);
		 gd=new GridData(GridData.FILL_HORIZONTAL);
		 fileText.setLayoutData(gd);
		 fileText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				dialogChanged();
			}
		});
		 
		 initialize();
		 dialogChanged();
		 setControl(composite);
		 
		// Composite container=new Composite(parent,SWT.NULL);
		// GridLayout layout=new GridLayout();
		// layout.numColumns=1;
		// container.setLayout(layout);
		// Text text=new Text(container,SWT.BORDER);
		// text.setText("设定名称");
		// text.addModifyListener(new ModifyListener() {
		// @Override
		// public void modifyText(ModifyEvent e) {
		// Text text=(Text)e.getSource();
		// name=text.getText();
		// if(name==null||name=="")
		// updateStatus("请输入名字");
		// else
		// updateStatus(null);
		// }
		// });
		//
		// Combo combo = new Combo(container,SWT.NONE);
		// combo.setText("设定类别");
		// combo.add("普通");
		// combo.add("同事");
		// combo.add("商业");
		// combo.add("朋友");
		// setControl(container);
	}

	/**
	 * 验证用户输入
	 */
	protected void dialogChanged() {
		// TODO Auto-generated method stub
//		IResource container=ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(getContainerName()));
//		String fileName=getFileName();
//		if(getContainerName().length()==0){
//			updateStatus("File container must be specified");
//			return ;
//		}
//		
//		if(container==null||(container.getType()&(IResource.PROJECT|IResource.FOLDER))==0){
//			updateStatus("File container must exist");
//			return ;
//		}
//		if(!container.isAccessible()){
//			updateStatus("Project must be writable");
//			return ;
//		}
//		if(fileName.length()==0){
//			updateStatus("File name must be specified");
//			return ;
//		}
//		if(fileName.replace('\\', '/').indexOf('/',1)>0){
//			updateStatus("File name must be valid");
//			return ;
//		}
//		
//		int dotloc=fileName.lastIndexOf('.');
//		if(dotloc!=-1){
//			String ext=fileName.substring(dotloc+1);
//			if(ext.equalsIgnoreCase("jsp")==false){
//				updateStatus("File extension must be \"jsp\"");
//				return ;
//			}
//		}
//		updateStatus(null);
	}

	//获得文件名
	private String getFileName() {
		return fileText.getText();
	}

	//用标准的容器选择对话框来选择一个新值为容器字段
	protected void handleBrowse() {
		// TODO Auto-generated method stub
//		ContainerSelectionDialog dialog=new ContainerSelectionDialog(getShell(), ResourcesPlugin.getWorkspace().getRoot(), false, "Select new file container");
//		if(dialog.open()==ContainerSelectionDialog.OK){
//			Object[] result=dialog.getResult();
//			if(result.length==1){
//				containerText.setText(((Path)result[0]).toString());
//			}
//		}
	}

	private void initialize() {
//		// TODO Auto-generated method stub
//		if(selection!=null&&selection.isEmpty()==false&&selection instanceof IStructuredSelection){
//			IStructuredSelection sset=(IStructuredSelection)selection;
//			if(sset.size()>1)
//				return;
//			Object obj=sset.getFirstElement();
//			if(obj instanceof IResource){
//				IContainer container;
//				if(obj instanceof IContainer)
//					container=(IContainer)obj;
//				else 
//					container=((IResource)obj).getParent();
//				containerText.setText(container.getFullPath().toString());
//			}
//		}
		//设置文件标题
		fileText.setText("new_file.jsp");
	}

	//更新状态
	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}
	
	//获得容器名
	public String getContainerName(){
		return containerText.getText();
	}
	
}
