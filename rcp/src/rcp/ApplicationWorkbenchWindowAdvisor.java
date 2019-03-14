package rcp;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
    	IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
   	 //��ʼ�����ڴ�С
       configurer.setInitialSize(new Point(200, 200));
       //���ò˵����Ŀɼ���
       configurer.setShowMenuBar(true);
       //���ù������Ŀɼ���
       configurer.setShowCoolBar(true);
       //����״̬���Ŀɼ���
       configurer.setShowStatusLine(true);
       //���ù�������
       configurer.setShowProgressIndicator(true);
       //���ÿ�����ͼ
       configurer.setShowFastViewBars(true);
       //����͸��ͼͼ��
       configurer.setShowPerspectiveBar(true);
       //���ÿ�����ͼ��
       configurer.setShowFastViewBars(true);
       
       configurer.setTitle("Patient Infomation Managerment Sysyem"); //$NON-NLS-1$
    }
}
