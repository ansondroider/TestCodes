package testcodes.intents;

import android.content.Intent;
import android.net.Uri;

import com.anson.acode.ALog;

import java.io.File;

/**
 * Created by anson on 15-12-5.
 * test for intent
 */
public class IntentData {
    public static final String TAG = "IntentData";
    public static void testPrintDataUri(){
        Intent intent;
        intent = new Intent();
        intent.setData(Uri.fromFile(new File("/mnt/sdcard/test.apk")));

        Uri uri = intent.getData();
        ALog.d(TAG, "uri.getPath() = " + uri.getPath());
    }
}
