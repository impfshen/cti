package com.shen.springbootwechat.Bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CallTaskBean {
    private CallTaskBean.ParamsBean params;
    private CallTaskBean.VariablesBean variables;

    @Data
    public static class ParamsBean {
        private boolean enable;
        private List<String> line_group;
        private Integer limit;
        private String start_time;
        private String stop_time;
        private List<CallTaskBean.DateBean> work_hour;
        private List<Integer> work_week;
        private String number_queue;
        private Integer number_cacheable;
        private String destination_extension;
        private String destination_dialplan;
        private String destination_context;
    }

    @Data
    public static class DateBean {
        private String wday;
        private String begin;
        private String end;

        public DateBean() {}

        public DateBean(String wday, String begin, String end) {
            this.wday = wday;
            this.begin = begin;
            this.end = end;
        }
    }

    @Data
    public static class VariablesBean {
        private String origination_caller_id_number;
    }

    public static CallTaskBean instance() {

        ParamsBean paramsBean = new ParamsBean();
        paramsBean.setDestination_context("robot");
        paramsBean.setDestination_dialplan("XML");
        paramsBean.setDestination_extension("6/v3");
        paramsBean.setEnable(true);
        paramsBean.setLimit(9);
        List<String> list_line_group = new ArrayList<>();
        list_line_group.add("138");
        paramsBean.setLine_group(list_line_group);
        paramsBean.setNumber_cacheable(0);
        paramsBean.setNumber_queue("FS_POOL:233:18");
        paramsBean.setStart_time("2022-05-16 16:00:00");
        paramsBean.setStop_time("2022-06-16 17:00:00");
        List<DateBean> list_work_hour = new ArrayList<>();
        DateBean dateBean = new DateBean();
        dateBean.setWday("-1");
        dateBean.setBegin("08:00:00");
        dateBean.setEnd("23:00:00");
        list_work_hour.add(dateBean);
        paramsBean.setWork_hour(list_work_hour);
        List<Integer> list_work_week = new ArrayList<>();
        list_work_week.add(0);
        list_work_week.add(1);
        list_work_week.add(2);
        list_work_week.add(3);
        list_work_week.add(4);
        list_work_week.add(5);
        list_work_week.add(6);
        paramsBean.setWork_week(list_work_week);

        VariablesBean variablesBean = new VariablesBean();
        variablesBean.setOrigination_caller_id_number("3001");

        CallTaskBean instance = new CallTaskBean();
        instance.setParams(paramsBean);
        instance.setVariables(variablesBean);

        return instance;
    }

}
