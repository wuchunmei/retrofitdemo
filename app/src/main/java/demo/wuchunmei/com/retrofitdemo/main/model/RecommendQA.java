package demo.wuchunmei.com.retrofitdemo.main.model;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by wuchunmei on 2017/8/8 .
 */
public class RecommendQA implements Serializable {
//    private String picUrl;
    private String url;
    private String categoryId;
    private int noteId;
    private String title;
    private List<Map> picList;

    public List<Map> getPicList() {
        return picList;
    }

    public void setPicList(List<Map> picList) {
        this.picList = picList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }


    @Override
    public String toString() {
        return "RecommendQA url="
                + url + ", categoryId="
                + categoryId + ", noteId=" + noteId
                + ", title=" + title + "]";
    }

}
