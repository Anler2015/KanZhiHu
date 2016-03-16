package com.gejiahui.kanzhihu.model;





import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * Created by gejiahui on 2016/3/11.
 */
public class Answer {
//    问题url：http://www.zhihu.com/question/questionid
//    答案url：http://www.zhihu.com/question/questionidanswerid
//    用户主页url：http://www.zhihu.com/people/authorhash
//    count(number)，答案数量
    //    answers(array)，答案列表，字段如下：
    //    title(string)，文章id
    //    time(datetime)，发表时间
    //    summary(string)，答案摘要
    //    questionid(string)，问题id，8位数字
    //    answerid(string)，答案id，8位数字
    //    authorname(string)，答主名称
    //    authorhash(string)，答主hash
    //    avatar(string)，答主头像url
    //    vote(number)，赞同票数

    private static String QUESTION_URL_PREFIX = "http://www.zhihu.com/question/";
    private static String ANSWER_URL_POSTFIX = "/answer/";
   // private static String USER_URL_PREFIX = "http://www.zhihu.com/people/";
    private static String USER_URL_PREFIX = "http://api.kanzhihu.com/userdetail2/";

    private String title;
    private String datetime;
    private String summary;
    private String questionId;
    private String answerId;
    private String authorName;
    private String authorHash;
    private String avatarUrl;
    private String vote;


    public static ArrayList<Answer> parse(JSONArray array){
        ArrayList<Answer> answers = new ArrayList<>();
        for(int i = 0; i < array.size(); i++){
            Answer answer = new Answer();
            JSONObject jsonObject = array.getJSONObject(i);
            answer.title = jsonObject.getString("title");
            answer.datetime = jsonObject.getString("time");
            answer.summary = jsonObject.getString("summary");
            answer.questionId = jsonObject.getString("questionid");
            answer.answerId = jsonObject.getString("answerid");
            answer.authorName = jsonObject.getString("authorname");
            answer.authorHash = jsonObject.getString("authorhash");
            answer.avatarUrl = jsonObject.getString("avatar");
            answer.vote = jsonObject.getString("vote");
            answers.add(answer);
        }
        return answers;
    }

    public String getQuestionUrl(){
        return QUESTION_URL_PREFIX + questionId;
    }

    public String getAnswerUrl(){
        return QUESTION_URL_PREFIX + questionId + ANSWER_URL_POSTFIX + answerId;
    }

    public String getUserUrl(){
        return USER_URL_PREFIX + authorHash;
    }

    public static String getQuestionUrlPrefix() {
        return QUESTION_URL_PREFIX;
    }

    public static void setQuestionUrlPrefix(String questionUrlPrefix) {
        QUESTION_URL_PREFIX = questionUrlPrefix;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public static String getAnswerUrlPostfix() {
        return ANSWER_URL_POSTFIX;
    }

    public static void setAnswerUrlPostfix(String answerUrlPostfix) {
        ANSWER_URL_POSTFIX = answerUrlPostfix;
    }

    public static String getUserUrlPrefix() {
        return USER_URL_PREFIX;
    }

    public static void setUserUrlPrefix(String userUrlPrefix) {
        USER_URL_PREFIX = userUrlPrefix;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorHash() {
        return authorHash;
    }

    public void setAuthorHash(String authorHash) {
        this.authorHash = authorHash;
    }
}
