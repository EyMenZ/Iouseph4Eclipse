package model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

	private String id;
	private String title;
	private String owner;
	private String source;
	private String url;
	private List<Track> tracks;

	public Playlist() {
		this(null, null, null, null, null,null);
	}

	public Playlist(String id, String title, String owner, String source,
			List<Track> tracks, String url) {
		super();
		this.id = id;
		this.title = title;
		this.owner = owner;
		this.source = source;
		this.tracks = new ArrayList<Track>();
		this.url=url;
	}



	/**
	 * @return
	 */
	public List<Track> getTracks() {
		return tracks;
	}

	/**
	 * @param track
	 * @return
	 */
	public boolean addTrack(Track track)
	{
		if(!tracks.contains(track)) {
			tracks.add(track);
			return true;
		}
		return false;
	}

	/**
	 * @param track
	 * @return
	 */
	public boolean deleteTrack(Track track){
		return tracks.remove(track);
	}
	/**
	 *
	 */
	public void clearPlaylist()
	{
		tracks.clear();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return /*"Playlist [id=" + id + ", title=" + title + ", owner=" + owner
				+ ", source=" + source + ", tracks=" + tracks + "]"*/title;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String myurl) {
		this.url = myurl;
	}
}
