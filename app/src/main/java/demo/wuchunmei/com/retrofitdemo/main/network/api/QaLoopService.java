package demo.wuchunmei.com.retrofitdemo.main.network.api;


import demo.wuchunmei.com.retrofitdemo.main.model.BaseEntity;
import demo.wuchunmei.com.retrofitdemo.main.model.RecommendList;
import demo.wuchunmei.com.retrofitdemo.main.model.RecommendQA;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by wuchunmei on 10/18/17.
 */
public interface QaLoopService {
    @GET("cms/getDataBySize?sectionIds=29191&pageNo=1&pageSize=5")
    Observable<BaseEntity<RecommendList>> getQaLoop();
}
