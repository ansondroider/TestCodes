package testcodes.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.ansondroid.testcodes.R;

/**
 * Created by anson on 17-10-18.
 * test ViewPager
 */

public class ViewPagerActivity extends Activity {
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPager = new ViewPager(this);
        setContentView(viewPager);
    }
}
