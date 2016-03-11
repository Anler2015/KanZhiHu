package com.gejiahui.kanzhihu.model;

/**
 * Created by gejiahui on 2016/3/11.
 */
public class Answers {
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
    private static String USER_URL_PREFIX = "http://www.zhihu.com/people/";

    public String error;
    public int count;
    public String title;
    public String datetime;
    public String summary;
    public String questionId;
    public String answerId;
    public String authorName;
    public String authorHash;
    public String avatarUrl;
    public String vote;

    public String getQuestionUrl(){
        return QUESTION_URL_PREFIX + questionId;
    }

    public String getAnswerUrl(){
        return QUESTION_URL_PREFIX + questionId + ANSWER_URL_POSTFIX + answerId;
    }

    public String getUserUrl(){
        return USER_URL_PREFIX + authorHash;
    }




}
