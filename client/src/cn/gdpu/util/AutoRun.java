/*
 *@author fatkun , Time:2009-3-27
 *
 *Website : http://www.olunx.com
 *
 *This: 自动运行
 */

package cn.gdpu.util;

import java.io.IOException;

public class AutoRun {

	public void regAdd(String name, String value) {
		String cmd = "reg add HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\run /v " + name + " /t REG_SZ /d \"\\\"" + value + "\"\" /f";
		runCmd(cmd);
	}

	public void regDelete(String name) {
		String cmd = "reg DELETE HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\run /v " + name + " /f";
		runCmd(cmd);
	}

	private static void runCmd(String cmd) {
		Runtime run = Runtime.getRuntime();
		try {
			run.exec(cmd);
		} catch (IOException e) {
//			cn.imgdpu.util.CatException.getMethod().catException(e, "IO异常");
		}
	}

}
