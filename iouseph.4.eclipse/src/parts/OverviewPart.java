package parts;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class OverviewPart {

	@PostConstruct
	public void createControls(Composite parent, EMenuService menuService) {
		TableViewer viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.MULTI);
		System.out.println(this.getClass().getSimpleName()
			+ " @PostConstruct method called.");

		// register context menu on the table
	    menuService.registerContextMenu(viewer.getControl(),
	        "iouseph.4.eclipse.popupmenu.popup");
	}

}
