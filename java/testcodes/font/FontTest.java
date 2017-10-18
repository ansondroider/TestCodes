package testcodes.font;

import android.app.Activity;
import android.graphics.Typeface;
import android.widget.TextView;

import com.ansondroid.testcodes.R;

/**
 * Created by anson on 15-9-26.
 */
public class FontTest {
    public static void testSetFamily(Activity a){
        a.setContentView(R.layout.activity_font);
        TextView tv = (TextView)a.findViewById(R.id.tv);
        tv.setTextSize(12);
        Typeface tf = Typeface.createFromAsset(a.getAssets(), "Roboto-Bold.ttf");
        tv.setTypeface(tf);
    }
}
