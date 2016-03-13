package parts;


import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.event.EventAdmin;

import api.Iapi;
import events.IEventConstants;

public class DetailsPart {
	private ListViewer listViewer;

	@PostConstruct
	public void createControls(Composite parent) {
	  System.out.println(this.getClass().getSimpleName()
	  + " @PostConstruct method called.");

	  listViewer = new ListViewer(parent);

	}


	@Inject
	@Optional
	void showTracks(@UIEventTopic(IEventConstants.SHOW_TRACKS) Object message) {
		//listViewer.add(message);
	    //System.out.println(FrameworkUtil.getBundle(OverviewPart.class).getBundleContext().getServiceReference(EventAdmin.class));
		Iapi api = new DeezerClient();
		listViewer.add(api.get_search(message.toString()));
	}

	@Focus
	public void onFocus() {
		listViewer.getList().setFocus();
	}

}
