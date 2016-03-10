package parts;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.osgi.service.event.Event;

import events.IEventConstants;
import model.Track;

public class DetailsPart {
	private ListViewer listViewer;

	@PostConstruct
	public void createControls(Composite parent) {
	  System.out.println(this.getClass().getSimpleName()
	  + " @PostConstruct method called.");

	  listViewer = new ListViewer(parent);
	  Track track = new Track();
	  track.setTitle("dsijn");
	  track.setAlbum("album");
	  listViewer.add(track);
	  listViewer.add("kjbnd");
	  listViewer.add("kjbnd6");
	  listViewer.add("kjbnd+9");

	  listViewer.addSelectionChangedListener(new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent e) {
			System.out.println(e.getSelection());

			/*BundleContext ctx = FrameworkUtil.getBundle(DetailsPart.class).getBundleContext();
	        ServiceReference<EventAdmin> ref = ctx.getServiceReference(EventAdmin.class);
	        EventAdmin eventAdmin = ctx.getService(ref);
	        Map<String,Object> properties = new HashMap<String, Object>();
	        properties.put("DATA", e.getSelection());

	        Event event = new Event("viewcommunication/syncEvent", properties);
	        eventAdmin.sendEvent(event);

	        event = new Event("viewcommunication/asyncEvent", properties);
	        eventAdmin.postEvent(event);*/

			// asynchronously
			eventBroker.post(IEventConstants.PLAY_TRACK,
			    new Event(IEventConstants.PLAY_TRACK,
			        new HashMap<String, String>()));

		}
	});
	}

	@Inject
	private IEventBroker eventBroker;

	@Focus
	public void onFocus() {
		listViewer.getList().setFocus();
	}

}
