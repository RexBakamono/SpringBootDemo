package com.rex.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rex.springboot.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/token")
public class UUIDToken {
    @Autowired
    RedisTemplate redisTemplate = new RedisTemplate();

    @RequestMapping("/getToken")
    public String getToken (HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 将uuid存储到redis 设置过期时间
        String user_id = "111";
        Map<String, Object> tokenData = new HashMap<String, Object>();
        tokenData.put("user_id", user_id);
        tokenData.put("ip", IpUtils.getIpAddr(request));
        redisTemplate.opsForHash().putAll(uuid, tokenData);
        redisTemplate.expire(uuid, 1, TimeUnit.MINUTES);
        map.put("token", uuid);
        map.put("msg", "获取成功！");
        return JSONObject.toJSONString(map);
    }

    @RequestMapping("/verifyToken")
    public String verifyToken (String token) {
        if (token == null){
            return null;
        }
        Map<String, Object> tokenData = redisTemplate.opsForHash().entries(token);// ?
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_id", tokenData.get("user_id"));
        map.put("ip", tokenData.get("ip"));
        map.put("msg", "验证成功！");
        return JSONObject.toJSONString(map);
    }


}
