package model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/**
 *
 * @author youssef zemmahi, aymen zalila, marcial lopez ferrada
 *
 */
public class User {
	//TODO cette classe sera implementee dans une prochaine version
	private UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
	private String id_;
	private Map<String,Playlist> playlists=new HashMap<String, Playlist>();

	/**
	 * constructeur par defaut
	 */
	User()
	{
		id_=uid.randomUUID().toString();
	}

	/**
	 * methode retournant l'id de l'utilisateur
	 * @return
	 */
	public String getId() {
		return id_;
	}
	/**
	 * methode permettant d'attribuer un id a l'utilisateur
	 * @param id
	 */
	public void setId(String id) {
		this.id_ = id;
	}

	/**
	 * methode permettant de retourner une playlist selon son id
	 * @param playlistId
	 * @return playlist ou null si playlist non trouve
	 */
	public Playlist getPlaylist(String playlistId)
	{
		return playlists.get(playlistId);
	}

	/**
	 * methode permettant d'ajouter une playlist au playlists de l'utilisateur
	 * @param playlist
	 * @return true si la playlist a ete ajouter, false sinon
	 */
	public boolean addPlaylist(Playlist playlist)
	{
		if(!playlists.containsKey(playlist))
		{
			playlists.put(playlist.getId(), playlist);
			return true;
		}
		return false;
	}

	/**
	 * methode permettant de supprimer une playlist selon son id
	 * @param idPlaylist
	 * @return true si la playlist a ete supprime, false sinonz
	 */
	public boolean deletePlaylist(String idPlaylist)
	{
		if(!playlists.containsKey(idPlaylist))
		{
			playlists.remove(idPlaylist);
			return true;
		}

		return false;
	}

	/**
	 * methode permettant de vider les playlist de l'utilisateur
	 */
	void emptyPlaylists()
	{
		playlists.clear();
	}

}
