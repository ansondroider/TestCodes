package testcodes.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anson on 15-10-3.
 */
public class WaveView extends View {
    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init();
    }

    public WaveView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    Paint paint, paint2;
    Paint paintLine;
    Path wavePath, wavePath2;
    void init(){
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        //paint.setStyle(Style.STROKE);
        wavePath = new Path();
        wavePath.moveTo(0, 0);

        paint2 = new Paint();
        paint2.setColor(Color.BLUE);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.STROKE);

        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setStrokeWidth(1);
        paintLine.setColor(Color.RED);

        wavePath2 = new Path();
        wavePath2.moveTo(0, 0);
    }

    int width, height;
    int HW, HH;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        HW = width >> 1;
        HH = height >> 1;

    }

    float x = 0, y = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gesture.onTouchEvent(event);
        // TODO Auto-generated method stub
        //switch(event.getAction()){
        //case MotionEvent.ACTION_UP:
			/*float x2 = event.getX();
			float y2 = event.getY();
			float fx = (x2 + x1)/2;
			float fy = (y2 + y1)/2;
			wavePath.quadTo(x1, y1, fx, fy);
			x1 = x2;
			y1 = y2;
			//wavePath.moveTo(x2, y2);*/
        float x2 = x = event.getX();
        float y2 = y = event.getY();
        changePath(x2, y2);
        invalidate();
        //break;
        //}
        return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        canvas.drawPath(wavePath, paint);

        canvas.drawPath(wavePath2, paint2);

        canvas.drawLine(0, HH, width, HH, paintLine);
        canvas.drawLine(HW, 0, HW, height, paintLine);

        canvas.drawText((int)x + ", " + (int)y, x, y, paintLine);
    }

    int time = 0;
    void changePath(float x2, float y2){

        wavePath.reset();
        wavePath.moveTo(0, height);
        wavePath.lineTo(0, HH);
/*
        //draw wave
        float wave = y2*2 - HH;
        float wave2 = HH + (HH - y2)*2;
        //quadTo(w, h, x, y)
        wavePath.quadTo(HW/2, 	wave, 		HW, 	HH);
        wavePath.quadTo(HW*3/2,	wave2,		width, 	HH);
*/
        float yh = HH + (Math.abs(HH - y2) * (y2 > HH ? 2 : -2));
        wavePath.quadTo(x2, yh, HW, HH);


        //end area
        wavePath.lineTo(width, height);

        wavePath2.reset();
        wavePath2.moveTo(x2, HH);
        //wavePath2.lineTo(0, HH);
        float wave22 = y2*2 - HH;
        float wave222 = HH + (HH - y2)*2;
        wavePath2.quadTo(HW/2+x2, 	    wave22, 		HW+x2, 	    HH);

        wavePath2.quadTo(HW*3/2 + x2,	wave222,		width+x2, 	HH);

        //wavePath2.lineTo(width, height);
    }

    android.view.GestureDetector gesture = new android.view.GestureDetector(new android.view.GestureDetector.OnGestureListener() {
        @Override
        public boolean onSingleTapUp(MotionEvent arg0) {return false;}
        @Override
        public void onShowPress(MotionEvent arg0) {}

        @Override
        public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
                                float arg3) {return false;}
        @Override
        public void onLongPress(MotionEvent arg0) {}
        @Override
        public boolean onFling(MotionEvent arg0, MotionEvent arg1, float rx,
                               float ry) {
            // TODO Auto-generated method stub
            android.util.Log.d("onFling", "onFling rx = " + rx + ", ry = " + ry);
            return false;
        }
        @Override
        public boolean onDown(MotionEvent arg0) {return false;}
    });

}
