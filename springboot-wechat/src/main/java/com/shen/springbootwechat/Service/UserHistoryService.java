package com.shen.springbootwechat.Service;

import com.shen.springbootwechat.Bean.UserHistoryInfo;
import com.shen.springbootwechat.Util.HistoryUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserHistoryService {

    @Resource
    HistoryUtil historyUtil;

    public List<UserHistoryInfo> queryAllItem(){
        return historyUtil.selectAllItem();
    }

    public List<UserHistoryInfo> queryItem(String type,String user){
        return historyUtil.selectItem(type, user);
    }

    public boolean addItem(String type,String user,String time,String number){
        return historyUtil.insertItem(type,user,time,number);
    }

}
