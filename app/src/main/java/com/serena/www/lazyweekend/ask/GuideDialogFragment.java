package com.serena.www.lazyweekend.ask;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.serena.www.lazyweekend.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MapleDev
 * @time 16/07/30  18:05
 * @desc ${TODD}
 */
public class GuideDialogFragment extends DialogFragment implements ViewPager.OnPageChangeListener {

    private List<View> mViews;
    private View mView;
    private LinearLayout mGuiedIndicator;

    @Override
    public void onStart() {
        super.onStart();

        // safety check
        if (getDialog() == null) {
            return;
        }

        // set the animations to use on showing and hiding the dialog
        getDialog().getWindow().setWindowAnimations(R.style.guied_dialog_animation);
        // alternative way of doing it
        //getDialog().getWindow().getAttributes().
        //    windowAnimations = R.style.dialog_animation_fade;

        // ... other stuff you want to do in your onStart() method
//    setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        mView = inflater.inflate(R.layout.guide_popup_view_pager, container);
        ViewPager vpGuide = (ViewPager) mView.findViewById(R.id.vp_guide);

        mViews = new ArrayList<>();
        LayoutInflater layoutInflater = inflater.from(mView.getContext());
        View view1 = layoutInflater.inflate(R.layout.item_guide_img, null);
        View view2 = layoutInflater.inflate(R.layout.item_guide_img, null);
        View view3 = layoutInflater.inflate(R.layout.item_guide_img, null);

        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);

        initDots();
        GuideAdapter guideAdapter = new GuideAdapter(mViews);
        vpGuide.setAdapter(guideAdapter);
        vpGuide.addOnPageChangeListener(this);

        return mView;
    }

    private void initDots() {
        mGuiedIndicator = (LinearLayout) mView.findViewById(R.id.ll_guide_indicator);

        for (int i = 0; i < mViews.size(); i++) {
            ImageView indicatorIv = new ImageView(getActivity());
            if (i == 0) {
                indicatorIv.setBackgroundResource(R.drawable.gray_dot);
            } else {
                indicatorIv.setBackgroundResource(R.drawable.white_dot);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            params.setMargins(25, 0, 0, 0);
            indicatorIv.setLayoutParams(params);
            mGuiedIndicator.addView(indicatorIv, i);
        }


    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        // 设置底部小点选中状态
        int childCount = mGuiedIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView dot = (ImageView) mGuiedIndicator.getChildAt(i);

            if (i == position % childCount) {
                dot.setBackgroundResource(R.drawable.gray_dot);
            } else {
                dot.setBackgroundResource(R.drawable.white_dot);
            }


        }

    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
