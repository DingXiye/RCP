package com.dy.editor;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class IsSexFilter extends ViewerFilter{
	public IsSexFilter() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		StaffEntity entity=(StaffEntity)element;
		return entity.isSex()==true;
	}
}
