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
	private Text searchText, loginText, passwordText;
	private Button searchButton, loginButton, disconnectButton, signinButton;

	@PostConstruct
	public void createControls(Composite parent, EMenuService menuService) {
		loginText = new Text(parent, SWT.BORDER);
		loginText.setMessage("Login");
		loginText.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));

		passwordText = new Text(parent, SWT.BORDER);
		passwordText.setMessage("Password");
		passwordText.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 2));

		signinButton = new Button(parent, SWT.PUSH);
		signinButton.setText("Sign in");
		signinButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 3));
		signinButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

			}
		});

		loginButton = new Button(parent, SWT.PUSH);
		loginButton.setText("Login");
		loginButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 3));
		loginButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				eventBroker.post(IEventConstants.LOGIN, loginText.getText() + passwordText.getText());
				//TODO connect(loginText.getText() + passwordText.getText());
				searchText.setEnabled(true);
				searchButton.setEnabled(true);
				loginText.setEnabled(false);
				passwordText.setEditable(false);
				disconnectButton.setEnabled(true);
				loginButton.setEnabled(false);
				signinButton.setEnabled(false);
				eventBroker.post(IEventConstants.ENABLE_ADD_PLAYLIST, true);
			}
		});

		disconnectButton = new Button(parent, SWT.PUSH);
		disconnectButton.setEnabled(false);
		disconnectButton.setText("Save and disconnect");
		disconnectButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 3, 3));
		disconnectButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//TODO save()
				searchText.setEnabled(false);
				searchButton.setEnabled(false);
				loginText.setEnabled(true);
				passwordText.setEditable(true);
				disconnectButton.setEnabled(false);
				loginButton.setEnabled(true);
				signinButton.setEnabled(true);
				eventBroker.post(IEventConstants.ENABLE_ADD_PLAYLIST, false);
			}
		});

		searchText = new Text(parent, SWT.BORDER);
		searchText.setEnabled(false);
		searchText.setMessage("Track");
		searchText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 4));
		searchText.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.TRAVERSE_RETURN)	{
					eventBroker.post(IEventConstants.SHOW_TRACKS, searchText.getText());
					eventBroker.post(IEventConstants.SHOW_PLAYLISTS, searchText.getText());
				}
			}
		});

		searchButton = new Button(parent, SWT.PUSH);
		searchButton.setEnabled(false);
		searchButton.setText("Search");
		searchButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 5));
		searchButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				eventBroker.post(IEventConstants.SHOW_TRACKS, searchText.getText());
				eventBroker.post(IEventConstants.SHOW_PLAYLISTS, searchText.getText());
			}
		});

	}

	@Focus
	public void onFocus() {
		searchText.setFocus();
	}

}
