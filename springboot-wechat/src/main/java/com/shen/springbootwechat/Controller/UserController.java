package com.shen.springbootwechat.Controller;

import com.shen.springbootwechat.Bean.*;
import com.shen.springbootwechat.Util.StringUtil;
import com.shen.springbootwechat.Util.WeChatContant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserRegisterController userRegisterController;

    @Resource
    UserLoginController userLoginController;

    @Resource
    UserPrivacyController userPrivacyController;

    @Resource
    UserTagController userTagController;

    @Resource
    RouteController routeController;

    @Resource
    StringUtil stringUtil;

    @Resource
    RedisController redisController;

    @Resource
    UserHistoryController userHistoryController;

    // Link = { http://47.111.1.11/user/toRegister?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @RequestMapping("/toRegister")
    public String toRegister(String openid, Model model){
        model.addAttribute("TAG_KEY_USER",WeChatContant.TAG_KEY_USER);
        model.addAttribute("TAG_KEY_MANAGER",WeChatContant.TAG_KEY_MANAGER);
        model.addAttribute("TAG_KEY_RUNNER",WeChatContant.TAG_KEY_RUNNER);
        model.addAttribute("userBasicInfo",new UserBasicInfo("","","",""));
        return "register";  //显示 template/register.html 的内容
    }

    @RequestMapping("/register")
    public String Register(@ModelAttribute UserBasicInfo userBasicInfo, Model model){
        String str = UserRegister(userBasicInfo);
        if (str.equals("success")){
            userPrivacyController.insertUser(userBasicInfo.getPhone(),"","","","");
            model.addAttribute("message","注册成功，请重新登陆");
        }
        else if (str.equals("warning"))
            model.addAttribute("message","该账户已存在");
        else
            model.addAttribute("message",str);
        return "register_result";
    }

    private class StringException extends Exception{
        public StringException(){
            super();
        }
        public StringException(String message){
            super(message);
        }
    }

    private String UserRegister(UserBasicInfo userBasicInfo){
        try{
            if (userBasicInfo.hasEmpty())
                throw new StringException("注册信息不能为空");
            if (userRegisterController.insertUser(userBasicInfo.getUsername(),userBasicInfo.getPhone(),userBasicInfo.getPassword(), userBasicInfo.getOpenid()))
                return "success";
            else
                return "warning";
        } catch (StringException se){
            return se.getMessage();
        } catch (Exception e){
            return "连接超时，请检查网络状况";
        }
    }

    // Link = { http://47.111.1.11/user/toLogin?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @RequestMapping("/toLogin")
    public String toLogin(String openid,Model model){
        model.addAttribute("TAG_KEY_USER",WeChatContant.TAG_KEY_USER);
        model.addAttribute("TAG_KEY_MANAGER",WeChatContant.TAG_KEY_MANAGER);
        model.addAttribute("TAG_KEY_RUNNER",WeChatContant.TAG_KEY_RUNNER);
        model.addAttribute("userBasicInfo",new UserBasicInfo("","","",openid));
        return "login";  //显示 template/login.html 的内容
    }

    @RequestMapping("/login")
    public String Login(@ModelAttribute UserBasicInfo userBasicInfo, Model model) {
        if (!userTagController.getUserTag(userBasicInfo.getOpenid()).equals(WeChatContant.TAG_ID_VISITOR)) {
            model.addAttribute("message","当前状态已登录，请勿重复操作");
            return "login_result";
        }
        if (userLoginController.hasUser(userBasicInfo.getUsername(),userBasicInfo.getPhone(),userBasicInfo.getPassword())){
            userLoginController.updateOpenID(userBasicInfo);
            if (userBasicInfo.getUsername().equals(WeChatContant.TAG_KEY_USER))
                userTagController.tagToUser(userBasicInfo.getOpenid());
            else if (userBasicInfo.getUsername().equals(WeChatContant.TAG_KEY_MANAGER))
                userTagController.tagToManager(userBasicInfo.getOpenid());
            else if (userBasicInfo.getUsername().equals(WeChatContant.TAG_KEY_RUNNER))
                userTagController.tagToRunner(userBasicInfo.getOpenid());
            else
                userTagController.tagToVisitor(userBasicInfo.getOpenid());
            model.addAttribute("message","登陆成功");
        }
        else
            model.addAttribute("message","用户名或邮箱或密码错误，登陆失败");
        return "login_result";
    }

    // Link = { http://47.111.1.11/user/toGetPrivacy?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @RequestMapping("/toGetPrivacy")
    public String toGetPrivacy(String openid, Model model){
        UserBasicInfo userBasicInfo = userRegisterController.selectUserByOpenID(openid);
        String phone = userBasicInfo.getPhone();
        UserPersonalInfo userPersonalInfo = userPrivacyController.selectUser(phone);
        model.addAttribute("phone",phone);
        model.addAttribute("sex", userPersonalInfo.getSex());
        model.addAttribute("city", userPersonalInfo.getCity());
        model.addAttribute("email", userPersonalInfo.getEmail());
        model.addAttribute("profession", userPersonalInfo.getProfession());
        return "privacy_get";  //显示 template/privacy_get.html 的内容
    }

    // Link = { http://47.111.1.11/user/toSetPrivacy?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @RequestMapping("/toSetPrivacy")
    public String toSetPrivacy(String openid, Model model){
        UserBasicInfo userBasicInfo = userRegisterController.selectUserByOpenID(openid);
        String phone = userBasicInfo.getPhone();
        UserPersonalInfo userPersonalInfo = userPrivacyController.selectUser(phone);
        model.addAttribute("userPersonalInfo", userPersonalInfo);
        return "privacy_set";  //显示 template/privacy_set.html 的内容
    }

    @RequestMapping("/setPrivacy")
    public String setPrivacy(@ModelAttribute UserPersonalInfo userPersonalInfo, Model model){
        if (userPrivacyController.updateUser(userPersonalInfo.getSex(),userPersonalInfo.getCity(),userPersonalInfo.getEmail(),userPersonalInfo.getProfession(),userPersonalInfo.getPhone()))
            model.addAttribute("message","保存成功");
        else
            model.addAttribute("message","保存失败");
        return "privacy_set_result";
    }

    // Link = { http://47.111.1.11/user/unLogin?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @RequestMapping("/unLogin")
    public String unLogin(String openid,Model model){
        Integer tag = userTagController.getUserTag(openid);
        if (tag.equals(WeChatContant.TAG_ID_VISITOR))
            model.addAttribute("message","当前尚未登录");
        else {
            userTagController.tagToVisitor(openid);
            userLoginController.updateInvalidID(WeChatContant.invalidID, openid);
            model.addAttribute("message","退出成功");
        }
        return "unlogin";
    }

    // Link = { http://47.111.1.11/user/toUserHome?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @RequestMapping("/toUserHome")
    public String toUserHome(String openid, Model model){
        if (userTagController.getUserTag(openid).equals(WeChatContant.TAG_ID_USER))
            return "user";
        else
            return "visitor";
    }

    // Link = { http://47.111.1.11/user/toManagerHome?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @RequestMapping("/toManagerHome")
    public String toManagerHome(String openid, Model model){
        if (userTagController.getUserTag(openid).equals(WeChatContant.TAG_ID_MANAGER))
            return "manager";
        else
            return "visitor";
    }

    // Link = { http://47.111.1.11/user/toRunnerHome?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @RequestMapping("/toRunnerHome")
    public String toRunnerHome(String openid, Model model){
        if (userTagController.getUserTag(openid).equals(WeChatContant.TAG_ID_RUNNER))
            return "runner";
        else
            return "visitor";
    }

    // Link = { http://47.111.1.11/user/toGetRoute?id=ID }
    @RequestMapping("/toGetRoute")
    public String toGetRoute(Integer id, Model model){
        RouteInfo routeInfo = routeController.selectRoute(id);
        model.addAttribute("id",id);
        model.addAttribute("route_enable",true);
        model.addAttribute("line_group",routeInfo.getLine_group());
        model.addAttribute("number_limit",routeInfo.getNumber_limit());
        model.addAttribute("wkhour_wkday",routeInfo.getWkhour_wkday());
        model.addAttribute("wkhour_begin",routeInfo.getWkhour_begin());
        model.addAttribute("wkhour_end",routeInfo.getWkhour_end());
        model.addAttribute("work_week",routeInfo.getWork_week());
        model.addAttribute("number_cacheable",routeInfo.getNumber_cacheable());
        model.addAttribute("destination_extension",routeInfo.getDestination_extension());
        model.addAttribute("destination_dialplan",routeInfo.getDestination_dialplan());
        model.addAttribute("destination_context",routeInfo.getDestination_context());
        return "route_get";
    }

    // Link = { http://47.111.1.11/user/toSetRoute?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @RequestMapping("/toSetRoute")
    public String toSetRoute(Integer id, Model model){
        RouteInfo routeInfo = routeController.selectRoute(id);
        model.addAttribute("routeInfo",routeInfo);
        return "route_set";
    }

    @RequestMapping("/setRoute")
    public String setRoute(@ModelAttribute RouteInfo routeInfo, Model model){
        boolean state_mysql = routeController.updateRoute(true, routeInfo.getLine_group(), routeInfo.getNumber_limit(), routeInfo.getWkhour_wkday(), routeInfo.getWkhour_begin(), routeInfo.getWkhour_end(), routeInfo.getWork_week(), routeInfo.getNumber_cacheable(), routeInfo.getDestination_extension(), routeInfo.getDestination_dialplan(), routeInfo.getDestination_context(), routeInfo.getId());
        if (state_mysql)
            model.addAttribute("message","线路配置成功");
        else
            model.addAttribute("message","线路配置失败");
        return "route_set_result";
    }

    // Link = { http://47.111.1.11/user/initRoute }
    @RequestMapping("/initRoute")
    public boolean initRoute(){
        RouteInfo routeInfo = RouteInfo.instanse();
        return routeController.insertRoute(routeInfo.getId(), true,routeInfo.getLine_group(),routeInfo.getNumber_limit(),routeInfo.getWkhour_wkday(),routeInfo.getWkhour_begin(),routeInfo.getWkhour_end(),routeInfo.getWork_week(),routeInfo.getNumber_cacheable(),routeInfo.getDestination_extension(),routeInfo.getDestination_dialplan(),routeInfo.getDestination_context());
    }

    // Link = { http://47.111.1.11/user/inbound?openid=oT7V954WZK9gzaKsbBneH0yD17oc }
    @RequestMapping("/inbound")
    public String inbound(String openid, Model model){
        UserBasicInfo userBasicInfo = userRegisterController.selectUserByOpenID(openid);
        String phone = userBasicInfo.getPhone();
        if (redisController.hasKey("IN_FS_POOL:"+phone)){
            model.addAttribute("message","该号码正在拨号，请稍后");
            return "inbound_warning";
        }
        String start_time = stringUtil.getTime(0);
        String stop_time = stringUtil.getTime(120);
        InboundInfo inboundInfo = new InboundInfo(phone,start_time,stop_time,openid);
        RouteInfo routeInfo = routeController.selectRoute(WeChatContant.ROUTE_ID_INBOUND);
        CallTaskBean callTaskBean = CreateInCallTask(phone,routeInfo,inboundInfo);
        boolean state_calltask = redisController.setCallTask("Inbound:"+phone,callTaskBean);
        boolean state_numberlist = redisController.setNumber("IN_FS_POOL:"+phone,phone);
        if (state_calltask && state_numberlist){
            model.addAttribute("message","请求发起成功，请稍后");
            userHistoryController.insertHistory(WeChatContant.TYPE_HISTORY_INBOUND,phone,start_time,phone);
        }
        else
            model.addAttribute("message","线路故障，请求发起失败");
        return "inbound_result";
    }

    private CallTaskBean CreateInCallTask(String phone, RouteInfo routeInfo, InboundInfo inboundInfo){
        CallTaskBean.ParamsBean paramsBean = new CallTaskBean.ParamsBean();
        paramsBean.setEnable(routeInfo.isRoute_enable());
        paramsBean.setLine_group(stringUtil.toStringList(routeInfo.getLine_group()));
        paramsBean.setLimit(routeInfo.getNumber_limit());
        paramsBean.setStart_time(inboundInfo.getStart_time());
        paramsBean.setStop_time(inboundInfo.getStop_time());
        paramsBean.setWork_hour(stringUtil.toWorkHourList(routeInfo.getWkhour_wkday(),routeInfo.getWkhour_begin(),routeInfo.getWkhour_end()));
        paramsBean.setWork_week(stringUtil.toIntegerList(routeInfo.getWork_week()));
        paramsBean.setNumber_queue("IN_FS_POOL:"+phone);
        paramsBean.setNumber_cacheable(routeInfo.getNumber_cacheable());
        paramsBean.setDestination_extension(routeInfo.getDestination_extension());
        paramsBean.setDestination_dialplan(routeInfo.getDestination_dialplan());
        paramsBean.setDestination_context(routeInfo.getDestination_context());
        CallTaskBean.VariablesBean variablesBean = new CallTaskBean.VariablesBean();
        variablesBean.setOrigination_caller_id_number("3001");
        CallTaskBean callTaskBean = new CallTaskBean();
        callTaskBean.setParams(paramsBean);
        callTaskBean.setVariables(variablesBean);
        return callTaskBean;
    }

    // Link = { http://47.111.1.11/user/toOutbound?id=ID }
    @RequestMapping("/toOutbound")
    public String toOutbound(String openid, Model model){
        model.addAttribute("numberQueue",new OutboundInfo.NumberQueue("","","","","","","","","","",openid));
        return "outbound";
    }

    @RequestMapping("/outbound")
    public String outbound(@ModelAttribute OutboundInfo.NumberQueue numberQueue, Model model){
        String openid = numberQueue.getOpenid();
        UserBasicInfo userBasicInfo = userRegisterController.selectUserByOpenID(openid);
        String phone = userBasicInfo.getPhone();
        long len = stringUtil.getNumberQueueLength(numberQueue);
        if (len==0){
            model.addAttribute("message","号码队列为空");
            return "outbound_warning";
        }
        String name = phone+":"+ numberQueue.getName();
        if (redisController.hasKey("OUT_FS_POOL:"+name)){
            model.addAttribute("message","该号码队列正在运行，请修改队列名称");
            return "outbound_warning";
        }
        String start_time = stringUtil.getTime(0);
        String stop_time = stringUtil.getTime(len * 120);
        OutboundInfo outboundInfo = new OutboundInfo(numberQueue,start_time,stop_time,phone);
        RouteInfo routeInfo = routeController.selectRoute(WeChatContant.ROUTE_ID_OUTBOUND);
        CallTaskBean callTaskBean = CreateOutCallTask(name,routeInfo,outboundInfo);
        List<String> numberlist = stringUtil.toNumberList(numberQueue);
        boolean state_calltask = redisController.setCallTask("Outbound:"+name,callTaskBean);
        boolean state_numberlist = redisController.setNumberQueue("OUT_FS_POOL:"+name,numberlist);
        if (state_calltask && state_numberlist){
            model.addAttribute("message","请求发起成功，请稍后");
            userHistoryController.insertHistoryList(WeChatContant.TYPE_HISTORY_OUTBOUND,phone,start_time,numberlist);
        }
        else
            model.addAttribute("message","线路故障，请求发起失败");
        return "outbound_result";
    }

    private CallTaskBean CreateOutCallTask(String name, RouteInfo routeInfo, OutboundInfo outboundInfo){
        CallTaskBean.ParamsBean paramsBean = new CallTaskBean.ParamsBean();
        paramsBean.setEnable(routeInfo.isRoute_enable());
        paramsBean.setLine_group(stringUtil.toStringList(routeInfo.getLine_group()));
        paramsBean.setLimit(routeInfo.getNumber_limit());
        paramsBean.setStart_time(outboundInfo.getStart_time());
        paramsBean.setStop_time(outboundInfo.getStop_time());
        paramsBean.setWork_hour(stringUtil.toWorkHourList(routeInfo.getWkhour_wkday(),routeInfo.getWkhour_begin(),routeInfo.getWkhour_end()));
        paramsBean.setWork_week(stringUtil.toIntegerList(routeInfo.getWork_week()));
        paramsBean.setNumber_queue("OUT_FS_POOL:"+name);
        paramsBean.setNumber_cacheable(routeInfo.getNumber_cacheable());
        paramsBean.setDestination_extension(routeInfo.getDestination_extension());
        paramsBean.setDestination_dialplan(routeInfo.getDestination_dialplan());
        paramsBean.setDestination_context(routeInfo.getDestination_context());
        CallTaskBean.VariablesBean variablesBean = new CallTaskBean.VariablesBean();
        variablesBean.setOrigination_caller_id_number("3001");
        CallTaskBean callTaskBean = new CallTaskBean();
        callTaskBean.setParams(paramsBean);
        callTaskBean.setVariables(variablesBean);
        return callTaskBean;
    }

    @GetMapping("/history")
    public String history(String openid,Model model){
        UserBasicInfo userBasicInfo = userRegisterController.selectUserByOpenID(openid);
        String phone = userBasicInfo.getPhone();
        List<UserHistoryInfo> list = userHistoryController.selectHistory(WeChatContant.TYPE_HISTORY_OUTBOUND,phone);
        model.addAttribute("historyList",list);
        return "history";
    }

    @GetMapping("/allHistory")
    public String allHistory(Model model){
        List<UserHistoryInfo> list = userHistoryController.selectAllHistory();
        model.addAttribute("historyList",list);
        return "history_all";
    }
}
