package org.bbs.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {
	public static String getTime(){
		//获取当前系统时间
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dFormat.format(date).toString();
	}
}
