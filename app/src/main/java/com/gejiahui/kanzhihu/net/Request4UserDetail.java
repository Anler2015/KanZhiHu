package com.gejiahui.kanzhihu.net;

import com.alibaba.fastjson.JSON;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.gejiahui.kanzhihu.model.Answer;
import com.gejiahui.kanzhihu.model.Content;
import com.gejiahui.kanzhihu.model.UserDetail;
import com.orhanobut.logger.Logger;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by gejiahui on 2016/3/15.
 */
public class Request4UserDetail extends Request<UserDetail>{
    private Response.Listener<UserDetail> listener;

    public Request4UserDetail(String url, Response.Listener<UserDetail> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.listener = listener;

    }


    @Override
    protected Response<UserDetail> parseNetworkResponse(NetworkResponse response) {
        try {
            String parsed = new String(response.data,  HttpHeaderParser.parseCharset(response.headers));

            UserDetail res = JSON.parseObject(parsed,UserDetail.class);

            if(res.getError().equals("")){
                return Response.success(res,
                        HttpHeaderParser.parseCacheHeaders(response));
            }
            else {  //error不为空说明出错
                return Response.error(new ParseError4String(res.getError()));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError4String(e));
        }

    }

    @Override
    protected void deliverResponse(UserDetail response) {
        listener.onResponse(response);
    }
}
