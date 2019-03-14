package com.dy.ActionFactory.util;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.internal.actions.CommandAction;

public class MyActionFactory extends ActionFactory {

	protected MyActionFactory(String actionId, String commandId) {
		super(actionId, commandId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IWorkbenchAction create(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		return null;
	}

	private static class WorkbenchCommandAction extends CommandAction implements
			IWorkbenchAction {
		/**
		 * @param commandIdIn
		 * @param window
		 */
		public WorkbenchCommandAction(String commandIdIn,
				IWorkbenchWindow window) {
			super(window, commandIdIn);
		}
	}

	public static final ActionFactory My = new ActionFactory("my", //$NON-NLS-1$
			IWorkbenchCommandConstants.FILE_NEW) {
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if (window == null) {
				throw new IllegalArgumentException();
			}
			
			WorkbenchCommandAction action = new WorkbenchCommandAction(
					getCommandId(), window);
			System.out.println(window.getPages()[0].getLabel());
			System.out.println("commandid"+getCommandId());
			action.setId(getId());
			System.out.println("id"+getId());
			ISharedImages images = window.getWorkbench().getSharedImages();
			action.setImageDescriptor(images
					.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
			action.setDisabledImageDescriptor(images
					.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
			action.setText("MyFactory");
			action.setToolTipText("MyFactoryTooltip");
			//PlatformUI.PLUGIN_ID + "." + "new_action_context"
//			window.getWorkbench().getHelpSystem()
//					.setHelp(action, IWorkbenchHelpContextIds.NEW_ACTION);
//			System.out.println(IWorkbenchHelpContextIds.NEW_ACTION);
			return action;
		}
	};
	
}
