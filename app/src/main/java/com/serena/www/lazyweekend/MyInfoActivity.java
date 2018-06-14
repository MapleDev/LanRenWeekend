package com.serena.www.lazyweekend;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.serena.www.lazyweekend.base.BaseActivity;
import com.serena.www.lazyweekend.ui.SelectorImageView;
import com.serena.www.lazyweekend.util.ActivityUtil;
import com.serena.www.lazyweekend.util.ToastUtils;
import com.serena.www.lazyweekend.welcome.MyLikeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Serena
 * @time 2016/7/29  17:40
 * @desc 设置个人资料
 */
public class MyInfoActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.action_left)
    TextView mActionLeft;
    @BindView(R.id.action_right)
    TextView mActionRight;
    @BindView(R.id.center_title)
    TextView mCenterTitle;
    @BindView(R.id.imageView_male)
    SelectorImageView mImageViewMale;
    @BindView(R.id.imageView_female)
    SelectorImageView mImageViewFemale;
    @BindView(R.id.rg_my_info_status)
    RadioGroup mRgMyInfoStatus;
    @BindView(R.id.action_bar)
    RelativeLayout mActionBar;
    @BindView(R.id.rb_parent)
    RadioButton mRbParent;
    @BindView(R.id.rb_fff)
    RadioButton mRbFff;
    @BindView(R.id.rb_single_dog)
    RadioButton mRbSingleDog;
    @BindView(R.id.container)
    LinearLayout mContainer;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_info;
    }

    @Override
    protected void initView() {
        mActionLeft.setBackgroundResource(R.drawable.left);
        mActionRight.setBackgroundResource(R.drawable.right);

        mActionLeft.setOnClickListener(this);
        mActionRight.setOnClickListener(this);
        mCenterTitle.setText("设置个人资料");

        mImageViewMale.setOnClickListener(this);
        mImageViewFemale.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.action_left:
                ActivityUtil.startActivity(this, WelcomeActivity.class, false, 0);
                break;
            case R.id.action_right:
                Log.d("DDD", "mImageViewMale.isChecked() = " + !mImageViewMale.isChecked());
                Log.d("DDD", "mImageViewFemale.isChecked() = " + !mImageViewFemale.isChecked());
                if(!mImageViewMale.isChecked() && !mImageViewFemale.isChecked()) {
                    ToastUtils.show(this, "请选择您的性向 :P");
                    return;
                }
                if(!mRbParent.isChecked() && !mRbFff.isChecked() && !mRbSingleDog.isChecked()) {
                    ToastUtils.show(this, "请选择您的性取向 :P");
                    return;
                }
                String rbCheckedText = null;
                int checkedRadioButtonId = mRgMyInfoStatus.getCheckedRadioButtonId();
                switch (checkedRadioButtonId) {
                    case R.id.rb_parent:
                        rbCheckedText = mRbParent.getText().toString();
                        break;
                    case R.id.rb_fff:
                        rbCheckedText = mRbFff.getText().toString();
                        break;
                    case R.id.rb_single_dog:
                        rbCheckedText = mRbSingleDog.getText().toString();
                        break;
                }
                Log.d("DDD", "rbCheckedText = " + rbCheckedText);

                ActivityUtil.startActivity(this, MyLikeActivity.class, true, 0);
                break;

            case R.id.imageView_male:

                mImageViewMale.toggle(!mImageViewMale.isChecked());
                if(mImageViewFemale.isChecked()) {
                    mImageViewFemale.toggle(false);
                }
//                mImageViewMale.setImageResource(R.drawable.male_checked);
//                mImageViewFemale.setImageResource(R.drawable.female_unchecked);
                mImageViewMale.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));

//                mImageViewMale.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_left));
                break;
            case R.id.imageView_female:
                mImageViewFemale.toggle(!mImageViewFemale.isChecked());
                if(mImageViewMale.isChecked()) {
                    mImageViewMale.toggle(false);
                }
//                mImageViewFemale.setImageResource(R.drawable.female_checked);
//                mImageViewFemale.setImageResource(R.drawable.male_unchecked);
                mImageViewFemale.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
//                mImageViewFemale.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_right));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
