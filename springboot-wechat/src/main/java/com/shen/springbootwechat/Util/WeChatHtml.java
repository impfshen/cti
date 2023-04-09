package com.shen.springbootwechat.Util;

public class WeChatHtml {
    public static final String WECHAT_EXAMPLE =
            "<p>" +
            "<img src=\\\"\\\" alt=\\\"\\\" data-width=\\\"null\\\" data-ratio=\\\"NaN\\\">" +
            "<br  /><img src=\\\"\\\" alt=\\\"\\\" data-width=\\\"null\\\" data-ratio=\\\"NaN\\\"><br  />" +
            "</p>";
    public static final String VERTICLE_TABLE =
            "<p>" +
            "<table border=\"1\">" +
            "<caption>verticle</caption>" +
            "<tr>" + "<th>Name</th>" + "<td>Shen PengFei</td>" + "</tr>" +
            "<tr>" + "<th>SID</th>" + "<td>201806020913</td>" + "</tr>" +
            "<tr>" + "<th>Class</th>" + "<td>IOT 1802</td>" + "</tr>" +
            "<tr>" + "<th>Phone</th>" + "<td>15968800110</td>" + "</tr>" +
            "</table>" +
            "</p>";
    public static final String HORIZONTAL_TABLE =
            "<p>" +
            "<table border=\"1\">" +
            "<caption>horitontal</caption>" +
            "<tr>" + "<th>Name</th>" + "<th>SID</th>" + "<th>Class</th>" + "<th>Phone</th>" + "</tr>" +
            "<tr>" + "<td>Shen PengFei</td>" + "<td>201806020913</td>" + "<td>IOT 1802</td>" + "<td>15968800110</td>" + "</tr>" +
            "</table>" +
            "</p>";
    public static final String README_ARTICLE =
            "<p>" +
            "<table border=\"1\">" +
            "<caption>毕业设计项目介绍</caption>" +
            "<tr>" + "<th>课题名称</th>" + "<td>基于公众号的智能语音通知系统设计与实现</td>" + "</tr>" +
            "<tr>" + "<th>技术需求</th>" + "<td>技术要求使用JAVA开发，框架采用Springboot+Mybatis</td>" + "</tr>" +
            "<tr>" + "<th>用户分类</th>" + "<td>个人，平台管理员，平台运营人员</td>" + "</tr>" +
            "<tr>" + "<th>使用场景</th>" + "<td>个人需要语音提醒的场景</td>" + "</tr>" +
            "<tr>" + "<th>基础功能</th>" + "<td>注册，个人实名认证</td>" + "</tr>" +
            "<tr>" + "<th>使用功能</th>" + "<td>外呼任务创建，包含创建清单导入，呼叫语音内容导入，支持文字导入系统生成，和个性化语音录入，呼叫时间设置，呼叫场景输入。此外，还有任务提交，任务审核，关闭任务的功能</td>" + "</tr>" +
            "<tr>" + "<th>审核功能</th>" + "<td>个人提交任务后，平台运营人员审核，审核通过后，任务生效开始执行外呼任务。审核不通过打回，个人可重新进行修改</td>" + "</tr>" +
            "<tr>" + "<th>主要任务与目标</th>" + "<td>本课题针对语音呼叫通知场景，设计与实现能够自定义通知内容的智能对话系统，主要包括微信公众号用户端和对话系统设计</td>" + "</tr>" +
            "<tr>" + "<th>主要内容与基本要求</th>" + "<td>（1）自定义通知内容（2）对话机器人交互（3）呼叫、记录、查询、统计功能</td>" + "</tr>" +
            "<tr>" + "<th>主要负责人</th>" + "<td>沈鹏飞</td>" + "</tr>" +
            "</table>" +
            "</p>";
}
