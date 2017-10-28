package com.ansondroid.testcodes;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.anson.acode.ALog;
import com.caration.encryption.CREncryption;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import testcodes.assets.AssetsTest;
import testcodes.base.ByteTest;
import testcodes.base.ForText;
import testcodes.base.SocketTest;
import testcodes.base.StringTest;
import testcodes.base.ThreadTest;
import testcodes.deviceInfos.StateListenActivity;
import testcodes.font.FontTest;
import testcodes.intents.IntentData;
import testcodes.jnitest.JNICaller;
import testcodes.json.HHSWeather;
import testcodes.message.HandlerTest;
import testcodes.sensors.SensorImageViewActivity;
import testcodes.systemManagers.PackageManagerActivity;
import testcodes.time.TimeTest;
import testcodes.views.OverScrollBy;
import testcodes.views.TESTView;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    String TAG = "MainActivity";
    ListView lv;
    SimpleAdapter adapter;
    LayoutInflater inflater;
    H h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ALog.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        h = new H(this);
        inflater = getLayoutInflater();
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.lv);
        initActivities();
    }

    void initActivities(){
        adapter = new SimpleAdapter();
        adapter.addActivity(FilePickerActivity.class);
        adapter.addActivity(SensorImageViewActivity.class);
        adapter.addActivity(StateListenActivity.class);
        adapter.addActivity(PackageManagerActivity.class);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }


    static class H extends Handler{
        WeakReference<MainActivity> ma;
        H(MainActivity a){
            ma = new WeakReference<MainActivity>(a);
        }

        @Override
        public void handleMessage(Message msg) {
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, (Class)adapter.getItem(i));
        startActivity(intent);
    }

    class SimpleAdapter extends BaseAdapter {
        List<Class> activitys = new ArrayList<Class>();
        public void addActivity(Class cls){
            activitys.add(cls);
            notifyDataSetChanged();
        }
        @Override
        public int getCount() {
            return activitys.size();
        }

        @Override
        public Object getItem(int i) {
            return activitys.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Item item = null;
            if(view != null && view.getTag() != null){
                item = (Item)view.getTag();
            }else{
                item = new Item();
                view = inflater.inflate(R.layout.main_lv_item, viewGroup, false);
                item.tv = (TextView)view.findViewById(R.id.tv);
                view.setTag(item);
            }

            Class cls = activitys.get(i);
            item.tv.setText(cls.getSimpleName());
            return view;
        }
    }

    class Item{
        TextView tv;
    }

}
