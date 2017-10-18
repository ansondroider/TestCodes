package testcodes.sensors;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anson.acode.FileUtils;
import com.ansondroid.testcodes.R;

import java.io.File;

import static com.ansondroid.testcodes.FilePickerActivity.ACTION;


/**
 * Created by anson on 17-10-8.
 * show image and control with sensor.
 */

public class SensorImageViewActivity extends Activity implements View.OnClickListener {
    Button btMode, btPre, btNxt;
    SensorImageView siv;
    int curIdx = 0;
    File[] fs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_image_viewer);
        siv = (SensorImageView)findViewById(R.id.siv);
        btMode = (Button)findViewById(R.id.btMode);
        btMode.setOnClickListener(this);
        btPre = (Button)findViewById(R.id.btPre);
        btPre.setOnClickListener(this);
        btNxt = (Button)findViewById(R.id.btNxt);
        btNxt.setOnClickListener(this);

        if(null != getIntent().getData()){
            SensorImageView siv = (SensorImageView)findViewById(R.id.siv);
            siv.setImageURI(getIntent().getData());
            File f = new File(getIntent().getData().getPath());
            if(f.exists()){
                fs = FileUtils.getImageFiles(f.getParent());
                FileUtils.sortFile(fs);

                for(int i = 0; i < fs.length; i ++){
                    if(fs[i].getName().equals(f.getName())){
                        curIdx = i;
                        break;
                    }
                }
            }
        }else {
            Intent intent = new Intent(ACTION);
            startActivityForResult(intent, 0xf7);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0xf7){
            Uri result = data.getData();
            File f = new File(data.getData().getPath());
            if(f.exists()){
                fs = FileUtils.getImageFiles(f.getParent());
                FileUtils.sortFile(fs);

                for(int i = 0; i < fs.length; i ++){
                    if(fs[i].getName().equals(f.getName())){
                        curIdx = i;
                        break;
                    }
                }
            }
            siv.setImageURI(result);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btMode:
                siv.changeMode();
            break;
            case R.id.btPre:
                if(fs != null && curIdx > 0){
                    curIdx -= 1;
                    siv.setImageURI(Uri.fromFile(fs[curIdx]));
                }
                break;
            case R.id.btNxt:
                if(fs != null && curIdx < fs.length -1){
                    curIdx += 1;
                    siv.setImageURI(Uri.fromFile(fs[curIdx]));
                }
                break;
        }
    }
}
