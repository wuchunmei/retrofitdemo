package demo.wuchunmei.com.retrofitdemo.main.network;

import android.content.Context;

import java.util.Map;

import demo.wuchunmei.com.retrofitdemo.main.model.User;
import demo.wuchunmei.com.retrofitdemo.main.network.api.LoginService;
import demo.wuchunmei.com.retrofitdemo.main.network.util.ResponseResult;
import io.reactivex.Observable;


/**
 * 登录相关接口调用
 * Created by xm on 2016/8/8.
 */
public class LoginConnector {

    private static LoginService create(Context context){
        return RetrofitWrapper.INSTANCE.getInstance(context.getApplicationContext()).create(LoginService.class);
    }

    public static Observable<ResponseResult<User>> login(Context context, Map<String, Object> request) {
        return create(context.getApplicationContext()).login(request);
    }


}
