package com.ansondroid.testcodes;

import android.app.Activity;
import android.os.Bundle;

import com.anson.acode.ALog;
import com.anson.acode.jni.BitmapBlur;
import com.caration.encryption.CREncryption;

import testcodes.base.ArrayListTest;
import testcodes.base.ByteBufferTest;
import testcodes.base.ByteTest;
import testcodes.base.FileTest;
import testcodes.base.SystemTest;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    void test(){
        new Thread(){
            @Override
            public void run() {
                ALog.d("++++++++++++++++++++ Test start ++++++++++++++++++++");
                //testFile();
                //ByteBufferTest.testByteBufferFIFO();
                //ArrayListTest.testFIFO();
                //ByteTest.testFIFO();

                //SystemTest.testArrayCopy();
                CREncryption cre = new CREncryption();
                ALog.d("isEncryption = " + cre.isEncryption());

                ALog.d("--------------- Test end ------------------------");
            }
        }.start();

    }

}
