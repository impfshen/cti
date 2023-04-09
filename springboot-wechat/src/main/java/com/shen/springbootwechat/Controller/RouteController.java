package com.shen.springbootwechat.Controller;

import com.shen.springbootwechat.Bean.RouteInfo;
import com.shen.springbootwechat.Service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Resource
    RouteService routeService;

    @GetMapping("/insertRoute")
    public boolean insertRoute(Integer id, boolean route_enable, String line_group, Integer number_limit, String wkhour_wkday, String wkhour_begin, String wkhour_end, String work_week, Integer number_cacheable, String destination_extension, String destination_dialplan, String destination_context){
        return routeService.addItem(id, route_enable, line_group, number_limit, wkhour_wkday, wkhour_begin, wkhour_end, work_week, number_cacheable, destination_extension, destination_dialplan, destination_context);
    }

    @GetMapping("/selectRoute")
    public RouteInfo selectRoute(Integer id){
        return routeService.queryItem(id);
    }

    @GetMapping("/updateRoute")
    public boolean updateRoute(boolean route_enable, String line_group, Integer number_limit, String wkhour_wkday, String wkhour_begin, String wkhour_end, String work_week, Integer number_cacheable, String destination_extension, String destination_dialplan, String destination_context, Integer id){
        return routeService.changeItem(route_enable, line_group, number_limit, wkhour_wkday, wkhour_begin, wkhour_end, work_week, number_cacheable, destination_extension, destination_dialplan, destination_context, id);
    }
}
