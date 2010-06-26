package cn.gdpu.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import cn.gdpu.util.GeneralMethod;

import com.swtdesigner.SWTResourceManager;

public class PostTwitter extends Dialog {

	private StyledText styledText;
	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog
	 * @param parent
	 * @param style
	 */
	public PostTwitter(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Create the dialog
	 * @param parent
	 */
	public PostTwitter(Shell parent) {
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
		GeneralMethod.getGeneralMethod().setMainDisLoc(shell, 285, 295);// 设置显示位置
		shell.setSize(285, 295);
		shell.setText("发表新微博");

		final Group group = new Group(shell, SWT.NONE);
		group.setText("微博内容");
		final FormData fd_group = new FormData();
		fd_group.bottom = new FormAttachment(0, 170);
		fd_group.left = new FormAttachment(0, 5);
		fd_group.right = new FormAttachment(100, -5);
		fd_group.top = new FormAttachment(0, 5);
		group.setLayoutData(fd_group);
		group.setLayout(new FormLayout());

		styledText = new StyledText(group, SWT.WRAP | SWT.BORDER);
		final FormData fd_styledText = new FormData();
		fd_styledText.bottom = new FormAttachment(100, -5);
		fd_styledText.right = new FormAttachment(100, -5);
		fd_styledText.top = new FormAttachment(0, 5);
		fd_styledText.left = new FormAttachment(0, 5);
		styledText.setLayoutData(fd_styledText);

		final Group group_1 = new Group(shell, SWT.NONE);
		group_1.setText("还可以输入 140 个字符");
		final FormData fd_group_1 = new FormData();
		fd_group_1.bottom = new FormAttachment(0, 260);
		fd_group_1.top = new FormAttachment(group, 5, SWT.BOTTOM);
		fd_group_1.left = new FormAttachment(0, 5);
		fd_group_1.right = new FormAttachment(group, 0, SWT.RIGHT);
		group_1.setLayoutData(fd_group_1);
		group_1.setLayout(new FormLayout());

		final Button button = new Button(group_1, SWT.RADIO);
		final FormData fd_button = new FormData();
		fd_button.top = new FormAttachment(0, 5);
		fd_button.left = new FormAttachment(0, 5);
		button.setLayoutData(fd_button);
		button.setText("微博");

		final Button button_1 = new Button(group_1, SWT.RADIO);
		final FormData fd_button_1 = new FormData();
		fd_button_1.top = new FormAttachment(button, 0, SWT.TOP);
		fd_button_1.left = new FormAttachment(button, 5, SWT.RIGHT);
		button_1.setLayoutData(fd_button_1);
		button_1.setText("图片");

		final Button button_2 = new Button(group_1, SWT.RADIO);
		final FormData fd_button_2 = new FormData();
		fd_button_2.top = new FormAttachment(button_1, 0, SWT.TOP);
		fd_button_2.left = new FormAttachment(button_1, 5, SWT.RIGHT);
		button_2.setLayoutData(fd_button_2);
		button_2.setText("投票");

		final Button button_3 = new Button(group_1, SWT.RADIO);
		final FormData fd_button_3 = new FormData();
		fd_button_3.top = new FormAttachment(button_2, 0, SWT.TOP);
		fd_button_3.left = new FormAttachment(button_2, 5, SWT.RIGHT);
		button_3.setLayoutData(fd_button_3);
		button_3.setText("问答");

		final Button button_4 = new Button(group_1, SWT.RADIO);
		final FormData fd_button_4 = new FormData();
		fd_button_4.top = new FormAttachment(button_3, 0, SWT.TOP);
		fd_button_4.left = new FormAttachment(button_3, 5, SWT.RIGHT);
		button_4.setLayoutData(fd_button_4);
		button_4.setText("交换");

		final Button button_5 = new Button(group_1, SWT.NONE);
		button_5.setImage(SWTResourceManager.getImage(PostTwitter.class, "/cn/gdpu/ico/add_action.gif"));
		final FormData fd_button_5 = new FormData();
		fd_button_5.left = new FormAttachment(button_1, 0, SWT.RIGHT);
		fd_button_5.bottom = new FormAttachment(0, 60);
		fd_button_5.top = new FormAttachment(0, 29);
		fd_button_5.right = new FormAttachment(button, 208, SWT.RIGHT);
		button_5.setLayoutData(fd_button_5);
		button_5.setText("发表");

		final Button button_6 = new Button(group_1, SWT.NONE);
		button_6.setImage(SWTResourceManager.getImage(PostTwitter.class, "/cn/gdpu/ico/delete_edit.gif"));
		final FormData fd_button_6 = new FormData();
		fd_button_6.bottom = new FormAttachment(button_5, 0, SWT.BOTTOM);
		fd_button_6.right = new FormAttachment(button_5, -5, SWT.LEFT);
		fd_button_6.top = new FormAttachment(button_5, 0, SWT.TOP);
		fd_button_6.left = new FormAttachment(button, 0, SWT.LEFT);
		button_6.setLayoutData(fd_button_6);
		button_6.setText("清空");
		//
	}

}
