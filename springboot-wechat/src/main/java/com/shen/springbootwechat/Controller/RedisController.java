package com.shen.springbootwechat.Controller;

import com.shen.springbootwechat.Bean.CallTaskBean;
import com.shen.springbootwechat.Service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    RedisService redisService;

    @GetMapping("/helloRedis")
    public String helloRedis(){
        return "hello redis!";
    }

    @GetMapping("/hasKey")
    public boolean hasKey(String key){
        return redisService.hasKey(key);
    }

    @GetMapping("/getAllTasks")
    public String getAllTasks(){
        return redisService.getAllTasks().toString();
    }

    @GetMapping("/getCallTask")
    public String getCallTask(){
        return redisService.getCallTask("cti_queue_dialer_task@xiaoyi.com","233").toString();
    }

    @GetMapping("/setCallTask")
    public boolean setCallTask(String TaskID, CallTaskBean callTaskBean){
        return redisService.setCallTask("cti_queue_dialer_task@xiaoyi.com", TaskID,callTaskBean);
    }

    @GetMapping("/delCallTask")
    public boolean delCallTask(){
        return redisService.delCallTask("cti_queue_dialer_task@xiaoyi.com","233");
    }

    @GetMapping("/getNumberList")
    public String getNumberList(){
        return redisService.getNumberList("FS_POOL:233",0,-1).toString();
    }

    @GetMapping("/setNumber")
    public boolean setNumber(String ListID, String number){
        return redisService.setNumberList(ListID,number,0);
    }

    @GetMapping("/setNumberQueue")
    public boolean setNumberQueue(String ListID, List<String> queue) {
        boolean state = true;
        for (String s:queue)
            state = state & redisService.setNumberList(ListID,s,0);
        return state;
    }

    @GetMapping("/delNumberList")
    public boolean delNumberList(){
        return redisService.delNumberList("FS_POOL:233",0);
    }
}
