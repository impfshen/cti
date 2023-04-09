package com.shen.springbootwechat.Service;

import com.shen.springbootwechat.Bean.RouteInfo;
import com.shen.springbootwechat.Util.RouteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RouteService {

    @Resource
    RouteUtil routeUtil;

    public boolean addItem(Integer id, boolean route_enable, String line_group, Integer number_limit, String wkhour_wkday, String wkhour_begin, String wkhour_end, String work_week, Integer number_cacheable, String destination_extension, String destination_dialplan, String destination_context){
        return routeUtil.insertItem(id, route_enable, line_group, number_limit, wkhour_wkday, wkhour_begin, wkhour_end, work_week, number_cacheable, destination_extension, destination_dialplan, destination_context);
    }

    public RouteInfo queryItem(Integer id){
        return routeUtil.selectItem(id);
    }

    public boolean changeItem(boolean route_enable, String line_group, Integer number_limit, String wkhour_wkday, String wkhour_begin, String wkhour_end, String work_week, Integer number_cacheable, String destination_extension, String destination_dialplan, String destination_context, Integer id){
        return routeUtil.updateItem(route_enable, line_group, number_limit, wkhour_wkday, wkhour_begin, wkhour_end, work_week, number_cacheable, destination_extension, destination_dialplan, destination_context, id);
    }
}
