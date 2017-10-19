package demo.wuchunmei.com.retrofitdemo.main.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wuchunmei on 10/18/17.
 */
public class User implements Serializable,Comparable {
    //token	string	用户登录生成的token
    //uid	string	用户Id
    @SerializedName("token")
    private String token;
    @SerializedName("uid")
    private String uid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "User{" +
                "token=" + token +
                ", uid='" + uid + '\'';
    }

    @Override
    public int compareTo(Object object) {
        if (this==object){
            return 0;
        }
        return -1;
    }
}
