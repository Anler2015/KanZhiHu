package com.gejiahui.kanzhihu.ui;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gejiahui.kanzhihu.R;
import com.gejiahui.kanzhihu.base.BaseActivity;
import com.gejiahui.kanzhihu.model.UserDetail;
import com.gejiahui.kanzhihu.net.Request4UserDetail;
import com.gejiahui.kanzhihu.net.RequestManager;
import com.orhanobut.logger.Logger;
import com.victor.loading.rotate.RotateLoading;

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
    @Bind(R.id.answer_activity_title)
    TextView title;
    @Bind(R.id.web_view)
    WebView webView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.user_signature)
    TextView userSignature;
    @Bind(R.id.vote)
    TextView voteNumber;
    @Bind(R.id.ans_avatar)
    SimpleDraweeView avatar;
    @Bind(R.id.rotateloading)
    RotateLoading rotateLoading;

    private String answerURL;
    private String userURL;
    private Request4UserDetail request4UserDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_web);
        ButterKnife.bind(this);
        rotateLoading.start();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        answerURL = getIntent().getStringExtra("url");
        title.setText(getIntent().getStringExtra("title"));
        userURL = getIntent().getStringExtra("userurl");
        getUserInfo();
        Logger.d(""+answerURL);
        Logger.d(""+userURL);
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
//        sb.append("<!DOCTYPE html>");
//        sb.append("<html dir=\"ltr\" lang=\"zh\">");
//        sb.append("<head><style>img{ max-width: 100%!important;height: auto!important; display: block; margin: 10px 0;}</style>");
//        sb.append("<meta name=\"viewport\" content=\"width=100%; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\" />");
//        sb.append("<link rel=\"stylesheet\" href='file:///android_assets/style.css' type=\"text/css\" media=\"screen\" />");
//        sb.append("</head>");
//        sb.append("<body style=\"padding:0px 8px 8px 8px; word-wrap:break-word;\">");
//        sb.append("<div id=\"pagewrapper\">");
//        sb.append("<div id=\"mainwrapper\" class=\"clearfix\">");
//        sb.append("<div id=\"maincontent\">");
//        sb.append("<div class=\"post\">");
//        sb.append("<div class=\"posthit\">");
//        sb.append("<div class=\"postinfo\">");
//        sb.append("</div>");
//        sb.append("<div class=\"gjh\">");
//        sb.append(body);
//        sb.append("</div>");
//        sb.append("</div>");
//        sb.append("</div>");
//        sb.append("</div>");
//        sb.append("</div>");
//        sb.append("</div>");
//        sb.append("</body>");
//        sb.append("</html>");

        sb.append("<!DOCTYPE html>");
        sb.append("<html dir=\"ltr\" lang=\"zh\">");
        sb.append("<head><style>img{ max-width: 100%!important;height: auto!important; display: block; margin: 10px 0;}</style>");
        sb.append("<meta name=\"viewport\" content=\"width=100%; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\" />");
        sb.append("<link rel=\"stylesheet\" href='file:///android_assets/zh.css' type=\"text/css\" media=\"screen\" />");
        sb.append("</head>");
        sb.append("<body style=\"padding:0px 8px 8px 8px; word-wrap:break-word;\">");
        sb.append("<div class=\"zu-main-content-inner\">");
        sb.append("<div id=\"zh-question-answer-wrap\" class=\"zh-question-answer-wrapper autohide-false navigable\">");
        sb.append("<div class=\"zm-item-answer  zm-item-expanded\">");
        sb.append("<div class=\"zm-item-rich-text js-collapse-body\">");
        sb.append("<div class=\"zm-editable-content clearfix\">");
        sb.append(body);
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

        }catch (IOException e){

        }
        Elements content = doc.getElementsByClass("zm-editable-content");
        Logger.d(content.toString());
        for(int i = 0 ; i < content.size();i++){
            Element element = content.get(i);
            Elements ans = element.getElementsByClass("clearfix");
            if(ans.size()!=0){
                return imgReplace(ans.first().toString());
            }


        }

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


    /**
     * 将html中从默认的地址换成震震的imgUrl
     * @param html
     * @return
     */
    private String imgReplace(String html){
        Document  doc = Jsoup.parse(html);
        Elements imgs = doc.getElementsByTag("img");
        for(int i = 0; i < imgs.size(); i++){
            String imgUrl = imgs.get(i).attr("data-actualsrc");
            imgs.get(i).attr("src",imgUrl);
        }
        return doc.toString();
    }

    private void getUserInfo(){
        request4UserDetail = new Request4UserDetail(userURL, new Response.Listener<UserDetail>() {
            @Override
            public void onResponse(UserDetail response) {
                userName.setText(response.getName());
                userSignature.setText(response.getSignature());
            //    avatar.setImageURI(Uri.parse(response.getAvatar()));
                voteNumber.setText(getIntent().getStringExtra("vote"));


                GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                        .setFadeDuration(400)
                        .build();
                DraweeController controller = Fresco.newDraweeControllerBuilder().setUri(Uri.parse(response.getAvatar())).setOldController(avatar.getController()).build();
                controller.setHierarchy(hierarchy);
                avatar.setController(controller);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestManager.addQueue(request4UserDetail);
    }

    class WebLoadingThread extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... params) {
            return getHtml( getAnswerBody(answerURL));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            webView.loadDataWithBaseURL(null, s, "text/html", "utf-8", null);
            rotateLoading.stop();
        }
    }
}
