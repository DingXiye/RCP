package rcp;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		//����͸��ͼ��ID
		String NvaID="com.rengu.demo.views.NvaigatorView";
	
		String SearID="com.rengu.demo.views.SaerchView";
		
	String SaerchPatienInforViewID="com.rengu.demo.views.SaerchPatienInforView";
		
		String SaerchPatienExepenInfoViewID="com.rengu.demo.views.SaerchPatienExepenInfoView";
		
		//��ӵ�����ͼ
		String Aera=layout.getEditorArea();
		layout.addView(NvaID, IPageLayout.LEFT, 0.3f, Aera);
		
	//����������Saerfch
	IFolderLayout left=layout.createFolder("left", IPageLayout.BOTTOM, 0.40f, NvaID);
		
		//�����ͼ
	left.addView(SearID);
	//��Ӳ��˺��˵���Ϣ
	IFolderLayout button=layout.createFolder("button", IPageLayout.BOTTOM, 0.7f, Aera);
		
		button.addView(SaerchPatienInforViewID);
		button.addView(SaerchPatienExepenInfoViewID);
	}
}
