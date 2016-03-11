 
package parts;

import javax.inject.Inject;
import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;

public class TestPart {
	@Inject
	public TestPart() {
		
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		
	}
	
	
	
	
}