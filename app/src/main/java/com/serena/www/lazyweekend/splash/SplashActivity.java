package com.serena.www.lazyweekend.splash;

import com.serena.www.lazyweekend.R;
import com.serena.www.lazyweekend.WelcomeActivity;
import com.serena.www.lazyweekend.base.BaseActivity;
import com.serena.www.lazyweekend.util.ActivityUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Serena
 * @time 2016/7/29  12:20
 * @desc ${TODD}
 */
public class SplashActivity extends BaseActivity {


    private Timer mTimer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                ActivityUtil.startActivity(SplashActivity.this,WelcomeActivity.class,true,0);
            }
        },3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
