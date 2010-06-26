package cn.gdpu.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import cn.gdpu.util.GeneralMethod;

import com.swtdesigner.SWTResourceManager;

public class Settings extends Dialog {

	private StyledText styledText_1;
	private StyledText styledText;
	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog
	 * @param parent
	 * @param style
	 */
	public Settings(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Create the dialog
	 * @param parent
	 */
	public Settings(Shell parent) {
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
		shell.setImage(SWTResourceManager.getImage(Settings.class, "/cn/gdpu/ico/configure.gif"));
		GeneralMethod.getGeneralMethod().setMainDisLoc(shell, 369, 326);// 设置显示位置
		shell.setSize(369, 326);
		shell.setText("设置");

		final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		final FormData fd_tabFolder = new FormData();
		fd_tabFolder.bottom = new FormAttachment(100, -5);
		fd_tabFolder.right = new FormAttachment(100, -5);
		fd_tabFolder.top = new FormAttachment(0, 5);
		fd_tabFolder.left = new FormAttachment(0, 5);
		tabFolder.setLayoutData(fd_tabFolder);

		final TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("基本设置");
		tabItem.setImage(SWTResourceManager.getImage(Settings.class, "/cn/gdpu/ico/info.gif"));

		final Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new FormLayout());
		tabItem.setControl(composite);

		final Group group = new Group(composite, SWT.NONE);
		group.setText("账号密码");
		final FormData fd_group = new FormData();
		fd_group.bottom = new FormAttachment(0, 90);
		fd_group.right = new FormAttachment(100, -5);
		fd_group.top = new FormAttachment(0, 5);
		fd_group.left = new FormAttachment(0, 5);
		group.setLayoutData(fd_group);
		group.setLayout(new FormLayout());

		final CLabel label = new CLabel(group, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(Settings.class, "/cn/gdpu/ico/javabean.gif"));
		final FormData fd_label = new FormData();
		fd_label.bottom = new FormAttachment(0, 30);
		fd_label.right = new FormAttachment(0, 80);
		fd_label.top = new FormAttachment(0, 5);
		fd_label.left = new FormAttachment(0, 5);
		label.setLayoutData(fd_label);
		label.setText("用户名：");

		final CLabel label_1 = new CLabel(group, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(Settings.class, "/cn/gdpu/ico/javabean.gif"));
		final FormData fd_label_1 = new FormData();
		fd_label_1.bottom = new FormAttachment(0, 60);
		fd_label_1.right = new FormAttachment(label, 0, SWT.RIGHT);
		fd_label_1.top = new FormAttachment(label, 5, SWT.BOTTOM);
		fd_label_1.left = new FormAttachment(label, 0, SWT.LEFT);
		label_1.setLayoutData(fd_label_1);
		label_1.setText("密码：");

		styledText = new StyledText(group, SWT.BORDER);
		final FormData fd_styledText = new FormData();
		fd_styledText.bottom = new FormAttachment(label, 0, SWT.BOTTOM);
		fd_styledText.right = new FormAttachment(100, -5);
		fd_styledText.top = new FormAttachment(label, 0, SWT.TOP);
		fd_styledText.left = new FormAttachment(label, 5, SWT.RIGHT);
		styledText.setLayoutData(fd_styledText);

		styledText_1 = new StyledText(group, SWT.BORDER);
		final FormData fd_styledText_1 = new FormData();
		fd_styledText_1.bottom = new FormAttachment(label_1, 0, SWT.BOTTOM);
		fd_styledText_1.right = new FormAttachment(styledText, 0, SWT.RIGHT);
		fd_styledText_1.top = new FormAttachment(styledText, 5, SWT.BOTTOM);
		fd_styledText_1.left = new FormAttachment(label_1, 5, SWT.RIGHT);
		styledText_1.setLayoutData(fd_styledText_1);

		final Group group_1 = new Group(composite, SWT.NONE);
		group_1.setText("系统设置");
		final FormData fd_group_1 = new FormData();
		fd_group_1.bottom = new FormAttachment(0, 140);
		fd_group_1.right = new FormAttachment(100, -5);
		fd_group_1.top = new FormAttachment(group, 5, SWT.BOTTOM);
		fd_group_1.left = new FormAttachment(group, 0, SWT.LEFT);
		group_1.setLayoutData(fd_group_1);
		group_1.setLayout(new FormLayout());

		final Button button = new Button(group_1, SWT.CHECK);
		button.setImage(SWTResourceManager.getImage(Settings.class, "/cn/gdpu/ico/exportrunnablejar_wiz.gif"));
		final FormData fd_button = new FormData();
		fd_button.top = new FormAttachment(0, 5);
		fd_button.left = new FormAttachment(0, 5);
		button.setLayoutData(fd_button);
		button.setText("开机自动启动程序");

		final Button button_1 = new Button(group_1, SWT.CHECK);
		button_1.setImage(SWTResourceManager.getImage(Settings.class, "/cn/gdpu/ico/ilow_obj.gif"));
		final FormData fd_button_1 = new FormData();
		fd_button_1.right = new FormAttachment(0, 320);
		fd_button_1.left = new FormAttachment(0, 175);
		fd_button_1.top = new FormAttachment(button, 0, SWT.TOP);
		button_1.setLayoutData(fd_button_1);
		button_1.setText("启动后最小化窗口");
		//
	}

}
