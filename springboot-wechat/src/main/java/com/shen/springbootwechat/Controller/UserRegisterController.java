package com.shen.springbootwechat.Controller;

import com.shen.springbootwechat.Bean.UserBasicInfo;
import com.shen.springbootwechat.Service.UserRegisterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/register")
public class UserRegisterController {

    @Resource
    private UserRegisterService userRegisterService;

    // Link = { http://localhost/register/selectAll }
    @GetMapping("/selectAll")
    public String selectAllUser(){
        List<UserBasicInfo> list = userRegisterService.queryAllItem();
        if (list.size()==0)
            return "null";
        else
            return list.toString();
    }

    // Link = { http://localhost/register/select?username=USERNAME&phone=PHONE }
    @GetMapping("/selectUserByKey")
    public String selectUserByKey(String username,String phone){
        UserBasicInfo userBasicInfo = userRegisterService.queryItemByKey(username,phone);
        if (userBasicInfo.isEmpty())
            return "null";
        else
            return userRegisterService.queryItemByKey(username,phone).toString();
    }

    // Link = { http://localhost/register/select?openid=OPENID }
    @GetMapping("/selectUserByOpenID")
    public UserBasicInfo selectUserByOpenID(String openid){
        return userRegisterService.queryItemByOpenID(openid);
    }

    // Link = { http://localhost/register/insert?username=USERNAME&email=EMAIL&password=PASSWORD&openid=OPENID }
    @GetMapping("/insert")
    public boolean insertUser(String username,String phone,String password,String openid){
        UserBasicInfo userBasicInfo = new UserBasicInfo(username,phone,password,openid);
        return userRegisterService.addItem(userBasicInfo);
    }

    // Link = { http://localhost/register/update?username=USERNAME&email=EMAIL&password=password&openid=OPENID }
    @GetMapping("/update")
    public boolean updateUser(String username,String phone,String password,String openid){
        UserBasicInfo userBasicInfo = new UserBasicInfo(username,phone,password,openid);
        return userRegisterService.changeItem(userBasicInfo);
    }

    // Link = { http://localhost/register/delete?username=USERNAME&email=EMAIL }
    @GetMapping("/delete")
    public boolean deleteUser(String username,String phone){
        return userRegisterService.removeItem(username,phone);
    }

}
