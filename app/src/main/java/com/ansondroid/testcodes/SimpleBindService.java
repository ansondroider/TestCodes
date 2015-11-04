package com.ansondroid.testcodes;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.anson.acode.ALog;

import java.text.SimpleDateFormat;

/**
 * Created by anson on 15-11-4.
 */
public class SimpleBindService extends Service {
    XBinder mBinder = null;
    String TAG = "SimpleBindService";
    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new XBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ALog.d(TAG, "onStartCommand");
        new Thread(){
            public void run() {
                try {
                    sleep(3000);
                    running = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    boolean running = false;
    public boolean isRunning(){
        return running;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class XBinder extends Binder {
        public SimpleBindService getService(){
            return SimpleBindService.this;
        }
    }
}
