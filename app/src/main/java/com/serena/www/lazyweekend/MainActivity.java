package com.serena.www.lazyweekend;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.serena.www.lazyweekend.ask.EventBusMsg;
import com.serena.www.lazyweekend.ask.GuideDialogFragment;
import com.serena.www.lazyweekend.base.BaseActivity;
import com.serena.www.lazyweekend.factory.FragmentFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Serena
 * @time 2016/7/29  17:40
 * @desc 主页面
 */
public class MainActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.bottom_home)
    ImageView mBottomHome;
    @BindView(R.id.bottom_search)
    ImageView mBottomSearch;
    @BindView(R.id.bottom_cat)
    ImageView mBottomCat;
    @BindView(R.id.bottom_me)
    ImageView mBottomMe;
    @BindView(R.id.rootview)
    LinearLayout mRootview;
    @BindView(R.id.bottombar)
    LinearLayout mBottombar;
    private GuideDialogFragment mGuideDialogFragment;
    private SharedPreferences mSp;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_home;
    }

    @Override
    protected void initView() {
        mBottombar.setVisibility(View.VISIBLE);
        mBottomHome.setOnClickListener(this);
        mBottomSearch.setOnClickListener(this);
        mBottomCat.setOnClickListener(this);
        mBottomMe.setOnClickListener(this);

        // 默认 首页为黑
        mBottomHome.setEnabled(false);

        mViewpager.setAdapter(new ItemFragmentPageAdapter(getSupportFragmentManager()));

        mSp = getSharedPreferences("config", Context.MODE_PRIVATE);
        EventBus.getDefault().register(this);
        mViewpager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_home:
                // TODO: 2016/7/30 点击切换首页  黄宏雄、饶玥
                mViewpager.setCurrentItem(0);
                break;
            case R.id.bottom_search:
                // TODO: 2016/7/30 点击切换搜索页  黄杰晟
                mViewpager.setCurrentItem(1);
                break;
            case R.id.bottom_cat:
                // TODO: 2016/7/30 点击切换咨询页  周光华
                mViewpager.setCurrentItem(2);
                break;
            case R.id.bottom_me:
                // TODO: 2016/7/30 点击切换个人页  麻潇冰
                mViewpager.setCurrentItem(3);
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //----------- 处理 各个 Frag 显示的事件  start -------------------
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mBottomHome.setEnabled(true);
        mBottomSearch.setEnabled(true);
        mBottomCat.setEnabled(true);
        mBottomMe.setEnabled(true);

        switch (position) {
            case 0:
                mBottomHome.setEnabled(false);
                break;
            case 1:
                mBottomSearch.setEnabled(false);
                break;
            case 2:
                mBottomCat.setEnabled(false);
                // 如果首次进入 AskFrag , 则进入引导页面
                boolean isFirstEnterAskFrag = mSp.getBoolean("isFirstEnterAskFrag", true);
                if (isFirstEnterAskFrag) {
                    // 进入引导页面 guide
                    Log.w("DDD", "====== 进入引导页面 guide ======");
                    if (mGuideDialogFragment == null) {
                        mGuideDialogFragment = new GuideDialogFragment();
                        mGuideDialogFragment.setCancelable(false);
                        //                        mGuideDialogFragment.setEnterTransition(R.anim.down_popupwindow_show);
                        mGuideDialogFragment.show(getSupportFragmentManager(), "GuideDialogFragment");
                    }
                } else {
                    // 老司机了
                    Log.w("DDD", "====== 老司机了 ======");
                }
                break;
            case 3:
                mBottomMe.setEnabled(false);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //----------- 处理 各个 Frag 显示的事件  end -------------------

    // 完成 GuideDialogFragment 后的事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dismissDialogEventBus(EventBusMsg msg) {
        mGuideDialogFragment.dismiss();
        // 保存状态
        SharedPreferences.Editor edit = mSp.edit();
        edit.putBoolean("isFirstEnterAskFrag", false);
        edit.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /////////////////////////

    class ItemFragmentPageAdapter extends FragmentPagerAdapter {

        public ItemFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = FragmentFactory.createFragment(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }
    }

}