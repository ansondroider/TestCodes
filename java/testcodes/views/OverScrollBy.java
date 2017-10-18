package testcodes.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import testcodes.base.LOG;

/**
 * Created by anson on 16-12-2.
 * test overScrollBy
 */

public class OverScrollBy extends View {
    public OverScrollBy(Context context) {
        super(context);
    }

    public OverScrollBy(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OverScrollBy(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int CX, CY;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        CX = w >> 1;
        CY = h >> 1;
    }

    public void test(){
        overScrollBy(0, 80,
                0, getScrollY(),
                0, 100,
                0, 64, false);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        //super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        LOG.log("onOverScrolled scrollX(" + scrollX + "), scrollY(" +
                scrollY + "), clampedX(" + clampedX + "), clampedY(" + clampedY + ")");
        super.scrollTo(scrollX, scrollY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.RED);
        canvas.drawCircle(CX, CY, CX/10, paint);
    }
}
