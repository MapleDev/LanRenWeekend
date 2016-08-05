package com.serena.www.lazyweekend.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.serena.www.lazyweekend.R;
import com.serena.www.lazyweekend.base.BaseFragment;
import com.serena.www.lazyweekend.contenst.Constant;
import com.serena.www.lazyweekend.home.adapter.DetailViewPagerAdapter;
import com.serena.www.lazyweekend.home.bean.RDetail;
import com.serena.www.lazyweekend.home.bean.RResult;
import com.serena.www.lazyweekend.util.ImageUtil;
import com.serena.www.lazyweekend.util.Network;
import com.serena.www.lazyweekend.util.ToastUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Serena
 * @time 2016/8/4  12:07
 * @desc ${TODD}
 */
public class HomeDetailFragment extends BaseFragment {


    @BindView(R.id.home_detail_viewPager)
    ViewPager mHomeDetailViewPager;
    @BindView(R.id.info_tv)
    TextView mInfoTv;
    @BindView(R.id.home_detail_iv)
    ImageView mHomeDetailIv;
    @BindView(R.id.category_tv)
    TextView mCategoryTv;
    @BindView(R.id.price_tv)
    TextView mPriceTv;
    @BindView(R.id.time_tv)
    TextView mTimeTv;
    @BindView(R.id.address_tv)
    TextView mAddressTv;
    @BindView(R.id.info_huodong_ll)
    LinearLayout mInfoHuodongLl;
    @BindView(R.id.tip_title_tv)
    TextView mTipTitleTv;
    @BindView(R.id.tip_Ll)
    LinearLayout mTipLl;
    @BindView(R.id.must_know_tv)
    TextView mMustKnowTv;
    @BindView(R.id.know_Ll)
    LinearLayout mKnowLl;
    @BindView(R.id.how_todo_tv)
    TextView mHowTodoTv;
    @BindView(R.id.todo_Ll)
    LinearLayout mTodoLl;
    @BindView(R.id.go_back_tv)
    TextView mGoBackTv;
    @BindView(R.id.back_Ll)
    LinearLayout mBackLl;
    @BindView(R.id.ticket_data_tv)
    TextView mTicketDataTv;
    @BindView(R.id.ticket_tv)
    TextView mTicketTv;
    @BindView(R.id.ticket_tv1)
    TextView mTicketTv1;
    @BindView(R.id.booking_btn)
    Button mBookingBtn;
    @BindView(R.id.action_left)
    TextView mActionLeft;
    @BindView(R.id.action_right)
    TextView mActionRight;
    @BindView(R.id.action_bar)
    RelativeLayout mActionBar;


    private RDetail mDetail;
    InnerHandler handler;
    private DetailViewPagerAdapter mDetailViewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_detail;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initData();
        handler = new InnerHandler(this);
        return rootView;
    }



    @Override
    protected void initView() {
        mActionBar.setBackgroundResource(R.color.touming);
        mActionLeft.setBackgroundResource(R.drawable.left);
        mActionRight.setBackgroundResource(R.drawable.ic_nav_share_white);
        mActionLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(HomeDetailFragment.this).commit();
            }
        });
        mActionRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/8/4 跳转到分享
            }
        });
    }

    private void initList() {
        mDetailViewPagerAdapter = new DetailViewPagerAdapter();
        mDetailViewPagerAdapter.setDetail(mDetail);
        mHomeDetailViewPager.setAdapter(mDetailViewPagerAdapter);

        int middle = mDetail.getImages().size() / 2;
        mHomeDetailViewPager.setCurrentItem(middle - middle % mDetail.getImages().size());
        //增加滚动监听
        mHomeDetailViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mActionBar.setBackgroundResource(R.color.light_white);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mInfoTv.setText(mDetail.getTitle());
        ImageUtil.loadImg(mHomeDetailIv, mDetail.getCategory().icon_view);
        mCategoryTv.setText(mDetail.getCategory().cn_name);
        mPriceTv.setText("¥ " + mDetail.getPrice_info());
        mTimeTv.setText(mDetail.getTime().info);
        mAddressTv.setText(mDetail.getAddress());
        for (int i = 0; i < mDetail.getDescription().size(); i++) {
            if ("image".equals(mDetail.getDescription().get(i).getType())) {
                ImageView mInfoIv = new ImageView(getActivity());
                mInfoHuodongLl.addView(mInfoIv);
                ImageUtil.loadImg(mInfoIv, mDetail.getDescription().get(i).getContent());
                mInfoIv.setMaxHeight(mDetail.getDescription().get(i).getSize().get(0));
                mInfoIv.setMaxWidth(mDetail.getDescription().get(i).getSize().get(1));
            } else if ("text".equals(mDetail.getDescription().get(i).getType())) {
                TextView mInfoTv = new TextView(getActivity());
                mInfoTv.setGravity(Gravity.CENTER);
                mInfoTv.setTextSize(16);
                mInfoHuodongLl.addView(mInfoTv);
                mInfoTv.setText(mDetail.getDescription().get(i).getContent());
            }
        }

        for (int j = 0; j < mDetail.getLrzm_tips().size(); j++) {
            if ("text".equals(mDetail.getLrzm_tips().get(j).getType())) {
                TextView mTipTv = new TextView(getActivity());
                mTipLl.addView(mTipTv);
                mTipTv.setText((j + 1) + ":" + mDetail.getLrzm_tips().get(j).getContent());
                mTipTv.setTextSize(16);
                mTipTv.setSingleLine(false);
            } else {
                ImageView mTipIv = new ImageView(getActivity());
                mTipLl.addView(mTipIv);
                ImageUtil.loadImg(mTipIv, mDetail.getLrzm_tips().get(j).getContent());
            }
        }

        for (int k = 0; k < mDetail.getBooking_policy().size(); k++) {
            if ("text".equals(mDetail.getBooking_policy().get(k).getType())) {
                TextView mKnowTv = new TextView(getActivity());
                mKnowLl.addView(mKnowTv);
                mKnowTv.setText((k + 1) + ":" + mDetail.getBooking_policy().get(k).getContent());
                mKnowTv.setTextSize(16);
                mKnowTv.setSingleLine(false);
            } else {
                ImageView mKnowIv = new ImageView(getActivity());
                mKnowLl.addView(mKnowIv);
                ImageUtil.loadImg(mKnowIv, mDetail.getBooking_policy().get(k).getContent());
            }
        }

        for (int a = 0; a < mDetail.getTicket_usage().size(); a++) {
            if ("text".equals(mDetail.getTicket_usage().get(a).getType())) {
                TextView mTodoTv = new TextView(getActivity());
                mTodoLl.addView(mTodoTv);
                mTodoTv.setText((a + 1) + ":" + mDetail.getTicket_usage().get(a).getContent());
                mTodoTv.setTextSize(16);
                mTodoTv.setSingleLine(false);
            } else {
                ImageView mTodoIv = new ImageView(getActivity());
                mTodoLl.addView(mTodoIv);
                ImageUtil.loadImg(mTodoIv, mDetail.getTicket_usage().get(a).getContent());
            }
        }


        for (int b = 0; b < mDetail.getTicket_rule().size(); b++) {
            if ("text".equals(mDetail.getTicket_rule().get(b).getType())) {
                TextView mBackTv = new TextView(getActivity());
                mBackLl.addView(mBackTv);
                mBackTv.setText((b + 1) + ":" + mDetail.getTicket_rule().get(b).getContent());
                mBackTv.setTextSize(16);
                mBackTv.setSingleLine(false);
            } else {
                ImageView mBackIv = new ImageView(getActivity());
                mBackLl.addView(mBackIv);
                ImageUtil.loadImg(mBackIv, mDetail.getTicket_rule().get(b).getContent());
            }
        }

       /* mTicketTv.setVisibility(View.VISIBLE);
        mTicketTv.setText(mDetail.getRepresent_data().get(0).title);
        mTicketTv1.setVisibility(View.VISIBLE);
        mTicketTv1.setText("¥ " + mDetail.getRepresent_data().get(0).getPrice_info());*/
    }


    private void initData() {
        long leoId = getArguments().getLong("leo_id", 0);
        Network.getInstance().mService.getRBookingList(leoId, Constant.SESSION_ID, Constant.V).enqueue(new Callback<RResult<RDetail>>() {
            @Override
            public void onResponse(Call<RResult<RDetail>> call, Response<RResult<RDetail>> response) {
                RResult<RDetail> rResult = response.body();
                if (rResult.getStatus() == 200) {
                    mDetail = rResult.getResult();
                    Log.d("HomeDetailActivity", "onResponse: " + mDetail);
                } else {
                    ToastUtils.show(getActivity(), "网络异常");
                }
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(Call<RResult<RDetail>> call, Throwable t) {
                Log.d("HomeDetailActivity", "onFailure: ");
                t.printStackTrace();
            }
        });
    }


    static class InnerHandler extends Handler {

        WeakReference<HomeDetailFragment> mHomeDetailFragment;

        public InnerHandler(HomeDetailFragment hf) {
            this.mHomeDetailFragment = new WeakReference<>(hf);
        }

        @Override
        public void handleMessage(Message msg) {
            HomeDetailFragment homeDetailFragment = mHomeDetailFragment.get();
            if (homeDetailFragment == null) {
                return;
            }
            homeDetailFragment.initList();

        }
    }


}
