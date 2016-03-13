package parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.event.EventAdmin;

import events.IEventConstants;
import model.Track;

public class OverviewPart {

	@Inject
	private IEventBroker eventBroker;
	private TableViewer viewer;


	@PostConstruct
	public void createControls(Composite parent, EMenuService menuService) {
		viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.MULTI);
		System.out.println(this.getClass().getSimpleName() + " @PostConstruct method called.");

		// register context menu on the table
	    menuService.registerContextMenu(viewer.getControl(), "iouseph.4.eclipse.popupmenu.popup");

	    viewer.add("muse");
	    viewer.add("eminem");

	    viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent e) {
				System.out.println(e.getSelection());

				eventBroker.post(IEventConstants.SHOW_TRACKS, e.getSelection());

			}
		});
	}

	@Inject
	@Optional
	void logging(@UIEventTopic(IEventConstants.PLAY_TRACK) Object message) {
		viewer.add(message);
	}



	@Focus
	public void onFocus() {
		viewer.getTable().setFocus();
	}

}
