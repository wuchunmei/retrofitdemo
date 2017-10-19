package demo.wuchunmei.com.retrofitdemo.main.model;

import java.io.Serializable;

/**
 * Created by wuchunmei on 10/19/17.
 */
public class BaseEntity<T> implements Serializable {
    public String code;
    public String message;
    public T data;
    public int success;


    /**
     * 接口请求成功
     */
    public static final int RESPONCE__SUCCESS = 1;



    public BaseEntity(String code) {
        this.code = code;
    }
    public int getCode() {
        return Integer.parseInt(code);
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess(){
        return success == RESPONCE__SUCCESS;
    }


    @Override
    public String toString() {
        return ";code = " + code +
                ";msg = " + message +
                ";data = " + data;
    }
}
