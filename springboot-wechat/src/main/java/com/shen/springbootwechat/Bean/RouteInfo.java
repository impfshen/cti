package com.shen.springbootwechat.Bean;

public class RouteInfo {
    private Integer id;
    private String destination_context;
    private String destination_extension;
    private String destination_dialplan;
    private boolean route_enable;
    private Integer number_limit;
    private String line_group;
    private Integer number_cacheable;
    private String wkhour_wkday;
    private String wkhour_begin;
    private String wkhour_end;
    private String work_week;

    public RouteInfo() {
        id = 0;
        destination_context = "";
        destination_extension = "";
        destination_dialplan = "";
        route_enable = false;
        number_limit = 0;
        line_group = "";
        number_cacheable = -1;
        wkhour_wkday = "";
        wkhour_begin = "";
        wkhour_end = "";
        work_week = "";
    }

    public RouteInfo(Integer id, String destination_context, String destination_extension, String destination_dialplan, boolean route_enable, Integer number_limit, String line_group, Integer number_cacheable, String wkhour_wkday, String wkhour_begin, String wkhour_end, String work_week) {
        this.id = id;
        this.destination_context = destination_context;
        this.destination_extension = destination_extension;
        this.destination_dialplan = destination_dialplan;
        this.route_enable = route_enable;
        this.number_limit = number_limit;
        this.line_group = line_group;
        this.number_cacheable = number_cacheable;
        this.wkhour_wkday = wkhour_wkday;
        this.wkhour_begin = wkhour_begin;
        this.wkhour_end = wkhour_end;
        this.work_week = work_week;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestination_context() {
        return destination_context;
    }

    public void setDestination_context(String destination_context) {
        this.destination_context = destination_context;
    }

    public String getDestination_extension() {
        return destination_extension;
    }

    public void setDestination_extension(String destination_extension) {
        this.destination_extension = destination_extension;
    }

    public String getDestination_dialplan() {
        return destination_dialplan;
    }

    public void setDestination_dialplan(String destination_dialplan) {
        this.destination_dialplan = destination_dialplan;
    }

    public boolean isRoute_enable() {
        return route_enable;
    }

    public void setRoute_enable(boolean route_enable) {
        this.route_enable = route_enable;
    }

    public Integer getNumber_limit() {
        return number_limit;
    }

    public void setNumber_limit(Integer number_limit) {
        this.number_limit = number_limit;
    }

    public String getLine_group() {
        return line_group;
    }

    public void setLine_group(String line_group) {
        this.line_group = line_group;
    }

    public Integer getNumber_cacheable() {
        return number_cacheable;
    }

    public void setNumber_cacheable(Integer number_cacheable) {
        this.number_cacheable = number_cacheable;
    }

    public String getWkhour_wkday() {
        return wkhour_wkday;
    }

    public void setWkhour_wkday(String wkhour_wkday) {
        this.wkhour_wkday = wkhour_wkday;
    }

    public String getWkhour_begin() {
        return wkhour_begin;
    }

    public void setWkhour_begin(String wkhour_begin) {
        this.wkhour_begin = wkhour_begin;
    }

    public String getWkhour_end() {
        return wkhour_end;
    }

    public void setWkhour_end(String wkhour_end) {
        this.wkhour_end = wkhour_end;
    }

    public String getWork_week() {
        return work_week;
    }

    public void setWork_week(String work_week) {
        this.work_week = work_week;
    }

    @Override
    public String toString() {
        return "RouteInfo{" +
                "id=" + id +
                ", destination_context='" + destination_context + '\'' +
                ", destination_extension='" + destination_extension + '\'' +
                ", destination_dialplan='" + destination_dialplan + '\'' +
                ", route_enable=" + route_enable +
                ", number_limit=" + number_limit +
                ", line_group='" + line_group + '\'' +
                ", number_cacheable=" + number_cacheable +
                ", wkhour_wkday='" + wkhour_wkday + '\'' +
                ", wkhour_begin='" + wkhour_begin + '\'' +
                ", wkhour_end='" + wkhour_end + '\'' +
                ", work_week='" + work_week + '\'' +
                '}';
    }

    public static RouteInfo instanse(){
        RouteInfo routeInfo = new RouteInfo();
        routeInfo.setId(233);
        routeInfo.setDestination_context("robot");
        routeInfo.setDestination_dialplan("XML");
        routeInfo.setDestination_extension("6\\v3");
        routeInfo.setRoute_enable(true);
        routeInfo.setNumber_limit(9);
        routeInfo.setLine_group("138,test");
        routeInfo.setNumber_cacheable(0);
        routeInfo.setWkhour_wkday("-1,-1");
        routeInfo.setWkhour_begin("08:00:00,14:00:00");
        routeInfo.setWkhour_end("12:00:00,20:00:00");
        routeInfo.setWork_week("0,1,2,3,4,5,6");
        return routeInfo;
    }
}
