package com.dy.wizard;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.internal.registry.WizardParameterValues.New;

import com.dy.NewWizard.MyNewWizardSelectionPage;
import com.dy.wizardpage.FirstWizardPage;
import com.dy.wizardpage.Newpage;

public class ImportWizard extends Wizard implements INewWizard{
	  private FirstWizardPage newItemWizardPage;
      private ISelection selection;
      public ImportWizard(){
             super();
             //为向导设置进度监视
             setNeedsProgressMonitor(true);
      }
      
      @Override
      public void init(IWorkbench arg0, IStructuredSelection selection) {
             // TODO Auto-generated method stub
             this.selection = selection;
      }

      @Override
      public void addPages() {
             // TODO Auto-generated method stub
             newItemWizardPage=new FirstWizardPage( selection);
             addPage(newItemWizardPage);
      }

      /**
       * 当单击向导中的Finish时执行
       */
      @Override
      public boolean performFinish() {
    	  final String containerName=newItemWizardPage.getContainerName();
    	  
             System.out.println("完成按钮被按下！");
              try {
                  Thread.sleep(5000);
              } catch (InterruptedException e) {
              }
             return true;

      }

      /**
       * 供performFinish使用
       */
     private void doFinish(){
    	 
     }
}
