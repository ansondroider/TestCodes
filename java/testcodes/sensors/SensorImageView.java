package testcodes.sensors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;


import com.anson.acode.ALog;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;

/**
 * Created by anson on 17-10-8.
 * control with sensor.
 */

public class SensorImageView extends ImageView {
    final String TAG = "SensorImageView";

    /** Constructor **/
    public SensorImageView(Context context) {
        super(context);
        h = new H(this);
    }

    public SensorImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        h = new H(this);
    }

    public SensorImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        h = new H(this);
    }

    /** Handler **/
    H h;
    static final class H extends Handler {
        static final int MSG_NEW_STATE = 0;
        WeakReference<SensorImageView> siv;
        H(SensorImageView s){
            siv = new WeakReference<SensorImageView>(s);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_NEW_STATE:
                    siv.get().move();
                    break;
            }
        }
    }

    /** Sensor **/
    private SensorManager sensorManager;
    SensorManager getSensorManager(){
        if(sensorManager == null){
            sensorManager = (SensorManager)getContext().getSystemService(Context.SENSOR_SERVICE);
        }
        return sensorManager;
    }
    int samplingTime = 1000 * 10;
    void startListenSensor(){
        SensorManager sMgr = getSensorManager();
        sMgr.registerListener(oriSenListener, sMgr.getDefaultSensor(Sensor.TYPE_ORIENTATION), samplingTime);
    }

    void stopListenSensor(){
        SensorManager sMgr = getSensorManager();
        sMgr.unregisterListener(oriSenListener);
    }

    /** on state changed **/
    float[] geoMagnetic = new float[3];
    float[] gravity = new float[3];
    float[] curVals = new float[3];
    float[] lastVals = new float[3];
    SensorEventListener oriSenListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            ALog.d(TAG, "onSensorChanged type(" + event.sensor.getName() + ")");
            if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                gravity = event.values;
            }else if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                geoMagnetic = event.values;
            }else if(event.sensor.getType() == Sensor.TYPE_ORIENTATION) {

                lastVals[0] = curVals[0];
                lastVals[1] = curVals[1];
                lastVals[2] = curVals[2];

                curVals[0] = event.values[0];
                curVals[1] = event.values[1];
                curVals[2] = event.values[2];

                applyNewState();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    /** new state available **/
    float tarTx, tarTy, tarRo;
    void applyNewState(){
        if(bm == null)return;
        float o0 = curVals[0] - lastVals[0];
        o0 %= 360f;
        float o1 = curVals[1] - lastVals[1];
        float o2 = curVals[2] - lastVals[2];
        o0 /= 60f;
        o1 /= 35f;
        float dis = Math.max(modeInfo[idxTx], modeInfo[idxTy]);
        tarTx = -o0 * dis;
        tarTy = -o1 * dis;
        tarRo = o2;
        h.sendEmptyMessage(H.MSG_NEW_STATE);
    }

    /** update image matrix**/
    void move(){
        matrix = getImageMatrix();
        updateValues(matrix);
        matrix.postRotate(tarRo, getWidth()/2, getHeight()/2);
        matrix.postTranslate(tarTx, tarTy);
        setImageMatrix(matrix);
        postInvalidate();
    }

    /** save matrix value to float array[9]**/
    float[] mtxVal = new float[9];
    void updateValues(Matrix mtx){
        mtx.getValues(mtxVal);
    }



    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startListenSensor();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopListenSensor();
    }

    /** Bitmap info**/
    Bitmap bm = null;
    int bw, bh;
    Matrix matrix;
    void updateBitmap(){
        Drawable d = getDrawable();
        if(d != null){
            try{
                BitmapDrawable bd = (BitmapDrawable)d;
                bm = bd.getBitmap();
                bw = bm.getWidth();
                bh = bm.getHeight();
                calcScaleModes();
            }catch(Exception e){e.printStackTrace();}
        }
    }

    float[] scaleModes = {1f, 1f, 1f};
    float[] modeInfo = new float[6];
    final int idxBw = 0;
    final int idxBh = 1;
    final int idxTx = 2;
    final int idxTy = 3;
    int mode = 0;
    void calcScaleModes(){
        int vw = getWidth();
        int vh = getHeight();
        if(vw > 0 && vh > 0){
            float sx = vw / (float)bw;
            float sy = vh / (float)bh;
            scaleModes[1] = Math.min(sx, sy);
            scaleModes[2] = (sx + sy);
            //ALog.d(TAG, "Bitmap[" + bw + ", " + bh + "]\n" + "View[" + vw + "," + vh + "]\n" +
            //        "Scale[" + sx + "," + sy + "]");
            changeScaleMode(mode);
        }else{
            //do 200 ms later.
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    calcScaleModes();
                }
            }, 200);
        }
    }

    void changeScaleMode(int mode){
        matrix = getImageMatrix();
        //matrix.reset();
        float scale = scaleModes[mode];
        int vw = getWidth();
        int vh = getHeight();
        int bmW = (int)(bw * scale);
        int bmH = (int)(bh * scale);

        int tx = (vw - bmW) >> 1;
        int ty = (vh - bmH) >> 1;
        matrix.setScale(scale, scale);

        matrix.postTranslate(tx, ty);
        updateValues(matrix);
        setImageMatrix(matrix);
        postInvalidate();
        modeInfo[idxBw] = bmW;
        modeInfo[idxBh] = bmH;
        modeInfo[idxTx] = bmW - vw;
        modeInfo[idxTy] = bmH - vh;
    }

    /** set Image to ImageView **/
    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        updateBitmap();
    }

    @Override
    public void setImageResource(int resId) {
        setImageBitmap(BitmapFactory.decodeResource(getResources(), resId));
        updateBitmap();
    }

    @Override
    public void setImageURI(Uri uri) {
        setImageBitmap(BitmapFactory.decodeFile(uri.getPath()));
        updateBitmap();
    }

    /** input event handle **/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            changeScaleMode(mode);
        }
        return true;
    }

    public void changeMode(){
        mode += 1;
        if(mode >= scaleModes.length){
            mode = 0;
        }
        changeScaleMode(mode);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawXYZ(canvas);
    }

    /** for Debug **/
    boolean Dvals = false;
    Paint p = new Paint();
    DecimalFormat dFormat = new DecimalFormat("#.##");
    void drawXYZ(Canvas c){
        if(!Dvals || curVals == null)return;
        p.setTextSize(32);
        p.setStyle(Paint.Style.FILL);
        p.setColor(0x66000000);
        c.drawRect(0, 0, 400, 400, p);

        p.setColor(Color.RED);
        c.drawText("[0]__" +/* dFormat.format(Math.toDegrees(*/curVals[0]/*))*/, 100, 100, p);

        p.setColor(Color.GREEN);
        c.drawText("[1]__" + dFormat.format(curVals[1]), 100, 140, p);

        p.setColor(Color.CYAN);
        c.drawText("[2]__" + dFormat.format(curVals[2]), 100, 180, p);

        p.setColor(Color.YELLOW);
        c.drawText("curScale( " + dFormat.format(scaleModes[mode]) + " )", 100, 220, p);
    }
}
