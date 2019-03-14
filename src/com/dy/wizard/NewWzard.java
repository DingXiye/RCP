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
		// TODO 自动生成的构造函数存根
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
//		getContainer().updateButtons();//立即更改按钮情况
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
//			MessageDialogUtils.OpenError("创建" + DODAFProjectConsts.PRODUCT_TYPE.CV2.name() + "产品：" + productName + ",创建失败，请查看系统日志。");
			return true;
//		}
	}
}
