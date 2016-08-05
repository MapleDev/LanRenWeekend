package com.serena.www.lazyweekend.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.serena.www.lazyweekend.R;

/**
 * @author Serena
 * @time 2016/7/29  16:05
 * @desc ${TODD}
 */
public class DrawTextImageView extends ImageView {

    public DrawTextImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawTextImageView (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawTextImageView (Context context) {
        super(context);
    }

    /**
     * 文字
     */
    private String textString = "";

    /**
     * 文字大小
     */
    private float textSize = 30;

    /**
     * 文字位置x
     */
    private float x = -1000;

    /**
     * 文字位置y
     */
    private float y = -1000;

    /**
     * 文字颜色
     */
    private int textColor = R.color.white;

    /**
     * 文字线条粗细
     */
    private int textDrawStrokeWidth = 3;

    /**
     *
     */
    public void setDrawText(String string){
        textString = string;
        drawableStateChanged();
    }

    /**
     *  设置文字
     */
    public void setDrawTextSize(float textSize){
        this.textSize = textSize;
        drawableStateChanged();
    }

    /**
     * 设置文字位置x，y
     */
    public void setDrawLocalXY(float x, float y){
        this.x = x;
        this.y = y;
        drawableStateChanged();
    }

    /**
     * 设置文字颜色
     */
    public void setDrawTextColorResourse(int textColor){
        this.textColor = textColor;
        drawableStateChanged();
    }

    /**
     * 设置文字粗细
     */
    public void setDrawTextStrokeWidth(int textDrawStrokeWidth){
        this.textDrawStrokeWidth = textDrawStrokeWidth;
        drawableStateChanged();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!textString.equals("")) {
            Paint paint = new Paint();
            paint.setColor(getResources().getColor(textColor));
            paint.setStrokeWidth(textDrawStrokeWidth);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(textSize);
            canvas.drawText(textString, x==-1000 ? ( canvas.getWidth() - ( textSize * textString.length() ) )
                    : x, y == -1000 ? ( canvas.getHeight() - 50 ) : y, paint);
        }


    }

}
