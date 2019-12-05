package com.rex.springboot;

import javax.annotation.Resource;

import com.rex.springboot.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xcbeyond
 * 2018年7月19日下午3:08:04
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {
    @Resource
    private RedisUtil redisUtil;

    /**
     * 插入缓存数据
     */
    @Test
    public void set() {
        redisUtil.set("test", "test1");
    }

    /**
     * 读取缓存数据
     */
    @Test
    public void get() {
        String value = redisUtil.get("test");
        System.out.println(value);
    }

    /**
     * 更新缓存
     */
    @Test
    public void getAndSet() {
        boolean value = redisUtil.getAndSet("test", "test2");
        System.out.println(value);
    }

    /**
     * 删除缓存
     */
    @Test
    public void delete() {
        boolean value = redisUtil.delete("test");
        System.out.println(value);
    }


}