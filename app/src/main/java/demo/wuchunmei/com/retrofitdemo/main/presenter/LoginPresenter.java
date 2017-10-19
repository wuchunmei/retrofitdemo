package demo.wuchunmei.com.retrofitdemo.main.presenter;

import android.content.Context;
import android.util.Log;

import java.util.Map;

import demo.wuchunmei.com.retrofitdemo.main.model.User;
import demo.wuchunmei.com.retrofitdemo.main.network.LoginConnector;
import demo.wuchunmei.com.retrofitdemo.main.network.util.DefaultDisposableObserver;
import demo.wuchunmei.com.retrofitdemo.main.network.util.HttpRequest;
import demo.wuchunmei.com.retrofitdemo.main.network.util.ResponseResult;
import demo.wuchunmei.com.retrofitdemo.main.view.iface.ILoginView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wuchunmei on 10/18/17.
 */
public class LoginPresenter {
    private ILoginView mLoginView;
    private Context mContext;

    public LoginPresenter(ILoginView mLoginView, Context mContext) {
        this.mLoginView = mLoginView;
        this.mContext = mContext;
    }

    public void login(Context context,String userName, String password){
        //构建请求数据
        Map<String, Object> request = HttpRequest.getRequest();
        request.put("username", userName);
        request.put("password", password);
        LoginConnector.login(context,request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultDisposableObserver<ResponseResult<User>>() {
            @Override
            public void onNext(@NonNull ResponseResult<User> userResponseResult) {
                if (userResponseResult.success()) {
                    User user = userResponseResult.data;
                    Log.i("wu", "userResponseResult>>"+user.getUid());
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("wu", "e>>" + e);
            }
        });

    }
}
