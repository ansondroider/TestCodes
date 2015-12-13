package com.yhnavi.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.app.Activity;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import testcodes.ISimpleService;

/**
 * if NEED acode:
 * addCode to Settings.gradle
 * include ':acode'
 * project(':acode').projectDir = new File('/home/anson/StudioProjects/acode/app');
 * addCode to gradle.properties
 * android.useDeprecatedNdk=true
 **/
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        //<action android:name="com.ansondroider.action.BIND_SERVICE"/>
        intent.setAction("com.ansondroider.action.BIND_SERVICE");
        //intent.setPackage("com.ansondroid.testcodes");
        //intent.setClassName("com.ansondroid.testcodes", "com.ansondroid.testcodes.SimpleBindService");
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    ISimpleService iservice;
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iservice = ISimpleService.Stub.asInterface(service);
            try {
                Log.d("ServiceConnection", "ALog > " + iservice.getName());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
