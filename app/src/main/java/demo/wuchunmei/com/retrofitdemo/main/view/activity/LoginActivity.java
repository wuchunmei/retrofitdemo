package demo.wuchunmei.com.retrofitdemo.main.view.activity;


import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import demo.wuchunmei.com.retrofitdemo.R;
import demo.wuchunmei.com.retrofitdemo.main.main.BaseActivity;
import demo.wuchunmei.com.retrofitdemo.main.presenter.LoginPresenter;
import demo.wuchunmei.com.retrofitdemo.main.view.iface.ILoginView;

/**
 * Created by wuchunmei on 10/18/17.
 */
public class LoginActivity extends BaseActivity implements ILoginView {
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;

    private LoginPresenter mLoginPresenter = new LoginPresenter(this, this);


    @Override
    protected String getSimpleName() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initBundleData() {

    }


    @OnClick({R.id.login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();

                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                    return;
                }

                mLoginPresenter.login(this, userName, password);
                break;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void closeLoading() {

    }

    @Override
    public void showToast(String msg) {

    }
}
