package com.shen.springbootwechat.Controller;

import com.alibaba.fastjson.JSON;
import com.shen.springbootwechat.Bean.*;
import com.shen.springbootwechat.Interface.ArticleInterface;
import com.shen.springbootwechat.Interface.MaterialInterface;
import com.shen.springbootwechat.Interface.MenuInterface;
import com.shen.springbootwechat.Interface.UserTagInterface;
import com.shen.springbootwechat.Service.WeChatService;
import com.shen.springbootwechat.Util.WeChatContant;
import com.shen.springbootwechat.Util.WeChatHtml;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class WeChatTestController {

    @Resource
    WeChatService weChatService;

    @Resource
    MaterialInterface materialInterface;

    @Resource
    MenuInterface menuInterface;

    @Resource
    ArticleInterface articleInterface;

    @Resource
    UserTagInterface userTagInterface;

    @GetMapping("/hello")
    public String welcome() {
        return "hello springboot!";
    }

    @GetMapping("/token")
    public String getAccessToken() {
        try {
            return weChatService.getAccessToken();
        } catch (RuntimeException e) {
            return "获取失败，请稍后再试！";
        }
    }

    @GetMapping("/user")
    public String getUserInfo(){
        return weChatService.getUserInfo("oT7V954WZK9gzaKsbBneH0yD17oc");
    }

    @GetMapping("/qryMaterialCount")
    public String qryMaterialCount(){
        String token = weChatService.getAccessToken();
        return materialInterface.qryMaterialCount(token);
    }

    @GetMapping("/addMaterial")
    public String addMaterial(){
        String token = weChatService.getAccessToken();
        String path = "/Users/victorshen/Pictures/wechat/cover.jpg";
        String type = WeChatContant.MATERIAL_TYPE_THUMB;
        return materialInterface.addMaterial(token,path,type);
    }

    @GetMapping("/downMaterial")
    public boolean downMaterial(){
        String token = weChatService.getAccessToken();
        String media_id = WeChatContant.mediaID_cover;
        String path = "/Users/victorshen/Pictures/wechat/cover_bkp.jpg";
        return materialInterface.downMaterial(token,media_id,path);
    }

    @GetMapping("/delMaterial")
    public String delMaterial(){
        String token = weChatService.getAccessToken();
        String media_id = WeChatContant.mediaID_cover;
        return materialInterface.delMeterial(token,media_id);
    }

    @GetMapping("/qryMaterialList")
    public String qryMaterialList(){
        String token = weChatService.getAccessToken();

        QryMaterial qryMaterial = new QryMaterial();
        qryMaterial.setType(WeChatContant.MATERIAL_TYPE_IMAGE);
        qryMaterial.setOffset("0");
        qryMaterial.setCount("5");

        return materialInterface.qryMaterialList(token,qryMaterial);
    }

    @GetMapping("/addTemMaterial")
    public String addTemMaterial(){
        String token = weChatService.getAccessToken();
        String path = "/Users/victorshen/Pictures/wechat/code.jpg";
        String type = WeChatContant.MATERIAL_TYPE_IMAGE;
        return materialInterface.addTemMaterial(token,path,type);
    }

    @GetMapping("/downTemMaterial")
    public boolean downTemMaterial(){
        String token = weChatService.getAccessToken();
        String media_id = "Mz4JgRh5iT9PbFqVpFmMjzPNLxlnBJD8UU6PdobJd5WSzxKeTbDuyO7_aCaPpDRq";
        String path = "/Users/victorshen/Pictures/wechat/code_bkp.jpg";
        return materialInterface.downTemMaterial(token,media_id,path);
    }

    @GetMapping("/addNewsMaterial")
    public String addNewsMaterial(){
        String token = weChatService.getAccessToken();

        ItemNewsMaterial itemNewsMaterial = new ItemNewsMaterial();
        itemNewsMaterial.setTitle("基于微信公众号的智能语音通知系统");
        itemNewsMaterial.setAuthor("沈鹏飞");
        itemNewsMaterial.setDigest("毕业设计，指导老师：黄亮");
        itemNewsMaterial.setContent(WeChatHtml.HORIZONTAL_TABLE);
        itemNewsMaterial.setContent_source_url("http://www.ddrj.com/callcenter/index.html");
        itemNewsMaterial.setThumb_media_id(WeChatContant.mediaID_cover);
        itemNewsMaterial.setNeed_open_comment("0");
        itemNewsMaterial.setOnly_fans_can_comment("0");

        NewsMaterial newsMaterial = new NewsMaterial();
        List<ItemNewsMaterial> articles = new ArrayList<>();
        articles.add(itemNewsMaterial);
        newsMaterial.setArticles(articles);

        return materialInterface.addNewsMaterial(token,newsMaterial);
    }

    @GetMapping("/getNewsMaterial")
    public String getNewsMaterial(){
        String token = weChatService.getAccessToken();
        return materialInterface.getNewsMaterial(token,WeChatContant.mediaID_news);
    }

    @GetMapping("/editNewsMaterial")
    public String editNewsMaterial(){
        String token = weChatService.getAccessToken();

        ItemNewsMaterial item = new ItemNewsMaterial();
        item.setTitle("基于微信公众号的智能语音通知系统");
        item.setAuthor("沈鹏飞");
        item.setDigest("毕业设计，指导老师：黄亮");
        item.setContent(WeChatHtml.README_ARTICLE);
        item.setContent_source_url("http://www.ddrj.com/callcenter/index.html");
        item.setThumb_media_id(WeChatContant.mediaID_cover);
        item.setNeed_open_comment("0");
        item.setOnly_fans_can_comment("0");

        EditNewsMaterial editMaterial = new EditNewsMaterial();
        editMaterial.setMedia_id(WeChatContant.mediaID_news);
        editMaterial.setIndex("0");
        editMaterial.setArticles(item);

        return materialInterface.editNewsMaterial(token,editMaterial);
    }

    @GetMapping("/delNewsMaterial")
    public String delNewsMaterial(){
        String token = weChatService.getAccessToken();
        return materialInterface.delNewsMaterial(token,WeChatContant.mediaID_news);
    }

    @GetMapping("/qryNewsMaterialCount")
    public String qryNewsMaterialCount(){
        String token = weChatService.getAccessToken();
        return materialInterface.qryNewsMaterialCount(token);
    }

    @GetMapping("/qryNewsMaterialList")
    public String qryNewsMaterialList(){
        String token = weChatService.getAccessToken();
        QryNewsMaterial qryImgAndTextMaterial = new QryNewsMaterial();
        qryImgAndTextMaterial.setOffset("0");
        qryImgAndTextMaterial.setCount("5");
        qryImgAndTextMaterial.setNo_content("0");
        return materialInterface.qryNewsMaterialList(token,qryImgAndTextMaterial);
    }

    @GetMapping("/addMenu")
    public String addMenu(){
        String token = weChatService.getAccessToken();
        String openid = "oT7V954WZK9gzaKsbBneH0yD17oc";

        Menu menu = new Menu();

            List<MenuOne> listOne = new ArrayList<>(3);

            /* 账户 */
            MenuOne menuOneLeft = new MenuOne();
            menuOneLeft.setName("账户");

                List<MenuTwo> listTwoForLeft = new ArrayList<>(5);

                MenuTwo menuTwo1ForLeft = new MenuTwo();
                menuTwo1ForLeft.setType(WeChatContant.MENU_TYPE_VIEW);
                menuTwo1ForLeft.setName("注册");
                menuTwo1ForLeft.setUrl("http://47.111.1.11/authorize/getOpenID?state=Register");
                listTwoForLeft.add(menuTwo1ForLeft);

                MenuTwo menuTwo2ForLeft = new MenuTwo();
                menuTwo2ForLeft.setType(WeChatContant.MENU_TYPE_VIEW);
                menuTwo2ForLeft.setName("登录");
                menuTwo2ForLeft.setUrl("http://47.111.1.11/authorize/getOpenID?state=Login");
                listTwoForLeft.add(menuTwo2ForLeft);

                MenuTwo menuTwo3ForLeft = new MenuTwo();
                menuTwo3ForLeft.setType(WeChatContant.MENU_TYPE_VIEW);
                menuTwo3ForLeft.setName("退出");
                menuTwo3ForLeft.setUrl("http://47.111.1.11/authorize/getOpenID?state=unLogin");
                listTwoForLeft.add(menuTwo3ForLeft);

            menuOneLeft.setSub_button(listTwoForLeft);
            listOne.add(menuOneLeft);

            /* 功能 */
            MenuOne menuOneMiddle = new MenuOne();
            menuOneMiddle.setName("功能");

                List<MenuTwo> listTwoForMiddle = new ArrayList<>(5);

                MenuTwo menuTwo1ForMiddle = new MenuTwo();
                menuTwo1ForMiddle.setType(WeChatContant.MENU_TYPE_VIEW);
                menuTwo1ForMiddle.setName("用户模块");
                menuTwo1ForMiddle.setUrl("http://47.111.1.11/authorize/getOpenID?state=User");
                listTwoForMiddle.add(menuTwo1ForMiddle);

                MenuTwo menuTwo2ForMiddle = new MenuTwo();
                menuTwo2ForMiddle.setType(WeChatContant.MENU_TYPE_VIEW);
                menuTwo2ForMiddle.setName("管理者模块");
                menuTwo2ForMiddle.setUrl("http://47.111.1.11/authorize/getOpenID?state=Manager");
                listTwoForMiddle.add(menuTwo2ForMiddle);

                MenuTwo menuTwo3ForMiddle = new MenuTwo();
                menuTwo3ForMiddle.setType(WeChatContant.MENU_TYPE_VIEW);
                menuTwo3ForMiddle.setName("运营人员模块");
                menuTwo3ForMiddle.setUrl("http://47.111.1.11/authorize/getOpenID?state=Runner");
                listTwoForMiddle.add(menuTwo3ForMiddle);

            menuOneMiddle.setSub_button(listTwoForMiddle);
            listOne.add(menuOneMiddle);

            /* 其他 */
            MenuOne menuOneRight = new MenuOne();
            menuOneRight.setName("其他");

                List<MenuTwo> listTwoForRight = new ArrayList<>(5);

                MenuTwo menuTwo1ForRight = new MenuTwo();
                menuTwo1ForRight.setType(WeChatContant.MENU_TYPE_MEDIA_ID);
                menuTwo1ForRight.setName("二维码");
                menuTwo1ForRight.setMedia_id(WeChatContant.mediaID_code);
                listTwoForRight.add(menuTwo1ForRight);

                MenuTwo menuTwo2ForRight = new MenuTwo();
                menuTwo2ForRight.setType(WeChatContant.MENU_TYPE_VIEW);
                menuTwo2ForRight.setName("开发指引");
                menuTwo2ForRight.setUrl("http://www.ddrj.com/callcenter/index.html");
                listTwoForRight.add(menuTwo2ForRight);

                MenuTwo menuTwo3ForRight = new MenuTwo();
                menuTwo3ForRight.setType(WeChatContant.MENU_TYPE_ARTICLE_ID);
                menuTwo3ForRight.setName("项目介绍");
                menuTwo3ForRight.setArticle_id(WeChatContant.articleID_news);
                listTwoForRight.add(menuTwo3ForRight);

            menuOneRight.setSub_button(listTwoForRight);
            listOne.add(menuOneRight);

        menu.setButton(listOne);

        return menuInterface.addMenu(token,menu);
    }

    @GetMapping("/qryMenu")
    public String qryMenu(){
        String token = weChatService.getAccessToken();
        return menuInterface.qryMenu(token);
    }

    @GetMapping("/delMenu")
    public String delMenu(){
        String token = weChatService.getAccessToken();
        return menuInterface.delMenu(token);
    }

    @GetMapping("/addArticle")
    public String addArticle(){
        String token = weChatService.getAccessToken();
        return articleInterface.addArticle(token,WeChatContant.mediaID_news);
    }

    @GetMapping("/qryArticle")
    public String qryArticle(){
        String token = weChatService.getAccessToken();
        return articleInterface.qryArticle(token,WeChatContant.publishID_news);
    }

    @GetMapping("/getArticle")
    public String getArticle(){
        String token = weChatService.getAccessToken();
        return articleInterface.getArticle(token,WeChatContant.articleID_news);
    }

    @GetMapping("/qryArticleList")
    public String qryArticleList(){
        String token = weChatService.getAccessToken();
        QryArticle qryArticle = new QryArticle();
        qryArticle.setOffset("0");
        qryArticle.setCount("5");
        qryArticle.setNo_content("1");
        return articleInterface.qryArticleList(token,qryArticle);
    }

    // link = { http://47.111.1.11/test/getTagList }
    @GetMapping("/getTagList")
    public String getTagList(){
        String token = weChatService.getAccessToken();
        return userTagInterface.getTag(token);
    }

    // link = { http://47.111.1.11/test/createTag?name=NAME }
    @GetMapping("/createTag")
    public String createTag(String name){
        String token = weChatService.getAccessToken();
        return userTagInterface.createTag(token,name);
    }

    // link = { http://47.111.1.11/test/batchTaging?openid=oT7V954WZK9gzaKsbBneH0yD17oc&id=ID }
    @GetMapping("/batchTaging")
    public String batchTaging(String openid, Integer id){
        String token = weChatService.getAccessToken();
        List<String> list = new ArrayList<>();
        list.add(openid);
        return userTagInterface.batchTaging(token,list,id);
    }

    // link = { http://47.111.1.11/test/getUserTag?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @GetMapping("/getUserTag")
    public String getUserTag(String openid){
        String token = weChatService.getAccessToken();
        UserTagListBean userTagListBean;
        try {
            String json = userTagInterface.getUserTag(token,openid);
            userTagListBean = JSON.parseObject(json,UserTagListBean.class);
        } catch (Exception e) {
            userTagListBean = new UserTagListBean();
        }
        return userTagListBean.getTagid_list().toString();
    }

    // link = { http://47.111.1.11/test/batchunTaging?openid=oT7V954WZK9gzaKsbBneH0yD17oc&id=ID }
    @GetMapping("/batchunTaging")
    public String batchunTaging(String openid, Integer id){
        String token = weChatService.getAccessToken();
        List<String> list = new ArrayList<>();
        list.add(openid);
        return userTagInterface.batchunTaging(token,list,id);
    }
}
