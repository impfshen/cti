package com.shen.springbootwechat.Util;

import com.alibaba.fastjson.JSON;
import com.shen.springbootwechat.Bean.CallTaskBean;
import com.shen.springbootwechat.Bean.OutboundInfo;
import org.springframework.stereotype.Component;
import org.apache.commons.text.StringEscapeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class StringUtil {

    public <T> T toBean(String json, Class<T> clazz){
        String text = StringEscapeUtils.unescapeJava(json);
        T bean = JSON.parseObject(text,clazz);
        return bean;
    }

    public <T> String toJson(T obj){
        String json = JSON.toJSONString(obj);
        return StringEscapeUtils.unescapeJava(json);
    }

    public List<String> toStringList(String str){
        String[] list = str.split(",");
        List<String> List = new ArrayList<>();
        for (String s:list)
            List.add(s);
        return List;
    }

    public List<Integer> toIntegerList(String str){
        String[] list = str.split(",");
        List<Integer> List = new ArrayList<>();
        for (String s:list)
            List.add(Integer.valueOf(s));
        return List;
    }

    public List<CallTaskBean.DateBean> toWorkHourList(String wkhour_wkday, String wkhour_begin, String wkhour_end){
        String[] wkday_list = wkhour_wkday.split(",");
        String[] begin_list = wkhour_begin.split(",");
        String[] end_list = wkhour_end.split(",");
        int len = wkday_list.length;
        List<CallTaskBean.DateBean> dateBeanList = new ArrayList<>();
        for (int i=0; i<len; i++)
            dateBeanList.add(new CallTaskBean.DateBean(wkday_list[i],begin_list[i],end_list[i]));
        return dateBeanList;
    }

    public String getTime(long second){
        Date date = new Date(System.currentTimeMillis() + second * 1000);
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public long getNumberQueueLength(OutboundInfo.NumberQueue numberQueue){
        long len = 0;
        if (!numberQueue.getNumber1().equals("")) len++;
        if (!numberQueue.getNumber2().equals("")) len++;
        if (!numberQueue.getNumber3().equals("")) len++;
        if (!numberQueue.getNumber4().equals("")) len++;
        if (!numberQueue.getNumber5().equals("")) len++;
        if (!numberQueue.getNumber6().equals("")) len++;
        if (!numberQueue.getNumber7().equals("")) len++;
        if (!numberQueue.getNumber8().equals("")) len++;
        if (!numberQueue.getNumber9().equals("")) len++;
        return len;
    }

    public List<String> toNumberList(OutboundInfo.NumberQueue numberQueue){
        List<String> list = new ArrayList<>();
        if (!numberQueue.getNumber1().equals("")) list.add(numberQueue.getNumber1());
        if (!numberQueue.getNumber2().equals("")) list.add(numberQueue.getNumber2());
        if (!numberQueue.getNumber3().equals("")) list.add(numberQueue.getNumber3());
        if (!numberQueue.getNumber4().equals("")) list.add(numberQueue.getNumber4());
        if (!numberQueue.getNumber5().equals("")) list.add(numberQueue.getNumber5());
        if (!numberQueue.getNumber6().equals("")) list.add(numberQueue.getNumber6());
        if (!numberQueue.getNumber7().equals("")) list.add(numberQueue.getNumber7());
        if (!numberQueue.getNumber8().equals("")) list.add(numberQueue.getNumber8());
        if (!numberQueue.getNumber9().equals("")) list.add(numberQueue.getNumber9());
        return list;
    }
}
