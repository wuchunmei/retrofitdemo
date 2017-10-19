package demo.wuchunmei.com.retrofitdemo.main.network.util;

/**
 * 服务端响应数据
 *
 */
public final class ResponseResult<T> {

    // code 说明：
    // 200 返回正常
    // 500 执行失败

    /**
     * 接口请求成功
     */
    public static final int RESPONCE_CODE_SUCCESS = 200;

    public final int code;
    public String msg;
    public String dataStr;
    public T data;

    public boolean success(){
        return code == RESPONCE_CODE_SUCCESS;
    }

    public ResponseResult(int code) {
        this.code = code;
    }

    public ResponseResult() {
        this.code = RESPONCE_CODE_SUCCESS;
    }

    @Override
    public String toString() {
        return ";code = " + code +
                ";msg = " + msg +
                ";data = " + dataStr;
    }
}
