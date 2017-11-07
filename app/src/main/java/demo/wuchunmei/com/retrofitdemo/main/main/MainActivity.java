package demo.wuchunmei.com.retrofitdemo.main.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;

import butterknife.OnClick;
import demo.wuchunmei.com.retrofitdemo.R;
import demo.wuchunmei.com.retrofitdemo.main.model.BaseEntity;
import demo.wuchunmei.com.retrofitdemo.main.model.RecommendList;
import demo.wuchunmei.com.retrofitdemo.main.model.RecommendQA;
import demo.wuchunmei.com.retrofitdemo.main.network.QaConnector;
import demo.wuchunmei.com.retrofitdemo.main.network.util.DefaultDisposableObserver;
import demo.wuchunmei.com.retrofitdemo.main.network.util.ResponseResult;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionbar();
    }

    @Override
    protected String getSimpleName() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initBundleData() {

    }

    @OnClick(R.id.login)
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.login:
//                intent = new Intent(this, LoginActivity.class);
//                startActivity(intent);
                QaConnector.getQa(this).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultDisposableObserver<BaseEntity<RecommendList>>(){
                    @Override
                    public void onNext(@NonNull BaseEntity<RecommendList> recommendQABaseEntity) {
                        if(recommendQABaseEntity.isSuccess()){
                           Log.i("wu","data>>"+recommendQABaseEntity.getData().toString());

                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("wu","e>>"+e);
                    }
                });
                break;
        }

    }

}
