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
	private ToolBar toolBar_2;

	private final int ACTION_INDEX = 0;
	private final int ACTION_TWITTER = 1;
	private final int ACTION_SQUARE = 2;
	private final int ACTION_SCHOOL = 3;
	private final int ACTION_CLASSES = 4;
	private final int ACTION_SETTING = 5;
	private final int ACTION_MAILBOX = 6;
	
	final private String site = "http://localhost:8080/SchoolSNS/";

	/**
	 * Launch the application
	 * 
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

		final Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FormLayout());
		final FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 55);
		fd_composite.right = new FormAttachment(100, -5);
		fd_composite.top = new FormAttachment(0, 5);
		fd_composite.left = new FormAttachment(0, 5);
		composite.setLayoutData(fd_composite);

		final ToolBar toolBar = new ToolBar(composite, SWT.NONE);
		final FormData fd_toolBar = new FormData();
		fd_toolBar.right = new FormAttachment(0, 115);
		fd_toolBar.bottom = new FormAttachment(100, -5);
		fd_toolBar.top = new FormAttachment(0, 5);
		fd_toolBar.left = new FormAttachment(0, 5);
		toolBar.setLayoutData(fd_toolBar);

		final ToolItem backItem = new ToolItem(toolBar, SWT.PUSH);
		backItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.back();
			}
		});
		backItem.setText("后退");
		backItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/left.gif"));

		final ToolItem forwardItem = new ToolItem(toolBar, SWT.PUSH);
		forwardItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.forward();
			}
		});
		forwardItem.setText("前进");
		forwardItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/right.gif"));

		final ToolItem refreshItem = new ToolItem(toolBar, SWT.PUSH);
		refreshItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.refresh();
				createMainItems();
			}
		});
		refreshItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/refresh.gif"));
		refreshItem.setText("刷新");

		final Label label = new Label(composite, SWT.SEPARATOR);
		final FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0, 5);
		fd_label.left = new FormAttachment(toolBar, 0, SWT.RIGHT);
		fd_label.bottom = new FormAttachment(toolBar, 0, SWT.BOTTOM);
		fd_label.right = new FormAttachment(0, 117);
		label.setLayoutData(fd_label);

		final ToolBar toolBar_1 = new ToolBar(composite, SWT.NONE);
		final FormData fd_toolBar_1 = new FormData();
		fd_toolBar_1.right = new FormAttachment(0, 315);
		fd_toolBar_1.bottom = new FormAttachment(label, 0, SWT.BOTTOM);
		fd_toolBar_1.top = new FormAttachment(label, 0, SWT.TOP);
		fd_toolBar_1.left = new FormAttachment(toolBar, 5, SWT.RIGHT);
		toolBar_1.setLayoutData(fd_toolBar_1);

		final ToolItem indexItem = new ToolItem(toolBar_1, SWT.PUSH);
		indexItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				clickMenu(ACTION_INDEX);
			}
		});
		indexItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/internal_browser.gif"));
		indexItem.setText("首页");

		final ToolItem twitterItem = new ToolItem(toolBar_1, SWT.PUSH);
		twitterItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				clickMenu(ACTION_TWITTER);
			}
		});
		twitterItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/javadoc.gif"));
		twitterItem.setText("微博墙");

		final ToolItem squareItem = new ToolItem(toolBar_1, SWT.PUSH);
		squareItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				clickMenu(ACTION_SQUARE);
			}
		});
		squareItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/option_pane.gif"));
		squareItem.setText("广场");

		final ToolItem schoolItem = new ToolItem(toolBar_1, SWT.PUSH);
		schoolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				clickMenu(ACTION_SCHOOL);
			}
		});
		schoolItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/newenum_wiz.gif"));
		schoolItem.setText("学校");

		final ToolItem classItem = new ToolItem(toolBar_1, SWT.PUSH);
		classItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				clickMenu(ACTION_CLASSES);
			}
		});
		classItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/newclass_wiz.gif"));
		classItem.setText("班级");

		final Label label_1 = new Label(composite, SWT.SEPARATOR);
		final FormData fd_label_1 = new FormData();
		fd_label_1.bottom = new FormAttachment(toolBar_1, 0, SWT.BOTTOM);
		fd_label_1.left = new FormAttachment(toolBar_1, 0, SWT.RIGHT);
		fd_label_1.right = new FormAttachment(0, 317);
		fd_label_1.top = new FormAttachment(toolBar_1, 0, SWT.TOP);
		label_1.setLayoutData(fd_label_1);

		toolBar_2 = new ToolBar(composite, SWT.NONE);
		final FormData fd_toolBar_2 = new FormData();
		fd_toolBar_2.right = new FormAttachment(0, 730);
		fd_toolBar_2.bottom = new FormAttachment(label_1, 0, SWT.BOTTOM);
		fd_toolBar_2.top = new FormAttachment(label_1, 0, SWT.TOP);
		fd_toolBar_2.left = new FormAttachment(label_1, 5, SWT.RIGHT);
		toolBar_2.setLayoutData(fd_toolBar_2);

		final ToolBar toolBar_3 = new ToolBar(composite, SWT.NONE);
		final FormData fd_toolBar_3 = new FormData();
		fd_toolBar_3.left = new FormAttachment(100, -153);
		fd_toolBar_3.bottom = new FormAttachment(toolBar_2, 0, SWT.BOTTOM);
		fd_toolBar_3.right = new FormAttachment(100, -5);
		fd_toolBar_3.top = new FormAttachment(toolBar_2, 0, SWT.TOP);
		toolBar_3.setLayoutData(fd_toolBar_3);

		final ToolItem infoItem = new ToolItem(toolBar_3, SWT.PUSH);
		infoItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				clickMenu(ACTION_SETTING);
			}
		});
		infoItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/configure.gif"));
		infoItem.setText("个人信息");

		final ToolItem boxItem = new ToolItem(toolBar_3, SWT.PUSH);
		boxItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				clickMenu(ACTION_MAILBOX);
			}
		});
		boxItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/editor_pane.gif"));
		boxItem.setText("小纸箱");

		final ToolItem quitItem = new ToolItem(toolBar_3, SWT.PUSH);
		quitItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				shell.close();
			}
		});
		quitItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/run_exec.gif"));
		quitItem.setText("退出");

		final Composite composite_1 = new Composite(shell, SWT.BORDER);
		composite_1.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		composite_1.setLayout(new FormLayout());
		final FormData fd_composite_1 = new FormData();
		fd_composite_1.bottom = new FormAttachment(100, -5);
		fd_composite_1.right = new FormAttachment(100, -5);
		fd_composite_1.top = new FormAttachment(composite, 5, SWT.BOTTOM);
		fd_composite_1.left = new FormAttachment(composite, 0, SWT.LEFT);
		composite_1.setLayoutData(fd_composite_1);

		browser = new Browser(composite_1, SWT.NONE);
		browser.addProgressListener(new ProgressAdapter() {
			public void completed(final ProgressEvent event) {
				scrollUp();
			}
			public void changed(final ProgressEvent event) {
				scrollUp();
			}
		});
		final FormData fd_browser = new FormData();
		fd_browser.bottom = new FormAttachment(100, -5);
		fd_browser.right = new FormAttachment(100, -5);
		fd_browser.top = new FormAttachment(0, 5);
		fd_browser.left = new FormAttachment(0, 5);
		browser.setLayoutData(fd_browser);
		browser.setUrl(site);
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
		createMainItems();
	}

	// 滚动页面到logo下方
	private void scrollUp() {
		browser.execute("$.scrollTo(75 , 800 );");
	}

	//按钮处理动作
	private void clickMenu(int which) {
		
		//销毁原来的Item
		ToolItem[] c = toolBar_2.getItems();
		for(int i=0; i< c.length;i++) {
			c[i].dispose();
		}
		toolBar_2.update();
		
		switch (which) {
		case ACTION_INDEX: {
			browser.execute("$(\"a[rel='header_index']\").click();");
			createMainItems();
			break;
		}
		case ACTION_TWITTER: {
			browser.execute("$(\"a[rel='header_twitter']\").click();");
			break;
		}
		case ACTION_SQUARE: {
			browser.execute("$(\"a[rel='header_square']\").click();");
			break;
		}
		case ACTION_SCHOOL: {
			browser.execute("$(\"a[rel='header_school']\").click();");
			createSchoolItems();
			break;
		}
		case ACTION_CLASSES: {
			browser.execute("$(\"a[rel='header_classes']\").click();");
			createClassesItems();
			break;
		}
		case ACTION_SETTING: {
			browser.execute("$(\"a[rel='header_info']\").click();");
			break;
		}
		case ACTION_MAILBOX: {
			browser.execute("$(\"a[rel='header_mailbox']\").click();");
			break;
		}
		}
		
		scrollUp();
	}
	
	//创建默认频道显示的工具栏按钮
	private void createMainItems() {
		ToolItem twitterItem = new ToolItem(toolBar_2, SWT.PUSH);
		twitterItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/add_action.gif"));
		twitterItem.setText("微博");
		twitterItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.execute("cancel();");
			}
		});
		
		ToolItem linkItem = new ToolItem(toolBar_2, SWT.PUSH);
		linkItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/browser.gif"));
		linkItem.setText("链接");
		linkItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.execute("showLink();");
			}
		});
		
		ToolItem imageItem = new ToolItem(toolBar_2, SWT.PUSH);
		imageItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/layered_pane.gif"));
		imageItem.setText("图片");
		imageItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.execute("$(\"a[rel='post_image']\").click();");
			}
		});
		
		ToolItem voteItem = new ToolItem(toolBar_2, SWT.PUSH);
		voteItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/print_edit.gif"));
		voteItem.setText("投票");
		voteItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.execute("showVote();");
			}
		});
		
		ToolItem issueItem = new ToolItem(toolBar_2, SWT.PUSH);
		issueItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/generate.gif"));
		issueItem.setText("问答");
		issueItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.execute("showIssue();");
			}
		});
		
		ToolItem goodsItem = new ToolItem(toolBar_2, SWT.PUSH);
		goodsItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/cpyqual_menu.gif"));
		goodsItem.setText("交换");
		goodsItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.execute("showGoods();");
			}
		});
		
		//toolBar_2.redraw();
	}
	
	//创建班级菜单按钮
	private void createClassesItems() {
		ToolItem indexItem = new ToolItem(toolBar_2, SWT.PUSH);
		indexItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/folder.gif"));
		indexItem.setText("班级首页");
		indexItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.execute("$(\"a[rel='classes_index']\").click();");
			}
		});
		
		ToolItem feeItem = new ToolItem(toolBar_2, SWT.PUSH);
		feeItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/classf_obj.gif"));
		feeItem.setText("查看班费");
		feeItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.execute("$(\"a[rel='classes_fee']\").click();");
			}
		});
		
		ToolItem scoreItem = new ToolItem(toolBar_2, SWT.PUSH);
		scoreItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/configure.gif"));
		scoreItem.setText("成绩");
		scoreItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.execute("$(\"a[rel='classes_score']\").click();");
			}
		});
		
		ToolItem courseItem = new ToolItem(toolBar_2, SWT.PUSH);
		courseItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/composite.gif"));
		courseItem.setText("课程表");
		courseItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.execute("$(\"a[rel='classes_course']\").click();");
			}
		});
		
		ToolItem attenItem = new ToolItem(toolBar_2, SWT.PUSH);
		attenItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/gotoobj_tsk.gif"));
		attenItem.setText("考勤");
		attenItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.execute("$(\"a[rel='classes_atten']\").click();");
			}
		});
		
		ToolItem peopleItem = new ToolItem(toolBar_2, SWT.PUSH);
		peopleItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/cpyqual_menu.gif"));
		peopleItem.setText("班级成员");
		peopleItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.execute("$(\"a[rel='classes_people']\").click();");
			}
		});
	}
	
	//创建学校菜单按钮
	private void createSchoolItems() {
		ToolItem indexItem = new ToolItem(toolBar_2, SWT.PUSH);
		indexItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/folder.gif"));
		indexItem.setText("学校首页");
		indexItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.execute("$(\"a[rel='school_index']\").click();");
			}
		});
		
		ToolItem otherSchoolItem = new ToolItem(toolBar_2, SWT.PUSH);
		otherSchoolItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/choice.gif"));
		otherSchoolItem.setText("其它学校");
		otherSchoolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.execute("$(\"a[rel='school_other']\").click();");
			}
		});
		
		ToolItem peopleItem = new ToolItem(toolBar_2, SWT.PUSH);
		peopleItem.setImage(SWTResourceManager.getImage(MainWindow.class, "ico/cpyqual_menu.gif"));
		peopleItem.setText("学校成员");
		peopleItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				browser.execute("$(\"a[rel='school_people']\").click();");
			}
		});
	}
}
