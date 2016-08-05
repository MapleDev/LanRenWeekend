package com.serena.www.lazyweekend.mine.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.serena.www.lazyweekend.R;
import com.serena.www.lazyweekend.base.BaseActivity;
import com.serena.www.lazyweekend.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author MxBox
 * @time 2016/7/30  17:21
 * @task 登录界面
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.action_left)
    TextView mActionLeft;
    @BindView(R.id.center_title)
    TextView mCenterTitle;
    @BindView(R.id.login_mobile_et)
    EditText mLoginMobileEt;
    @BindView(R.id.login_pw_et)
    EditText mLoginPwEt;
    @BindView(R.id.login_login_btn)
    Button mLoginLoginBtn;
    @BindView(R.id.login_weibo)
    ImageView mLoginWeibo;
    @BindView(R.id.login_weixin)
    ImageView mLoginWeixin;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mActionLeft.setBackgroundResource(R.drawable.ic_me_back);
        mCenterTitle.setText("登录");
    }


    @OnClick({R.id.action_left, R.id.login_login_btn,R.id.login_weibo,R.id.login_weixin})
    public void onClick(View view) {
        switch (view.getId()) {
            //退出
            case R.id.action_left:
                this.finish();
                break;
            case R.id.login_login_btn:
                this.finish();
                break;
            case R.id.login_weibo:
                ToastUtils.show(this, "什么");
                break;
            case R.id.login_weixin:
                ToastUtils.show(this, "什么");
                break;
        }
    }



}
