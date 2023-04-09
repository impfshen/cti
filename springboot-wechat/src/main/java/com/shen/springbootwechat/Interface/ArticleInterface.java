package com.shen.springbootwechat.Interface;

import com.shen.springbootwechat.Bean.QryArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleInterface {

    @Autowired
    HttpInterface httpInterface;

    /**
     * 发布接口
     * @return
     */
    public String addArticle(String token,String media_id){
        String url = "https://api.weixin.qq.com/cgi-bin/freepublish/submit?access_token=" + token;
        return httpInterface.doPost(url,"{\"media_id\":\"" + media_id + "\"}");
    }

    /**
     * 发布状态轮询接口
     * @return
     */
    public String qryArticle(String token,String publish_id){
        String url = "https://api.weixin.qq.com/cgi-bin/freepublish/get?access_token=" + token;
        return httpInterface.doPost(url,"{\"publish_id\":\"" + publish_id + "\"}");
    }

    /**
     * 通过 article_id 获取已发布文章
     * @return
     */
    public String getArticle(String token,String article_id){
        String url = "https://api.weixin.qq.com/cgi-bin/freepublish/getarticle?access_token=" + token;
        return httpInterface.doPost(url,"{\"article_id\":\"" + article_id + "\"}");
    }

    /**
     * 获取成功发布列表
     * @return
     */
    public String qryArticleList(String token, QryArticle qryArticle){
        String url = "https://api.weixin.qq.com/cgi-bin/freepublish/batchget?access_token=" + token;
        return httpInterface.doPost(url,qryArticle);
    }
}
