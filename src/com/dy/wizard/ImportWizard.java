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
             //Ϊ�����ý��ȼ���
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
       * ���������е�Finishʱִ��
       */
      @Override
      public boolean performFinish() {
    	  final String containerName=newItemWizardPage.getContainerName();
    	  
             System.out.println("��ɰ�ť�����£�");
              try {
                  Thread.sleep(5000);
              } catch (InterruptedException e) {
              }
             return true;

      }

      /**
       * ��performFinishʹ��
       */
     private void doFinish(){
    	 
     }
}
