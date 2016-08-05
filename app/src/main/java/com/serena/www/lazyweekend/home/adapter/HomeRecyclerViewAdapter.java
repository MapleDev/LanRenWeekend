package com.serena.www.lazyweekend.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.serena.www.lazyweekend.R;
import com.serena.www.lazyweekend.home.HomeDetailActivity;
import com.serena.www.lazyweekend.home.bean.RItem;
import com.serena.www.lazyweekend.util.ImageUtil;

import java.util.List;

/**
 * @author Serena
 * @time 2016/7/31  16:12
 * @desc ${TODD}
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<RItem> mRItems;

    public void setRItems(List<RItem> RItems) {
        mRItems = RItems;
        notifyDataSetChanged();
    }


    public HomeRecyclerViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_home_view, parent, false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(view);
        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        final RItem rItem = mRItems.get(position);
        ImageUtil.loadImg(holder.mInfoImageView, rItem.getFront_cover_image_list().get(0));
        holder.mInfoTextView.setText(rItem.getTitle());
        holder.mShopTextView.setText(rItem.getPoi_name());
        holder.mCategoryTextView.setText(rItem.getCategory());
        holder.mEndTimeTextView.setText(rItem.getTime_info());
        holder.mFavTextView.setText(rItem.getCollected_num() + "人收藏");
        holder.mPriceTextView.setText("¥ " + rItem.getPrice_info());
        holder.mItemHomeLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HomeDetailActivity.class);
                intent.putExtra("leo_id", rItem.leo_id);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mRItems != null ? mRItems.size() : 0;
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mItemHomeLl;
        ImageView mInfoImageView;
        TextView mInfoTextView;
        TextView mShopTextView;
        TextView mCategoryTextView;
        TextView mEndTimeTextView;
        TextView mFavTextView;
        TextView mPriceTextView;

        public HomeViewHolder(View itemView) {
            super(itemView);
            mItemHomeLl = (LinearLayout) itemView.findViewById(R.id.ll_item_home);
            mInfoImageView = (ImageView) itemView.findViewById(R.id.info_iv);
            mInfoTextView = (TextView) itemView.findViewById(R.id.info_tv);
            mShopTextView = (TextView) itemView.findViewById(R.id.shop_tv);
            mCategoryTextView = (TextView) itemView.findViewById(R.id.category_tv);
            mEndTimeTextView = (TextView) itemView.findViewById(R.id.end_time_tv);
            mFavTextView = (TextView) itemView.findViewById(R.id.fav_tv);
            mPriceTextView = (TextView) itemView.findViewById(R.id.price_tv);
        }
    }
}
