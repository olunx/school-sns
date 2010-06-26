package cn.gdpu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
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
		newItemMenuItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/configure.gif"));
		newItemMenuItem.setText("设置");

		final MenuItem newSubmenuMenuItem_1 = new MenuItem(menu, SWT.CASCADE);
		newSubmenuMenuItem_1.setText("其它");

		final Menu menu_2 = new Menu(newSubmenuMenuItem_1);
		newSubmenuMenuItem_1.setMenu(menu_2);

		final MenuItem newItemMenuItem_1 = new MenuItem(menu_2, SWT.NONE);
		newItemMenuItem_1.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/cheatsheet_view.gif"));
		newItemMenuItem_1.setText("帮助");

		final MenuItem newItemMenuItem_2 = new MenuItem(menu_2, SWT.NONE);
		newItemMenuItem_2.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/index_co.gif"));
		newItemMenuItem_2.setText("关于");

		final ToolBar toolBar = new ToolBar(shell, SWT.NONE);
		final FormData fd_toolBar = new FormData();
		fd_toolBar.bottom = new FormAttachment(0, 45);
		fd_toolBar.right = new FormAttachment(100, -5);
		fd_toolBar.top = new FormAttachment(0, 5);
		fd_toolBar.left = new FormAttachment(0, 5);
		toolBar.setLayoutData(fd_toolBar);

		final ToolItem newItemToolItem = new ToolItem(toolBar, SWT.PUSH);
		newItemToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				new PostTwitter(MainWindow.shell).open();
			}
		});
		newItemToolItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/add_action.gif"));
		newItemToolItem.setText("新微博");

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

		final ToolItem newItemToolItem_5 = new ToolItem(toolBar, SWT.PUSH);
		newItemToolItem_5.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				new About(shell).open();
			}
		});
		newItemToolItem_5.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/index_co.gif"));
		newItemToolItem_5.setText("关于");

		final ToolItem newItemToolItem_4 = new ToolItem(toolBar, SWT.PUSH);
		newItemToolItem_4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				shell.close();
			}
		});
		newItemToolItem_4.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/run_exec.gif"));
		newItemToolItem_4.setText("退出");

		final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		final FormData fd_tabFolder = new FormData();
		fd_tabFolder.bottom = new FormAttachment(100, -5);
		fd_tabFolder.right = new FormAttachment(100, -5);
		fd_tabFolder.top = new FormAttachment(toolBar, 5, SWT.BOTTOM);
		fd_tabFolder.left = new FormAttachment(toolBar, 0, SWT.LEFT);
		tabFolder.setLayoutData(fd_tabFolder);

		final TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/internal_browser.gif"));
		tabItem.setText("我的主页");

		final Browser browser = new Browser(tabFolder, SWT.NONE);
		browser.setUrl("http://localhost:8080/SchoolSNS/");
		tabItem.setControl(browser);
		
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

}

