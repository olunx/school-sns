package cn.gdpu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.TrayItem;

import cn.gdpu.dialog.About;
import cn.gdpu.dialog.PostTwitter;
import cn.gdpu.dialog.Settings;
import cn.gdpu.util.GeneralMethod;

import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.MenuDetectEvent;

public class MainWindow {

	public static Shell shell;
	/**
	 * @wbp.nonvisual location=223,239
	 */
	private final TrayItem trayItem = new TrayItem(Display.getDefault().getSystemTray(), SWT.NONE);
	
	private Browser browser;

	final private String site = "http://localhost:8080/SchoolSNS/";
	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window
	 */
	public void open() {
		final Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {
		shell = new Shell();
		shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				trayItem.setVisible(false);
			}
		});
		shell.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/home_nav.gif"));
		shell.setLayout(new FormLayout());
		GeneralMethod.getGeneralMethod().setMainDisLoc(shell, 1024, 600);// 设置显示位置
		shell.setSize(1024, 600);
		shell.setText("校园互动社区");

		final Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		final MenuItem newSubmenuMenuItem = new MenuItem(menu, SWT.CASCADE);
		newSubmenuMenuItem.setText("工具");

		final Menu menu_1 = new Menu(newSubmenuMenuItem);
		newSubmenuMenuItem.setMenu(menu_1);

		final MenuItem newItemMenuItem = new MenuItem(menu_1, SWT.NONE);
		newItemMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				new Settings(shell).open();
			}
		});
		newItemMenuItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/configure.gif"));
		newItemMenuItem.setText("设置");

		new MenuItem(menu_1, SWT.SEPARATOR);

		final MenuItem newItemMenuItem_7 = new MenuItem(menu_1, SWT.NONE);
		newItemMenuItem_7.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				shell.close();
			}
		});
		newItemMenuItem_7.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/run_exec.gif"));
		newItemMenuItem_7.setText("退出");

		final MenuItem newSubmenuMenuItem_1 = new MenuItem(menu, SWT.CASCADE);
		newSubmenuMenuItem_1.setText("其它");

		final Menu menu_2 = new Menu(newSubmenuMenuItem_1);
		newSubmenuMenuItem_1.setMenu(menu_2);

		final MenuItem newItemMenuItem_1 = new MenuItem(menu_2, SWT.NONE);
		newItemMenuItem_1.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/cheatsheet_view.gif"));
		newItemMenuItem_1.setText("帮助");

		final MenuItem newItemMenuItem_2 = new MenuItem(menu_2, SWT.NONE);
		newItemMenuItem_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				new About(shell).open();
			}
		});
		newItemMenuItem_2.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/index_co.gif"));
		newItemMenuItem_2.setText("关于");

		final ToolBar toolBar = new ToolBar(shell, SWT.NONE);
		final FormData fd_toolBar = new FormData();
		fd_toolBar.bottom = new FormAttachment(0, 45);
		fd_toolBar.right = new FormAttachment(100, -5);
		fd_toolBar.top = new FormAttachment(0, 5);
		toolBar.setLayoutData(fd_toolBar);

		final ToolItem newItemToolItem_1 = new ToolItem(toolBar, SWT.PUSH);
		newItemToolItem_1.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/editor_pane.gif"));
		newItemToolItem_1.setText("小纸箱");

		final ToolItem newItemToolItem_2 = new ToolItem(toolBar, SWT.PUSH);
		newItemToolItem_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				new Settings(shell).open();
			}
		});
		newItemToolItem_2.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/configure.gif"));
		newItemToolItem_2.setText("设置");

		final ToolItem newItemToolItem_3 = new ToolItem(toolBar, SWT.PUSH);
		newItemToolItem_3.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/cheatsheet_view.gif"));
		newItemToolItem_3.setText("帮助");

		final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
//				System.out.println("选择：" + e.item.toString().contains(""));
				if(e.item.toString().contains("微博墙")) {
					System.out.println("选择");
				}
			}
		});
		final FormData fd_tabFolder = new FormData();
		fd_tabFolder.left = new FormAttachment(0, 5);
		fd_tabFolder.bottom = new FormAttachment(100, -5);
		fd_tabFolder.right = new FormAttachment(100, -5);
		fd_tabFolder.top = new FormAttachment(toolBar, 5, SWT.BOTTOM);
		tabFolder.setLayoutData(fd_tabFolder);

		final TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/internal_browser.gif"));
		tabItem.setText("我的主页");

		final Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new FormLayout());
		tabItem.setControl(composite);

		final ToolBar toolBar_1 = new ToolBar(composite, SWT.NONE);
		toolBar_1.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		final FormData fd_toolBar_1 = new FormData();
		fd_toolBar_1.right = new FormAttachment(100, -5);
		fd_toolBar_1.bottom = new FormAttachment(0, 45);
		fd_toolBar_1.top = new FormAttachment(0, 5);
		fd_toolBar_1.left = new FormAttachment(0, 5);
		toolBar_1.setLayoutData(fd_toolBar_1);

		final ToolItem newItemToolItem_7 = new ToolItem(toolBar_1, SWT.PUSH);
		newItemToolItem_7.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				scrollUp();
				browser.execute("cancel();");
			}
		});
		newItemToolItem_7.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/add_action.gif"));
		newItemToolItem_7.setText("微博");

		final ToolItem newItemToolItem = new ToolItem(toolBar_1, SWT.PUSH);
		newItemToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				scrollUp();
				browser.execute("showLink();");
			}
		});
		newItemToolItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/browser.gif"));
		newItemToolItem.setText("链接");

		final ToolItem newItemToolItem_6 = new ToolItem(toolBar_1, SWT.PUSH);
		newItemToolItem_6.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				scrollUp();
				browser.execute("showImage();");
			}
		});
		newItemToolItem_6.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/layered_pane.gif"));
		newItemToolItem_6.setText("图片");

		final ToolItem newItemToolItem_8 = new ToolItem(toolBar_1, SWT.PUSH);
		newItemToolItem_8.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				scrollUp();
				browser.execute("showVote();");
			}
		});
		newItemToolItem_8.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/print_edit.gif"));
		newItemToolItem_8.setText("投票");

		final ToolItem newItemToolItem_9 = new ToolItem(toolBar_1, SWT.PUSH);
		newItemToolItem_9.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				scrollUp();
				browser.execute("showIssue();");
			}
		});
		newItemToolItem_9.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/generate.gif"));
		newItemToolItem_9.setText("问答");

		final ToolItem newItemToolItem_10 = new ToolItem(toolBar_1, SWT.PUSH);
		newItemToolItem_10.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				scrollUp();
				browser.execute("showGoods();");
			}
		});
		newItemToolItem_10.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/cpyqual_menu.gif"));
		newItemToolItem_10.setText("交换");

		browser = new Browser(composite, SWT.NONE);
		browser.addProgressListener(new ProgressAdapter() {
			public void completed(final ProgressEvent event) {
				scrollUp();
			}
		});
		final FormData fd_browser = new FormData();
		fd_browser.top = new FormAttachment(toolBar_1, 5, SWT.DEFAULT);
		fd_browser.bottom = new FormAttachment(100, -5);
		fd_browser.right = new FormAttachment(100, -5);
		fd_browser.left = new FormAttachment(toolBar_1, 0, SWT.LEFT);
		browser.setLayoutData(fd_browser);
		browser.setUrl(site);
		
		final Menu traymenu = new Menu(shell);
		shell.setMenu(traymenu);
		
		MenuItem menuItem = new MenuItem(traymenu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				shell.setVisible(true);
			}
		});
		menuItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/internal_browser.gif"));
		menuItem.setText("我的主页");

		new MenuItem(traymenu, SWT.SEPARATOR);
		
		MenuItem menuItem_1 = new MenuItem(traymenu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				new PostTwitter(shell).open();
			}
		});
		menuItem_1.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/add_action.gif"));
		menuItem_1.setText("新微博");

		final MenuItem newItemMenuItem_3 = new MenuItem(traymenu, SWT.NONE);
		newItemMenuItem_3.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/editor_pane.gif"));
		newItemMenuItem_3.setText("小纸箱");

		new MenuItem(traymenu, SWT.SEPARATOR);

		final MenuItem newItemMenuItem_4 = new MenuItem(traymenu, SWT.NONE);
		newItemMenuItem_4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				new Settings(shell).open();
			}
		});
		newItemMenuItem_4.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/configure.gif"));
		newItemMenuItem_4.setText("设置");

		final MenuItem newItemMenuItem_6 = new MenuItem(traymenu, SWT.NONE);
		newItemMenuItem_6.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				new About(shell).open();
			}
		});
		newItemMenuItem_6.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/index_co.gif"));
		newItemMenuItem_6.setText("关于");

		new MenuItem(traymenu, SWT.SEPARATOR);

		final MenuItem newItemMenuItem_5 = new MenuItem(traymenu, SWT.NONE);
		newItemMenuItem_5.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				shell.close();
			}
		});
		newItemMenuItem_5.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/run_exec.gif"));
		newItemMenuItem_5.setText("退出");

		final ToolBar toolBar_2 = new ToolBar(shell, SWT.NONE);
		final FormData fd_toolBar_2 = new FormData();
		fd_toolBar_2.right = new FormAttachment(0, 115);
		fd_toolBar_2.bottom = new FormAttachment(toolBar, 0, SWT.BOTTOM);
		fd_toolBar_2.top = new FormAttachment(toolBar, 0, SWT.TOP);
		fd_toolBar_2.left = new FormAttachment(tabFolder, 0, SWT.LEFT);
		toolBar_2.setLayoutData(fd_toolBar_2);

		final ToolItem newItemToolItem_11 = new ToolItem(toolBar_2, SWT.PUSH);
		newItemToolItem_11.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.back();
			}
		});
		newItemToolItem_11.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/left.gif"));
		newItemToolItem_11.setText("后退");

		final ToolItem newItemToolItem_12 = new ToolItem(toolBar_2, SWT.PUSH);
		newItemToolItem_12.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.forward();
			}
		});
		newItemToolItem_12.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/right.gif"));
		newItemToolItem_12.setText("前进");

		final ToolItem newItemToolItem_13 = new ToolItem(toolBar_2, SWT.PUSH);
		newItemToolItem_13.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.refresh();
			}
		});
		newItemToolItem_13.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/refresh.gif"));
		newItemToolItem_13.setText("刷新");

		Label label;
		label = new Label(shell, SWT.SEPARATOR);
		fd_toolBar.left = new FormAttachment(label, 5, SWT.RIGHT);
		final FormData fd_label = new FormData();
		fd_label.bottom = new FormAttachment(toolBar_2, 0, SWT.BOTTOM);
		fd_label.left = new FormAttachment(toolBar_2, 5, SWT.RIGHT);
		fd_label.right = new FormAttachment(0, 122);
		fd_label.top = new FormAttachment(toolBar_2, 0, SWT.TOP);
		label.setLayoutData(fd_label);
		trayItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
					if (shell.isVisible()) {
						shell.setVisible(false);
					} else {
						shell.setVisible(true);
						shell.setMinimized(false);
					}
			}
		});
		trayItem.addMenuDetectListener(new MenuDetectListener() {
			public void menuDetected(MenuDetectEvent arg0) {
				traymenu.setVisible(true);
			}
		});
		trayItem.setImage(SWTResourceManager.getImage(MainWindow.class, "/cn/gdpu/ico/home_nav.gif"));
		//
	}

	private void scrollUp() {
		browser.execute("$.scrollTo(75 , 800 );");
	}
}

