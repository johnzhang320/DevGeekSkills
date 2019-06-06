package com.security.app.crypto.service;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @TODO enhance this class by moving __ALL__ time-related methods and variables from any other classes
 *
 */
import org.apache.logging.log4j.Logger;
import com.mailfrontier.msgcenter.app.log.Logger;

 public class TimeUtil {
    public static Logger Log =Logger.getLogger(TimeUtil.class.getName());

    public final static int FIVE_SECONDS_IN_MILLIS = 5 * 1000;
    public final static int TWENTY_SECONDS_IN_MILLIS = 20 * 1000;
    public final static int FIFTY_SECONDS_IN_MILLIS = 50 * 1000;
    public final static int NINETY_SECONDS_IN_MILLIS = 90 * 1000;
    public final static int FIVE_MINUTES_IN_MILLIS = 5 * 60 * 1000;
    public final static int TEN_HOURS_IN_MILLIS = 10 * 60 * 60 * 1000;
	
    public static final int MINUTES_PER_HOUR = 60;
    public static final int SECONDS_PER_MINUTE = 60;
    public static final int SECONDS_PER_HOUR = MINUTES_PER_HOUR * SECONDS_PER_MINUTE;
    public static final int REFRESH_LOCK_INTERVAL = 50 * SECONDS_PER_MINUTE; //50 Minutes. Should be less than an hour

    public static final String DEFAULT_TIMEZONE = "GMT";
    public static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:00";
    
    private TimeUtil(){}
    
    /**
     * getDateTimeStamp - produces a string like "20030720HHMMSS" so you know when an event occurs
     */
    public static String getDateTimeStamp() {
        Calendar myCal = Calendar.getInstance();

        int year = myCal.get(Calendar.YEAR);
        int month = myCal.get(Calendar.MONTH) + 1;
        int dayOfMonth = myCal.get(Calendar.DAY_OF_MONTH);
        int hour = myCal.get(Calendar.HOUR_OF_DAY);
        int min = myCal.get(Calendar.MINUTE);
        int sec = myCal.get(Calendar.SECOND);

        String dateTimeStamp = Integer.toString(year);

        if (month < 10) {
            dateTimeStamp += "0";
        }
        dateTimeStamp += Integer.toString(month);

        if (dayOfMonth < 10) {
            dateTimeStamp += "0";
        }
        dateTimeStamp += Integer.toString(dayOfMonth);

        if (hour < 10) {
            dateTimeStamp += "0";
        }
        dateTimeStamp += Integer.toString(hour);

        if (min < 10) {
            dateTimeStamp += "0";
        }
        dateTimeStamp += Integer.toString(min);

        if (sec < 10) {
            dateTimeStamp += "0";
        }
        dateTimeStamp += Integer.toString(sec);

        return (dateTimeStamp);
    }
    /**
     * Get Time Stamp based GMT time zone
     * getDateTimeStamp - produces a string like "20030720HHMMSSmmmm" so you know when an event occurs
     */
    public static String getDateTimeStampInMilliSeconds() {
    	
        Calendar myCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        int year = myCal.get(Calendar.YEAR);
        int month = myCal.get(Calendar.MONTH) + 1;
        int dayOfMonth = myCal.get(Calendar.DAY_OF_MONTH);
        int hour = myCal.get(Calendar.HOUR_OF_DAY);
        int min = myCal.get(Calendar.MINUTE);
        int sec = myCal.get(Calendar.SECOND);
        int mSec = myCal.get(Calendar.MILLISECOND);
        
        String dateTimeStamp = Integer.toString(year);

        if (month < 10) {
            dateTimeStamp += "0";
        }
        dateTimeStamp += Integer.toString(month);

        if (dayOfMonth < 10) {
            dateTimeStamp += "0";
        }
        dateTimeStamp += Integer.toString(dayOfMonth);

        if (hour < 10) {
            dateTimeStamp += "0";
        }
        dateTimeStamp += Integer.toString(hour);

        if (min < 10) {
            dateTimeStamp += "0";
        }
        dateTimeStamp += Integer.toString(min);

        if (sec < 10) {
            dateTimeStamp += "0";
        }
        dateTimeStamp += Integer.toString(sec);
 
        if (mSec < 100 && mSec >10) {
            dateTimeStamp += "0";
        }
        if (mSec < 10 ) {
            dateTimeStamp += "00";
        }
        dateTimeStamp += Integer.toString(mSec);
        return (dateTimeStamp);
    }
    /**
     * This code is basically copied from Log.getDateTimeStamp()
     */
    public static String getTimeZoneInfo() {
        TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
        Calendar cal = Calendar.getInstance(gmtTimeZone);  // GMT

        long currentMillisGmt = cal.getTimeInMillis();
        TimeZone localTimeZone = TimeZone.getDefault();

        int millisAheadOfUtc = localTimeZone.getOffset(currentMillisGmt);
        int minutesAheadOfUtc = millisAheadOfUtc / (60 * 1000);

        int minutes = minutesAheadOfUtc % 60;
        int hours = minutesAheadOfUtc / 60;

        String localTzName = localTimeZone.getDisplayName();
        StringBuffer sb = new StringBuffer(localTzName);

        sb.append(" tz=GMT");

        if (hours >= 0 && minutes >= 0) {
            sb.append("+");
        }
        else if (hours >= 0 && minutes < 0) {
            // -30 mins -> GMT-0:30
            minutes = -1 * minutes;
            sb.append("-");
        }
        else {
            // -90 mins -> GMT-1:30
            minutes = -1 * minutes;
        }

        sb.append(hours);
        sb.append(":");

        // so we get 8:00, not 8:0
        if (minutes > -10 && minutes < 10) {
            sb.append("0");
        }

        sb.append(minutes);

        return sb.toString();
    }
}
