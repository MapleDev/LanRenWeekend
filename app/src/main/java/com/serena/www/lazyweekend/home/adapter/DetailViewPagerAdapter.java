package com.serena.www.lazyweekend.home.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.serena.www.lazyweekend.home.bean.RDetail;
import com.serena.www.lazyweekend.util.ImageUtil;

/**
 * @author Serena
 * @time 2016/8/4  1:28
 * @desc ${TODD}
 */
public class DetailViewPagerAdapter extends PagerAdapter {

    /*ArrayList<ImageView> view;
    private Context mContext;*/
    private RDetail mDetail;

    public void setDetail(RDetail detail) {
        mDetail = detail;
        notifyDataSetChanged();
    }

    public DetailViewPagerAdapter(/*ArrayList<ImageView> view, Context context*/) {
        /*mContext = context;
        this.view = view;*/
    }

    @Override
    public int getCount() {
        return mDetail!= null ? mDetail.getImages().size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int p = position % mDetail.getImages().size();
        ImageView image = new ImageView(container.getContext());
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageUtil.loadImg(image, mDetail.getImages().get(p));
        container.addView(image);
        return image;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView)object);
    }
}

