package com.serena.www.lazyweekend.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author Serena
 * @time 2016/7/29  18:07
 * @desc actionBar的自定义MenuIcon
 */
public class MenuIcon extends TextView {
    public MenuIcon(Context context) {
        super(context);
    }

    public MenuIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /*private int mIconId;
    private String mString;
    private Paint mPaint;
    private Bitmap mBitmap;

    public MenuIcon(Context context) {
        super(context);
    }

    public MenuIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MenuIcon);
        mIconId = a.getResourceId(R.styleable.MenuIcon_menu_icon, 0);
        mString = a.getString(R.styleable.MenuIcon_menu_text);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBitmap = BitmapFactory.decodeResource(getResources(), mIconId);
        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        }
    }

    protected void setMenuIcon(int resourceId) {
        mIconId = resourceId;
        invalidate();
    }*/
}
