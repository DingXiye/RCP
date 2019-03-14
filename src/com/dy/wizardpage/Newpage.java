package com.dy.wizardpage;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
//import org.apache.commons.lang3.StringUtils;

import org.eclipse.jface.wizard.WizardPage;

import com.mysql.jdbc.StringUtils;

public class Newpage extends WizardPage{
	private String productType;

	private Text productNameText;

	public Newpage(String productType) {
		super("创建" + productType + "产品向导");
		this.productType = productType;
		this.setTitle("创建" + productType + "产品向导");
		this.setDescription("输入正确的产品名称，创建一个DODAF产品");
		this.setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		setControl(composite);
		GridLayout gridLayout = new GridLayout(2, false);
		composite.setLayout(gridLayout);
		Label productNameLable = new Label(composite, SWT.NONE);
		productNameLable.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		productNameLable.setText(productType + "产品名称：");
		productNameText = new Text(composite, SWT.BORDER);
		productNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		productNameText.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event event) {
				isFinished();
			}
		});
	}

	private void isFinished() {
		String productName = productNameText.getText();
		if (StringUtils.isNullOrEmpty(productName)) {
			setErrorMessage("请输入合法的产品名称！");
			setPageComplete(false);
			return;
		}
		setErrorMessage(null);
		setPageComplete(true);
		IDialogSettings iDialogSettings = getWizard().getDialogSettings();
		iDialogSettings.put("productName", productName);
	}
}
