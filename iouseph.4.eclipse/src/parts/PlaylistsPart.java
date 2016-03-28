package parts;

import java.io.ObjectInputStream.GetField;
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
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import api.DeezerClient;
import api.Iapi;
import events.IEventConstants;
import model.Playlist;
import model.Track;
import model.User;

public class PlaylistsPart {

	@Inject
	private IEventBroker eventBroker;
	private ListViewer listViewer;
	private Text newPlaylistText;
	private Button newPlaylistButton;
	private User user;

	@PostConstruct
	public void createControls(Composite parent) {
		newPlaylistText = new Text(parent, SWT.BORDER);
		newPlaylistText.setEnabled(false);
		newPlaylistText.setMessage("new playlist");
		newPlaylistText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		newPlaylistText.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.TRAVERSE_RETURN) {
					eventBroker.post(IEventConstants.NEW_PLAYLIST, newPlaylistText.getText());
				}
			}
		});

		newPlaylistButton = new Button(parent, SWT.PUSH);
		newPlaylistButton.setEnabled(false);
		newPlaylistButton.setText("save");
		newPlaylistButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 2));
		newPlaylistButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				eventBroker.post(IEventConstants.NEW_PLAYLIST, newPlaylistText.getText());
			}
		});
		listViewer = new ListViewer(parent);

		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				eventBroker.post(IEventConstants.SELECT_PLAYLIST, event.getSelection());
			}
		});

		listViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				eventBroker.post(IEventConstants.SHOW_PLAYLIST_TRACKS, event.getSelection());
			}
		});

	}

	@Inject
	@Optional
	void showPlaylists(@UIEventTopic(IEventConstants.SHOW_PLAYLISTS) Object message) {
		listViewer.getList().removeAll();
		for (int i = 0; i < user.getPlaylists().values().size(); i++){
			listViewer.add(user.getPlaylists().values().toArray()[i]);//TODO je c pas pourquoi ça n'affiche pas les playlists !!!!
		}
	}

	@Inject
	@Optional
	void newPlaylist(@UIEventTopic(IEventConstants.NEW_PLAYLIST) Object message) {
		Playlist playlist = new Playlist();
		playlist.setIdUser(user.getId());
		playlist.setTitle(message.toString());
		playlist.setOwner(user.getUsername());
		playlist.setSource("Iouseph");
		if (user.addPlaylist(playlist))
			listViewer.add(playlist);
	}

	@Inject
	@Optional
	void newPlaylistEnable(@UIEventTopic(IEventConstants.ENABLE_ADD_PLAYLIST) Object message) {
		newPlaylistText.setEnabled((boolean) message);
		newPlaylistButton.setEnabled((boolean) message);
		listViewer.getList().removeAll();
	}

	@Inject
	@Optional
	void setUser(@UIEventTopic(IEventConstants.SET_USER) Object message) {
		user = (User) message;
	}

	@Inject
	@Optional
	void addTrack(@UIEventTopic(IEventConstants.ADD_TO_SELECTED_PLAYLIST) Object message) {
		Playlist playlist = (Playlist) listViewer.getStructuredSelection().getFirstElement();
		playlist.addTrack((Track) ((StructuredSelection) message).getFirstElement());
		if (!user.addPlaylist(playlist))
			user.getPlaylists().get(playlist.getTitle())
					.addTrack((Track) ((StructuredSelection) message).getFirstElement());
		eventBroker.post(IEventConstants.SET_USER, user);
	}

	@Focus
	public void onFocus() {
		listViewer.getList().setFocus();
	}

}
