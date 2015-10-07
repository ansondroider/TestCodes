package testcodes.views;

import android.app.Activity;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.ansondroid.testcodes.R;

/**
 * testcodes.views
 * Created by anson on 15-9-22.
 */
public class TESTView {
    Activity a;
    public TESTView(Activity a){
        this.a = a;
        //a.setContentView(R.layout.activity_main);
        //a.setContentView(R.layout.activity_waveview);
        testCode();
    }

    void testCode(){
        testXFormat();
        //testSpinner();
    }

    void testXFormat(){
        a.setContentView(R.layout.activity_xformat);
        final XFormatView xfView = (XFormatView)a.findViewById(R.id.xfv);
        SeekBar sb = (SeekBar)a.findViewById(R.id.sb_mode);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                xfView.updateXFormat(progress-1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    void testSpinner(){
        Spinner spinner = (Spinner)a.findViewById(R.id.spinner);
        String array[] = {"one", "two", "three", "four"};
        XArrayAdapter<String> adapter = new XArrayAdapter<String>(a, android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
    }

}
