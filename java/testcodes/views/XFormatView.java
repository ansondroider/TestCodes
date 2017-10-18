package testcodes.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import com.ansondroid.testcodes.R;

/**
 * Created by anson on 15-10-4.
 */
public class XFormatView extends View {
    public XFormatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public XFormatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XFormatView(Context context) {
        super(context);
        init();
    }

    Paint paint0;
    Paint paintBorder;
    Bitmap bm;
    float borderWidth = 8;
    int W = 400;
    int H = 400;
    float round = 72;
    RectF border;
    void init(){
        paint0 = new Paint();
        paint0.setStrokeWidth(1);
        paint0.setColor(Color.WHITE);
        paint0.setAntiAlias(true);

        paintBorder = new Paint();
        paintBorder.setStrokeWidth(borderWidth);
        paintBorder.setColor(Color.WHITE);
        paintBorder.setStyle(Paint.Style.STROKE);
        paintBorder.setAntiAlias(true);

        border = new RectF(borderWidth/2, borderWidth/2, W - borderWidth/2, H - borderWidth/2);

        bm = BitmapFactory.decodeResource(getResources(), R.drawable.sample);
        bm = bm.createScaledBitmap(bm, W, H, false);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.WHITE);
        setXFormat(paintBorder, m);
        canvas.drawBitmap(bm, 0, 0, paintBorder);

        canvas.drawRoundRect(border, round, round, paintBorder);
    }

    private void setXFormat(Paint p, int type){
        PorterDuffXfermode mode = type == -1 ? null : new PorterDuffXfermode(modes[type]);
        p.setXfermode(mode);
    }

    int m = 0;
    public void updateXFormat(int type){
        m = type;
        invalidate();
    }

    final PorterDuff.Mode[] modes = {
            PorterDuff.Mode.ADD,//0
            PorterDuff.Mode.CLEAR,//1
            PorterDuff.Mode.DARKEN,//2
            PorterDuff.Mode.DST,//3
            PorterDuff.Mode.DST_ATOP,//4
            PorterDuff.Mode.DST_IN,//5
            PorterDuff.Mode.DST_OVER,//6
            PorterDuff.Mode.DST_OUT,//7
            PorterDuff.Mode.LIGHTEN,//8
            PorterDuff.Mode.MULTIPLY,//9
            PorterDuff.Mode.OVERLAY,//10
            PorterDuff.Mode.SCREEN,//11
            PorterDuff.Mode.SRC,//12
            PorterDuff.Mode.SRC_ATOP,//13
            PorterDuff.Mode.SRC_IN,//14
            PorterDuff.Mode.SRC_OVER,//15
            PorterDuff.Mode.SRC_OUT,//16
            PorterDuff.Mode.XOR//17
    };
}
