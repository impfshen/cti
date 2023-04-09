package com.shen.springbootwechat.Service;

import com.shen.springbootwechat.Bean.UserBasicInfo;
import com.shen.springbootwechat.Util.RegisterUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRegisterService {

    @Resource
    private RegisterUtil registerUtil;

    public List<UserBasicInfo> queryAllItem(){
        return registerUtil.selectAllItem();
    }

    public boolean existItem(String username,String phone){
        if (registerUtil.hasItem(username,phone).size()==0)
            return false;
        else
            return true;
    }

    public UserBasicInfo queryItemByKey(String username,String phone){
        if (existItem(username,phone))
            return registerUtil.selectItemByKey(username,phone);
        else
            return new UserBasicInfo();
    }

    public UserBasicInfo queryItemByOpenID(String openid){
        return registerUtil.selectItemByOpenID(openid);
    }

    public boolean addItem(UserBasicInfo userBasicInfo){
        if (existItem(userBasicInfo.getUsername(),userBasicInfo.getPhone()))
            return false;
        else
            return registerUtil.insertItem(userBasicInfo.getUsername(),userBasicInfo.getPhone(), userBasicInfo.getPassword(), userBasicInfo.getOpenid());
    }

    public boolean removeItem(String username,String phone){
        if (existItem(username,phone))
            return registerUtil.deleteItem(username,phone);
        else
            return false;
    }

    public boolean changeItem(UserBasicInfo userBasicInfo){
        if (existItem(userBasicInfo.getUsername(),userBasicInfo.getPhone()))
            return registerUtil.updateItem(userBasicInfo.getUsername(), userBasicInfo.getPhone(), userBasicInfo.getPassword(),userBasicInfo.getOpenid());
        else
            return false;
    }

}
