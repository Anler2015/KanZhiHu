package com.gejiahui.kanzhihu.CallBack;

import com.gejiahui.kanzhihu.model.Answer;

import java.util.ArrayList;

/**
 * Created by gejiahui on 2016/3/14.
 */
public interface LoadResultCallBack {
    void onSuccess(ArrayList<Answer> result);
    void onFail(String failReason);
}
