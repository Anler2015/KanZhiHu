package com.gejiahui.kanzhihu.net;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

/**
 * Created by gejiahui on 2016/3/14.
 */
public class ParseError4String extends VolleyError {
    private String errorReason;
    public ParseError4String(String str)
    {
        super(str);
        errorReason = str;
    }
    public ParseError4String() { }

    public ParseError4String(NetworkResponse networkResponse) {
        super(networkResponse);
    }

    public ParseError4String(Throwable cause) {
        super(cause);
    }

    public String getErrorReason(){
        return errorReason;
    }
}
