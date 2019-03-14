package test;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * ��͸��ͼ��ע����ͼ
 * @author dingye
 *
 */
public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		//���͸��ͼ�ı༭�ռ��ʶ
		String editorArea=layout.getEditorArea();
		IFolderLayout left=layout.createFolder("left", IPageLayout.LEFT, 0.3f, editorArea);
		//��͸��ͼ�������ͼ
		left.addView("view1");
		left.addView("view2");//��plugin.xml�е�id��Ӧ
		//ʵ����ͼ����Ч��
		IFolderLayout buttom=layout.createFolder("buttom", IPageLayout.BOTTOM, IPageLayout.DEFAULT_VIEW_RATIO, editorArea);
		buttom.addView("view3");
		buttom.addView("view4");
		//���������ͼ
		buttom.addView(IPageLayout.ID_PROBLEM_VIEW);
		buttom.addView(IPageLayout.ID_TASK_LIST);
	}
}
