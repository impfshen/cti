package com.shen.springbootwechat.Util;

import com.shen.springbootwechat.Bean.UserBasicInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegisterUtil {

    @Select("select * from UserBasicInfo")
    List<UserBasicInfo> selectAllItem();

    @Select("select * from UserBasicInfo where username=#{username} and phone=#{phone}")
    List<UserBasicInfo> hasItem(String username, String phone);

    @Select("select * from UserBasicInfo where username=#{username} and phone=#{phone}")
    UserBasicInfo selectItemByKey(String username,String phone);

    @Select("select * from UserBasicInfo where openid=#{openid}")
    UserBasicInfo selectItemByOpenID(String openid);

    @Insert("insert into UserBasicInfo values (#{username},#{phone},#{password},#{openid})")
    boolean insertItem(String username,String phone,String password,String openid);

    @Delete("delete from UserBasicInfo where username=#{username} and phone=#{phone}")
    boolean deleteItem(String username,String phone);

    @Update("update UserBasicInfo set username=#{username},phone=#{phone},password=#{password},openid=#{openid} where username=#{username} and phone=#{phone}")
    boolean updateItem(String username,String phone,String password,String openid);

}
