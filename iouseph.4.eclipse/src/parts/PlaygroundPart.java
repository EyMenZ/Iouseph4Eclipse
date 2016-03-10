package parts;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

public class PlaygroundPart {
	private Text text;
	private Browser browser;

	@PostConstruct
	public void createControls(Composite parent) {
		System.out.println(this.getClass().getSimpleName()
				  + " @PostConstruct method called.");
		parent.setLayout(new GridLayout(2, false));

		text = new Text(parent, SWT.BORDER);
		text.setMessage("Enter City");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button button = new Button(parent, SWT.PUSH);
		button.setText("Search");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String city = text.getText();
				if (city.isEmpty()) {
					return;
				}
				try {
					// not supported at the moment by Google
					// browser.setUrl("http://maps.google.com/maps?q="
					// + URLEncoder.encode(city, "UTF-8")
					// + "&output=embed");
					browser.setUrl(
							"https://www.google.com/maps/place/" + URLEncoder.encode(city, "UTF-8") + "/&output=embed");

				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}
		});

		browser = new Browser(parent, SWT.NONE);
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		/*BundleContext ctx = FrameworkUtil.getBundle(PlaygroundPart.class).getBundleContext();
	    EventHandler handler = new EventHandler() {
	      public void handleEvent(final Event event) {
	        if( parent.getDisplay().getThread() == Thread.currentThread() ) {
	          text.setText(event.getProperty("DATA").toString());
	        } else {
	          parent.getDisplay().syncExec(new Runnable() {
	            public void run() {
	            	text.setText(event.getProperty("DATA").toString());
	            }
	          });
	        }
	      }
	    };

	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, "viewcommunication/*");
	    ctx.registerService(EventHandler.class, handler, properties);*/

	}

	@Focus
	public void onFocus() {
		text.setFocus();
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}


}
