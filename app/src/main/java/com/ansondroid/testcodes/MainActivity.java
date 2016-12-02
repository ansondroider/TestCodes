package com.ansondroid.testcodes;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.TextView;

import com.anson.acode.ALog;
import com.caration.encryption.CREncryption;

import java.lang.ref.WeakReference;

import testcodes.assets.AssetsTest;
import testcodes.base.ByteTest;
import testcodes.base.ForText;
import testcodes.base.SocketTest;
import testcodes.base.StringTest;
import testcodes.base.ThreadTest;
import testcodes.font.FontTest;
import testcodes.intents.IntentData;
import testcodes.jnitest.JNICaller;
import testcodes.json.HHSWeather;
import testcodes.message.HandlerTest;
import testcodes.time.TimeTest;
import testcodes.views.TESTView;


public class MainActivity extends Activity {

    String TAG = "MainActivity";
    H h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ALog.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        h = new H(this);
        setContentView(R.layout.activity_main);
        test();
        //intentService = new Intent(this, SimpleBindService.class);
        //startService(intentService);
        //bindService(intentService, conn, BIND_AUTO_CREATE);
    }



    void test(){

        //AssetsTest.playAssetsVideo(MainActivity.this);
        //FontTest.testSetFamily(this);
        //TESTView tv = new TESTView(this);
        new Thread(){
            @Override
            public void run() {
                try {
                    ALog.d("++++++++++++++++++++ Test start ++++++++++++++++++++");

                    //testFile();
                    //ByteBufferTest.testByteBufferFIFO();
                    //ArrayListTest.testFIFO();
                    //ByteTest.testFIFO();

                    //SystemTest.testArrayCopy();
                    //CREncryption.testEncryption();
                    //ALog.d(StringTest.insertToString("aaabbbcccdddeee", "-ccc-", 3));
                    //HandlerTest.customHandler();
                    //TimeTest.testTimeZone();
                    //String res = "find str at : " + JNICaller.matchString("七九");
                    JNICaller jni = new JNICaller();
                    //jni.getCallerIntField();
                    int result = jni.testInitUri();

                    /*for (int i = 0; i < 10; i++) {
                        SocketTest.testSendUDP("OP_DEL\n" + i + "acd\n/mnt/sdcard/七.pdf\n100730\n20000000000", "192.168.1.112", 8402);
                        Thread.sleep(500);
                    }*/
                    //IntentData.testPrintDataUri();
                    //HHSWeather.testAnaWeather();
                    //TimeTest.testTimeFormat();
                    //StringTest.stringCompare();
                    //ForText.breakFor();
                    //ByteTest.byte2Int();
                    //HHSWeather.testDahongWeather();
                    //ByteTest.main(new String[]{});
                    //ThreadTest.testRestartThread();
                    String res = "sended " + result;
                    h.sendMessageDelayed(h.obtainMessage(0, res), 400);
                    ALog.d("--------------- Test end ------------------------");
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    class H extends Handler{
        WeakReference<MainActivity> ma;
        public H(MainActivity a){
            ma = new WeakReference<MainActivity>(a);
        }

        @Override
        public void handleMessage(Message msg) {
            ((TextView)findViewById(R.id.tv_result)).setText("RESULT:\n" + (String)msg.obj);
        }
    }

/**    Intent intentService = new Intent("com.ansondroider.action.BIND_SERVICE");
    SimpleBindService sbs = null;
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, final IBinder service) {
            sbs = ((SimpleBindService.XBinder)service).getService();
            ALog.d("onServiceConnected _running=" + sbs.isRunning());
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ALog.d("after 5's _running=" + sbs.isRunning());
                }
            }, 5000);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            sbs = null;
        }
    };
 **/

}
