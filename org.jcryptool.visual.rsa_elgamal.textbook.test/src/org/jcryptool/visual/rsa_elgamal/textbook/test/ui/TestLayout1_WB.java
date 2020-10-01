package org.jcryptool.visual.rsa_elgamal.textbook.test.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Group;

public class TestLayout1_WB extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TestLayout1_WB(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		Group composite = new Group(this, SWT.BORDER);
		composite.setText("@self:id=info \"Information\"");
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(0, 0, 70, 17);
		lblNewLabel.setText("Insert info about the operation");
		
		Group composite_1 = new Group(this, SWT.BORDER);
		composite_1.setText("@self:id=params \"Parameters\"");
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite_1.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setText("Introduce the parameters here");
		
		Group composite_3 = new Group(composite_1, SWT.NONE);
		composite_3.setText("@anchor @self.id=allparams");
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite_3.setLayout(new GridLayout(1, false));
		
		Group composite_2 = new Group(this, SWT.BORDER);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_2.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel_1_1 = new Label(composite_2, SWT.NONE);
		lblNewLabel_1_1.setText("Summarize a finished operation and explain what the user is looking at");
		lblNewLabel_1_1.setBounds(0, 0, 169, 17);
		
		Group composite_3_1 = new Group(composite_2, SWT.NONE);
		composite_3_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_3_1.setText("@anchor @self:id=lastresult");
		composite_3_1.setBounds(0, 0, 225, 29);
		composite_3_1.setLayout(new GridLayout(1, false));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
