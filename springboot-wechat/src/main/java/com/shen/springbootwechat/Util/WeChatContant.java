package com.shen.springbootwechat.Util;

public class WeChatContant {

    public static final String appID = "wxdff233b782152a95";
    public static final String secret = "786c0df26a3d767d7aab70fb0b3af10b";

    public static final String token = "wxToken";
    public final static String ACCESS_TOKEN_KEY = "accessToken";
    public final static String ERROR_KEY = "accessToken";

    public static final String DEFAULT_RESPONSE = "success";
    public static final String WELCOME_RESPONSE = "Welcome";
    public static final String BYE_RESPONSE = "Bye";

    public static final String MESSAGE_TYPE_TEXT = "text";
    public static final String MESSAGE_TYPE_IMAGE = "image";
    public static final String MESSAGE_TYPE_VOICE = "voice";
    public static final String MESSAGE_TYPE_NEWS = "news";
    public static final String MESSAGE_TYPE_VIDEO = "video";
    public static final String MESSAGE_TYPE_LOCATION = "location";
    public static final String MESSAGE_TYPE_LINK = "link";
    public static final String MESSAGE_TYPE_EVENT = "event";

    public static final String EVENT_TYPE_CLICK = "CLICK";
    public static final String EVENT_TYPE_VIEW = "VIEW";
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    public static final String EVENT_TYPE_SCAN = "SCAN";
    public static final String EVENT_TYPE_LOCATION = "LOCATION";

    public static final String FromUserName = "FromUserName";
    public static final String ToUserName = "ToUserName";
    public static final String MsgType = "MsgType";
    public static final String CreateTime = "CreateTime";
    public static final String Content = "Content";
    public static final String MediaId = "MediaId";
    public static final String Event = "Event";
    public static final String EventKey = "EventKey";
    public static final String ArticleCount = "ArticleCount";
    public static final String Articles = "Articles";
    public static final String Title = "Title";
    public static final String Description = "Description";
    public static final String PicUrl = "PicUrl";
    public static final String Url = "Url";

    public static final String MATERIAL_TYPE_IMAGE = "image";
    public static final String MATERIAL_TYPE_VOICE = "voice";
    public static final String MATERIAL_TYPE_VEDIO = "vedio";
    public static final String MATERIAL_TYPE_THUMB = "thumb";

    public static final String mediaID_code = "3PGcujOMFDksGu3lf-L9FB7H0vizbFmtslpzsKOYfMq7vqu3Sb6adPGssZuIJtlq";
    public static final String mediaID_cover = "3PGcujOMFDksGu3lf-L9FOXsKCpgXJms-wqaVJ0eq8E9BgYRPxq8ws4DUz_2nidB";
    public static final String mediaID_news = "3PGcujOMFDksGu3lf-L9FIYC3EqhzoM-Xog8j4u6Xz5KGrF6hFC69XPrz124ebS4";

    public static final String MENU_TYPE_CLICK = "click";
    public static final String MENU_TYPE_VIEW = "view";
    public static final String MENU_TYPE_MEDIA_ID = "media_id";
    public static final String MENU_TYPE_ARTICLE_ID = "article_id";
    public static final String MENU_TYPE_ARTICLE_VIEW_LIMITED = "article_view_limited";

    public static final String publishID_news = "2247483656";
    public static final String articleID_news = "PoRPatPNBohWwEBapvcixRHmIyK87V9_CoTgawkPwzZRyBo5UWtMNkbgCjNrern5";

    public static final String openID = "oT7V954WZK9gzaKsbBneH0yD17oc";
    public static final String invalidID = "xxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    public static final String TAG_NAME_VISITOR = "游客";
    public static final String TAG_NAME_USER = "用户";
    public static final String TAG_NAME_MANAGER = "管理员";
    public static final String TAG_NAME_RUNNER = "运营人员";

    public static final Integer TAG_ID_VISITOR = 100;
    public static final Integer TAG_ID_USER = 101;
    public static final Integer TAG_ID_MANAGER = 102;
    public static final Integer TAG_ID_RUNNER = 103;

    public static final String TAG_KEY_VISITOR = "VISITOR";
    public static final String TAG_KEY_USER = "USER";
    public static final String TAG_KEY_MANAGER = "MANAGER";
    public static final String TAG_KEY_RUNNER = "RUNNER";

    public static final String api_auth = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    public static final String api_auth_access_token = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    public static final String auth_call_back = "http://47.111.1.11/authorize/authCallBack";

    public static final Integer ROUTE_ID_INBOUND = 233;
    public static final Integer ROUTE_ID_OUTBOUND = 486;

    public static final String TYPE_HISTORY_INBOUND = "inbound";
    public static final String TYPE_HISTORY_OUTBOUND = "outbound";
}
