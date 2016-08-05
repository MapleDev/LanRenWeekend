package com.serena.www.lazyweekend;

import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.serena.www.lazyweekend.base.BaseActivity;
import com.serena.www.lazyweekend.ui.DrawTextImageView;
import com.serena.www.lazyweekend.util.ActivityUtil;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * @author Serena
 * @time 2016/7/29  14:42
 * @desc ${TODD}
 */
public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.sina_login_btn)
    LinearLayout mSinaLoginBtn;
    @BindView(R.id.weixin_login_btn)
    LinearLayout mWeixinLoginBtn;
    @BindView(R.id.third_login_layout)
    LinearLayout mThirdLoginLayout;
    @BindView(R.id.direct_login_btn)
    Button mDirectLoginBtn;

    private TypedArray mDrawables;
    private String[] mTitles;
    private ArrayList<DrawTextImageView> images;
    private int currentItem;
    private ScheduledExecutorService scheduledExecutorService;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        initViewPager();

        mDirectLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.startActivity(WelcomeActivity.this, MyInfoActivity.class, false, 0);
            }
        });
    }

    private void initViewPager() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;    //得到宽度
        int height = dm.heightPixels;  //得到高度

        mTitles = getResources().getStringArray(R.array.splash_tip);
        mDrawables = getResources().obtainTypedArray(R.array.splash_pic);
        images = new ArrayList<>();
        for (int i = 0; i < mDrawables.length(); i++) {
            DrawTextImageView imageView = new DrawTextImageView(this);
            imageView.setImageDrawable(mDrawables.getDrawable(i));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setDrawText(mTitles[i]);
            imageView.setDrawLocalXY(width / 2, height / 2);
            images.add(imageView);
        }
        mViewpager.setAdapter(new ViewPagerAdapter());
        //mViewpager.setPageTransformer(true,new ScalePageTransformer());
        //mViewpager.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_out_600));
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //每隔2秒钟切换一张图片
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 2, 2, TimeUnit.SECONDS);
    }

    //切换图片
    private class ViewPagerTask implements Runnable {
        @Override
        public void run() {
            currentItem = (currentItem + 1) % mDrawables.length();
            //更新界面
            handler.obtainMessage().sendToTarget();
        }

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //设置当前页面
            mViewpager.setCurrentItem(currentItem);
        }

    };

    @Override
    protected void onStop() {
        super.onStop();
    }


    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(images.get(position));
            return images.get(position);
        }
    }
}
