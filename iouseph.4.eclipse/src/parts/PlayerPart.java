package parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import events.IEventConstants;
import model.Track;

public class PlayerPart {
	private Label title, album, artist;
	private Browser browser;

	@PostConstruct
	public void createControls(Composite parent) {
		parent.setLayout(new GridLayout(2, false));

		title = new Label(parent, SWT.NONE);
		title.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		album = new Label(parent, SWT.NONE);
		album.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		artist = new Label(parent, SWT.NONE);
		artist.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		browser = new Browser(parent, SWT.NONE);
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
	}

	@Inject
	@Optional
	void playTrack(@UIEventTopic(IEventConstants.PLAY_TRACK) Object message) {
		Track track = (Track) ((StructuredSelection) message).getFirstElement();
		browser.setUrl(track.getExternalUrl());
		title.setText(track.getTitle());
		album.setText(track.getAlbum());
		artist.setText(track.getArtist());
	}

	@Focus
	public void onFocus() {
		title.setFocus();
		album.setFocus();
		artist.setFocus();
	}

	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

}
