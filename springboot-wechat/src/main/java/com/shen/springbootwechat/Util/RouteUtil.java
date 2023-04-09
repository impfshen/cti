package com.shen.springbootwechat.Util;

import com.shen.springbootwechat.Bean.RouteInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RouteUtil {

    @Insert("insert into RouteInfo values (#{id},#{route_enable},#{line_group},#{number_limit},#{wkhour_wkday},#{wkhour_begin},#{wkhour_end},#{work_week},#{number_cacheable},#{destination_extension},#{destination_dialplan},#{destination_context})")
    boolean insertItem(Integer id, boolean route_enable, String line_group, Integer number_limit, String wkhour_wkday, String wkhour_begin, String wkhour_end, String work_week, Integer number_cacheable, String destination_extension, String destination_dialplan, String destination_context);

    @Select("select * from RouteInfo where id=#{id}")
    RouteInfo selectItem(Integer id);

    @Update("update RouteInfo set route_enable=#{route_enable},line_group=#{line_group},number_limit=#{number_limit},wkhour_wkday=#{wkhour_wkday},wkhour_begin=#{wkhour_begin},wkhour_end=#{wkhour_end},work_week=#{work_week},number_cacheable=#{number_cacheable},destination_extension=#{destination_extension},destination_dialplan=#{destination_dialplan},destination_context=#{destination_context} where id=#{id}")
    boolean updateItem(boolean route_enable, String line_group, Integer number_limit, String wkhour_wkday, String wkhour_begin, String wkhour_end, String work_week, Integer number_cacheable, String destination_extension, String destination_dialplan, String destination_context, Integer id);

}
