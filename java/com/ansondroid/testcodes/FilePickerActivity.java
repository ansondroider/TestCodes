package com.ansondroid.testcodes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anson.acode.PreferenceUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by anson on 17-10-8.
 * to get File by this file picker.
 * send intent:
 * android.intent.action.FileManager.PICK
 * scheme with file type.
 */

public class FilePickerActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{
    final String TAG = "FilePickerActivity";
    public static final String ACTION = "android.intent.action.FileManager.PICK";
    final String ROOT = "/mnt";
    final String KEY_lastPath = "lastPath";
    boolean showWithGrid = true;
    File curPath;
    File[] fs;
    HorizontalScrollView hsvRoot;
    LinearLayout llPath;
    AbsListView listView;
    FileAdapter adapter;
    ViewStub vsList;
    LayoutInflater inflater;
    float dp;
    H h;
    static class H extends Handler {
        static final int MSG_START_LOAD = 0;
        static final int MSG_LOAD_COMP = 1;
        WeakReference<FilePickerActivity> a;
        H(FilePickerActivity a){
            this.a = new WeakReference<FilePickerActivity>(a);
        }

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            if(what == MSG_START_LOAD){
                a.get().updatePath();
            }else if(what == MSG_LOAD_COMP){
                a.get().updateFileList();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_picker);
        inflater = getLayoutInflater();
        dp = getResources().getDisplayMetrics().density;
        h = new H(this);
        //Views
        hsvRoot = (HorizontalScrollView)findViewById(R.id.hsvPath);
        llPath = (LinearLayout)findViewById(R.id.llPath);
        vsList = (ViewStub)findViewById(R.id.vsFiles);

        //init list
        initContainerView();
    }

    void initContainerView(){
        vsList.setLayoutResource(showWithGrid ? R.layout.file_picker_grid : R.layout.file_picker_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(vsList.getVisibility() != View.VISIBLE) {
            adapter = new FileAdapter();
            listView = (AbsListView)vsList.inflate();
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);

            //first in.
            String lastPath = PreferenceUtils.getStringFromDefault(this, KEY_lastPath, ROOT);
            goPath(lastPath);
        }
    }

    void goPath(String path){
        final File f = new File(path);
        if(f.exists() && f.isDirectory()){
            curPath = f;
            //update path;
            h.sendEmptyMessage(H.MSG_START_LOAD);
            new Thread(){
                @Override
                public void run() {
                    fs = f.listFiles();
                    if(fs != null) {
                        sortFiles(fs);
                    }
                    h.sendEmptyMessage(H.MSG_LOAD_COMP);
                }
            }.start();
        }
    }

    void updatePath(){
        String[] folders = curPath.getAbsolutePath().split("/");
        llPath.removeAllViews();
        int len = folders.length;
        String tmpPath = "";
            for(int i = 0; i < len; i ++){
                TextView tv = (TextView)inflater.inflate(R.layout.file_picker_path_item, llPath, false);
                tv.setText((i > 0 ? folders[i] : "") + "/");
                llPath.addView(tv);
                tmpPath += tv.getText().toString();
                tv.setTag(tmpPath);
                tv.setOnClickListener(this);
            }

    }

    void updateFileList(){
        adapter.notifyDataSetChanged();
    }

    void sortFiles(File[] fs){
        final Comparator comp = Collator.getInstance(java.util.Locale.getDefault());
        Arrays.sort(fs, new Comparator<File>() {
            @Override
            public int compare(File lhs, File rhs) {
                if(lhs.isDirectory() && rhs.isFile()){
                    return -1;
                }else if(lhs.isFile() && rhs.isDirectory()){
                    return 1;
                }
                return comp.compare(lhs.getName(), rhs.getName());
            }
        });
    }

    @Override
    public void onClick(View v) {
        //ALog.d(TAG, "onClick");
        //click on path item.
        if(v instanceof TextView){
            String path = (String)v.getTag();
            //ALog.d(TAG, "onClick ->" + path);
            goPath(path);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        File f = fs[position];
        if(f.isFile()){
            setResult(0, new Intent(ACTION, Uri.fromFile(f)));
            finish();
        }

        if(f.isDirectory()){
            goPath(f.getAbsolutePath());
        }
    }

    @Override
    public void onBackPressed() {
        if(curPath.getAbsolutePath().length() > ROOT.length()){
            goPath(curPath.getParent());
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        PreferenceUtils.setStringToDefault(this, KEY_lastPath, curPath.getAbsolutePath());
    }

    //File adapter for ListView and GradView
    private class FileAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return fs == null ? 0 : fs.length;
        }

        @Override
        public Object getItem(int position) {
            return fs == null ? null : fs[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            FileItem item;
            if(convertView != null && convertView.getTag() != null){
                item = (FileItem)convertView.getTag();
            }else{
                item = new FileItem();
                item.tv = (TextView)inflater.inflate(R.layout.file_picker_file_item, parent, false);
            }
            int iconId = fs[position].isFile() ? R.drawable.ic_file : R.drawable.ic_folder;
            item.tv.setGravity(showWithGrid ? Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL : Gravity.CENTER_VERTICAL);
            if(showWithGrid) {
                item.tv.setMinHeight(item.tv.getMinWidth());
                item.tv.setCompoundDrawablesWithIntrinsicBounds(0, iconId, 0, 0);
            }else{
                item.tv.setCompoundDrawablesWithIntrinsicBounds(iconId, 0, 0, 0);
            }
            item.tv.setText(fs[position].getName());

            return item.tv;
        }

        class FileItem {
            TextView tv;
        }
    }
}
