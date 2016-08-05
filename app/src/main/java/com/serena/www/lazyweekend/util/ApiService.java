package com.serena.www.lazyweekend.util;

import com.serena.www.lazyweekend.contenst.Constant;
import com.serena.www.lazyweekend.home.bean.RDetail;
import com.serena.www.lazyweekend.home.bean.RItem;
import com.serena.www.lazyweekend.home.bean.RResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Yuan
 * @time 2016/7/26  1:06
 * @desc ${TODD}
 */
public interface ApiService {

    @GET(Constant.HOME_URL)
    Call<RResult<List<RItem>>> getRItemList();

    @GET(Constant.DETAIL_URL)
    Call<RResult<RDetail>> getRBookingList(@Query("leo_id") long leoId, @Query("session_id") String sessionId, @Query("v") int v);
}
