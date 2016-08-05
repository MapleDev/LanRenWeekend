package com.serena.www.lazyweekend.ask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.serena.www.lazyweekend.R;
import com.serena.www.lazyweekend.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Serena
 * @time 2016/7/30  14:12
 * @desc ${TODD}
 */
public class AskFragment extends BaseFragment {

    @BindView(R.id.center_title)
    TextView mCenterTitle;
    @BindView(R.id.list_view)
    ListView mListView;
    @BindView(R.id.send)
    Button mSend;
    @BindView(R.id.input_need)
    EditText mInputNeed;
    private List<Msg> mMsges;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_require_send;
    }


    @Override
    protected void initView() {

        mCenterTitle.setText("懒猫助理");

        mMsges = new ArrayList<>();
        mMsges.add(new Msg("你好， 我是产品狗 Uo・ｪ・oU ，欢迎您反馈使用产品的感受和建议。。。", Msg.TYPE_RECEIVED));

        final MsgAdapter chatAdapter = new MsgAdapter(mContext, R.layout.item_msg, mMsges);
        mListView.setAdapter(chatAdapter);
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mInputNeed.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    mMsges.add(msg);
                    chatAdapter.notifyDataSetChanged(); // 当有新消息时，刷新ListView中的显示
                    mListView.setSelection(mMsges.size()); // 将ListView定位到最后一行
                    mInputNeed.setText(""); // 清空输入框中的内容
                }
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
