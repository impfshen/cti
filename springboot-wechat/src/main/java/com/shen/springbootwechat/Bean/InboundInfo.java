package com.shen.springbootwechat.Bean;

public class InboundInfo {
    private String phone;
    private String start_time;
    private String stop_time;
    private String openid;

    public InboundInfo() {
        phone = "";
        start_time = "";
        stop_time = "";
        openid = "";
    }

    public InboundInfo(String phone, String start_time, String stop_time, String openid) {
        this.phone = phone;
        this.start_time = start_time;
        this.stop_time = stop_time;
        this.openid = openid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStop_time() {
        return stop_time;
    }

    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "InbountInfo{" +
                "phone='" + phone + '\'' +
                ", start_time='" + start_time + '\'' +
                ", stop_time='" + stop_time + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }

    public static InboundInfo instance(){
        InboundInfo inbountInfo = new InboundInfo();
        inbountInfo.setPhone("13067762336");
        inbountInfo.setStart_time("2021-06-05 16:29:42");
        inbountInfo.setStop_time("2021-06-05 16:32:46");
        inbountInfo.setOpenid("oT7V954WZK9gzaKsbBneH0yD17oc");
        return inbountInfo;
    }
}
