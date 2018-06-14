package com.serena.www.lazyweekend.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.serena.www.lazyweekend.R;
import com.serena.www.lazyweekend.base.BaseFragment;
import com.serena.www.lazyweekend.home.adapter.HomeRecyclerViewAdapter;
import com.serena.www.lazyweekend.home.bean.RItem;
import com.serena.www.lazyweekend.home.bean.RResult;
import com.serena.www.lazyweekend.ui.SuperSwipeRefreshLayout;
import com.serena.www.lazyweekend.util.Network;
import com.serena.www.lazyweekend.util.ToastUtils;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Serena
 * @time 2016/7/30  14:13
 * @desc 首页
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.center_title)
    TextView mCenterTitle;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_container)
    SuperSwipeRefreshLayout mSwipeContainer;

    private TextView mPullTv;
    private String[] mUpTips;

    private List<RItem> mRItems;
    InnerHandler handler;
    private int index = 0;
    private HomeRecyclerViewAdapter mHomeRecyclerViewAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handler = new InnerHandler(this);
        initData();
    }


    @Override
    protected void initView() {
        mCenterTitle.setText("懒人周末");

        mHomeRecyclerViewAdapter = new HomeRecyclerViewAdapter(getActivity());
        mRecyclerView.setAdapter(mHomeRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        // 跳转至 item 详情页面
        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        mUpTips = getActivity().getResources().getStringArray(R.array.up_tip);

        // 设置 头部View
        mSwipeContainer.setHeaderView(createHeaderView());
        // 下拉刷新事件
        mSwipeContainer.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh() {
                mPullTv.setText(mUpTips[index++ % mUpTips.length]);
                mSwipeContainer.setRefreshing(false);
                //  16/08/03 显示时间太短了 ，能否睡一下？
            }

            @Override
            public void onPullDistance(int distance) {

            }

            @Override
            public void onPullEnable(boolean enable) {

            }
        });
    }


    private void initData() {

        // TODO: 16/08/04 show一朵菊花

        Network.getInstance().mService.getRItemList().enqueue(new Callback<RResult<List<RItem>>>() {
            @Override
            public void onResponse(Call<RResult<List<RItem>>> call, Response<RResult<List<RItem>>> response) {
                RResult<List<RItem>> rResult = response.body();
                if (rResult.getStatus() == 200) {
                    mRItems = rResult.getResult();
                } else {
                    ToastUtils.show(getActivity(), "网络异常");
                }
                //Log.d("ss", "onResponse: " + mRItems);
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(Call<RResult<List<RItem>>> call, Throwable t) {

            }
        });

    }

    static class InnerHandler extends Handler {

        WeakReference<HomeFragment> fragment;

        public InnerHandler(HomeFragment hf) {
            this.fragment = new WeakReference<>(hf);
        }

        @Override
        public void handleMessage(Message msg) {
            HomeFragment homeFragment = fragment.get();
            if (homeFragment == null) {
                return;
            }
            homeFragment.initList();
            homeFragment.mSwipeContainer.setRefreshing(false);
            // TODO: 16/08/04 一朵菊花.gone
        }
    }


    private void initList() {
        mHomeRecyclerViewAdapter.setRItems(mRItems);
    }

    private View createHeaderView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pull_style, null);
        mPullTv = (TextView) view.findViewById(R.id.pull_tv);
        return view;
    }

}
