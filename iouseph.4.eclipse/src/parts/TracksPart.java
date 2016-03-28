package parts;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import api.DeezerClient;
import api.Iapi;
import api.SpotifyClient;
import events.IEventConstants;
import model.Playlist;
import model.Track;
import model.User;

public class TracksPart {

	@Inject
	private IEventBroker eventBroker;
	private TableViewer viewer;
	private Playlist selectedPlaylist = null;
	private User user;

	@PostConstruct
	public void createControls(Composite parent) {

	  viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.MULTI);
	  viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent e) {
				eventBroker.post(IEventConstants.ADD_TO_SELECTED_PLAYLIST, e.getSelection());
			}
		});
	  viewer.addDoubleClickListener(new IDoubleClickListener() {

		@Override
		public void doubleClick(DoubleClickEvent event) {
			eventBroker.post(IEventConstants.PLAY_TRACK, event.getSelection());
		}
	});
	}


	@Inject
	@Optional
	void showTracks(@UIEventTopic(IEventConstants.SHOW_TRACKS) Object message) {
		viewer.getTable().removeAll();
		Iapi api = new SpotifyClient();
		List<Track> tracks = api.get_search(message.toString());
		Iapi api2 = new DeezerClient();
		List<Track> tracks2 = api2.get_search(message.toString());
		for (int i = 0; i < tracks.size(); i++)
			{
			viewer.add(tracks.get(i));
			viewer.add(tracks2.get(i));
			}
	}

	@Inject
	@Optional
	void getSelectedPlaylist(@UIEventTopic(IEventConstants.SELECT_PLAYLIST) Object message) {
		selectedPlaylist = new Playlist();//FIXME
	}

	@Inject
	@Optional
	void showPlaylistTracks(@UIEventTopic(IEventConstants.SHOW_PLAYLIST_TRACKS) Object message) {
		viewer.getTable().removeAll();
		viewer.add(((Playlist) ((StructuredSelection) message).getFirstElement()).getTracks().toArray());
	}

	@Inject
	@Optional
	void setUser(@UIEventTopic(IEventConstants.SET_USER) Object message) {
		user = (User) message;
	}

	@Focus
	public void onFocus() {
		viewer.getTable().setFocus();
	}

}
