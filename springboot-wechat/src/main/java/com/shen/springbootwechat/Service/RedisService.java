package com.shen.springbootwechat.Service;

import com.shen.springbootwechat.Bean.CallTaskBean;
import com.shen.springbootwechat.Util.StringUtil;
import com.shen.springbootwechat.Util.TaskUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RedisService {

    @Resource
    TaskUtil taskUtil;

    @Resource
    StringUtil stringUtil;

    public Set<String> getAllTasks(){
        return taskUtil.keys();
    }

    public boolean hasKey(String key){
        return taskUtil.hasKey(key);
    }

    public CallTaskBean getCallTask(String key, String hashKey){
        String json = (String) taskUtil.HashGet(key,hashKey);
        return stringUtil.toBean(json,CallTaskBean.class);
    }

    public boolean setCallTask(String key, String hashKey, CallTaskBean callTaskBean){
        String json = stringUtil.toJson(callTaskBean);
        return taskUtil.HashSet(key,hashKey,json);
    }

    public boolean delCallTask(String key, String hashKey){
        String json = (String) taskUtil.HashGet(key,hashKey);
        return taskUtil.HashDel(key,hashKey,json);
    }

    public List<String> getNumberList(String key, long start, long end){
        List<String> list = (List<String>) taskUtil.ListGet(key,start,end);
        return list;
    }

    public boolean setNumberList(String key, String number, int mod){
        return taskUtil.ListAdd(key,number,mod);
    }

    public boolean delNumberList(String key, int mod){
        return taskUtil.ListDel(key,mod);
    }
}
