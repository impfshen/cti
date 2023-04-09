package com.shen.springbootwechat.Util;

import com.shen.springbootwechat.Bean.UserBasicInfo;
import com.shen.springbootwechat.Bean.UserHistoryInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HistoryUtil {

    @Select("select * from UserHistoryInfo")
    List<UserHistoryInfo> selectAllItem();

    @Select("select * from UserHistoryInfo where type=#{type} and user=#{user}")
    List<UserHistoryInfo> selectItem(String type,String user);

    @Insert("insert into UserHistoryInfo values (#{type},#{user},#{time},#{number})")
    boolean insertItem(String type,String user,String time,String number);

}
