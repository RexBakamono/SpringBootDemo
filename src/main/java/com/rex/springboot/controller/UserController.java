package com.rex.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.rex.springboot.bean.User;
import com.rex.springboot.service.UserService;
import com.rex.springboot.utils.Base64Utils;
import com.rex.springboot.utils.MD5Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    public PrintWriter pWriter(HttpServletResponse response) {
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pw;
    }


    /**
     * erp登录接口
     *
     * @throws IOException
     */
    @RequestMapping("/login")
    public void login() throws IOException {
        String name = "Tabor";
        String pass = "yh123456";
        String s1 = new MD5Tools().MD5Str("youhe123").toLowerCase();
        String s2 = new MD5Tools().MD5Str(name + new MD5Tools().MD5Str(pass).toLowerCase()).toLowerCase();
        String url = "http://192.168.11.9:8096/app/user_loginTest?key=" + s1 + "&wname=" + name + "&password=" + s2;
        System.out.println(url);
    }


    /**
     * 测试
     *
     * @param response
     */
    @RequestMapping("/query")
    public void query(HttpServletResponse response) {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = pWriter(response);
        List<User> ls = userService.query();
        String js = JSON.toJSONString(ls);
        pw.write(js);
    }


    @RequestMapping("ftl")
    public ModelAndView ftl(HttpSession session) {
        List<User> ls = userService.query();

        List list = new ArrayList();
        Map map1 = new HashMap();
        map1.put("phone", "13655555555");
        map1.put("email", "admin@vip.com");
        list.add(map1);
        Map map2 = new HashMap();
        map2.put("phone", "13888888888");
        map2.put("email", "china@vip.com");
        map2.put("address", "beijing");
        list.add(map2);

        session.setAttribute("list", list);

        return new ModelAndView("err", "ls", ls);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String a = null;
        try {
            // 一个键对应多个值
            MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
            map.add("user","123");
            map.add("user","456");
            map.add("user","789");
            System.out.println(map.get("user"));

            a = Base64Utils.Encoder("API_RECEIVEDATADC408976A7BB4211420FCD4ADE03455B");
            System.out.println(a);
            System.out.println(Base64Utils.Decoder(a));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
