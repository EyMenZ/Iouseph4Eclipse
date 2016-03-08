package parts;

import javax.annotation.PostConstruct;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Composite;

import model.Track;

public class DetailsPart {

	@PostConstruct
	public void createControls(Composite parent) {
	  System.out.println(this.getClass().getSimpleName()
	  + " @PostConstruct method called.");

	  ListViewer listViewer = new ListViewer(parent);
	  Track track = new Track();
	  track.setTitle("dsijn");
	  //listViewer.add(track.getTitle());
	  listViewer.add("kjbnd");
	  listViewer.add("kjbnd6");
	  listViewer.add("kjbnd+9");
	}
}
