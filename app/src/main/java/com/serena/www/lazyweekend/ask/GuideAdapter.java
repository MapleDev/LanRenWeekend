package com.serena.www.lazyweekend.ask;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.serena.www.lazyweekend.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author MapleDev
 * @time 16/07/30  18:46
 * @desc ${TODD}
 */
public class GuideAdapter extends PagerAdapter {


    private List<View> mViews;

    public GuideAdapter(List<View> views) {
        mViews = views;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = mViews.get(position);
        container.removeView(view);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        switch (position) {
            case 0:
                ((ImageView) view.findViewById(R.id.guide_img)).setImageResource(R.drawable.guide1);
                ((TextView) view.findViewById(R.id.title)).setText("喵喵喵～～～_1");
                ((TextView) view.findViewById(R.id.description)).setText("喵喵喵description～～～_1");
                break;
            case 1:
                ((ImageView) view.findViewById(R.id.guide_img)).setImageResource(R.drawable.guide2);
                ((TextView) view.findViewById(R.id.title)).setText("喵喵喵～～～_2");
                ((TextView) view.findViewById(R.id.description)).setText("喵喵喵description～～～_2");
                break;
            case 2:
                ((ImageView) view.findViewById(R.id.guide_img)).setImageResource(R.drawable.guide3);
                ImageView close = (ImageView) view.findViewById(R.id.close);

                ((TextView) view.findViewById(R.id.title)).setText("喵喵喵～～～_3");
                ((TextView) view.findViewById(R.id.description)).setText("喵喵喵description～～～_3");
                TextView textViewEnter = (TextView) view.findViewById(R.id.start_btn);

                // 设置可见
                close.setVisibility(View.VISIBLE);
                textViewEnter.setVisibility(View.VISIBLE);

                // close 关闭事件
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(v.getContext(), "close", Toast.LENGTH_SHORT).show();
                        EventBus.getDefault().post(new EventBusMsg());
                    }
                });
                // textViewEnter 关闭事件
                textViewEnter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(v.getContext(), "textViewEnter", Toast.LENGTH_SHORT).show();
                        EventBus.getDefault().post(new EventBusMsg());

                    }
                });

                break;
        }
        container.addView(view);
        return view;
    }
}
