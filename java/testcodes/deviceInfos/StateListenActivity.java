package testcodes.deviceInfos;

import android.app.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ansondroid.testcodes.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by anson on 17-9-20.
 * listener WIFI / MOBILE /ETHERNET state.
 */

public class StateListenActivity extends Activity implements View.OnClickListener{
    TextView tvInfo;
    ScrollView sv;
    boolean isListening;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_listen);
        tvInfo = (TextView)findViewById(R.id.tvInfo);
        sv = (ScrollView)findViewById(R.id.sv);
        findViewById(R.id.btStart).setOnClickListener(this);
        findViewById(R.id.btStop).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btStart:
                startListen();
                break;
            case R.id.btStop:
                stopListen();
                break;
        }
    }

    void startListen(){
        isListening = true;
        //WIFI
        IntentFilter filter = new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiReceiver, filter);

        //Ethernet
        final String ETHERNET_STATE_CHANGED_ACTION = "android.net.ethernet.ETHERNET_STATE_CHANGED";
        filter = new IntentFilter(ETHERNET_STATE_CHANGED_ACTION);
        registerReceiver(ethernetReceiver, filter);

        //Mobile
        final String ACTION_DATA_CONNECTION_CONNECTED_TO_PROVISIONING_APN
                = "android.intent.action.DATA_CONNECTION_CONNECTED_TO_PROVISIONING_APN";
        filter = new IntentFilter(ACTION_DATA_CONNECTION_CONNECTED_TO_PROVISIONING_APN);
        registerReceiver(mobileReceiver, filter);
    }

    void stopListen(){
        if(!isListening)return;
        isListening = false;
        unregisterReceiver(wifiReceiver);
        unregisterReceiver(ethernetReceiver);
        unregisterReceiver(mobileReceiver);
    }

    @Override
    protected void onDestroy() {
        stopListen();
        super.onDestroy();
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            appendLog(action);
            if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)) {
                int state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                        WifiManager.WIFI_STATE_UNKNOWN);
            }
        }
    };
    BroadcastReceiver ethernetReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            appendLog(action);
            final String EXTRA_ETHERNET_STATE = "ethernet_state";
            final int ETHER_STATE_DISCONNECTED = 0;
            final int ETHER_STATE_CONNECTING = 1;
            final int ETHER_STATE_CONNECTED = 2;
            int EtherState=intent.getIntExtra(EXTRA_ETHERNET_STATE, -1);
        }
    };

    BroadcastReceiver mobileReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            appendLog(action);
        }
    };

    void appendLog(String log){
        tvInfo.append(log + "\n");
        Log.d("StateListenActivity", "ALog " + log);
        isNetworkEnable();
        //findViewById(R.id.tail).requestFocus();
        sv.fullScroll(ScrollView.FOCUS_DOWN);
    }

    public void isNetworkEnable(){
        EthernetMgr ethMgr = new EthernetMgr(this);
        MobileDataMgr mobMgr = new MobileDataMgr(this);
        WifiManager wifiMgr = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        boolean ethEnable = ethMgr.isEnabled();
        boolean wifiEnable = wifiMgr.isWifiEnabled();
        boolean mobileEnable = mobMgr.isEnabled();
        NetworkInfo ni = ((ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        Log.d("StateListenActivity",
                "ALog " + "ETHE[" + ethEnable + "], WIFI[" + wifiEnable + "], MOBI[" + mobileEnable + "], ACTI["  + (ni == null ? -1 : ni.getTypeName()) + "]");
        tvInfo.append("ETHE[" + ethEnable + "], WIFI[" + wifiEnable + "], MOBI[" + mobileEnable + "]\n");
    }

    private class MobileDataMgr{
        List<SubscriptionInfo> subInfos;
        TelephonyManager telMgr;
        public MobileDataMgr(Context ctx){
            telMgr = (TelephonyManager)ctx.getSystemService(TELEPHONY_SERVICE);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                SubscriptionManager subMgr = SubscriptionManager.from(ctx);
                subInfos = subMgr.getActiveSubscriptionInfoList();
            }
        }

        boolean isEnabled(){
            if(subInfos != null){
                for(SubscriptionInfo si : subInfos){
                    if(isEnabled(si))return true;
                }
            }
            return false;
        }

        private boolean isEnabled(SubscriptionInfo si){
            try {
                Method getDataEnabled = TelephonyManager.class.getDeclaredMethod("getDataEnabled", Integer.TYPE);
                if(null != getDataEnabled){
                    Boolean b = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                        b = (Boolean)getDataEnabled.invoke(telMgr, si.getSubscriptionId());
                    }
                    return b;
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return false;
        }
    }
    private class EthernetMgr{
        Object mgr;
        EthernetMgr(Context ctx){
            mgr = ctx.getSystemService("ethernet");
        }

        boolean isEnabled(){
            try {
                Class EthernetManager = Class.forName("android.net.EthernetManager");
                if(EthernetManager != null){
                    Method getEthernetIfaceState = EthernetManager.getDeclaredMethod("getEthernetIfaceState");
                    if(getEthernetIfaceState != null){
                        Integer state = (Integer)getEthernetIfaceState.invoke(mgr);
                        return state == 1;
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return false;
        }

    }

}
