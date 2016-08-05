package com.serena.www.lazyweekend.factory;

import com.serena.www.lazyweekend.ask.AskFragment;
import com.serena.www.lazyweekend.base.BaseFragment;
import com.serena.www.lazyweekend.home.HomeFragment;
import com.serena.www.lazyweekend.mine.MineFragment;
import com.serena.www.lazyweekend.search.SearchFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Serena
 * @time 2016/7/30  14:10
 * @desc ${TODD}
 */
public class FragmentFactory {

    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_SEARCH = 1;
    public static final int FRAGMENT_ASK = 2;
    public static final int FRAGMENT_MINE = 3;
    private static Map<Integer, BaseFragment> mFragmentMap=new HashMap<>();

    public static BaseFragment createFragment(int position) {
        BaseFragment fragment = null;
        if(mFragmentMap.containsKey(position)) {
            fragment = mFragmentMap.get(position);
            return fragment;
        }
        switch (position) {
            case FRAGMENT_HOME:
                fragment = new HomeFragment();
                break;
            case FRAGMENT_SEARCH:
                fragment = new SearchFragment();
                break;
            case FRAGMENT_ASK:
                fragment = new AskFragment();
                break;
            case FRAGMENT_MINE:
                fragment = new MineFragment();
                break;
        }
        mFragmentMap.put(position,fragment);
        return fragment;
    }
}
