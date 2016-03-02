package handlers;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;

import model.Track;

public class NewHandler {

	@Execute
	public void execute(IEclipseContext context) {

		  System.out.println((this.getClass().getSimpleName() + " called"));

		  Track track = new Track();
	  // put an example value in the context
	  context.set("myactivePartId",  "parts.OverviewPart");
	}
}
