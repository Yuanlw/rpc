package com.chris.test;

import com.chris.rpc.client.RPCClient;
import com.chris.rpc.service.core.Server;
import com.chris.rpc.service.core.ServiceCenter;
import com.chris.rpc.service.inter.HelloService;
import com.chris.rpc.service.service.HelloServiceImpl;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by IntelliJ IDEA.
 * User: yuanliwei@le.com
 * Date: 2017/4/13
 * Time: 18:09
 * DESCRIPTION:
 */
public class RPCTest {

    public static void main(String[] args) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Server serviceServer = new ServiceCenter(8088);
                    serviceServer.register(HelloService.class, HelloServiceImpl.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
        System.out.println(service.sayHi("test"));
    }
}
