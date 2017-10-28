package testcodes.systemManagers;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.anson.acode.ALog;
import com.ansondroid.testcodes.R;

/**
 * Created by anson on 17-10-28.
 * for test package manager
 */

public class PackageManagerActivity extends Activity {
    final String TAG = "PackageManagerActivity";
    PackageManager pm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkm);
        pm = getPackageManager();
        findViewById(R.id.btGetSign).setOnClickListener(clickLis);
    }

    View.OnClickListener clickLis = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btGetSign:
                    getSystemSign(((EditText)findViewById(R.id.etPkg)).getText().toString());
                    break;
            }
        }
    };

    void getSystemSign(String pkg){
        try {
            PackageInfo pi = pm.getPackageInfo(pkg, PackageManager.GET_SIGNATURES);
            if(pi != null){
                Signature[] sigs = pi.signatures;
                if(sigs != null) {
                    for (Signature s : sigs) {
                        ALog.d(TAG, s.toCharsString());
                    }
                }else{
                    ALog.w(TAG, "signature was EMPTY");
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
