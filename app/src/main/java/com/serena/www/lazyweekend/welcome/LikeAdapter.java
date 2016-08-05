package com.serena.www.lazyweekend.welcome;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.serena.www.lazyweekend.R;
import com.serena.www.lazyweekend.util.SharePreferenceUtil;

/**
 * @author Serena
 * @time 2016/7/30  19:58
 * @desc 兴趣标签页的recyclerView的Adapter
 */
public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.LikeViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private String[] mList;
    private TypedArray mA;
    private TypedArray mB;

    public LikeAdapter(String[] list,Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        mList = list;
        mA = context.getResources().obtainTypedArray(R.array.category_icon_list);
        mB = context.getResources().obtainTypedArray(R.array.category_icon_list_checked);
    }

    @Override
    public LikeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.like_recycler_item_view, parent, false);
        LikeViewHolder likeViewHolder = new LikeViewHolder(view);
        return likeViewHolder;
    }

    @Override
    public int getItemCount() {
        return mList.length;
    }

    @Override
    public void onBindViewHolder(final LikeViewHolder holder, final int position) {
        holder.mLikeImageView.setImageDrawable(mA.getDrawable(position));
        holder.mLikeCheckBox.setText(mList[position]);
        holder.mLikeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.mLikeCheckBox.isChecked()) {
                    holder.mLikeImageView.setImageDrawable(mB.getDrawable(position));
                    SharePreferenceUtil.putBoolean(mContext, String.valueOf(position), isChecked);
                } else {
                    holder.mLikeImageView.setImageDrawable(mA.getDrawable(position));
                }
            }
        });
    }


    class LikeViewHolder extends RecyclerView.ViewHolder {

        ImageView mLikeImageView;
        CheckBox mLikeCheckBox;

        public LikeViewHolder(View itemView) {
            super(itemView);
            mLikeImageView = (ImageView) itemView.findViewById(R.id.like_iv);
            mLikeCheckBox = (CheckBox) itemView.findViewById(R.id.like_cb);
        }
    }
}





