package parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
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
import model.AccountManager;
import model.ObjectsManager;
import model.User;


public class SearchPart {

	@Inject
	private IEventBroker eventBroker;
	//private TableViewer viewer;
	private Text searchText, loginText, passwordText;
	private Button searchButton, loginButton, disconnectButton, signinButton;
	private User user = new User();
	private AccountManager am = new AccountManager();
	private ObjectsManager om = new ObjectsManager();

	@PostConstruct
	public void createControls(Composite parent, EMenuService menuService) {
		am.loadAccountsInformations();

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
				Enable(am.Enregistrement(loginText.getText(), passwordText.getText()));
			}
		});

		loginButton = new Button(parent, SWT.PUSH);
		loginButton.setText("Login");
		loginButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 3));
		loginButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				//eventBroker.post(IEventConstants.LOGIN, loginText.getText() + passwordText.getText());
				//TODO connect(loginText.getText() + passwordText.getText());
				String id = am.Authentification(loginText.getText(), passwordText.getText());
				if (id != null)
					user = om.DeserializeUser(id);
				eventBroker.post(IEventConstants.SET_USER, user);
				eventBroker.post(IEventConstants.SHOW_PLAYLISTS, user);
				Enable(true);
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
				Enable(false);
				am.saveAccountsInformations();
				om.SerializeUser(user);
				user = new User();
				eventBroker.post(IEventConstants.SET_USER, user);
				eventBroker.post(IEventConstants.SHOW_PLAYLISTS, user);
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
			}
		});

	}

	private void Enable(boolean b){
		searchText.setEnabled(b);
		searchButton.setEnabled(b);
		loginText.setEnabled(!b);
		passwordText.setEditable(!b);
		disconnectButton.setEnabled(b);
		loginButton.setEnabled(!b);
		signinButton.setEnabled(!b);
		eventBroker.post(IEventConstants.ENABLE_ADD_PLAYLIST, b);
	}

	@Inject
	@Optional
	void setUser(@UIEventTopic(IEventConstants.SET_USER) Object message) {
		user = (User) message;
	}

	@Focus
	public void onFocus() {
		searchText.setFocus();
	}

}
