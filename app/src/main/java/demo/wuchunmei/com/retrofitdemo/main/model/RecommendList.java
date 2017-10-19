package demo.wuchunmei.com.retrofitdemo.main.model;

import java.util.List;

/**
 * Created by wuchunmei on 10/19/17.
 */
public class RecommendList {

    private int total;
    private List<RecommendQA> item;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RecommendQA> getItems() {
        return item;
    }

    public void setItems(List<RecommendQA> items) {
        this.item = items;
    }

    @Override
    public String toString() {
        return "Results [total=" + total + ", item="
                + item + "]";
    }
}
