package com.akaxin.zaly.utils;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * @author Sam{@link an.guoyue254@gmail.com}
 * @since 2018-04-12 18:54:14
 */
public class StringHelper {
	/**
	 * 构建符合格式的字符串
	 * 
	 * @param messagePattern
	 *            "hello,nice {} see {}"
	 * @param objects
	 *            ["to","see"]
	 * @return hello,nice to see you
	 */
	public static String format(String messagePattern, Object... objects) {
		FormattingTuple format = MessageFormatter.arrayFormat(messagePattern, objects);
		return format.getMessage();
	}

}