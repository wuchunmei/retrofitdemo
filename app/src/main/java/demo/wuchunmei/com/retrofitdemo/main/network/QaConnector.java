package demo.wuchunmei.com.retrofitdemo.main.network;

import android.content.Context;


import demo.wuchunmei.com.retrofitdemo.main.model.BaseEntity;
import demo.wuchunmei.com.retrofitdemo.main.model.RecommendList;
import demo.wuchunmei.com.retrofitdemo.main.model.RecommendQA;
import demo.wuchunmei.com.retrofitdemo.main.network.api.QaLoopService;
import demo.wuchunmei.com.retrofitdemo.main.network.util.ResponseResult;
import io.reactivex.Observable;

/**
 * Created by wuchunmei on 10/18/17.
 */
public class QaConnector {
    private static QaLoopService create(Context context){
        return RetrofitWrapper.INSTANCE.getInstance(context.getApplicationContext()).create(QaLoopService.class);
    }

    public static Observable<BaseEntity<RecommendList>> getQa(Context context) {
        return create(context.getApplicationContext()).getQaLoop();
    }
}
