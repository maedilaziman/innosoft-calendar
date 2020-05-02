package com.maedi.soft.ino.calendar.utils;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;

import com.maedi.soft.ino.calendar.model.ListObject;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public class MtHelper {

    public static void setNestedScroll(RecyclerView listView, boolean b){
        listView.setNestedScrollingEnabled(b);
        listView.setFocusable(b);
    }

    public static String twoDigit(int i)
    {
        if(i < 10)
            return "0"+i;
        else
            return ""+i;
    }

    public static String getMonthFromValue(String month, boolean isShort) {
        if (month.equals("01")) {
            return isShort ? "Jan" : "January";
        } else if (month.equals("02")) {
            return isShort ? "Feb" : "February";
        } else if (month.equals("03")) {
            return isShort ? "Mar" : "March";
        } else if (month.equals("04")) {
            return isShort ? "Apr" : "April";
        } else if (month.equals("05")) {
            return isShort ? "May" : "May";
        } else if (month.equals("06")) {
            return isShort ? "Jun" : "June";
        } else if (month.equals("07")) {
            return isShort ? "Jul" : "July";
        } else if (month.equals("08")) {
            return isShort ? "Aug" : "August";
        } else if (month.equals("09")) {
            return isShort ? "Sep" : "September";
        } else if (month.equals("10")) {
            return isShort ? "Okt" : "October";
        } else if (month.equals("11")) {
            return isShort ? "Nov" : "November";
        } else if (month.equals("12")) {
            return isShort ? "Dec" : "December";
        }
        return null;
    }

    public static String getShortMonthFromValue(String month) {
        if (month.equals("January")) {
            return "Jan";
        } else if (month.equals("February")) {
            return "Feb";
        } else if (month.equals("March")) {
            return "Mar";
        } else if (month.equals("April")) {
            return "Apr";
        } else if (month.equals("May")) {
            return "May";
        } else if (month.equals("June")) {
            return "Jun";
        } else if (month.equals("July")) {
            return "Jul";
        } else if (month.equals("August")) {
            return "Aug";
        } else if (month.equals("September")) {
            return "Sep";
        } else if (month.equals("October")) {
            return "Oct";
        } else if (month.equals("November")) {
            return "Nov";
        } else if (month.equals("December")) {
            return "Dec";
        }
        return null;
    }

    public static String getMonthFromValue(int month, boolean isShort) {

        String monthName = null;
        switch (month)
        {
            case 0:
                monthName = isShort ? "Jan" : "January";
                break;
            case 1:
                monthName = isShort ? "Feb" : "February";
                break;
            case 2:
                monthName = isShort ? "Mar" : "March";
                break;
            case 3:
                monthName = isShort ? "Apr" : "April";
                break;
            case 4:
                monthName = isShort ? "May" : "May";
                break;
            case 5:
                monthName = isShort ? "Jun" : "June";
                break;
            case 6:
                monthName = isShort ? "Jul" : "July";
                break;
            case 7:
                monthName = isShort ? "Aug" : "August";
                break;
            case 8:
                monthName = isShort ? "Sep" : "September";
                break;
            case 9:
                monthName = isShort ? "Oct" : "October";
                break;
            case 10:
                monthName = isShort ? "Nov" : "November";
                break;
            case 11:
                monthName = isShort ? "Dec" : "December";
                break;
        }

        return monthName;
    }

    public static String getDayFromValue(int day) {
        String dayName = null;
        switch (day)
        {
            case 1:
                dayName = "Sunday";
                break;
            case 2:
                dayName = "Monday";
                break;
            case 3:
                dayName = "Tuesday";
                break;
            case 4:
                dayName = "Wednesday";
                break;
            case 5:
                dayName = "Thursday";
                break;
            case 6:
                dayName = "Friday";
                break;
            case 7:
                dayName = "Saturday";
                break;
        }

        return dayName;
    }

    public static String getDateTimestamp(){
        Date date= new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return String.valueOf(timestamp);
    }

    public static void setListDate(String val, int currentMonth, int currentYear, int currentDay, int numDays,
                                   DateFormat defDateFormat, ListObject listDate)
    {
        final String dateStr = getMonthFromValue(currentMonth, true)+"/"+twoDigit(Integer.parseInt(val))+"/"+currentYear;
        String fromDate = twoDigit(currentMonth)+"/"+twoDigit(Integer.parseInt(val))+"/"+currentYear;
        final String dateAfterWeeks = getCalculatedDate(fromDate, "MMM/dd/yyyy", 7);

        Date date = null;
        try {
            date = defDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);//1 indexed starting from Sunday
        String nameOfDay = getDayFromValue(day);
        Timber.d("SET_LIST_DATE: DAY_OF_WEEK - " +day+ " | MONTH_OF_YEAR - " +currentMonth+ " | NAME_OF_DAY_MONTH - "+nameOfDay);

        listDate.clear();
        stcAddDayTitle(listDate);
        stcAddEmptyStrDay(listDate, currentDay);
        for(int i=1; i<=numDays; i++)
        {
            if(i==Integer.parseInt(val))listDate.add(new ListObject(""+i, true));
            else listDate.add(new ListObject(""+i, false));
        }
    }

    public static void stcAddDayTitle(ListObject listDate)
    {
        int y=1;
        for(String s: DataStatic.dayOfWeek)
        {
            if(y == 7)listDate.add(new ListObject(s, true));
            else listDate.add(new ListObject(s, false));
            y++;
        }
    }

    public static void stcAddEmptyStrDay(ListObject listDate, int day)
    {
        Timber.d("SET_LIST_DATE: stcAddEmptyStrDay > day - "+day);
        if(day > 1)
        {
            for(int i=1; i<day; i++)
            {
                listDate.add(new ListObject("", false));
            }
        }
    }

    public static int getCurrentDay(int currentMonth, int currentYear)
    {
        ////////////////////////////////////////////////////////////////////////////////////////////////
        //must be start from 1
        final DateFormat defDateFormat = new SimpleDateFormat("MMM/dd/yyyy", Locale.ENGLISH);
        String dateStr = getMonthFromValue(currentMonth, true)+"/"+twoDigit(1)+"/"+currentYear;
        Timber.d("dateStr - "+dateStr);
        Date defDate = null;
        try {
            defDate = defDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            Timber.d("SET_LIST_DATE: defDateFormat > exception - "+e.getLocalizedMessage());
        }
        Calendar cald = Calendar.getInstance();
        cald.setTime(defDate);
        return cald.get(Calendar.DAY_OF_WEEK);//1 indexed starting from Sunday
        ////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public static String getCalculatedDate(String date, String dateFormat, int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        String[] arrDate = date.split("\\/");
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arrDate[1]));
        cal.set(Calendar.MONTH, Integer.parseInt(arrDate[0]));
        cal.set(Calendar.YEAR, Integer.parseInt(arrDate[2]));
        cal.add(Calendar.DAY_OF_MONTH, days);

        Date newDate = cal.getTime();
        String strdate = null;
        try {
            strdate = s.format(newDate);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Timber.d("SET_LIST_DATE: Error Ex -> "+e.getMessage());
        }
        return strdate;
    }

    public static void setDefListDate(ListObject listDate, int currentDay, int numDays, int dateNow)
    {
        stcAddDayTitle(listDate);
        stcAddEmptyStrDay(listDate, currentDay);
        Timber.d("SET_LIST_DATE: setDefListDate > numDays - "+numDays +" | dateNow -> "+ dateNow);
        for(int i=1; i<=numDays; i++)
        {
            Timber.d("SET_LIST_DATE: setDefListDate > day - "+i);
            if(i==dateNow)listDate.add(new ListObject(""+i, true));
            else listDate.add(new ListObject(""+i, false));
        }
    }

    public static int formatDateOneChar()
    {
        DateFormat dateFormat_OneChar = new SimpleDateFormat("dd");
        Date date = new Date();
        return Integer.parseInt(dateFormat_OneChar.format(date));
    }

    public static DateFormat simpleFormatForCalendar()
    {
        DateFormat defDateFormat = new SimpleDateFormat("MMM/dd/yyyy", Locale.ENGLISH);
        return defDateFormat;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String getVersionName(Context context) {
        String versionName = null;
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {}
        return versionName;
    }

    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {}
        return versionCode;
    }

    public static boolean hasAPI_LEVEL24_ANDROID_7_Above() {
        return Build.VERSION.SDK_INT >= 24; //API Level 7
    }
}