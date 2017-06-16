package com.testprojects.utils;

import android.content.Context;
import android.content.MutableContextWrapper;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by she on 2017/6/16.
 * 摇一摇 工具类
 */

public class ShakeUtil implements SensorEventListener {

    private String TAG = "ShakeUtil";
    // 速度阀值 当摇晃速度达到这个值后产生作用
    private final static int SPEED_SHRESHOLD = 300;
    // 俩次检测的时间间隔
    private final static int UPTATE_INTERVAL_TIME = 70;
    // 传感器管理器
    private SensorManager sensorManager;
    // 传感器
    private Sensor sensor;
    // 重力感应监听器
    private onShakeListener onShakeListener;
    // 上下文
    private Context mContext;
    // 手机上一个位置 重力感应坐标
    private float lastX, lastY, lastZ;
    // 上次检测时间
    private long lastUpdateTime;

    // 构造器
    public ShakeUtil(Context context) {
        // 获得监听对象
        mContext = context;
        start();
    }

    // 开始
    public void start() {
        //获得传感器管理器
        sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        if (null != sensorManager) {
            //获得重力传感器
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        if (null != sensor) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    //停止检测
    public void stop() {
        if (null != sensorManager) {
            sensorManager.unregisterListener(this);
        }
    }

    // 设置重力感应监听器
    public void setOnShakeListener(onShakeListener shakeListener) {
        onShakeListener = shakeListener;
    }

    //重力传感器感应数据变化
    @Override
    public void onSensorChanged(SensorEvent event) {
        // 现在检测时间
        long currentUpdateTime = System.currentTimeMillis();
        // 两次检测的时间间隔
        long timeInterval = currentUpdateTime - lastUpdateTime;
        // 判断是否达到了检测时间间隔
        if (timeInterval < UPTATE_INTERVAL_TIME) {
            return;
        }
        // 更改时间
        lastUpdateTime = currentUpdateTime;
        // 获得坐标
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // 获得x,y,z的变化值
        float deltaX = x - lastX;
        float deltaY = y - lastY;
        float deltaZ = z - lastZ;

        //将现在的坐标变成last的坐标
        lastX = x;
        lastY = y;
        lastZ = z;

        double speed = (Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ) / timeInterval) * 1000;
        // 达到速度阀值 发出提示
        if (speed > SPEED_SHRESHOLD) {
            onShakeListener.onShake();
        }
    }

    //摇晃接口
    public interface onShakeListener {
        void onShake();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
