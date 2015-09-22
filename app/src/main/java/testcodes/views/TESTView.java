package testcodes.views;

import android.app.Activity;
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
        a.setContentView(R.layout.activity_main);
    }

    void testCode(){
        testSpinner();
    }

    void testSpinner(){
        Spinner spinner = (Spinner)a.findViewById(R.id.spinner);
        String array[] = {"one", "two", "three", "four"};
        XArrayAdapter<String> adapter = new XArrayAdapter<String>(a, android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
    }

}
