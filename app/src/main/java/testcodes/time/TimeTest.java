package testcodes.time;

import com.anson.acode.ALog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by anson on 15-10-10.
 */
public class TimeTest {
    public static final String TAG = "TimeTest";
    public static void testTimeZone(){
        TimeZone tz = Calendar.getInstance().getTimeZone();
        SimpleDateFormat sdf = new SimpleDateFormat("ZZZZ");
        sdf.setTimeZone(tz);

        ALog.d(TAG, "testTimeZone:" + sdf.format(new Date()) + ", " + tz.getID());
    }
}
