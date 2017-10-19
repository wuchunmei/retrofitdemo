package demo.wuchunmei.com.retrofitdemo.main.network.api;


import java.util.Map;

import demo.wuchunmei.com.retrofitdemo.main.model.User;
import demo.wuchunmei.com.retrofitdemo.main.network.util.ResponseResult;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


/**
 * Created by wuchunmei on 10/18/17.
 */
public interface LoginService {
    @GET("user/login")
    Observable<ResponseResult<User>> login(@QueryMap Map<String, Object> request);
}
