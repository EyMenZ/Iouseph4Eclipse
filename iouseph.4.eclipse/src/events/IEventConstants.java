package events;

import org.osgi.service.event.EventConstants;

public interface IEventConstants extends EventConstants{

	String PLAY_TRACK = "PLAY";

	String SHOW_TRACKS = "tracks";

	String SHOW_PLAYLISTS = "playlists";

	String LOGIN = "login";

	String ADD_TO_SELECTED_PLAYLIST = "playlist/selected/add";

	String NEW_PLAYLIST = "playlist/new";

	String SELECT_PLAYLIST = "playlist/select";

	String ENABLE_ADD_PLAYLIST = "playlist/add/enable";

}
