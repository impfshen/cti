package com.shen.springbootwechat.Service;

import com.shen.springbootwechat.Bean.UserBasicInfo;
import com.shen.springbootwechat.Util.LoginUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserLoginService {

    @Resource
    private LoginUtil loginUtil;

    public boolean checkItem(String username, String phone, String password){
        int len = loginUtil.hasItem(username,phone,password).size();
        if (len == 0)
            return false;
        else
            return true;
    }

    public boolean changeOpenID(UserBasicInfo userBasicInfo){
        return loginUtil.updateOpenID(userBasicInfo.getUsername(), userBasicInfo.getPhone(), userBasicInfo.getOpenid());
    }

    public boolean changeInvalidID(String invalidid, String openid){
        return loginUtil.updateInvalidID(invalidid,openid);
    }
}
