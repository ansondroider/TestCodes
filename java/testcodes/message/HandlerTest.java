package testcodes.message;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import com.anson.acode.ALog;

/**
 * Created by anson on 15-10-2.
 */
public class HandlerTest {
    public static final String TAG = "HandlerTest";
    public static void customHandler(){
        HandlerThread ht = new HandlerThread("test-0", Thread.MIN_PRIORITY);
        ht.start();
        final Handler h = new Handler(ht.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                switch(msg.what){
                    case 0:
                        ALog.d(TAG, "msg:0");
                        break;
                    case 1:
                        ALog.d(TAG, "msg:1");
                        break;
                }
            }
        };

        new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < 10; i ++){
                    h.sendEmptyMessage(i % 2);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }


}
