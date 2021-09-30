/**
 * Copyright InnoTech Solutions Pvt. Ltd 2011. All rights reserved. No part of this program may be photocopied 
 * reproduced or translated to another program language without prior written consent of InnoTech Solutions Pvt. Ltd
 *
 * FILE         : DateUtils.java
 *
 * PACKAGE      : com.creckett.util
 *
 * AUTHOR(S)    : Dhaval P Shah - shah_d_p@yahoo.com
 *
 * VERSION      : 1.0
 *
 * REVISION LOG :
 *
 *    Date          By         Version  TicketId        Description
 *    ------------  ---------  -------  --------  --------------------------------------------------
 *    May 5, 2011	    Dhaval	   1.0      NA        Created first cut of code.
 *
 */
package com.creckett.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.creckett.logger.CreckettLogger;

public class DateUtils {

	private String dateFormatPattern;

	private String timeFormatPattern;
	
	private String marketOpenTime;
	
	private String marketOpenTimerTime;
	
	private String dateFormat = "dd MMM yyyy, hh:mm a z";
	
	private String timeFormat = "hh:mm a z";
	
	//private static final Logger LOG = LoggerInstancesManager
		//	.getLogger(DateUtils.class);
	private CreckettLogger creckettLogger = CreckettLogger.getInstance();

	public String formateTime(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				timeFormat);
		String dateString = simpleDateFormat.format(date);
		return dateString;
	}

	public String formatDate(Date date) {
		if (isTodaysDate(date))
			return "Today";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				dateFormat);
		String dateString = simpleDateFormat.format(date);
		return dateString;
	}

	public Date generateTodaysDateUsingCalendar() {
		creckettLogger.info("Entering generateTodaysDateUsingCalendar");
		Calendar calendar = Calendar.getInstance();
		// String todaysDate = ( now.get(Calendar.MONTH) + 1 ) + "-" +
		// now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR) ;
		this.toMidnight(calendar);
		Date todaysDate = calendar.getTime();
		creckettLogger.info("Leaving generateTodaysDateUsingCalendar :: todaysDate - "
				+ todaysDate);
		return todaysDate;
	}

	public void toMidnight(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}

	public Date generateDateAfterNDaysUsingCalendar(int days) {
		creckettLogger.info("Entering generateDateAfterNDaysUsingCalendar");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		creckettLogger.info("Leaving generateDateAfterNDaysUsingCalendar :: dateAfterNDays - "
				+ calendar.getTime());
		return calendar.getTime();
	}

	/**
	 * <p>
	 * Checks if two dates are on the same day ignoring time.
	 * </p>
	 * 
	 * @param date1
	 *            the first date, not altered, not null
	 * @param date2
	 *            the second date, not altered, not null
	 * @return true if they represent the same day
	 * @throws IllegalArgumentException
	 *             if either date is <code>null</code>
	 */
	public boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}

	public boolean isTodaysDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return isSameDay(calendar, Calendar.getInstance());
	}

	/**
	 * <p>
	 * Checks if two calendars represent the same day ignoring time.
	 * </p>
	 * 
	 * @param cal1
	 *            the first calendar, not altered, not null
	 * @param cal2
	 *            the second calendar, not altered, not null
	 * @return true if they represent the same day
	 * @throws IllegalArgumentException
	 *             if either calendar is <code>null</code>
	 */
	public boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
				&& cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
					.get(Calendar.DAY_OF_YEAR) == cal2
				.get(Calendar.DAY_OF_YEAR));
	}

	public static Date getDateFromStringDateTime(String date, String time)
			throws ParseException {
		String dateFormat = "dd/MM/yyyy HHmm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		if (time.length() < 4) {
			throw new ParseException("Not valid time: " + time, 2);
		}
		return simpleDateFormat.parse(date + " " + time);
	}

	public String formatDateInDDMMYYYY(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(date);
	}

	public String formatDateAndTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1);
		if (isTodaysDate(date)) {
			return "Today " + formateTime(date);
		} else if (isSameDay(date, calendar.getTime())) {
			return "Tomorrow " + formateTime(date);
		}
		return formatDate(date);
	}

	public String getformated30MinutesBeforeMatchTime(Date date) {
		Calendar tommorow = Calendar.getInstance();
		tommorow.setTime(new Date());
		tommorow.add(Calendar.DATE, 1);
		Calendar matchTime = Calendar.getInstance();
		matchTime.setTime(date);
		matchTime.add(Calendar.MINUTE, -Integer.parseInt(marketOpenTime));
		if (isTodaysDate(matchTime.getTime())) {
			return "Today " + formateTime(matchTime.getTime());
		} else if (isSameDay(matchTime.getTime(), tommorow.getTime())) {
			return "Tomorrow " + formateTime(matchTime.getTime());
		}
		return formateTime(matchTime.getTime());
	}

	public long getSecBetweenTime(Date date1, Date date2) {
		long time = getMilliSecBetweenTime(date1, date2);
		if(time > -1){
			return time/1000;
		}
		return -1;
	}
	
	public long getMilliSecBetweenTime(Date date1, Date date2) {
		if( date1 == null ){
			date1 = new Date();
		}
		if( date2 == null ){
			date2 = new Date();
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return cal2.getTimeInMillis() - cal1.getTimeInMillis();
	}
	
	public long getTimeRemainingInMarketOpen( Date date ){
		//if( isMatchTimeInIntervalMinutes(date,Integer.parseInt(marketOpenTime)+Integer.parseInt(marketOpenTimerTime)) ){
			Calendar now = Calendar.getInstance();
			now.setTime(new Date());
			Calendar matchTime = Calendar.getInstance();
			matchTime.setTime(date);
			matchTime.add(Calendar.MINUTE, -Integer.parseInt(marketOpenTime));
			//long diff = matchTime.getTimeInMillis() - now.getTimeInMillis();
			return matchTime.getTimeInMillis() - now.getTimeInMillis();
		//}
		//return -1;
	}
	
	public long getTimeRemainingInMarketOpen( Date date, int time ){
		//if( isMatchTimeInIntervalMinutes(date,Integer.parseInt(marketOpenTime)+Integer.parseInt(marketOpenTimerTime)) ){
			Calendar now = Calendar.getInstance();
			now.setTime(new Date());
			Calendar matchTime = Calendar.getInstance();
			matchTime.setTime(date);
			matchTime.add(Calendar.MINUTE, -time);
			//long diff = matchTime.getTimeInMillis() - now.getTimeInMillis();
			return matchTime.getTimeInMillis() - now.getTimeInMillis();
		//}
		//return -1;
	}
	
	public boolean isMatchTimeInIntervalMinutes(Date date, int interval) {
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		Calendar matchTime = Calendar.getInstance();
		matchTime.setTime(date);
		matchTime.add(Calendar.MINUTE, -interval);
		if (now.compareTo(matchTime) == -1) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isMatchTimeIn30Minutes(Date date) {
		return this.isMatchTimeInIntervalMinutes(date, Integer.parseInt(marketOpenTime));
	}

	public String formatSimpleTime(Date date) {
		String timeFormat = "HHmm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);
		return simpleDateFormat.format(date);
	}
	
	public String getCurrentTimeString() {
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		return now.get(Calendar.YEAR) + " - "
			+ now.get(Calendar.MONTH) + " - "
			+ now.get(Calendar.DATE) + " - "
			+ now.get(Calendar.HOUR_OF_DAY) + " - "
			+ now.get(Calendar.MINUTE);
	}

	public String getDateFormatPattern() {
		return dateFormatPattern;
	}

	public void setDateFormatPattern(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}

	public String getTimeFormatPattern() {
		return timeFormatPattern;
	}

	public void setTimeFormatPattern(String timeFormatPattern) {
		this.timeFormatPattern = timeFormatPattern;
	}

	public String getMarketOpenTime() {
		return marketOpenTime;
	}

	public void setMarketOpenTime(String marketOpenTime) {
		this.marketOpenTime = marketOpenTime;
	}

	public String getMarketOpenTimerTime() {
		return marketOpenTimerTime;
	}

	public void setMarketOpenTimerTime(String marketOpenTimerTime) {
		this.marketOpenTimerTime = marketOpenTimerTime;
	}
}
