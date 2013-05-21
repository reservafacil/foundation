package com.brazoft.foundation.util;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.Period;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class DateTimeUtil {

    /**
     * @param start
     * @param end
     * @return Returns years between start and end
     */
    public static int getYears(Date start, Date end) {
	return new Period(start.getTime(), end.getTime()).getYears();
    }

    /**
     * @param start
     * @param end
     * @return Returns months between start and end
     */
    public static int getMonths(Date start, Date end) {
	return new Period(start.getTime(), end.getTime()).getMonths();
    }

    /**
     * @param start
     * @param end
     * @return Returns days between start and end
     */
    public static int getDays(Date start, Date end) {
	return new Period(start.getTime(), end.getTime()).getDays();
    }

    /**
     * @param start
     * @param end
     * @return Returns number of hours between start and end
     */
    public static int getHours(Date start, Date end) {
	return new Period(start.getTime(), end.getTime()).getHours();
    }

    /**
     * @param start
     * @param end
     * @return Returns number of minutes between start and end
     */
    public static int getMinutes(Date start, Date end) {
	return new Period(start.getTime(), end.getTime()).getMinutes();
    }

    /**
     * @param start
     * @param end
     * @return Returns number of seconds between start and end
     */
    public static int getSeconds(Date start, Date end) {
	return new Period(start.getTime(), end.getTime()).getSeconds();
    }

    /**
     * @param millis
     * @return Returns Calendar instance from millis
     */
    public static Calendar toCalendar(long millis) {
	Calendar calendar;

	calendar = Calendar.getInstance();
	calendar.setTimeInMillis(millis);

	return calendar;
    }

    /**
     * @param time
     * @return Returns Calendar instance from time
     */
    public static Calendar toCalendar(Date time) {
	Calendar calendar;

	calendar = Calendar.getInstance();
	calendar.setTime(time);

	return calendar;
    }

    /**
     * @param calendar
     * @param toCompare
     * @return Returns if objects have the same date as well
     */
    public static boolean isTheSameDate(Calendar calendar, Calendar toCompare) {
	return DateTimeUtil.isTheSame(calendar, toCompare, Calendar.DATE)
	        && DateTimeUtil.isTheSame(calendar, toCompare, Calendar.MONTH)
	        && DateTimeUtil.isTheSame(calendar, toCompare, Calendar.YEAR);
    }

    /**
     * @param calendar
     * @param toCompare
     * @param field
     * @return Returns if objects have the same values from field
     */
    public static boolean isTheSame(Calendar calendar, Calendar toCompare, int field) {
	return calendar.get(field) == toCompare.get(field);
    }

    /**
     * @param calendar
     * @param toCompare
     * @param field
     * @return Returns if objects have less than values from field
     */
    public static boolean isLessThan(Calendar calendar, Calendar toCompare, int field) {
	return calendar.get(field) < toCompare.get(field);
    }

    /**
     * @param calendar
     * @param toCompare
     * @param field
     * @return Returns if objects have more than values from field
     */
    public static boolean isMoreThan(Calendar calendar, Calendar toCompare, int field) {
	return !DateTimeUtil.isLessThan(calendar, toCompare, field);
    }

    /**
     * @param calendar
     * @param toCompare
     * @return Returns if calendar is less than toCompare using Year, Month and Date, ignoring after
     *         all
     */
    public static boolean isLessThan(Calendar calendar, Calendar toCompare) {
	DateTimeUtil.resetTime(calendar, toCompare);

	return calendar.before(toCompare);
    }

    /**
     * @param calendar
     * @param toCompare
     * @return Returns if calendar is more than toCompare using Year, Month and Date, ignoring after
     *         all
     */
    public static boolean isMoreThan(Calendar calendar, Calendar toCompare) {
	DateTimeUtil.resetTime(calendar, toCompare);

	return calendar.after(toCompare);
    }

    private static void resetTime(Calendar calendar, Calendar toCompare) {
	calendar.set(Calendar.MILLISECOND, 0);
	toCompare.set(Calendar.MILLISECOND, 0);

	calendar.set(Calendar.SECOND, 0);
	toCompare.set(Calendar.SECOND, 0);

	calendar.set(Calendar.MINUTE, 0);
	toCompare.set(Calendar.MINUTE, 0);

	calendar.set(Calendar.HOUR, 0);
	toCompare.set(Calendar.HOUR, 0);
    }
}