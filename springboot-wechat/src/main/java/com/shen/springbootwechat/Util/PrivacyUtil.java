package com.shen.springbootwechat.Util;

import com.shen.springbootwechat.Bean.UserBasicInfo;
import com.shen.springbootwechat.Bean.UserPersonalInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PrivacyUtil {

    @Select("select * from UserPersonalInfo where phone=#{phone}")
    UserPersonalInfo selectItem(String phone);

    @Select("select * from UserPersonalInfo where phone=#{phone}")
    List<UserPersonalInfo> hasItem(String phone);

    @Insert("insert into UserPersonalInfo values (#{phone},#{sex},#{city},#{email},#{profession})")
    boolean insertItem(String phone,String sex,String city,String email,String profession);

    @Update("update UserPersonalInfo set sex=#{sex},city=#{city},email=#{email},profession=#{profession} where phone=#{phone}")
    boolean updateItem(String sex,String city,String email,String profession,String phone);

    @Delete("delete from UserPersonalInfo where phone=#{phone}")
    boolean deleteItem(String phone);

}
