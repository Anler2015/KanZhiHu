package com.gejiahui.kanzhihu.net;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.gejiahui.kanzhihu.model.UserDetail;

/**
 * Created by gejiahui on 2016/3/15.
 */
public class Request4UserDatail  extends Request<UserDetail>{
    public Request4UserDatail(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    @Override
    protected Response<UserDetail> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    protected void deliverResponse(UserDetail response) {

    }
}
