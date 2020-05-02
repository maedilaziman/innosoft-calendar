package com.maedi.soft.ino.calendar.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.maedi.soft.ino.calendar.fragment.CalendarMonth1;
import com.maedi.soft.ino.calendar.fragment.CalendarMonth10;
import com.maedi.soft.ino.calendar.fragment.CalendarMonth11;
import com.maedi.soft.ino.calendar.fragment.CalendarMonth12;
import com.maedi.soft.ino.calendar.fragment.CalendarMonth2;
import com.maedi.soft.ino.calendar.fragment.CalendarMonth3;
import com.maedi.soft.ino.calendar.fragment.CalendarMonth4;
import com.maedi.soft.ino.calendar.fragment.CalendarMonth5;
import com.maedi.soft.ino.calendar.fragment.CalendarMonth6;
import com.maedi.soft.ino.calendar.fragment.CalendarMonth7;
import com.maedi.soft.ino.calendar.fragment.CalendarMonth8;
import com.maedi.soft.ino.calendar.fragment.CalendarMonth9;

import timber.log.Timber;

public class CalendarDateAdapter extends FragmentStatePagerAdapter {

    private final String TAG = this.getClass().getName()+"- CALENDAR_DATE_ADAPTER - ";

    FragmentActivity f;

    private CalendarMonth1 calFragment1;

    private CalendarMonth2 calFragment2;

    private CalendarMonth3 calFragment3;

    private CalendarMonth4 calFragment4;

    private CalendarMonth5 calFragment5;

    private CalendarMonth6 calFragment6;

    private CalendarMonth7 calFragment7;

    private CalendarMonth8 calFragment8;

    private CalendarMonth9 calFragment9;

    private CalendarMonth10 calFragment10;

    private CalendarMonth11 calFragment11;

    private CalendarMonth12 calFragment12;

    public interface CalendarDateAdapter_Fragment1
    {
        void getVal(String val);
    }

    private CalendarDateAdapter_Fragment1 listenerFragment1;

    public void setListener_Fragment1(CalendarDateAdapter_Fragment1 listenerFragment1)
    {
        this.listenerFragment1 = listenerFragment1;
    }


    public CalendarDateAdapter(FragmentManager supportFragmentManager, FragmentActivity f) {
        super(supportFragmentManager);
        this.f = f;
    }

    @Override
    public Fragment getItem(int position) {
        Timber.d(TAG+"Get Item called - "+position);
        switch(position) {
            case 0:
                calFragment1 = CalendarMonth1.newInstance(listenerFragment1);
                return calFragment1;
            case 1:
                calFragment2 = CalendarMonth2.newInstance(listenerFragment1);
                return calFragment2;
            case 2:
                calFragment3 = CalendarMonth3.newInstance(listenerFragment1);
                return calFragment3;
            case 3:
                calFragment4 = CalendarMonth4.newInstance(listenerFragment1);
                return calFragment4;
            case 4:
                calFragment5 = CalendarMonth5.newInstance(listenerFragment1);
                return calFragment5;
            case 5:
                calFragment6 = CalendarMonth6.newInstance(listenerFragment1);
                return calFragment6;
            case 6:
                calFragment7 = CalendarMonth7.newInstance(listenerFragment1);
                return calFragment7;
            case 7:
                calFragment8 = CalendarMonth8.newInstance(listenerFragment1);
                return calFragment8;
            case 8:
                calFragment9 = CalendarMonth9.newInstance(listenerFragment1);
                return calFragment9;
            case 9:
                calFragment10 = CalendarMonth10.newInstance(listenerFragment1);
                return calFragment10;
            case 10:
                calFragment11 = CalendarMonth11.newInstance(listenerFragment1);
                return calFragment11;
            case 11:
                calFragment12 = CalendarMonth12.newInstance(listenerFragment1);
                return calFragment12;
            default: return CalendarMonth1.newInstance(listenerFragment1);
        }
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        try {
            super.restoreState(state, loader);
        } catch (Exception e) {
            Timber.d(TAG+"Error_Restore_State of Fragment : " + e.getLocalizedMessage());
        }
    }
}