package com.shen.springbootwechat.Interface;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shen.springbootwechat.Bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialInterface {

    @Autowired
    HttpInterface httpInterface;

    /**
     * 获取永久素材数量
     * @return
     */
    public String qryMaterialCount(String token){
        String url = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=" + token;
        return httpInterface.doGet(url);
    }

    /**
     * 上传非图文的永久素材
     * @param path 文件路径
     * @param type 素材类型
     * @return
     */
    public String addMaterial(String token,String path,String type){
        String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + token+"&type=" + type;
        return httpInterface.doFlie(url,path);
    }

    /**
     * 上传图文消息内的图片获取URL 用来在图文消息正文中填写url
     * @param path 图片路径 绝对
     * @return
     */
    public String addNewsImgMeterial(String token,String path){
        String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + token;
        return httpInterface.doFlie(url,path);
    }

    /**
     * 下载永久非视频与非图文的素材
     * @param media_id
     * @param path
     * @return
     */
    public boolean downMaterial(String token,String media_id,String path){
        String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=" + token;
        return httpInterface.doDown(url,path,"{\"media_id\":\"" + media_id + "\"}");
    }

    /**
     * 删除永久素材
     * @param media_id 素材id
     * @return
     */
    public String delMeterial(String token,String media_id){
        String url = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=" + token;
        return httpInterface.doPost(url,"{\"media_id\":\"" + media_id + "\"}");
    }

    /**
     * 获取素材列表
     * @param material
     * @return
     */
    public String qryMaterialList(String token, QryMaterial material){
        String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" + token;
        return httpInterface.doPost(url,material);
    }

    /**
     * 上传临时素材
     * @param path 文件路径
     * @param type 素材类型
     * @return
     */
    public String addTemMaterial(String token,String path,String type){
        String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" + token + "&type=" + type;
        return httpInterface.doFlie(url,path);
    }

    /**
     * 下载临时素材
     * @param media_id id
     * @param path 保存文件的绝对路径包括文件名
     */
    public boolean downTemMaterial(String token,String media_id,String path){
        String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + token + "&media_id=" + media_id;
        return httpInterface.doDown(url,path);
    }

    /**
     * 上传图文永久素材
     * @param newsMaterial
     * @return
     */
    public String addNewsMaterial(String token,NewsMaterial newsMaterial){
        String url = "https://api.weixin.qq.com/cgi-bin/draft/add?access_token=" + token;
        return httpInterface.doPost(url,newsMaterial);
    }

    /**
     * 获取图文永久素材
     * @param media_id
     * @return
     */
    public String getNewsMaterial(String token,String media_id){
        String url = "https://api.weixin.qq.com/cgi-bin/draft/get?access_token=" + token;
        String rel = httpInterface.doPost(url,"{\"media_id\":\"" + media_id + "\"}");
        JSONObject jsonObject = JSONObject.parseObject(rel);
        String news_item = jsonObject.getString("news_item");
        List<ItemRespNewsMaterial> list = JSONArray.parseArray(news_item,ItemRespNewsMaterial.class);
        RespNewsMaterial itemList = new RespNewsMaterial(list);
        return itemList.toString();
    }

    /**
     * 修改素材图文消息
     * @param material
     * @return
     */
    public String editNewsMaterial(String token, EditNewsMaterial material){
        String url = "https://api.weixin.qq.com/cgi-bin/draft/update?access_token=" + token;
        return httpInterface.doPost(url,material);
    }

    /**
     * 删除图文素材
     * @return
     */
    public String delNewsMaterial(String token,String media_id){
        String url = "https://api.weixin.qq.com/cgi-bin/draft/delete?access_token=" + token;
        return httpInterface.doPost(url,"{\"media_id\":\"" + media_id + "\"}");
    }

    /**
     * 获取图文素材数量
     * @return
     */
    public String qryNewsMaterialCount(String token){
        String url = "https://api.weixin.qq.com/cgi-bin/draft/count?access_token=" + token;
        return httpInterface.doGet(url);
    }

    /**
     * 获取图文素材列表
     * @return
     */
    public String qryNewsMaterialList(String token,QryNewsMaterial qryNewsMaterial){
        String url = "https://api.weixin.qq.com/cgi-bin/draft/batchget?access_token=" + token;
        return httpInterface.doPost(url,qryNewsMaterial);
    }

}
