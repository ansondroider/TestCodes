package com.ansondroid.testcodes;

import android.app.Activity;
import android.os.Bundle;

import com.anson.acode.ALog;
import com.caration.encryption.CREncryption;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);
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
                //CREncryption.testEncryption();

                ALog.d("--------------- Test end ------------------------");
            }
        }.start();



    }

}
