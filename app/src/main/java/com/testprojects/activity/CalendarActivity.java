package com.testprojects.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.TextClock;
import android.widget.TimePicker;

import com.testprojects.R;
import com.testprojects.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by she on 2017/6/22.
 * 日历
 */

public class CalendarActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_calendar;
    }

    @Override
    protected void initView() {
        /**
         * 继承自 TextView  可以设置TextSize改变大小 等等
         */
        Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer2);
        TextClock textClock = (TextClock) findViewById(R.id.textClock);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //倒计时 值
            //            chronometer.setCountDown(true);
            chronometer.setCountDown(false);
        }

        chronometer.setBase(SystemClock.elapsedRealtime());
        findViewById(R.id.start).setOnClickListener(view ->
                chronometer.start()
        );
        findViewById(R.id.stop).setOnClickListener(view ->
                chronometer.stop()
        );
        findViewById(R.id.reStart).setOnClickListener(view ->
                {
                    chronometer.stop();
                    chronometer.setBase(SystemClock.elapsedRealtime());
                }
        );
        chronometer.setOnChronometerTickListener(chronometer1 ->
                {
                    // 获取到当前的值
                    Log.e(TAG, chronometer1.getText().toString());
                }
        );

        Log.e(TAG, "textClock---" + textClock.is24HourModeEnabled());
        //24小时显示
        timePicker.setIs24HourView(false);

        //经过测试  timePicker.getHour() 和 timePicker.setIs24HourView(false);
        //这个没有关系 一直是24小时显示
        if (timePicker.is24HourView()) {
            Log.e(TAG, timePicker.getHour() - 12 + "--hour--" + timePicker.getMinute());
        } else {
            Log.e(TAG, timePicker.getHour() + "--hour--" + timePicker.getMinute());
        }


        // 注意 Android系统的month 从0开始
        // 星期天是一个周的第一天

        Log.e(TAG, "getDayOfMonth: " + datePicker.getDayOfMonth()
                + "--getMonth--" + (datePicker.getMonth() + 1)
                + "--getYear--" + datePicker.getYear());


        //日历Util类
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH);
        //Calendar.WEEK_OF_MONTH 一个月的第几周
        int week = instance.get(Calendar.WEEK_OF_MONTH);
        // WEEK_OF_YEAR 一年中的第几周
        //        int week = instance.get(Calendar.WEEK_OF_YEAR);
        //一个周中的第几天
        //        int day = instance.get(Calendar.DAY_OF_WEEK);
        //一个月中的第几天
        int day = instance.get(Calendar.DAY_OF_MONTH);
        //属于一个月 第几周
        //        int week_in_month = instance.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        //一年中的第几天
        //        int day_of_year = instance.get(Calendar.DAY_OF_YEAR);
        Log.e(TAG, "year: " + year
                + "--month--" + month
                + "--week--" + week
                + "--day--" + day);

        //日期类
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日hh:mm:ss");
        long date = calendarView.getDate();
        String dataTime = f.format(date);

        Log.e(TAG, "--当前日期--" + dataTime);
    }
}
