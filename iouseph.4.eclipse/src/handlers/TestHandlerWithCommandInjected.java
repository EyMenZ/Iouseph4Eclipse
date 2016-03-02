package handlers;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.di.annotations.Execute;

public class TestHandlerWithCommandInjected {
	private String parametername = "iouseph.4.eclipse.commandparameter.0.input";
		  @Execute
		  public void execute(ParameterizedCommand command) {
		    Object queryId = command.getParameterMap().get(parametername);
		    // more...
		  }
}
