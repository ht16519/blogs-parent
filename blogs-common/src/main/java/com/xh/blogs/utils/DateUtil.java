package com.xh.blogs.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Name DateUtil
 * @Description 简单的时间处理
 * @Date 2019年2月10日
 * @Author wen
 */
public class DateUtil {

	private static final String DATE_PATTERN = "yyyyMMddHHmmssSSS";

	/**
	 * @Name getDateStr
	 * @Description 获取当前时间字符串
	 * @Date 2019年2月10日
	 * @Author wen
	 * @return
	 */
	public static String getDateStr() {
		return getDateStr(new Date());
	}

	public static String getDateStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		return sdf.format(date).toString();
	}
	
	/**
	 * @Name getNanoTime
	 * @Description 获取当前纳秒字符串
	 * @Date 2019年2月10日
	 * @Author wen
	 * @return
	 */
	public static String getNanoTime() {
		 Long nanoTime = System.nanoTime();
		 return nanoTime.toString();
	}



}
