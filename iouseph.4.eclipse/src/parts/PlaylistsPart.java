package parts;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Composite;

import api.DeezerClient;
import api.Iapi;
import events.IEventConstants;
import model.Playlist;

public class PlaylistsPart {
	private ListViewer listViewer;

	@PostConstruct
	public void createControls(Composite parent) {

	  listViewer = new ListViewer(parent);

	}

	@Inject
	@Optional
	void showPlaylists(@UIEventTopic(IEventConstants.SHOW_PLAYLISTS) Object message) {
		Iapi api = new DeezerClient();
		List<Playlist> playlists = api.get_playlists(message.toString());
		for (int i = 0; i < playlists.size(); i++)
			listViewer.add(playlists.get(i));
	}

	@Focus
	public void onFocus() {
		listViewer.getList().setFocus();
	}

}
