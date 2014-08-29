package com.tarena.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils {
	public static String format(long duration) {
		SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
		return formatter.format(new Date(duration));
	}
}
