package com.maedi.example.calendar;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import com.maedi.soft.ino.base.BuildActivity;
import com.maedi.soft.ino.base.func_interface.ActivityListener;
import com.maedi.soft.ino.base.store.MapDataParcelable;
import com.maedi.soft.ino.calendar.InitalCalendar;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BuildActivity<View> implements ActivityListener<Integer> {

    private final String TAG = this.getClass().getName() +"- MAIN_ACTIVITY - ";

    private FragmentActivity f;

    @BindView(R.id.beauty_calendar)
    View viewBeautyCalendar;

    @OnClick(R.id.post1)
    public void OpenCalendar() {
        initialCalendar.resetCalendar();
    }

    private InitalCalendar initialCalendar;

    private InitalCalendar.CommInitialCalendar listenerCalendar;

    @Override
    public int setPermission() {
        return 0;
    }

    @Override
    public boolean setAnalytics() {
        return false;
    }

    @Override
    public int baseContentView() {
        return R.layout.activity_main;
    }

    @Override
    public ActivityListener createListenerForActivity() {
        return this;
    }

    @Override
    public void onCreateActivity(Bundle savedInstanceState) {
        f = this;
        ButterKnife.bind(this);
        initialCalendar = new InitalCalendar();
        initialCalendar.initView(null, f);
    }

    @Override
    public void onBuildActivityCreated() {
        initialCalendar.initCalendar();
        listenerCalendar = new InitalCalendar.CommInitialCalendar() {
            @Override
            public void resMap(Map m) {
                String dateStr = (String) m.get("data1");
                String schedule = (String) m.get("data2");
                Toast.makeText(f, dateStr, Toast.LENGTH_LONG).show();
            }

            @Override
            public void setData() {

            }

            @Override
            public void hideView() {
                viewBeautyCalendar.setVisibility(View.GONE);
            }

            @Override
            public void showView() {
                viewBeautyCalendar.setVisibility(View.VISIBLE);
            }

            @Override
            public void startAnim(Animation animation) {
                viewBeautyCalendar.startAnimation(animation);
            }
        };
        initialCalendar.setListener(listenerCalendar);
    }

    @Override
    public void onActivityResume() {

    }

    @Override
    public void onActivityPause() {

    }

    @Override
    public void onActivityStop() {

    }

    @Override
    public void onActivityDestroy() {

    }

    @Override
    public void onActivityKeyDown(int keyCode, KeyEvent event) {

    }

    @Override
    public void onActivityFinish() {

    }

    @Override
    public void onActivityRestart() {

    }

    @Override
    public void onActivitySaveInstanceState(Bundle outState) {

    }

    @Override
    public void onActivityRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

    }

    @Override
    public void onActivityMResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void setAnimationOnOpenActivity(Integer firstAnim, Integer secondAnim) {

    }

    @Override
    public void setAnimationOnCloseActivity(Integer firstAnim, Integer secondAnim) {

    }

    @Override
    public View setViewTreeObserverActivity() {
        return null;
    }

    @Override
    public void getViewTreeObserverActivity() {

    }

    @Override
    public Intent setResultIntent() {
        return null;
    }

    @Override
    public String getTagDataIntentFromActivity() {
        return null;
    }

    @Override
    public void getMapDataIntentFromActivity(MapDataParcelable parcleable) {

    }

    @Override
    public MapDataParcelable setMapDataIntentToNextActivity(MapDataParcelable parcleable) {
        return null;
    }
}
