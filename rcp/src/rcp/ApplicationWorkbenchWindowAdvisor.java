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
   	 //初始化窗口大小
       configurer.setInitialSize(new Point(200, 200));
       //设置菜单栏的可见性
       configurer.setShowMenuBar(true);
       //设置工具栏的可见性
       configurer.setShowCoolBar(true);
       //设置状态栏的可见性
       configurer.setShowStatusLine(true);
       //设置工作进度
       configurer.setShowProgressIndicator(true);
       //设置快速视图
       configurer.setShowFastViewBars(true);
       //设置透视图图栏
       configurer.setShowPerspectiveBar(true);
       //设置快速视图栏
       configurer.setShowFastViewBars(true);
       
       configurer.setTitle("Patient Infomation Managerment Sysyem"); //$NON-NLS-1$
    }
}
