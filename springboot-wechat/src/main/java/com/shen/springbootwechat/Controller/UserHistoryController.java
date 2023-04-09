package com.shen.springbootwechat.Controller;

import com.shen.springbootwechat.Bean.UserHistoryInfo;
import com.shen.springbootwechat.Service.UserHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/history")
public class UserHistoryController {

    @Resource
    UserHistoryService userHistoryService;

    @GetMapping("/selectAllHistory")
    public List<UserHistoryInfo> selectAllHistory(){
        return userHistoryService.queryAllItem();
    }

    @GetMapping("/selectHistory")
    public List<UserHistoryInfo> selectHistory(String type, String user){
        return userHistoryService.queryItem(type,user);
    }

    @GetMapping("/insertHistory")
    public boolean insertHistory(String type,String user,String time,String number){
        return userHistoryService.addItem(type, user, time, number);
    }

    @GetMapping("/insertHistoryList")
    public boolean insertHistoryList(String type,String user,String time,List<String> numberlist){
        boolean state = true;
        for (String s:numberlist)
            state = state & userHistoryService.addItem(type,user,time,s);
        return state;
    }

}
