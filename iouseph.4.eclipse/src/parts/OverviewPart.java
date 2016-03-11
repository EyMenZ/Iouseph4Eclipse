package parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import events.IEventConstants;

public class OverviewPart {
	private TableViewer viewer;


	@PostConstruct
	public void createControls(Composite parent, EMenuService menuService) {
		viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.MULTI);
		System.out.println(this.getClass().getSimpleName() + " @PostConstruct method called.");

		// register context menu on the table
	    menuService.registerContextMenu(viewer.getControl(), "iouseph.4.eclipse.popupmenu.popup");

	}

	@Inject
	@Optional
	void logging(@UIEventTopic(IEventConstants.PLAY_TRACK) Object message) {
		viewer.add(message);
	}

}
