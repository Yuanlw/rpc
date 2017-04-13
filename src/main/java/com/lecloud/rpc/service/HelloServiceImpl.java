package com.lecloud.rpc.service;

import com.lecloud.rpc.inter.HelloService;

/**
 * Created by IntelliJ IDEA.
 * User: yuanliwei@le.com
 * Date: 2017/4/13
 * Time: 15:00
 * DESCRIPTION:
 */
public class HelloServiceImpl implements HelloService {


    public String sayHi(String name) {
        return " Hello, " + name;
    }
}
