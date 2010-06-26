package cn.gdpu.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import cn.gdpu.util.GeneralMethod;

import com.swtdesigner.SWTResourceManager;

public class About extends Dialog {

	private StyledText styledText;
	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog
	 * @param parent
	 * @param style
	 */
	public About(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Create the dialog
	 * @param parent
	 */
	public About(Shell parent) {
		this(parent, SWT.NONE);
	}

	/**
	 * Open the dialog
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		return result;
	}

	/**
	 * Create contents of the dialog
	 */
	protected void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setLayout(new FormLayout());
		GeneralMethod.getGeneralMethod().setMainDisLoc(shell, 417, 298);// 设置显示位置
		shell.setSize(417, 298);
		shell.setText("关于软件");

		final Group group = new Group(shell, SWT.NONE);
		final FormData fd_group = new FormData();
		fd_group.right = new FormAttachment(100, -5);
		fd_group.bottom = new FormAttachment(100, -5);
		fd_group.top = new FormAttachment(0, 5);
		fd_group.left = new FormAttachment(0, 5);
		group.setLayoutData(fd_group);
		group.setLayout(new FormLayout());

		final CLabel label = new CLabel(group, SWT.NONE);
		final FormData fd_label = new FormData();
		fd_label.bottom = new FormAttachment(0, 35);
		fd_label.right = new FormAttachment(100, -5);
		fd_label.top = new FormAttachment(0, 5);
		fd_label.left = new FormAttachment(0, 5);
		label.setLayoutData(fd_label);
		label.setText("label");

		styledText = new StyledText(group, SWT.V_SCROLL | SWT.BORDER);
		styledText.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		styledText.setEditable(false);
		styledText.setText("简介");
		final FormData fd_styledText = new FormData();
		fd_styledText.bottom = new FormAttachment(0, 205);
		fd_styledText.top = new FormAttachment(label, 5, SWT.BOTTOM);
		fd_styledText.right = new FormAttachment(100, -5);
		fd_styledText.left = new FormAttachment(label, 0, SWT.LEFT);
		styledText.setLayoutData(fd_styledText);

		final CLabel copyrightLabel = new CLabel(group, SWT.CENTER);
		copyrightLabel.setImage(SWTResourceManager.getImage(About.class, "/cn/gdpu/ico/info.gif"));
		final FormData fd_copyrightLabel = new FormData();
		fd_copyrightLabel.top = new FormAttachment(styledText, 5, SWT.BOTTOM);
		fd_copyrightLabel.right = new FormAttachment(styledText, 0, SWT.RIGHT);
		fd_copyrightLabel.bottom = new FormAttachment(100, -5);
		fd_copyrightLabel.left = new FormAttachment(styledText, 0, SWT.LEFT);
		copyrightLabel.setLayoutData(fd_copyrightLabel);
		copyrightLabel.setText("CopyRight (c) 2010 3C-Team");
		//
	}

}
