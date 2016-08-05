package com.serena.www.lazyweekend.mine;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.serena.www.lazyweekend.R;
import com.serena.www.lazyweekend.base.BaseFragment;
import com.serena.www.lazyweekend.constant.IntentConstant;
import com.serena.www.lazyweekend.mine.activity.LoginActivity;
import com.serena.www.lazyweekend.mine.activity.SetActivity;
import com.serena.www.lazyweekend.util.ActivityUtil;
import com.serena.www.lazyweekend.welcome.MyLikeActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Serena
 * @time 2016/7/30  14:12
 * @desc ${TODD}
 */
public class MineFragment extends BaseFragment {

    protected boolean loginState = false;

    @BindView(R.id.mine_icon_iv)
    ImageView mMineIconIv;
    @BindView(R.id.order_btn)
    RelativeLayout mOrderBtn;
    @BindView(R.id.interest_btn)
    RelativeLayout mInterestBtn;
    @BindView(R.id.setting_btn)
    RelativeLayout mSettingBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.mine_icon_iv, R.id.order_btn, R.id.interest_btn, R.id.setting_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            //头像
            case R.id.mine_icon_iv:
                if(loginState) {

                }else {
                    ActivityUtil.startActivity(getActivity(), LoginActivity.class,false,0);
                }
                break;
            //我预定的
            case R.id.order_btn:
                if(loginState) {

                }else {
                    ActivityUtil.startActivity(getActivity(), LoginActivity.class,false,0);
                }
                break;
            //兴趣标签
            case R.id.interest_btn:
                Intent intent = new Intent(getActivity(),MyLikeActivity.class);
                intent.putExtra("set2mylike", IntentConstant.SET_MYLIKE);
                getActivity().startActivity(intent);
                break;
            //设置
            case R.id.setting_btn:
                ActivityUtil.startActivity(getActivity(), SetActivity.class,false,0);
                break;
        }
    }
}
