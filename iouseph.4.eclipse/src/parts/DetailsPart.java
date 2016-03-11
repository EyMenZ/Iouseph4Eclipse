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

			eventBroker.post(IEventConstants.PLAY_TRACK, e.getSelection());

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
