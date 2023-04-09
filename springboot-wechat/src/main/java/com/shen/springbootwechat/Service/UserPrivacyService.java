package com.shen.springbootwechat.Service;

import com.shen.springbootwechat.Bean.UserPersonalInfo;
import com.shen.springbootwechat.Util.PrivacyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserPrivacyService {

    @Resource
    private PrivacyUtil privacyUtil;

    public UserPersonalInfo queryItem(String phone){
        return privacyUtil.selectItem(phone);
    }

    public boolean existItem(String phone){
        if (privacyUtil.hasItem(phone).size()==0)
            return false;
        else
            return true;
    }

    public boolean addItem(String phone,String sex,String city,String email,String profession){
        if (existItem(phone))
            return true;
        else
            return privacyUtil.insertItem(phone,sex,city,email,profession);
    }

    public boolean changeItem(String sex,String city,String email,String profession,String phone){
        return privacyUtil.updateItem(sex,city,email,profession,phone);
    }

    public boolean removeItem(String phone){
        return privacyUtil.deleteItem(phone);
    }
}
