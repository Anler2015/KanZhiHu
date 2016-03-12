package com.gejiahui.kanzhihu.model;

import com.alibaba.fastjson.JSONArray;


/**
 * Created by gejiahui on 2016/3/12.
 */
public class Content  {

    private String error;
    private int count;
    private JSONArray answers;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public JSONArray getAnswers() {
        return answers;
    }

    public void setAnswers(JSONArray answers) {
        this.answers = answers;
    }
}
