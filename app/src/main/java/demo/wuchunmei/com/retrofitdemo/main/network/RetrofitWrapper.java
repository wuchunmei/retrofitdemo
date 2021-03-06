package demo.wuchunmei.com.retrofitdemo.main.network;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import demo.wuchunmei.com.retrofitdemo.main.network.util.CommonInterceptor;
import demo.wuchunmei.com.retrofitdemo.main.network.util.MD5Util;
import demo.wuchunmei.com.retrofitdemo.main.network.util.NetworkUtils;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wuchunmei on 10/18/17.
 */
public enum  RetrofitWrapper {
    INSTANCE;
    static final int CONNECTION_TIMEOUT = 6000;
    static final int READ_TIMEOUT = 50000;

    static final int SHORT_CONNECTION_TIMEOUT = 2000;
    static final int SHORT_READ_TIMEOUT = 3000;

    //private Gson mGson;
    private Retrofit mRetrofit, mFastRetrofit;
    private Context mApplicationContext;
    private Map<String, String> mHeaderMap;
    private CommonInterceptor mInterceptor;//通用拦截器
    public static final String BASE_API = "http://wireless.tianya.cn/v/";

    public RetrofitWrapper getInstance(Context applicationContext) {
        if (mApplicationContext == null || mRetrofit == null) {
            init(applicationContext);
        }
        return this;
    }

    private void init(Context applicationContext) {
        Log.v("RetrofitWrapper", "init");
        mApplicationContext = applicationContext;
        mHeaderMap = getStaticHeaders();

        String proxyHost = NetworkUtils.getConnectProxy(mApplicationContext);
        boolean bool = NetworkUtils.getCTWapHolder();//用一个变量保存之前的状态
        //切换CTWapHolder状态作为retry使用的proxyHost
        NetworkUtils.setCTWapHolder(!bool);
        String retryProxyHost = NetworkUtils.getConnectProxy(mApplicationContext);
        NetworkUtils.setCTWapHolder(bool);//获取完新的proxy后恢复其状态

        mInterceptor = new CommonInterceptor(proxyHost, retryProxyHost, mHeaderMap);

        //打印日志
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //通用mRetrofit
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS).
                readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).addInterceptor(mInterceptor).addInterceptor(logging).build();
        mRetrofit = new Retrofit.Builder().baseUrl(BASE_API).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    private Map<String, String> getStaticHeaders() {
        Map<String, String> map = new HashMap<String, String>();
        String verName = null;
        int verCode = 0;
        String uuid = null;

        //获取versionCode、versionName
        try {
            PackageInfo pm = mApplicationContext.getPackageManager().getPackageInfo(mApplicationContext.getPackageName(), 0);
            verCode = pm.versionCode;
            verName = pm.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        map.put("vcode", "" + verCode);
        map.put("vname", verName);
        //获取uuid
        TelephonyManager telephonyManager = (TelephonyManager) mApplicationContext.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null
                || TextUtils.isEmpty(telephonyManager.getDeviceId())) {
            uuid = UUID.randomUUID().toString().replace("-", "");
        } else {
            uuid = MD5Util.MD5(telephonyManager.getDeviceId().toString());
        }
        map.put("uuid", uuid);
        //获取机器码及版本号
        map.put("mobile", android.os.Build.MODEL + ";" + android.os.Build.DEVICE);
        map.put("sdk", "" + android.os.Build.VERSION.SDK_INT);
        map.put("os", "android");
        return map;
    }

    /**
     * 大部分接口使用
     *
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

}
