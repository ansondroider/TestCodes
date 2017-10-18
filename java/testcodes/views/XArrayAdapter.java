package testcodes.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.anson.acode.ALog;


/**
 * com.ansondroider.yhtestcodes.view
 * Created by anson on 15-9-21.
 * make couldn't select items expect 1
 */
public class XArrayAdapter<T> extends ArrayAdapter<T> {

    public XArrayAdapter(Context context, int resource, T[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    View.OnClickListener oriListener;
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = super.getDropDownView(position, convertView, parent);
        v.setTag(position);
        //v.setFocusable(pos == 1);
        //v.setEnabled(pos == 1);
        ALog.d(v.getRootView().toString());
        v.setFocusable(position != 1);
        v.setActivated(position == 1);
        v.setEnabled(position == 1);
        /*v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int p = (int) v.getTag();
                return p == 1;
            }
        });*/

        return v;
    }
}
