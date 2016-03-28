package events;

import org.osgi.service.event.EventConstants;

public interface IEventConstants extends EventConstants{

	String PLAY_TRACK = "track/play";

	String SHOW_TRACKS = "track/show";

	String SHOW_PLAYLISTS = "playlist/show";

	String LOGIN = "login";

	String ADD_TO_SELECTED_PLAYLIST = "playlist/selected/add";

	String NEW_PLAYLIST = "playlist/new";

	String SELECT_PLAYLIST = "playlist/select";

	String ENABLE_ADD_PLAYLIST = "playlist/add/enable";

	String SET_USER = "user";

	String ADD_TRACK = "track/add";

	String SHOW_PLAYLIST_TRACKS = "playlist/show/tracks";

}
