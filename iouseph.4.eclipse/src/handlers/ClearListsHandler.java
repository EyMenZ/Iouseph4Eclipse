package handlers;

import org.eclipse.e4.core.di.annotations.Execute;


public class ClearListsHandler {
	@Execute
	public void execute() {
	  System.out.println((this.getClass().getSimpleName() + " called"));


	}
}