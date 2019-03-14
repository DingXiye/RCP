package test;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.dy.dialog.LoginDialog;
import com.dy.util.Doctor;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public Object start(IApplicationContext context) throws Exception {
		Display display = PlatformUI.createDisplay();
		try {
			if(!preOpen())//登录判断
				return IApplication.EXIT_OK;
			int returnCode=PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			if(returnCode==PlatformUI.RETURN_RESTART)
				return IApplication.EXIT_RESTART;
			else
				return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
		
	}

	//判断用户名和密码是否已经存在
	private boolean preOpen() {
		LoginDialog id =new LoginDialog(null);
		Doctor doctor=new Doctor();
		id.setDoctor(doctor);
		if(id.open()==IDialogConstants.OK_ID)
			doctor=new Doctor("dy","1234");
		if(doctor==null){
			MessageDialog.openInformation(null, null, "请填写正确信息");
			return false;
		}
		if(doctor!=null){//用户存在
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
//		if (!PlatformUI.isWorkbenchRunning())
//			return;
//		final IWorkbench workbench = PlatformUI.getWorkbench();
//		final Display display = workbench.getDisplay();
//		display.syncExec(new Runnable() {
//			public void run() {
//				if (!display.isDisposed())
//					workbench.close();
//			}
//		});
		final IWorkbench iWorkbench=PlatformUI.getWorkbench();
		if(iWorkbench==null){
			return;
		}
		final Display display=iWorkbench.getDisplay();
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(!display.isDisposed())
					iWorkbench.close();
			}
		});
	}
}
