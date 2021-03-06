package gui;

import gui.models.Firmware;
import gui.models.Firmwares;
import gui.tools.FtfFilter;
import gui.tools.WidgetTask;
import gui.tools.WidgetsTool;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;
import java.util.jar.JarFile;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.system.OS;

public class TABackupSet extends Dialog {

	protected Shell shlBackupName;
	private Text txtName;
	private Button btnOK;
	Vector files = new Vector();
	String result = null;
	private Button btnCancel;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public TABackupSet(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public String open() {
		createContents();
		WidgetsTool.setSize(shlBackupName);
		
		Label lblBackupName = new Label(shlBackupName, SWT.NONE);
		FormData fd_lblBackupName = new FormData();
		fd_lblBackupName.top = new FormAttachment(0, 10);
		fd_lblBackupName.left = new FormAttachment(0, 10);
		lblBackupName.setLayoutData(fd_lblBackupName);
		lblBackupName.setText("Name of backup :");
		txtName = new Text(shlBackupName, SWT.BORDER);
		FormData fd_txtName = new FormData();
		fd_txtName.right = new FormAttachment(btnCancel, 0, SWT.RIGHT);
		fd_txtName.top = new FormAttachment(lblBackupName, -3, SWT.TOP);
		fd_txtName.left = new FormAttachment(lblBackupName, 6);
		txtName.setLayoutData(fd_txtName);
		shlBackupName.open();
		shlBackupName.layout();
		Display display = getParent().getDisplay();
		while (!shlBackupName.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlBackupName = new Shell(getParent(), getStyle());
		shlBackupName.setSize(366, 105);
		shlBackupName.setText("TA Backup Name");
		shlBackupName.setLayout(new FormLayout());
		
		btnCancel = new Button(shlBackupName, SWT.NONE);
		FormData fd_btnCancel = new FormData();
		fd_btnCancel.right = new FormAttachment(100, -11);
		fd_btnCancel.bottom = new FormAttachment(100, -10);
		btnCancel.setLayoutData(fd_btnCancel);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result="";
				shlBackupName.dispose();
			}
		});
		btnCancel.setText("Cancel");
		
		btnOK = new Button(shlBackupName, SWT.NONE);
		FormData fd_btnOK = new FormData();
		fd_btnOK.top = new FormAttachment(btnCancel, 0, SWT.TOP);
		fd_btnOK.right = new FormAttachment(btnCancel, -6);
		btnOK.setLayoutData(fd_btnOK);
		btnOK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (txtName.getText().length()==0) {
					WidgetTask.openOKBox(shlBackupName, "Name cannot be blank");
					return;
				}
				result=txtName.getText();
				shlBackupName.dispose();
			}
		});
		btnOK.setText("OK");
	}

}
