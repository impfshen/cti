package com.shen.springbootwechat.Bean;

import lombok.Data;

@Data
public class OutboundInfo {
    private NumberQueue numberQueue;
    private String start_time;
    private String stop_time;
    private String phone;

    @Data
    public static class NumberQueue {
        private String name;
        private String number1;
        private String number2;
        private String number3;
        private String number4;
        private String number5;
        private String number6;
        private String number7;
        private String number8;
        private String number9;
        private String openid;

        public NumberQueue() {
            name = "";
            number1 = "";
            number2 = "";
            number3 = "";
            number4 = "";
            number5 = "";
            number6 = "";
            number7 = "";
            number8 = "";
            number9 = "";
            openid = "";
        }

        public NumberQueue(String name, String number1, String number2, String number3, String number4, String number5, String number6, String number7, String number8, String number9, String openid) {
            this.name = name;
            this.number1 = number1;
            this.number2 = number2;
            this.number3 = number3;
            this.number4 = number4;
            this.number5 = number5;
            this.number6 = number6;
            this.number7 = number7;
            this.number8 = number8;
            this.number9 = number9;
            this.openid = openid;
        }
    }

    public OutboundInfo() {
        numberQueue = new NumberQueue();
        start_time = "";
        stop_time = "";
        phone = "";
    }

    public OutboundInfo(NumberQueue numberQueue, String start_time, String stop_time, String openid) {
        this.numberQueue = numberQueue;
        this.start_time = start_time;
        this.stop_time = stop_time;
        this.phone = openid;
    }

    public static OutboundInfo instance(){
        NumberQueue numberQueue = new NumberQueue();
        numberQueue.setName("test");
        numberQueue.setNumber1("13067762336");
        numberQueue.setNumber2("13758138216");
        numberQueue.setNumber3("13805737279");
        numberQueue.setOpenid("oT7V954WZK9gzaKsbBneH0yD17oc");
        OutboundInfo outboundInfo = new OutboundInfo();
        outboundInfo.setNumberQueue(numberQueue);
        outboundInfo.setStart_time("2021-06-05 16:29:42");
        outboundInfo.setStop_time("2021-06-05 16:32:46");
        outboundInfo.setPhone("15968800110");
        return outboundInfo;
    }
}
