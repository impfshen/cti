package com.shen.springbootwechat.Interface;

import com.shen.springbootwechat.Bean.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuInterface {

    @Autowired
    HttpInterface httpInterface;

    /**
     * 更新全部菜单
     * @param menu
     * @return 返回接口返回值
     */
    public String addMenu(String token, Menu menu){
        if (menu == null || menu.getButton() == null)
            throw new NullPointerException("参数不可为空");
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token;
        return httpInterface.doPost(url,menu);
    }

    /**
     * 查询菜单结构
     * @return 返回菜单接口返回值
     */
    public String qryMenu(String token){
        String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + token;
        return httpInterface.doGet(url);
    }

    /**
     * 删除全部菜单接口
     * @return 接口返回值
     */
    public String delMenu(String token){
        String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + token;
        return httpInterface.doGet(url);
    }
}
