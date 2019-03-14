package rcp;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		//设置透视图的ID
		String NvaID="com.rengu.demo.views.NvaigatorView";
	
		String SearID="com.rengu.demo.views.SaerchView";
		
	String SaerchPatienInforViewID="com.rengu.demo.views.SaerchPatienInforView";
		
		String SaerchPatienExepenInfoViewID="com.rengu.demo.views.SaerchPatienExepenInfoView";
		
		//添加导航视图
		String Aera=layout.getEditorArea();
		layout.addView(NvaID, IPageLayout.LEFT, 0.3f, Aera);
		
	//导航栏放置Saerfch
	IFolderLayout left=layout.createFolder("left", IPageLayout.BOTTOM, 0.40f, NvaID);
		
		//添加视图
	left.addView(SearID);
	//添加病人和账单信息
	IFolderLayout button=layout.createFolder("button", IPageLayout.BOTTOM, 0.7f, Aera);
		
		button.addView(SaerchPatienInforViewID);
		button.addView(SaerchPatienExepenInfoViewID);
	}
}
