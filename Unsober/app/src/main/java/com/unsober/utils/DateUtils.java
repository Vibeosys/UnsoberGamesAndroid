package com.unsober.utils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by akshay on 07-07-2016.
 */
public class DateUtils {

    final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    final SimpleDateFormat dateReadFormat = new SimpleDateFormat("dd MMM yyyy");
    final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    final SimpleDateFormat timeReadFormat = new SimpleDateFormat("hh:mm aa");
    final SimpleDateFormat SqlFormat = new SimpleDateFormat("yyyy-MM-dd");
    final SimpleDateFormat dateWithTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public String getGMTCurrentDate() {
        dateWithTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateWithTimeFormat.format(new java.util.Date());
    }

    public String getLocalCurrentDate() {
        return dateWithTimeFormat.format(new java.util.Date());
    }

    public String getLocalSQLCurrentDate() {
        return SqlFormat.format(new java.util.Date());
    }

    public String getLocalDateInFormat(java.util.Date date) {
        return dateFormat.format(date);
    }

    public String getLocalDateInReadableFormat(java.util.Date date) {
        return dateReadFormat.format(date);
    }

    public String getLocalTimeInReadableFormat(java.util.Date date) {
        return timeReadFormat.format(date);
    }

    public String getLocalTimeInReadableFormat() {
        return timeReadFormat.format(new java.util.Date());
    }

    public String getGMTDateInFormat(Date date) {
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(date);
    }

    public String getGMTCurrentTime() {
        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return timeFormat.format(new java.util.Date());
    }

    public String getSqlOffsetTime(int hour, int min) {
        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        long time = new java.util.Date().getTime() - getTimeOffsetAsPerLocal(hour, min);
        return timeFormat.format(new java.util.Date(time));
    }


    public String getLocalCurrentTime() {
        return timeFormat.format(new java.util.Date());
    }

    public String getLocalTimeInFormat(Date date) {
        return timeFormat.format(date);
    }

    public String getGMTTimeInFormat(Date date) {
        return timeFormat.format(date);
    }

    public java.util.Date getFormattedDate(String strDate) {
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        java.util.Date date = null;
        try {
            date = df2.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public java.util.Date getFormattedOnlyDate(String strDate) {
        //DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        java.util.Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public long getTimeOffsetAsPerLocal(int hours, int mins) {
        return ((hours * 60 * 60 * 1000) + (mins * 60 * 1000));
    }

    public String getReadableDateFromLong(long timeStamp) {
        long tradeDate = timeStamp * 1000;
        java.util.Date tDate = new java.util.Date(tradeDate);
        return getLocalDateInFormat(tDate);
    }

    public String getDateAndTimeFromLong(long timeStamp) {
        long tradeDate = timeStamp * 1000;
        java.util.Date tDate = new java.util.Date(tradeDate);
        return dateWithTimeFormat.format(tDate);
    }

    public String convertTimeToDate(String strDate) {
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        java.util.Date date = null;
        try {
            date = df2.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getLocalDateInFormat(date);
    }
}
