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
 * ������¼����
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
		
		//���öԻ������
		newShell.setText("�û���¼");
		
		//���öԻ����С
		newShell.setSize(500,300);
		
		//���öԻ���λ��
		newShell.setLocation(260, 260);
	}
	
	//�����Ի���ɽ�ı��⼰��Ϣ��ʾ
	protected Control createContents(Composite parent){
		Control contents=super.createContents(parent);
		//���ñ���
		setTitle("��ӵ�¼��Ϣ");
		//������Ϣ��ʾ
		setMessage("ע���ı�����Ϣ����Ϊ��",IMessageProvider.INFORMATION);
		return contents;
	}
	
	//���öԻ�����������
	protected  Control createDialogArea(Composite parent){
		Composite composite=new Composite(parent,SWT.BORDER);
		composite.setLayout(new GridLayout(2,false));
		GridData data=new GridData(GridData.FILL_HORIZONTAL);
		composite.setLayoutData(data);
		new Label(composite,SWT.NONE).setText("�û���:");
		useName=new Text(composite, SWT.BORDER);
		useName.setLayoutData(data);
		new Label(composite,SWT.NONE).setText("�� ��:");
		passWord=new Text(composite, SWT.BORDER);
		passWord.setEchoChar('*');
		passWord.setLayoutData(data);
		return composite;
	}
	
	//������ťִ�еĲ���
	protected void buttonPressed(int buttonId) {
		//����okִ�����²���
		if(IDialogConstants.OK_ID==buttonId){
			//��֤������Ϣ�ĺϷ���
			if(!checkValue())
				return;
			getValues(doctor);
			boolean flag=DataBaseOperate.getLogin(doctor);
			if(flag){
				okPressed();
			}else
				setErrorMessage("�û������������");
				return;
		}
		//����Cancelִ��
		if(IDialogConstants.CANCEL_ID==buttonId){
//			System.exit(0);
			cancelPressed();
		}
	}

	/**
	 * ���Ͻǹرհ�ť�Ĳ���
	 */
	protected void handleShellCloseEvent() {
        MessageBox messagebox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
        messagebox.setMessage("��ȷ��Ҫ�˳���?");
        int message = messagebox.open();
        if (message == SWT.YES) {
        	System.exit(0);
        }
    }
	
	//��öԻ����е�����
	private void getValues(Doctor doctor) {
		// TODO Auto-generated method stub
		String un=useName.getText();
		doctor.setName(un);
		String pw=passWord.getText();
		doctor.setPassword(pw);
	}

	//���������ݽ�����֤�Ƿ�Ϸ�
	private boolean checkValue() {
		String un =useName.getText();
		String pw=passWord.getText();
		if(un==null||pw==null||un.equals("")||pw.equals("")){
			setErrorMessage("�û��������벻��Ϊ��");
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
