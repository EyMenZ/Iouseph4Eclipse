 package parts;

import javax.annotation.PostConstruct;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Composite;


public class DeezerPart {
	private ListViewer listViewer;


	@PostConstruct
	public void postConstruct(Composite parent) {
		System.out.println(this.getClass().getSimpleName()
				  + " @PostConstruct method called.");

		  listViewer = new ListViewer(parent);
		  Track track = new Track();
		  track.setTitle("dsijn");
		  track.setAlbum("album");
		  listViewer.add(track);
		  listViewer.add("kjbnd");
		  listViewer.add("kjbnd6");
	}




}