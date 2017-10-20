package demo.wuchunmei.com.retrofitdemo.main.view.widget;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

/**
 * Created by wuchunmei on 9/4/17.
 * 增加getTabView，使我们可以自定义TabIndicator布局
 */
public abstract class PagerAdapterEx extends FragmentPagerAdapter {

    public PagerAdapterEx(FragmentManager fm) {
        super(fm);
    }

    public abstract View getTabView(int position);
}
