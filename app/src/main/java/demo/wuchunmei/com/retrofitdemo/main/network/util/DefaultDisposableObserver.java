package demo.wuchunmei.com.retrofitdemo.main.network.util;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by wuchunmei on 10/18/17.
 * 默认实现
 * 减少实际使用时不需要关注onError/onComplete时的代码量
 */
public class DefaultDisposableObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}
