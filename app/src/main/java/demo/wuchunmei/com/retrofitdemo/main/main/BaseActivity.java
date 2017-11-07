package demo.wuchunmei.com.retrofitdemo.main.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import demo.wuchunmei.com.retrofitdemo.R;



/**
 * Created by wuchunwei on 2017/10/18.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private TextView mTvTitle;
    protected Context mContext;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        //友盟统计
        MobclickAgent.openActivityDurationTrack(false);
        mContext = this;
        mUnbinder = ButterKnife.bind(this);
        initBundleData();
        init();
    }

    protected void initActionbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar == null) return;
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText(R.string.app_name);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return true;
    }

    protected void setActionbarTitle(int resid) {
        mTvTitle.setText(resid);
    }

    protected void setActionbarTitle(String title) {
        mTvTitle.setText(title);
    }

    protected boolean hasFragment() {
        return false;
    }

    /**
     * 获取类名的simplename，用于统计
     *
     * @return
     */
    protected abstract String getSimpleName();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);

    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);

    }

    /**
     * 获取显示view的xml文件ID
     */
    protected abstract int getContentViewId();

    /**
     * 初始化应用程序，设置一些初始化数据,获取数据等操作
     */
    protected abstract void init();

    /**
     * 获取上一个界面传送过来的数据
     */
    protected abstract void initBundleData();

}
