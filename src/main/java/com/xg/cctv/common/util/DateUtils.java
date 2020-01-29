/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xg.cctv.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理类
 *
 * @author 知秋
 * @email fei6751803@163.com
 */
public class DateUtils {

    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";



    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date StringFormatLongDate(String time){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
		Date date = new Date();
		try {
			date =  sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 字符串转日期
	 * @param time
	 * @param format
	 * @return
	 */
	public static Date stringFormat(String time , String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		try {
			date =  sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 判断是否当天生日
	 * @param birthday
	 * @return
	 */
	public static boolean isBirthday(Date birthday) {
		if (birthday == null) {
			return false;
		}

		Calendar calendar = Calendar.getInstance();
		int curr_month = calendar.get(Calendar.MONTH);
		int curr_day = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.setTime(birthday);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		return curr_month == month && curr_day == day;
	}


	public static Date getYesterday(){
		Calendar   cal   =   Calendar.getInstance();
		cal.add(Calendar.DATE,   -1);
		return cal.getTime();
	}


	public static Date getTomorrow(){
		Calendar   cal   =   Calendar.getInstance();
		cal.add(Calendar.DATE,   +1);
		return cal.getTime();
	}


	/**
	 * date2比date1多的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDays(Date date1,Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1= cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if(year1 != year2) {//同一年
			int timeDistance = 0 ;
			for(int i = year1 ; i < year2 ; i ++)
			{
				if(i%4==0 && i%100!=0 || i%400==0)    //闰年
				{
					timeDistance += 366;
				}
				else    //不是闰年
				{
					timeDistance += 365;
				}
			}

			return timeDistance + (day2-day1) ;
		}
		else { //不同年
			return day2-day1;
		}
	}

	public static long differentS(Date date1,Date date2){
		long diff = date1.getTime() - date2.getTime();
		return diff /1000;
	}

	public static  long differentMinutes(Date date1,Date date2){
		long diff = date1.getTime() - date2.getTime();
		return diff / (1000 * 60);
	}

	public static boolean isLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
		if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 取一个时间的小时数
	 * @param date
	 * @return
	 */
	public static int getDateHour(Date date){
		SimpleDateFormat f = new SimpleDateFormat("HH");
		String hour = f.format(date);
		return Integer.parseInt(hour);
	}

	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到下一月
	 * @param yearMonth 年月字符串  例：  2019-11
	 * @return
	 */
	public static String getNextMonthString(String yearMonth){

		Calendar c = Calendar.getInstance();//获得一个日历的实例
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

		Date newDate = null;
		try{
			newDate = sdf.parse(yearMonth);//初始日期
		}catch(Exception e){

		}
		c.setTime(newDate);//设置日历时间
		c.add(Calendar.MONTH,1);//在日历的月份上增加6个月


		return format(c.getTime(),"yyyy-MM");
	}

	/**
	 * 两个字符串 转成日期后取相差的天数，是否大于传进来的days天数
	 * @param date1Str
	 * @param date2Str
	 * @param days
	 * @return
	 */
	public static boolean DifferentDaysMoreThan(String date1Str,String date2Str,int days){
		boolean result = false;

		Date date1 = stringFormat(date1Str,"yyyy-MM-dd");
		Date date2 = stringFormat(date2Str,"yyyy-MM-dd");

		int resultDays = differentDays(date2,date1);

		if(resultDays>days){
			result = true;
		}

		return  result;
	}

	public static Date theDateTomorrow(Date date){
		Calendar c = Calendar.getInstance();

		c.setTime(date);
		int day1 = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day1 + 1);
		return c.getTime();
	}

	public static void main(String[] args) {
		//yyyy-MM-dd HH:mm:ss
		System.out.println(getNextMonthString("2019-12"));
	}
}
