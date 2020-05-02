package com.maedi.soft.ino.calendar.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maedi.soft.ino.base.BaseFragment;
import com.maedi.soft.ino.calendar.R;
import com.maedi.soft.ino.calendar.adapter.CalendarDateAdapter;
import com.maedi.soft.ino.calendar.adapter.RecyclerDate;
import com.maedi.soft.ino.calendar.model.ListObject;
import com.maedi.soft.ino.calendar.utils.DataStatic;
import com.maedi.soft.ino.calendar.utils.MtHelper;
import com.maedi.soft.ino.calendar.view.GridSpacingItemDecoration_V2;

import java.text.DateFormat;
import java.util.Calendar;

import timber.log.Timber;

public class CalendarMonth6 extends BaseFragment {

    private final String TAG = this.getClass().getName()+"- CALENDAR_MONTH_6 - ";

    private FragmentActivity f;

    private RecyclerView listViewDate;

    private RecyclerDate adapterDate;

    private ListObject listDate;

    private static CalendarDateAdapter.CalendarDateAdapter_Fragment1 listenerFragment1;

    public static CalendarMonth6 newInstance(CalendarDateAdapter.CalendarDateAdapter_Fragment1 listen) {
        CalendarMonth6 fragmentMonth = new CalendarMonth6();
        listenerFragment1 = listen;
        return fragmentMonth;
    }

    @Override
    public int baseContentView() {
        return R.layout.fragment_calendar;
    }

    @Override
    public void onCreateMView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, View v) {
        listViewDate = (RecyclerView) v.findViewById(R.id.listViewCalendar);
        LinearLayoutManager layoutManagerGrid = new GridLayoutManager(f, DataStatic.dayInWeeks);
        listViewDate.addItemDecoration(new GridSpacingItemDecoration_V2(DataStatic.dayInWeeks, DataStatic.VERTICAL_ITEM_SPACE_LIST_DATE, true, 0));
        listViewDate.setLayoutManager(layoutManagerGrid);
        MtHelper.setNestedScroll(listViewDate, false);
    }

    @Override
    public void onViewMCreated(View v, Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        final int currentYear = calendar.get(Calendar.YEAR);
        final int currentMonth = calendar.get(Calendar.MONTH);
        final int numDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        int dateNow = MtHelper.formatDateOneChar();
        final DateFormat defDateFormat = MtHelper.simpleFormatForCalendar();
        final int currentDay = MtHelper.getCurrentDay(currentMonth, currentYear);

        listDate = new ListObject();
        MtHelper.setDefListDate(listDate, currentDay, numDays, dateNow);

        RecyclerDate.CommGetRecyclerDate listenerDate = new RecyclerDate.CommGetRecyclerDate(){

            @Override
            public void setVal(String val) {

                MtHelper.setListDate(val, currentMonth, currentYear, currentDay,
                        numDays, defDateFormat, listDate);
                adapterDate.updateListItems(listDate);
                listenerFragment1.getVal(val);
            }
        };
        Timber.d(TAG+"TOTAL_LIST_DATE -> "+listDate.size());
        adapterDate = new RecyclerDate(f, f, R.layout.list_item_date, listenerDate, listDate, "");
        listViewDate.setAdapter(adapterDate);
    }

    @Override
    public void setMUserVisibleHint(boolean isVisibleToUser) {

    }

    @Override
    public void onMAttach(Context context) {
        if(null == f)f = (FragmentActivity) context;
    }

    @Override
    public void onMDetach() {
        if(null != f)f = null;
    }

    @Override
    public void onMStop() {

    }

    @Override
    public void onMDestroy() {

    }

    @Override
    public void onMDestroyView() {

    }

    @Override
    public void onMRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    @Override
    public void onMActivityResult(int requestCode, int resultCode, Intent data) {

    }
}