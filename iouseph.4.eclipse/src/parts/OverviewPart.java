package parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.widgets.Composite;

public class OverviewPart {

	@PostConstruct
	public void createControls(Composite parent) {
	  System.out.println(this.getClass().getSimpleName()
	  + " @PostConstruct method called.");
	}

}
