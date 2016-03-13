package com.gejiahui.kanzhihu.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gejiahui.kanzhihu.R;
import com.gejiahui.kanzhihu.base.BaseActivity;
import com.orhanobut.logger.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/3/12.
 */
public class AnswerActivity extends BaseActivity {
    @Bind(R.id.web_view)
    WebView webView;
    String answerURL;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_web);
        ButterKnife.bind(this);
        answerURL = getIntent().getStringExtra("url");
        Logger.d(""+answerURL);
        webViewInit();
        new WebLoadingThread().execute();

    }

    private void webViewInit(){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setVerticalScrollBarEnabled(false);

    }

    private String getHtml(String body){
        final StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html dir=\"ltr\" lang=\"zh\">");
        sb.append("<head><style>img{ max-width: 100%!important;height: auto!important; display: block; margin: 10px 0;}</style>");
        sb.append("<meta name=\"viewport\" content=\"width=100%; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\" />");
        sb.append("<link rel=\"stylesheet\" href='file:///android_asset/style.css' type=\"text/css\" media=\"screen\" />");
        sb.append("</head>");
        sb.append("<body style=\"padding:0px 8px 8px 8px; word-wrap:break-word;\">");
        sb.append("<div id=\"pagewrapper\">");
        sb.append("<div id=\"mainwrapper\" class=\"clearfix\">");
        sb.append("<div id=\"maincontent\">");
        sb.append("<div class=\"post\">");
        sb.append("<div class=\"posthit\">");
        sb.append("<div class=\"postinfo\">");
        sb.append("</div>");
        sb.append("<div class=\"gjh\">");
        sb.append(body);
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    private  String getAnswerBody(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements content = doc.getElementsByClass("zm-editable-content");
        Logger.d(""+content.size());
        if(content.size()>3){
            Element answerBody = content.get(3);
            return  answerBody.toString();
        }
        else{
            return "<div class=\"answer-status\" id=\"answer-status\">\n" +
                    "<a class=\"zg-right\" data-tip=\"s$b$为什么回答会被建议修改？\" href=\"/question/24752645\"><i class=\"zg-icon zg-icon-question-mark\"></i></a>\n" +
                    "<p>回答等待修改（已修改・评估中）：<a href=\"/question/20258015\">不规范转载</a></p>\n" +
                    "<p class=\"note\">\n" +
                    "\n" +
                    "作者修改内容通过后，回答会重新显示。如果一周内未得到有效修改，回答会自动折叠。\n" +
                    "\n" +
                    "</p>\n" +
                    "</div>";
        }
    }

    class WebLoadingThread extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... params) {
            return getHtml( getAnswerBody(answerURL));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            webView.loadDataWithBaseURL("", s, "text/html", "utf-8", "");
        }
    }
}
