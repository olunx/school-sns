package cn.gdpu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Functions {
	
	public static String formatDate(Date date) {
		Calendar cal = Calendar.getInstance();
		Calendar nowcal = Calendar.getInstance();
		cal.setTime(date);
		long longtime = (nowcal.getTimeInMillis() - cal.getTimeInMillis()) / 1000;

		long sec = longtime % 60;
		long min = longtime / 60;
		long hour = longtime / (60 * 60);
		long day = longtime / (60*60*24);
		if (longtime >= 4 * 24 * 60 * 60)
			return new SimpleDateFormat("MM月dd日 hh:mm").format(date);
		else if (longtime >= 24 * 60 * 60 && longtime < 4 * 24 * 60 * 60)
			return day + "天前";
		else if (longtime > 60 * 60)
			return hour + "小时前";
		else if (longtime > 60)
			return min + "分钟前";
		else if (longtime > 10)
			return sec + "秒前";
		else if (longtime >= 0)
			return "刚刚";
		else
			return new SimpleDateFormat("yyyy-MM-dd hh:mm").format(date);
	};
	
}
