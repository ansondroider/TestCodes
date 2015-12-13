package com.ansondroid.testcodes;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.anson.acode.ALog;

import java.text.SimpleDateFormat;

import testcodes.ISimpleService;

/**
 * Created by anson on 15-11-4.
 */
public class SimpleBindService extends Service {
    XBinder mBinder = null;
    XXBinder mXBinder = null;
    String TAG = "SimpleBindService";
    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new XBinder();
        mXBinder = new XXBinder();
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
        ///return mBinder;
        return iservice;
    }

    class XBinder extends Binder {
        public SimpleBindService getService(){
            return SimpleBindService.this;
        }
    }

    class XXBinder extends Binder {
        public ISimpleService.Stub getService(){
            return iservice;
        }
    }

    ISimpleService.Stub iservice = new ISimpleService.Stub() {
        @Override
        public String getName() throws RemoteException {
            return this.toString();
        }
    };
}
