package com.ansondroid.testcodes;

import android.app.Activity;
import android.os.Bundle;

import com.anson.acode.ALog;
import com.caration.encryption.CREncryption;

import testcodes.assets.AssetsTest;
import testcodes.base.StringTest;
import testcodes.font.FontTest;
import testcodes.message.HandlerTest;
import testcodes.views.TESTView;


public class MainActivity extends Activity {

    String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ALog.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        test();
    }



    void test(){

        //AssetsTest.playAssetsVideo(MainActivity.this);
        //FontTest.testSetFamily(this);
        TESTView tv = new TESTView(this);
        new Thread(){
            @Override
            public void run() {
                ALog.d("++++++++++++++++++++ Test start ++++++++++++++++++++");

                //testFile();
                //ByteBufferTest.testByteBufferFIFO();
                //ArrayListTest.testFIFO();
                //ByteTest.testFIFO();

                //SystemTest.testArrayCopy();
                //CREncryption.testEncryption();
                //ALog.d(StringTest.insertToString("aaabbbcccdddeee", "-ccc-", 3));
                //HandlerTest.customHandler();
                ALog.d("--------------- Test end ------------------------");
            }
        }.start();



    }

}
