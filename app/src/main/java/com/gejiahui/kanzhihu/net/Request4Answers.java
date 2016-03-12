package com.gejiahui.kanzhihu.net;


import com.alibaba.fastjson.JSON;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.gejiahui.kanzhihu.model.Answer;
import com.gejiahui.kanzhihu.model.Content;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


/**
 * Created by gejiahui on 2016/3/12.
 */
public class Request4Answers extends Request<ArrayList<Answer>> {

    private Response.Listener<ArrayList<Answer>> listener;
    /**
     * Creates a new GET request.
     *104
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public Request4Answers(String url, Response.Listener<ArrayList<Answer>> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.listener = listener;

    }
    @Override
    protected Response<ArrayList<Answer>> parseNetworkResponse(NetworkResponse response) {

        try {
            String parsed = new String(response.data,  HttpHeaderParser.parseCharset(response.headers));

            Content res = JSON.parseObject(parsed,Content.class);

            if(res.getError().equals("")){
                return Response.success(Answer.parse(res.getAnswers()),
                        HttpHeaderParser.parseCacheHeaders(response));
            }
            else {  //error不为空说明出错
                return Response.error(new ParseError());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }

    }

    @Override
    protected void deliverResponse(ArrayList<Answer> response) {
        listener.onResponse(response);
    }
}
