package com.dy.wizard;

import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.dy.wizardpage.Newpage;

public class NewWzard extends Wizard implements INewWizard {
//	public static final String ID = "com.rengu.zebra.dodaf.ui.wizards.CreateCV2ProductWizard";

	private Newpage createDODAFProductWizardPage;

	public NewWzard() {
		// TODO �Զ����ɵĹ��캯�����
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setDialogSettings(new DialogSettings(getWindowTitle()));
		createDODAFProductWizardPage = new Newpage("name");
		addPage(createDODAFProductWizardPage);
		
		/////
		for(int i = 0;i<getPageCount();i++){  
		    IWizardPage[] pages = getPages(); 
		    System.out.println(pages[0].getName()+pages[0].getTitle());
		    ((WizardPage)pages[i]).setPageComplete(true);  
		}  
		 ////           
//		getContainer().updateButtons();//�������İ�ť���
	}

	@Override
	public boolean performFinish() {
//		String productName = "";
//		try {
//			productName = getDialogSettings().get("productName");
//			CreateDODAFProductJob createDODAFProjectJob = new CreateDODAFProductJob(productName, DODAFProjectConsts.PRODUCT_TYPE.CV2.name());
//			getContainer().run(true, false, createDODAFProjectJob);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			MessageDialogUtils.OpenError("����" + DODAFProjectConsts.PRODUCT_TYPE.CV2.name() + "��Ʒ��" + productName + ",����ʧ�ܣ���鿴ϵͳ��־��");
			return true;
//		}
	}
}
