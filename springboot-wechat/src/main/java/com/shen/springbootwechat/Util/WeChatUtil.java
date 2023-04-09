package com.shen.springbootwechat.Util;

import com.shen.springbootwechat.Bean.LinkItem;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class WeChatUtil {

    public static boolean signature(String signature,String timestamp,String nonce){
        String[] arr = {WeChatContant.token,timestamp,nonce};
        Arrays.sort(arr);
        StringBuffer sb = new StringBuffer();
        for(String a:arr) {
            sb.append(a);
        }
        String sha1Msg = DigestUtils.sha1Hex(sb.toString().getBytes());
        if(signature.equals(sha1Msg))
            return true;
        else
            return false;
    }

    public static Map<String,String> parseXml(HttpServletRequest request)throws Exception{
        Map<String,String> map = new HashMap<>();
        InputStream inputStream = request.getInputStream();
        Document document = null;
        try{
            SAXReader reader = new SAXReader();
            document = reader.read(inputStream);
        } catch (Exception e){
            String inputString = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while((line=br.readLine())!=null){
                inputString += line;
            }
            br.close();
            document = DocumentHelper.parseText(inputString);
        }
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList)
            map.put(e.getName(),e.getText());
        inputStream.close();
        return map;
    }

    public static String sendTextMsg(Map<String,String> requestMap,String responseContent){
        Map<String,Object> map = new HashMap<>();
        map.put(WeChatContant.ToUserName,requestMap.get(WeChatContant.FromUserName));
        map.put(WeChatContant.FromUserName,requestMap.get(WeChatContant.ToUserName));
        map.put(WeChatContant.CreateTime,new Date().getTime());
        map.put(WeChatContant.MsgType,WeChatContant.MESSAGE_TYPE_TEXT);
        map.put(WeChatContant.Content,responseContent);
        return mapToXmlForText(map);
    }

    public static String mapToXmlForText(Map map) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        mapToXmlForTextIndex(map, sb);
        sb.append("</xml>");
        try {
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void mapToXmlForTextIndex(Map map, StringBuffer sb){
        sb.append("<ToUserName><![CDATA["+map.get(WeChatContant.ToUserName)+"]]></ToUserName>");
        sb.append("<FromUserName><![CDATA["+map.get(WeChatContant.FromUserName)+"]]></FromUserName>");
        sb.append("<CreateTime>"+map.get(WeChatContant.CreateTime)+"</CreateTime>");
        sb.append("<MsgType><![CDATA["+map.get(WeChatContant.MsgType)+"]]></MsgType>");
        sb.append("<Content><![CDATA["+map.get(WeChatContant.Content)+"]]></Content>");
    }

    public static String sendImageMsg(Map<String,String> requestMap,String mediaId){
        Map<String,Object> map = new HashMap<>();
        map.put(WeChatContant.ToUserName,requestMap.get(WeChatContant.FromUserName));
        map.put(WeChatContant.FromUserName,requestMap.get(WeChatContant.ToUserName));
        map.put(WeChatContant.CreateTime,new Date().getTime());
        map.put(WeChatContant.MsgType,WeChatContant.MESSAGE_TYPE_IMAGE);
        map.put(WeChatContant.MediaId,mediaId);
        return mapToXmlForImage(map);
    }

    public static String mapToXmlForImage(Map map) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        mapToXmlForImageIndex(map, sb);
        sb.append("</xml>");
        try {
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void mapToXmlForImageIndex(Map map, StringBuffer sb){
        sb.append("<ToUserName><![CDATA["+map.get(WeChatContant.ToUserName)+"]]></ToUserName>");
        sb.append("<FromUserName><![CDATA["+map.get(WeChatContant.FromUserName)+"]]></FromUserName>");
        sb.append("<CreateTime>"+map.get(WeChatContant.CreateTime)+"</CreateTime>");
        sb.append("<MsgType><![CDATA["+map.get(WeChatContant.MsgType)+"]]></MsgType>");
        sb.append("<Image>");
        sb.append("<MediaId><![CDATA["+map.get(WeChatContant.MediaId)+"]]></MediaId>");
        sb.append("</Image>");
    }

    public static String sendVoiceMsg(Map<String,String> requestMap,String mediaId){
        Map<String,Object> map = new HashMap<>();
        map.put(WeChatContant.ToUserName,requestMap.get(WeChatContant.FromUserName));
        map.put(WeChatContant.FromUserName,requestMap.get(WeChatContant.ToUserName));
        map.put(WeChatContant.CreateTime,new Date().getTime());
        map.put(WeChatContant.MsgType,WeChatContant.MESSAGE_TYPE_VOICE);
        map.put(WeChatContant.MediaId,mediaId);
        return mapToXmlForVoice(map);
    }

    public static String mapToXmlForVoice(Map map) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        mapToXmlForVoiceIndex(map, sb);
        sb.append("</xml>");
        try {
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void mapToXmlForVoiceIndex(Map map, StringBuffer sb){
        sb.append("<ToUserName><![CDATA["+map.get(WeChatContant.ToUserName)+"]]></ToUserName>");
        sb.append("<FromUserName><![CDATA["+map.get(WeChatContant.FromUserName)+"]]></FromUserName>");
        sb.append("<CreateTime>"+map.get(WeChatContant.CreateTime)+"</CreateTime>");
        sb.append("<MsgType><![CDATA["+map.get(WeChatContant.MsgType)+"]]></MsgType>");
        sb.append("<Voice>");
        sb.append("<MediaId><![CDATA["+map.get(WeChatContant.MediaId)+"]]></MediaId>");
        sb.append("</Voice>");
    }

    public static String sendArticleMsg(Map<String,String> requestMap,List<LinkItem> items){
        if(items == null || items.size()<1)
            return "success";
        Map<String,Object> map = new HashMap<>();
        map.put(WeChatContant.ToUserName,requestMap.get(WeChatContant.FromUserName));
        map.put(WeChatContant.FromUserName,requestMap.get(WeChatContant.ToUserName));
        map.put(WeChatContant.CreateTime,new Date().getTime());
        map.put(WeChatContant.MsgType,WeChatContant.MESSAGE_TYPE_NEWS);

        List<Map<String,Object>> Articles = new ArrayList<>();
        for(LinkItem itembean : items){
            Map<String,Object> item = new HashMap<>();
            item.put(WeChatContant.Title,itembean.getTitle());
            item.put(WeChatContant.Description, itembean.getDescription());
            item.put(WeChatContant.PicUrl, itembean.getPicUrl());
            item.put(WeChatContant.Url, itembean.getUrl());
            Articles.add(item);
        }

        map.put(WeChatContant.Articles, Articles);
        map.put(WeChatContant.ArticleCount, Articles.size());
        return mapToXmlForNews(map);
    }

    public static String mapToXmlForNews(Map map) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        mapToXmlForNewsIndex(map, sb);
        sb.append("</xml>");
        try {
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void mapToXmlForNewsIndex(Map map, StringBuffer sb){
        sb.append("<ToUserName><![CDATA["+map.get(WeChatContant.ToUserName)+"]]></ToUserName>");
        sb.append("<FromUserName><![CDATA["+map.get(WeChatContant.FromUserName)+"]]></FromUserName>");
        sb.append("<CreateTime>"+map.get(WeChatContant.CreateTime)+"</CreateTime>");
        sb.append("<MsgType><![CDATA["+map.get(WeChatContant.MsgType)+"]]></MsgType>");
        sb.append("<ArticleCount>"+map.get(WeChatContant.ArticleCount)+"</ArticleCount>");
        sb.append("<Articles>");
        List<Map<String,Object>> items = (List<Map<String,Object>>) map.get(WeChatContant.Articles);
        for (Map<String,Object> item : items){
            sb.append("<item>");
            sb.append("<Title><![CDATA["+item.get(WeChatContant.Title)+"]]></Title>");
            sb.append("<Description><![CDATA["+item.get(WeChatContant.Description)+"]]></Description>");
            sb.append("<PicUrl><![CDATA["+item.get(WeChatContant.PicUrl)+"]]></PicUrl>");
            sb.append("<Url><![CDATA["+item.get(WeChatContant.Url)+"]]></Url>");
            sb.append("</item>");
        }
        sb.append("</Articles>");
    }

}
