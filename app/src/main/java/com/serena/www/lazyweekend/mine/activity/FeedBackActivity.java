package com.serena.www.lazyweekend.mine.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.serena.www.lazyweekend.R;
import com.serena.www.lazyweekend.ask.Msg;
import com.serena.www.lazyweekend.ask.MsgAdapter;
import com.serena.www.lazyweekend.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author MxBox
 * @time 2016/7/31  11:35
 * @task 用户吐槽界面
 */
public class FeedBackActivity extends BaseActivity {

    @BindView(R.id.action_left)
    TextView mActionLeft;
    @BindView(R.id.center_title)
    TextView mCenterTitle;
    @BindView(R.id.list_view)
    ListView mListView;
    @BindView(R.id.input_need)
    EditText mInputNeed;
    @BindView(R.id.send)
    Button mSend;
    private List<Msg> mMsges;
    private MsgAdapter mChatAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_require_send;
    }

    @Override
    protected void initView() {
        mActionLeft.setBackgroundResource(R.drawable.ic_nav_left);

        mCenterTitle.setText("用户反馈");
        mMsges = new ArrayList<>();
        mMsges.add(new Msg("你好， 我是产品狗 Uo・ｪ・oU ，欢迎您反馈使用产品的感受和建议。。。", Msg.TYPE_RECEIVED));

        mChatAdapter = new MsgAdapter(this, R.layout.item_msg, mMsges);
        mListView.setAdapter(mChatAdapter);
    }



    @OnClick({R.id.action_left, R.id.send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_left:
                finish();
                break;
            case R.id.send:
                String content = mInputNeed.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    mMsges.add(msg);
                    mChatAdapter.notifyDataSetChanged(); // 当有新消息时，刷新ListView中的显示
                    mListView.setSelection(mMsges.size()); // 将ListView定位到最后一行
                    mInputNeed.setText(""); // 清空输入框中的内容
                    break;
                }
        }
    }
}
