package com.shen.springbootwechat.Controller;

import com.shen.springbootwechat.Bean.UserPersonalInfo;
import com.shen.springbootwechat.Service.UserPrivacyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/privacy")
public class UserPrivacyController {

    @Resource
    private UserPrivacyService userPrivacyService;

    // Link = { http://47.111.1.11/privacy/selectUser?phone=PHONE }
    @GetMapping("/selectUser")
    public UserPersonalInfo selectUser(String phone){
        return userPrivacyService.queryItem(phone);
    }

    // Link = { http://47.111.1.11/privacy/insertUser?phone=PHONE&sex=SEX&city=CITY&email=EMAIL&profession=PROFESSION }
    @GetMapping("/insertUser")
    public boolean insertUser(String phone,String sex,String city,String email,String profession){
        return userPrivacyService.addItem(phone,sex,city,email,profession);
    }

    // Link = { http://47.111.1.11/privacy/updateUser?&sex=SEX&city=CITY&email=EMAIL&profession=PROFESSION&phone=PHONE }
    @GetMapping("/updateUser")
    public boolean updateUser(String sex,String city,String email,String profession,String phone){
        return userPrivacyService.changeItem(sex,city,email,profession,phone);
    }

    // Link = { http://47.111.1.11/privacy/deleteUser?phone=PHONE }
    @GetMapping("/deleteUser")
    public boolean deleteUser(String phone){
        return userPrivacyService.removeItem(phone);
    }
}
