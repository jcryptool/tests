package org.jcryptool.test.nestedscroll;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.jcryptool.test.nestedscroll.ScrollingUtils.ScrollableControl;

public class ScrollTest extends Shell {
	private Text txtTextfieldLine;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			ScrollTest shell = new ScrollTest(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public ScrollTest(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(1, false));
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel = new Label(composite, SWT.WRAP);
		lblNewLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblNewLabel.setBounds(0, 0, 53, 14);
		lblNewLabel.setText("New Label");
		
		txtTextfieldLine = new Text(composite, SWT.BORDER | SWT.H_SCROLL| SWT.READ_ONLY | SWT.MULTI);
		txtTextfieldLine.setText("textfield line 1 mit einem super lanegen tet der dfjsdkfj ejfklsjdfkl  ajsdklfjaslkd jflsk df\ntextfield line 2\ntextfield line 3\ntextfield line 4\ntextfield line 5\ntextfield line 6\ntextfield line 7\ntextfield line 8\ntextfield line 9\ntextfield line 10\ntextfield line 11\ntextfield line 12\ntextfield line 13\ntextfield line 14\ntextfield line 15\ntextfield line 16\ntextfield line 17\ntextfield line 18\ntextfield line 19\ntextfield line 20\ntextfield line 21\ntextfield line 22\ntextfield line 23\ntextfield line 24\ntextfield line 25\ntextfield line 26\ntextfield line 27\ntextfield line 28\n\n");
		GridData gd_txtTextfieldLine = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtTextfieldLine.heightHint = 110;
		gd_txtTextfieldLine.widthHint = 200;
		txtTextfieldLine.setLayoutData(gd_txtTextfieldLine);

		txtTextfieldLine.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseScrolled(MouseEvent e) {
				ScrollableControl thisAsScrollable;
				if ((txtTextfieldLine.getStyle() & SWT.V_SCROLL) != 0) {
					thisAsScrollable = new ScrollingUtils.ScrollableControl(txtTextfieldLine, txtTextfieldLine.getVerticalBar());
				} else if ((txtTextfieldLine.getStyle() & SWT.H_SCROLL) != 0) {
					thisAsScrollable = new ScrollingUtils.ScrollableControl(txtTextfieldLine, txtTextfieldLine.getHorizontalBar());
				} else {
					thisAsScrollable = new ScrollingUtils.ScrollableControl(txtTextfieldLine, null);
				}
					
					
				
				thisAsScrollable.propagateScrollIfNecessary(e);
			}
		});

		Label lblNewLabel_1 = new Label(composite, SWT.WRAP);
		lblNewLabel_1.setBounds(0, 0, 53, 14);
		lblNewLabel_1.setText("line 1\nline 2\nline 3\nline 4\nline 5\nline 6\nline 7\nline 8\nline 9\nline 10\nline 11\nline 12\nline 13\nline 14\nline 15\nline 16\nline 17\nline 18\nline 19\nline 20\nline 21\nline 22\nline 23\nline 24\nline 25\nline 26\nline 27\nline 28\n\n");
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(450, 300);
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
