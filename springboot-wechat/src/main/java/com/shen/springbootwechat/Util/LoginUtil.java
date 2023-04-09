package com.shen.springbootwechat.Util;

import com.shen.springbootwechat.Bean.UserBasicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LoginUtil {

    @Select("select * from UserBasicInfo where username=#{username} and phone=#{phone} and password=#{password}")
    List<UserBasicInfo> hasItem(String username,String phone, String password);

    @Update("update UserBasicInfo set openid=#{openid} where username=#{username} and phone=#{phone}")
    boolean updateOpenID(String username,String phone,String openid);

    @Update("update UserBasicInfo set openid=#{invalidid} where openid=#{openid}")
    boolean updateInvalidID(String invalidid,String openid);
}
