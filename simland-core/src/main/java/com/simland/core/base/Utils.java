package com.simland.core.base;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

/***
 * 工具类
 * 
 * @author Leezh
 * 
 */
public class Utils {

	public static boolean isObjectEmpty(Object objects) {
		return (objects == null) || ("".equals(objects));
	}

	public static boolean isObjectNotEmpty(Object objects) {
		return !isObjectEmpty(objects);
	}

	public static String URLDecode(String url) {
		try {
			return URLDecoder.decode(url, "UTF-8");
		} catch (Exception e) {
			return "";
		}
	}

	/***
	 * 
	 * @Title: 字符转整形
	 * @author Leezh @date 2013-8-2
	 * @Description:
	 * @return
	 */
	public static Integer strToInteger(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * @Title：产生指定位数的随机字符串
	 * @author John @date 2014-03-18
	 * @Description:
	 * @return
	 */
	public static final String randomString(int length) {
		Random randGen = null;
		char[] numbersAndLetters = null;

		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			randGen = new Random();
			numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	/**
	 * @Title：将json数组转化为String型 数组
	 * @author John @date 2014-03-26
	 * @Description:
	 * @return
	 */
	public static String[] getStrArrayFromJson(JSONArray jsonArray) {
		String[] strArray = new String[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			strArray[i] = jsonArray.getString(i);
		}
		return strArray;
	}

	/**
	 * @Title：通过String数组构造生成csv文件的字符串
	 * @author John @date 2014-03-26
	 * @Description:
	 * @return
	 */
	public static StringBuffer getCsvByStrArray(List<String[]> list) {
		StringBuffer buffer = new StringBuffer();
		if (null == list || list.size() == 0) {
			return buffer;
		}
		String[][] original = new String[list.size()][];
		for (int m = 0; m < list.size(); m++) {
			original[m] = list.get(m);
		}
		String[][] target = new String[original[0].length][original.length];
		for (int i = 0; i < original.length; i++) {
			for (int j = 0; j < original[i].length; j++) {
				target[j][i] = original[i][j];
			}
		}
		for (int i = 0; i < target.length; i++) {
			for (int j = 0; j < target[i].length; j++) {
				buffer.append(target[i][j] + ",");
			}
			buffer.delete(buffer.length() - 1, buffer.length());
			buffer.append("\n");
		}
		return buffer;
	}

	public static String getJSONObjectStringVal(Object object, String key) {
		try {
			return String.valueOf(JSONObject.fromObject(object).get(key));
		} catch (Exception e) {
			return "";
		}
	}

	public static Integer getJSONObjectIntegerVal(Object object, String key) {
		try {
			return new BigDecimal(JSONObject.fromObject(object).get(key) + "").intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	public static Date getJSONObjectDateVal(Object object, String key) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			return sf.parse(JSONObject.fromObject(object).get(key) + "");
		} catch (Exception e) {
			return null;
		}
	}

	public static String getToday(String format) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			/*
			 * TimeZone timeZone = TimeZone.getTimeZone("GMT+08:00");
			 * sf.setTimeZone(timeZone);
			 */
			return sf.format(new Date());
		} catch (Exception e) {
			return "";
		}
	}

	public static Date stringToDate(String dateStr, String formatStr) {
		SimpleDateFormat dd = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = dd.parse(dateStr);
		} catch (Exception e) {
			return null;
		}
		return date;
	}

	public static boolean validateTime(Date startDate) {
		if (Utils.isObjectEmpty(startDate))
			return true;
		return startDate.after(new Date());
	}

	public static Date getDefaultDate() {
		String format = new String("yyyy-MM-dd");
		Date defaultDate = Utils.stringToDate(Utils.getToday(format) + " 00:00:00.000", format + " hh:mm:ss.SSS");
		return defaultDate;
	}

	// Get the latest 8 days of the date
	public static Map<String, String> getDateList() {
		Date fdate;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("M月d");
		Map<String, String> map = new LinkedHashMap<String, String>();
		Date currentDate = new Date();
		Long fTime = currentDate.getTime();
		for (int a = 0; a < 8; a++) {
			fdate = new Date();
			fdate.setTime(fTime - (Long.valueOf(a) * 24 * 3600000));
			map.put(sdf1.format(fdate), sdf2.format(fdate));
		}
		return map;
	}

	public static String tryParseString(Object obj, String defaultValue) {
		if (isObjectEmpty(obj)) {
			return defaultValue;
		}
		try {
			return obj.toString();
		} catch (NumberFormatException e) {
			// do nothing.
		}
		return defaultValue;
	}

	// Get the report csv file Suffix date
	// eg:2014-05-19_2014-05-26
	public static String getCsvFileSuffixDate() {
		Map<String, String> map = getDateList();
		List<String> list = new ArrayList<String>(8);
		for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
			String suffDate = it.next();
			list.add(suffDate);
		}

		return list.get(list.size() - 1) + "_" + list.get(0);
	}

	// generate csv content
	public static String getCsvMessage(List<String> rowMapper, List<Map<String, String>> exportData) {
		StringBuffer strBuffer = new StringBuffer("");
		if (isObjectNotEmpty(exportData) && !exportData.isEmpty()) {
			// 写入文件头部
			for (Iterator<String> it = rowMapper.iterator(); it.hasNext();) {
				strBuffer.append(it.next());
				if (it.hasNext()) {
					strBuffer.append(",");
				}
			}
			strBuffer.append("\n");
			// 写入文件内容
			for (Iterator<Map<String, String>> iterator = exportData.iterator(); iterator.hasNext();) {
				Map<String, String> row = iterator.next();
				for (Iterator<Entry<String, String>> it = row.entrySet().iterator(); it.hasNext();) {
					java.util.Map.Entry<String, String> entry = it.next();
					strBuffer.append(entry.getValue());
					if (it.hasNext()) {
						strBuffer.append(",");
					}
				}
				if (iterator.hasNext()) {
					strBuffer.append("\n");
				}
			}
		}
		return strBuffer.toString();
	}

	public static String objToJson(Object obj) {
		try {
			return JSONObject.fromObject(obj).toString();
		} catch (Exception e) {
			return "{}";
		}
	}

	public static String arrayToJson(Object obj) {
		try {
			return JSONArray.fromObject(obj).toString();
		} catch (Exception e) {
			return "[]";
		}
	}

	public static String objToJsonp(Object obj, String callBackName) {
		if (Utils.isObjectEmpty(obj))
			return "{}";

		try {
			return callBackName + "(" + JSONObject.fromObject(obj).toString() + ")";
		} catch (Exception e) {
			e.printStackTrace();
			return "{}";
		}
	}

	public static String arrayToJsonp(Object obj, String callBackName) {

		if (Utils.isObjectEmpty(obj))
			return "[]";

		try {
			return callBackName + "(" + JSONArray.fromObject(obj).toString() + ")";
		} catch (Exception e) {
			e.printStackTrace();
			return "[]";
		}
	}

	public static String notNullTrim(String strTemp) {
		if (strTemp == null) {
			return "";
		} else if ("null".equalsIgnoreCase(strTemp.trim())) {
			return "";
		} else {
			return strTemp.trim();
		}
	}

	/**
	 * 将字符串数组转化成中间用逗号分割的字符串 "a,b,c"
	 */
	public static String getStrs(String[] strs) {

		List<String> params = new ArrayList<String>();
		for (int i = 0; strs != null && i < strs.length; i++) {
			if (Utils.isObjectNotEmpty(strs[i]) && !("null".equalsIgnoreCase(strs[i])))
				params.add(strs[i]);
		}

		StringBuffer ids = new StringBuffer();
		for (int i = 0; i < params.size(); i++) {
			if (i == params.size() - 1) {
				ids.append(Utils.notNullTrim(params.get(i)));
			} else {
				ids.append(Utils.notNullTrim(params.get(i)) + ",");
			}
		}
		return ids.toString();
	}

	/**
	 * 将字符串数组转化成中间用逗号分割的字符串 "'a','b','c'"
	 */
	public static String getRecordIds(String[] recordIds) {
		if (recordIds == null || recordIds.length == 0)
			return "";
		if (recordIds.length == 1)
			return recordIds[0];
		StringBuffer ids = new StringBuffer();
		for (int i = 0; i < recordIds.length; i++) {
			if (i == recordIds.length - 1) {
				ids.append("'" + recordIds[i] + "'");
			} else {
				ids.append("'" + recordIds[i] + "'" + ",");
			}
		}
		return ids.toString();
	}

	public static Set<String> toSet(String[] str) {

		if (str == null || str.length == 0)
			return new HashSet<String>();

		Set<String> set = new HashSet<String>();

		for (String s : str) {
			set.add(s);
		}

		return set;
	}

	public static void main(String[] args) {
		String s1 = "你妈妈喊你回家吃饭哦，回家罗回家罗";
		String s2 = "你妈妈叫你回家吃饭啦，回家罗回家罗";

		long t1 = System.currentTimeMillis();

		for (int i = 0; i < 1048576; i++) {
			int dis = StringUtils.getLevenshteinDistance(s1, s2);
		}

		long t2 = System.currentTimeMillis();

		System.out.println(" 耗费时间： " + (t2 - t1) + "  ms ");
	}

}
