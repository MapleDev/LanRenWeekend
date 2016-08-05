package com.serena.www.lazyweekend.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.serena.www.lazyweekend.R;
import com.serena.www.lazyweekend.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Serena
 * @time 2016/7/30  13:40
 * @desc ${TODD}
 */
public class SearchFragment extends BaseFragment {

    private GridView gridview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    //分类图片数组 9个
    private int[] itemImage = {R.drawable.clock,R.drawable.clock,R.drawable.clock,R.drawable.clock,R.drawable.clock,
            R.drawable.clock,R.drawable.clock,R.drawable.clock,R.drawable.clock};
    //分类文字数组 9个
    private String[] itemName = { "全部类目", "沙龙学堂", "DIY手作", "派对聚会", "暂不开放", "运动健身", "户外活动",
            "茶会雅集", "文艺生活" };

    //该方法成功显示
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_search,null);
        gridview= (GridView) view.findViewById(R.id.category_gv);
        data_list = new ArrayList<>();
        //往data_list集合中添加数据
        getData();
        //参数：1上下文，2集合，3显示的item布局，4显示的内容名称(map用)，5显示的布局id
        sim_adapter = new SimpleAdapter(this.getContext(),data_list,R.layout.category_item,
                new String[]{"imageItem","textItem"},new int[]{R.id.category_iv,R.id.category_tv});
        gridview.setAdapter(sim_adapter);
        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //GridView gridView= (GridView) view.findViewById(R.id.category_gv);
    }

    @Override
    protected void initView() {
        //gridview = (GridView) getActivity().findViewById(R.id.category_gv);  //空指针？
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_search,null);
//        gridview= (GridView) view.findViewById(R.id.category_gv);   //显示为空？
//        data_list = new ArrayList<>();
//        //往data_list集合中添加数据
//        getData();
//        //参数：1上下文，2集合，3显示的item布局，4显示的内容名称(map用)，5显示的布局id
//        sim_adapter = new SimpleAdapter(this.getContext(),data_list,R.layout.category_item,
//                new String[]{"imageItem","textItem"},new int[]{R.id.category_iv,R.id.category_tv});
//        gridview.setAdapter(sim_adapter);
    }

    private void getData() {
        for(int i=0;i<itemImage.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("imageItem",itemImage[i]);
            map.put("textItem",itemName[i]);
            data_list.add(map);
        }

    }

}
