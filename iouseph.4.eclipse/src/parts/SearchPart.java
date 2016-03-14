package parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import events.IEventConstants;


public class SearchPart {

	@Inject
	private IEventBroker eventBroker;
	//private TableViewer viewer;
	private Text text;

	@PostConstruct
	public void createControls(Composite parent, EMenuService menuService) {
		text = new Text(parent, SWT.BORDER);
		text.setMessage("Track");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		text.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.TRAVERSE_RETURN)	{
					eventBroker.post(IEventConstants.SHOW_TRACKS, text.getText());
					eventBroker.post(IEventConstants.SHOW_PLAYLISTS, text.getText());
				}
			}
		});

		Button button = new Button(parent, SWT.PUSH);
		button.setText("Search");
		button.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 2));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				eventBroker.post(IEventConstants.SHOW_TRACKS, text.getText());
				eventBroker.post(IEventConstants.SHOW_PLAYLISTS, text.getText());
			}
		});

	}

	@Focus
	public void onFocus() {
		text.setFocus();
	}

}
