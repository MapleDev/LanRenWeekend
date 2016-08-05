package com.serena.www.lazyweekend.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @author Serena
 * @time 2016/7/29  20:44
 * @desc ${TODD}
 */
public class NoScrollListView extends ListView {

    public NoScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

}
