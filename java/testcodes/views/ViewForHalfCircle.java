package testcodes.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anson on 15-11-4.
 */
public class ViewForHalfCircle extends View {
    public ViewForHalfCircle(Context context) {
        super(context);
        initPaint();
    }

    public ViewForHalfCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    public ViewForHalfCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    Paint p, pp;
    float startAngle;
    float sweepAngle;
    float paintSize = 40f;
    float maxSweepAngle = 120;
    RectF area;
    void initPaint(){
        p = new Paint();
        p.setColor(Color.CYAN);
        p.setStrokeWidth(paintSize);
        p.setStyle(Paint.Style.STROKE);
        p.setAntiAlias(true);
        Shader mShader = new SweepGradient(100, 100, new int[]{Color.RED, Color.GREEN, Color.RED},
                null);
        //new RadialGradient(100, 100, 100, new int[]{Color.GREEN, Color.RED}, null, Shader.TileMode.REPEAT);
        p.setShader(mShader);

        pp = new Paint();
        pp.setColor(Color.RED);
        pp.setStrokeWidth(paintSize);
        pp.setStyle(Paint.Style.STROKE);
        pp.setAntiAlias(true);

        startAngle = 180;
        sweepAngle = 90;
        area = new RectF(0, 0, 200, 200);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        sweepAngle = event.getY();
        postInvalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawArc(area, startAngle, Math.min(maxSweepAngle, sweepAngle), false, p);

        if(sweepAngle > maxSweepAngle) canvas.drawArc(area, maxSweepAngle  + 180, sweepAngle - maxSweepAngle, false, pp);

        canvas.drawArc(area, startAngle, sweepAngle, false, p);
    }
}
