package com.serena.www.lazyweekend.welcome;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.serena.www.lazyweekend.MainActivity;
import com.serena.www.lazyweekend.MyInfoActivity;
import com.serena.www.lazyweekend.R;
import com.serena.www.lazyweekend.base.BaseActivity;
import com.serena.www.lazyweekend.util.ActivityUtil;
import com.serena.www.lazyweekend.util.SharePreferenceUtil;
import com.serena.www.lazyweekend.util.ToastUtils;

import butterknife.BindView;

/**
 * @author Serena
 * @time 2016/7/30  20:11
 * @desc ${TODD}
 */
public class MyLikeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.action_left)
    TextView mActionLeft;
    @BindView(R.id.action_right)
    TextView mActionRight;
    @BindView(R.id.center_title)
    TextView mCenterTitle;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private LikeAdapter mLikeAdapter;
    private String[] mList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_like_tip;
    }


    @Override
    protected void initView() {
        mList = getResources().getStringArray(R.array.category_list);
        mActionLeft.setBackgroundResource(R.drawable.left);
        mActionRight.setBackgroundResource(R.drawable.right);

        mActionLeft.setOnClickListener(this);
        mActionRight.setOnClickListener(this);
        mCenterTitle.setText("选择您的兴趣标签");

        SharePreferenceUtil.clear(this);

        mLikeAdapter = new LikeAdapter(mList,this);
        mRecyclerView.setAdapter(mLikeAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_left:
                ActivityUtil.startActivity(this, MyInfoActivity.class, false, 0);
                break;
            case R.id.action_right:
                boolean isChecked = false;
                for (int i = 0; i < mList.length; i++) {
                    if (SharePreferenceUtil.getBoolean(this, String.valueOf(i))) {
                        isChecked = true;
                        break;
                    }
                }
                if (isChecked) {
                    ActivityUtil.startActivity(this, MainActivity.class, true, 0);
                } else {
                    ToastUtils.show(this, "还没有选兴趣标签哦~");
                }
                break;
        }
    }
}
