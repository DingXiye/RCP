package com.dy.NewWizard;
import org.eclipse.jface.viewers.IBasicPropertyConstants;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.ui.internal.dialogs.WizardCollectionElement;
import org.eclipse.ui.internal.dialogs.WorkbenchWizardElement;
import org.eclipse.ui.internal.registry.WizardsRegistryReader;

public class MyNewWizardCollectionComparator extends ViewerComparator {
	/**
	 * Static instance of this class.
	 */
	public final static MyNewWizardCollectionComparator INSTANCE = new MyNewWizardCollectionComparator();

	/**
	 * Creates an instance of <code>NewWizardCollectionSorter</code>. Since this
	 * is a stateless sorter, it is only accessible as a singleton; the private
	 * visibility of this constructor ensures this.
	 */
	private MyNewWizardCollectionComparator() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ViewerSorter#category(java.lang.Object)
	 */
	public int category(Object element) {
		if (element instanceof WorkbenchWizardElement) {
			return -1;
		}
		if (element instanceof WizardCollectionElement) {
			String id = ((WizardCollectionElement) element).getId();
			if (WizardsRegistryReader.GENERAL_WIZARD_CATEGORY.equals(id)) {
				return 1;
			}
			if (WizardsRegistryReader.UNCATEGORIZED_WIZARD_CATEGORY.equals(id)) {
				return 3;
			}
			if (WizardsRegistryReader.FULL_EXAMPLES_WIZARD_CATEGORY.equals(id)) {
				return 4;
			}
			return 2;
		}
		return super.category(element);
	}

	/**
	 * Return true if this sorter is affected by a property change of
	 * propertyName on the specified element.
	 */
	public boolean isSorterProperty(Object object, String propertyId) {
		return propertyId.equals(IBasicPropertyConstants.P_TEXT);
	}
}
