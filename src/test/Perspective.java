package test;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * 在透视图中注册视图
 * @author dingye
 *
 */
public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		//获得透视图的编辑空间标识
		String editorArea=layout.getEditorArea();
		IFolderLayout left=layout.createFolder("left", IPageLayout.LEFT, 0.3f, editorArea);
		//在透视图中添加视图
		left.addView("view1");
		left.addView("view2");//与plugin.xml中的id对应
		//实现视图叠加效果
		IFolderLayout buttom=layout.createFolder("buttom", IPageLayout.BOTTOM, IPageLayout.DEFAULT_VIEW_RATIO, editorArea);
		buttom.addView("view3");
		buttom.addView("view4");
		//添加问题视图
		buttom.addView(IPageLayout.ID_PROBLEM_VIEW);
		buttom.addView(IPageLayout.ID_TASK_LIST);
	}
}
