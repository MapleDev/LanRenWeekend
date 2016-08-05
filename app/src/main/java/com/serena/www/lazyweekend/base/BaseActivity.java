package com.serena.www.lazyweekend.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author Serena
 * @time 2016/7/29  12:21
 * @desc ${TODD}
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.getLayoutId());
        ButterKnife.bind(this);
        this.initView();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();
}
