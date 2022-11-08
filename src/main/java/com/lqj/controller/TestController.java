package com.lqj.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.lqj.datasource.DynamicDataSource;
import com.lqj.entity.TeleFrameInfoEntity;
import com.lqj.entity.User;
import com.lqj.mapper.TeleFrameInfoMapper;
import com.lqj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author lqj
 * @Date 2022-11-08 11:01
 */
@RestController
public class TestController {

    @Autowired
    private TeleFrameInfoMapper teleFrameInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test")
    public String test(){
        //默认数据源
        List<TeleFrameInfoEntity> list = teleFrameInfoMapper.selectList(null);
        return list.get(0).toString();
    }

    @GetMapping("/test2")
    public String test2(String dataSourceName){
        //数据源 test
        change(dataSourceName);
        //切换数据源
        DynamicDataSource.setDataSource(dataSourceName);

        List<TeleFrameInfoEntity> list = teleFrameInfoMapper.selectList(null);
        System.out.println(list.get(0).toString());
        DynamicDataSource.clear();
        return list.get(0).toString();
    }

    @GetMapping("/test3")
    public String test3(String dataSourceName){
        //数据源 sc
        change(dataSourceName);
        //切换数据源
        DynamicDataSource.setDataSource(dataSourceName);

        List<User> list1 = userMapper.selectList(null);
        System.out.println(list1.get(0).toString());
        DynamicDataSource.clear();
        return list1.get(0).toString();
    }

    public void change(String dataSourceName){
        if(DynamicDataSource.dataSourcesMap.get(dataSourceName) == null){
            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.setUrl("jdbc:mysql://localhost:3306/"+dataSourceName+"?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&useAffectedRows=true");
            druidDataSource.setUsername("root");
            druidDataSource.setPassword("root");
            DynamicDataSource.dataSourcesMap.put(dataSourceName, druidDataSource);
        }
    }

}
