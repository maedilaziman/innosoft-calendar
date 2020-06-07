package com.maedi.soft.ino.calendar;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.maedi.soft.ino.calendar.adapter.CalendarDateAdapter;
import com.maedi.soft.ino.calendar.adapter.FormSpinnerAdapter;
import com.maedi.soft.ino.calendar.adapter.RecyclerDate;
import com.maedi.soft.ino.calendar.adapter.RecyclerMonth;
import com.maedi.soft.ino.calendar.model.ListObject;
import com.maedi.soft.ino.calendar.utils.DataStatic;
import com.maedi.soft.ino.calendar.utils.MtHelper;
import com.maedi.soft.ino.calendar.utils.NonSwipeableViewPager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import timber.log.Timber;

public class InitalCalendar {

    private final String TAG = "## InitalCalendar ## ";

    private FragmentActivity f;

    private RecyclerView listViewMonth;

    private TextView titleYear;

    private TextView titleMonth;

    private TextView titleCancel;

    private Spinner spinnerYear;

    private LinearLayout layoutPrevMonth;

    private LinearLayout layoutNextMonth;

    private LinearLayout layoutParentDate;

    private NonSwipeableViewPager viewPager;

    private ProgressBar progressBarCalendar;

    private FormSpinnerAdapter spinAdapter;

    //------------------------------------------//

    private RecyclerMonth adapterMonth;

    private RecyclerDate adapterDate;

    private ListObject listMonth;

    private List<String> listYears;

    private int currentDay, currentMonth, currentYear, userChooseMonth, calcCurrMonth, calcNextMonth, calcPrevMonth;

    private Calendar calendar;

    private DateFormat dateFormat_OneChar;

    private int dateNow;

    private int numDays;

    private String nameOfMonth;

    private DateFormat defDateFormat;

    private CalendarDateAdapter calendarDateAdapter;

    public interface CommInitialCalendar
    {
        void resMap(Map m);

        void setData();

        void hideView();

        void showView();

        void startAnim(Animation animation);
    }

    private CommInitialCalendar listener;

    public void setListener(CommInitialCalendar listen)
    {
        listener = listen;
    }

    public void initView(View v, FragmentActivity f)
    {
        this.f = f;
        listViewMonth = (RecyclerView) null != v ? v.findViewById(R.id.listViewMonth) : f.findViewById(R.id.listViewMonth);
        titleYear = (TextView) null != v ? v.findViewById(R.id.title_year) : f.findViewById(R.id.title_year);
        titleMonth = (TextView) null != v ? v.findViewById(R.id.title_month) : f.findViewById(R.id.title_month);
        titleCancel = (TextView) null != v ? v.findViewById(R.id.title_cancel) : f.findViewById(R.id.title_cancel);
        spinnerYear = (Spinner) null != v ? v.findViewById(R.id.spinner_year) : f.findViewById(R.id.spinner_year);
        layoutPrevMonth = (LinearLayout) null != v ? v.findViewById(R.id.layout_prev_month) : f.findViewById(R.id.layout_prev_month);
        layoutNextMonth = (LinearLayout) null != v ? v.findViewById(R.id.layout_next_month) : f.findViewById(R.id.layout_next_month);
        layoutParentDate = (LinearLayout) null != v ? v.findViewById(R.id.layout_parent_date) : f.findViewById(R.id.layout_parent_date);
        viewPager = (NonSwipeableViewPager) null != v ? v.findViewById(R.id.viewpager_calendar) : f.findViewById(R.id.viewpager_calendar);
        progressBarCalendar = (ProgressBar) null != v ? v.findViewById(R.id.progressBarCalendar) : f.findViewById(R.id.progressBarCalendar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(f, LinearLayoutManager.HORIZONTAL, false);
        listViewMonth.setLayoutManager(layoutManager);
        MtHelper.setNestedScroll(listViewMonth, false);
    }

    public void initCalendar()
    {
        //##########################################################################################//
        defDateFormat = new SimpleDateFormat("MMM/dd/yyyy", Locale.ENGLISH);
        listMonth = new ListObject();
        currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        userChooseMonth = currentMonth;
        calcCurrMonth = currentMonth;

        //if(currentMonth < 12)
        //    calcNextMonth = currentMonth + 1;
        //else
        //    calcNextMonth = currentMonth;
        //if(currentMonth > 0)
        //    calcPrevMonth = currentMonth - 1;
        //else
        //    calcPrevMonth = 0;

        nameOfMonth = MtHelper.getMonthFromValue(currentMonth, false);
        int x = 0;
        for(String s: DataStatic.monthOfYear)
        {
            if(x == currentMonth)listMonth.add(new ListObject(s, ""+x, true));
            else listMonth.add(new ListObject(s, ""+x, false));
            x++;
        }

        dateFormat_OneChar = new SimpleDateFormat("dd");
        Date date = new Date();
        dateNow = Integer.parseInt(dateFormat_OneChar.format(date));
        calendar = Calendar.getInstance();
        numDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        //int y=1;
        //listDay = new ListObject();
        //for(String s: DataStatic.dayOfWeek)
        //{
        //    if(y == 7)listDay.add(new ListObject(s, true));
        //    else listDay.add(new ListObject(s, false));
        //    y++;
        //}
        //adapterDay = new RecyclerDay(f, f, R.layout.list_item_day, this, listDay, "");
        //listViewDay.setAdapter(adapterDay);

        currentYear = Calendar.getInstance().get(Calendar.YEAR);//LocalDate.now().getYear();
        titleYear.setText(""+currentYear);
        titleMonth.setText(MtHelper.getMonthFromValue(currentMonth, false));

        listYears = new ArrayList<String>();

        int maxCountYear = 70;
        int calcTotalYear = currentYear - maxCountYear;
        int iyear = 0;
        for(int j=1; j<=maxCountYear; j++)
        {
            iyear = calcTotalYear+j;
            listYears.add(""+iyear);
        }

        spinAdapter = new FormSpinnerAdapter(f, R.layout.form_spinner_item, listYears);
        spinnerYear.setAdapter(spinAdapter);
        spinnerYear.setSelection(listYears.size()-1);

        ////////////////////////////////////////////////////////////////////////////////////////////////
        //must be start from 1
        String dateStr = MtHelper.getMonthFromValue(currentMonth, true)+"/"+MtHelper.twoDigit(1)+"/"+currentYear;
        //String dateStr = FuncHelper.getMonthFromValue(currentMonth, true)+"/"+FuncHelper.twoDigit(dateNow)+"/"+currentYear;
        Timber.d(TAG+"dateStr - "+dateStr);
        Date defDate = null;
        try {
            defDate = defDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            Timber.d(TAG+"defDateFormat > exception - "+e.getLocalizedMessage());
        }
        Calendar cald = Calendar.getInstance();
        cald.setTime(defDate);
        currentDay = cald.get(Calendar.DAY_OF_WEEK);//1 indexed starting from Sunday
        String nameOfDay = MtHelper.getDayFromValue(currentDay);
        Timber.d(TAG+"NAME_OF_DAY_MONTH - "+currentDay +" | "+ nameOfDay +" | "+ nameOfMonth +" | CurrentMonth -> "+ currentMonth);

        ////////////////////////////////////////////////////////////////////////////////////////////////

        /*
        listDate = new ListObject();
        stcAddDayTitle();
        stcAddEmptyStrDay(currentDay);
        for(int i=1; i<=numDays; i++)
        {
            if(i==dateNow)listDate.add(new ListObject(""+i, true));
            else listDate.add(new ListObject(""+i, false));
        }
        */

        RecyclerMonth.CommGetRecyclerMonth listenerMonth = new RecyclerMonth.CommGetRecyclerMonth()
        {
            @Override
            public void setData(String data, String data2) {
                //setMonth(data, data2, titleMonth, listViewDate, null);
                Timber.d(TAG+"RecyclerMonth -> setData = "+data +" | data2 = "+ data2);
                listMonth.clear();
                int x = 0;
                int position = Integer.parseInt(data2);
                for(String s : DataStatic.monthOfYear)
                {
                    if(x == position)
                    {
                        listMonth.add(new ListObject(s, ""+x, true));
                    }
                    else
                    {
                        listMonth.add(new ListObject(s, ""+x, false));
                    }
                    x++;
                }
                calcCurrMonth = position;
                calcNextMonth = position;
                calcPrevMonth = position;
                viewPager.setCurrentItem(position);
                adapterMonth.updateListItems(listMonth);
                listViewMonth.smoothScrollToPosition(position);

                titleMonth.setText(MtHelper.getMonthFromValue(position, false));
            }
        };

        adapterMonth = new RecyclerMonth(f, f, R.layout.list_item_month, listenerMonth, listMonth, "");
        listViewMonth.setAdapter(adapterMonth);

        calendarDateAdapter = new CalendarDateAdapter(f.getSupportFragmentManager(), f);
        CalendarDateAdapter.CalendarDateAdapter_Fragment1 listenerFragment1 = new CalendarDateAdapter.CalendarDateAdapter_Fragment1() {
            @Override
            public void getVal(String val) {
                //final String dateStr = MtHelper.getMonthFromValue(calcCurrMonth, true)+"/"+MtHelper.twoDigit(Integer.parseInt(val))+"/"+currentYear;
                //String fromDate = MtHelper.twoDigit(calcCurrMonth)+"/"+MtHelper.twoDigit(Integer.parseInt(val))+"/"+currentYear;
                final String dateStr = MtHelper.getMonthFromValue(calcCurrMonth, true)+"/"+MtHelper.twoDigit(Integer.parseInt(val))+"/"+spinnerYear.getSelectedItem();
                String fromDate = MtHelper.twoDigit(calcCurrMonth)+"/"+MtHelper.twoDigit(Integer.parseInt(val))+"/"+spinnerYear.getSelectedItem();
                final String dateAfterWeeks = getCalculatedDate(fromDate, "MMM/dd/yyyy", 7);
                Timber.d(TAG+"THE_DATE - "+dateStr);
                Date date = null;
                try {
                    date = defDateFormat.parse(dateStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);

                String schedule = dateStr +" - "+ dateAfterWeeks;
                Map map = new HashMap<>();
                map.put("data1", dateStr);
                map.put("data2", schedule);
                if(null != listener)listener.resMap(map);

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(null != listener)listener.hideView();
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(null != listener)listener.setData();
                            }
                        }, 600);
                    }
                }, 400);
            }
        };

        calendarDateAdapter.setListener_Fragment1(listenerFragment1);
        viewPager.setAdapter(calendarDateAdapter);
        //viewPager.setOffscreenPageLimit(12);
        viewPager.setCurrentItem(currentMonth);
        viewPager.setPagingEnabled(true);

        layoutPrevMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(calcPrevMonth > 0)
                {
                    calcPrevMonth = calcCurrMonth-1;
                }
                else
                {
                    calcPrevMonth = 0;
                }
                calcCurrMonth = calcPrevMonth;
                calcNextMonth = calcCurrMonth+1;
                viewPager.setCurrentItem(calcPrevMonth);

                listMonth.clear();
                int x = 0;
                for(String s : DataStatic.monthOfYear)
                {
                    if(x == calcPrevMonth)
                    {
                        listMonth.add(new ListObject(s, ""+x, true));
                    }
                    else
                    {
                        listMonth.add(new ListObject(s, ""+x, false));
                    }
                    x++;
                }
                adapterMonth.updateListItems(listMonth);
                listViewMonth.smoothScrollToPosition(calcPrevMonth);
                titleMonth.setText(MtHelper.getMonthFromValue(calcPrevMonth, false));
            }
        });

        layoutNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.d(TAG+"layoutNextMonth -> calcNextMonth = "+calcNextMonth);
                //if(calcNextMonth < 12)
                //    setMonth(FuncHelper.getMonthFromValue(calcNextMonth, false), "", titleMonth, listViewDate, listViewMonth);
                if(calcNextMonth < 11)
                {
                    calcNextMonth = calcCurrMonth+1;
                }
                else
                {
                    calcNextMonth = 11;
                }
                calcCurrMonth = calcNextMonth;
                calcPrevMonth = calcCurrMonth-1;
                viewPager.setCurrentItem(calcNextMonth);

                listMonth.clear();
                int x = 0;
                for(String s : DataStatic.monthOfYear)
                {
                    if(x == calcNextMonth)
                    {
                        listMonth.add(new ListObject(s, ""+x, true));
                    }
                    else
                    {
                        listMonth.add(new ListObject(s, ""+x, false));
                    }
                    x++;
                }
                adapterMonth.updateListItems(listMonth);
                listViewMonth.smoothScrollToPosition(calcNextMonth);
                titleMonth.setText(MtHelper.getMonthFromValue(calcNextMonth, false));
            }
        });

        titleCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != listener)listener.hideView();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Timber.d(TAG+"PAGE_SCROLLED_GET_CURRENT_ITEM - "+viewPager.getCurrentItem() + " |" +position +" |" +positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                Timber.d(TAG+"PAGER2_PAGE_SELECTED_POSITION - "+position);
                listMonth.clear();
                int x = 0;
                for(String s : DataStatic.monthOfYear)
                {
                    if(x == position)
                    {
                        listMonth.add(new ListObject(s, ""+x, true));
                    }
                    else
                    {
                        listMonth.add(new ListObject(s, ""+x, false));
                    }
                    x++;
                }
                calcCurrMonth = position;
                calcNextMonth = position;
                calcPrevMonth = position;
                adapterMonth.updateListItems(listMonth);
                listViewMonth.smoothScrollToPosition(position);
                titleMonth.setText(MtHelper.getMonthFromValue(position, false));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void resetCalendar()
    {
        if(null != listener)listener.showView();
        viewPager.setVisibility(View.GONE);
        progressBarCalendar.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(f, R.anim.overlay_in_from_top);
        spinnerYear.setSelection(listYears.size()-1);
        if(null != listener)listener.startAnim(animation);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                viewPager.setVisibility(View.VISIBLE);
                progressBarCalendar.setVisibility(View.GONE);

                calcCurrMonth = currentMonth;
                calcNextMonth = calcCurrMonth;
                calcPrevMonth = calcCurrMonth;
                viewPager.setCurrentItem(calcCurrMonth);

                listMonth.clear();
                int x = 0;
                for(String s : DataStatic.monthOfYear)
                {
                    if(x == calcCurrMonth)
                    {
                        listMonth.add(new ListObject(s, ""+x, true));
                    }
                    else
                    {
                        listMonth.add(new ListObject(s, ""+x, false));
                    }
                    x++;
                }
                adapterMonth.updateListItems(listMonth);
                listViewMonth.smoothScrollToPosition(calcPrevMonth);
                titleMonth.setText(MtHelper.getMonthFromValue(calcPrevMonth, false));
            }
        }, 800);
    }

    private String getCalculatedDate(String date, String dateFormat, int days) {
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
            Timber.d(TAG+"Error Ex -> "+e.getMessage());
        }
        return strdate;
    }
}
