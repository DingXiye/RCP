package com.dy.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.dy.database.DataBaseOperate;
import com.dy.util.Doctor;
/**
 * 创建登录界面
 * @author dingye
 *
 */
public class LoginDialog extends TitleAreaDialog{
	private Text useName;
	private Text passWord;
	private Doctor doctor;
	
	public LoginDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	protected void configureShell(Shell newShell){
		super.configureShell(newShell);
		
		//设置对话框标题
		newShell.setText("用户登录");
		
		//设置对话框大小
		newShell.setSize(500,300);
		
		//设置对话框位置
		newShell.setLocation(260, 260);
	}
	
	//建立对话框山的标题及信息提示
	protected Control createContents(Composite parent){
		Control contents=super.createContents(parent);
		//设置标题
		setTitle("添加登录信息");
		//设置信息提示
		setMessage("注意文本框信息不能为空",IMessageProvider.INFORMATION);
		return contents;
	}
	
	//设置对话框区域内容
	protected  Control createDialogArea(Composite parent){
		Composite composite=new Composite(parent,SWT.BORDER);
		composite.setLayout(new GridLayout(2,false));
		GridData data=new GridData(GridData.FILL_HORIZONTAL);
		composite.setLayoutData(data);
		new Label(composite,SWT.NONE).setText("用户名:");
		useName=new Text(composite, SWT.BORDER);
		useName.setLayoutData(data);
		new Label(composite,SWT.NONE).setText("密 码:");
		passWord=new Text(composite, SWT.BORDER);
		passWord.setEchoChar('*');
		passWord.setLayoutData(data);
		return composite;
	}
	
	//单击按钮执行的操作
	protected void buttonPressed(int buttonId) {
		//单击ok执行以下操作
		if(IDialogConstants.OK_ID==buttonId){
			//验证输入信息的合法性
			if(!checkValue())
				return;
			getValues(doctor);
			boolean flag=DataBaseOperate.getLogin(doctor);
			if(flag){
				okPressed();
			}else
				setErrorMessage("用户名或密码错误");
				return;
		}
		//单击Cancel执行
		if(IDialogConstants.CANCEL_ID==buttonId){
//			System.exit(0);
			cancelPressed();
		}
	}

	/**
	 * 右上角关闭按钮的操作
	 */
	protected void handleShellCloseEvent() {
        MessageBox messagebox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
        messagebox.setMessage("您确定要退出吗?");
        int message = messagebox.open();
        if (message == SWT.YES) {
        	System.exit(0);
        }
    }
	
	//获得对话框中的数据
	private void getValues(Doctor doctor) {
		// TODO Auto-generated method stub
		String un=useName.getText();
		doctor.setName(un);
		String pw=passWord.getText();
		doctor.setPassword(pw);
	}

	//对输入数据进行验证是否合法
	private boolean checkValue() {
		String un =useName.getText();
		String pw=passWord.getText();
		if(un==null||pw==null||un.equals("")||pw.equals("")){
			setErrorMessage("用户名和密码不能为空");
			return false;
		}
		return true;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	protected int getShellStyle() {
		return super.getShellStyle()|SWT.RESIZE|SWT.MAX|SWT.MIN;
	}
}
