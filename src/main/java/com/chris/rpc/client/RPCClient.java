package com.chris.rpc.client;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: yuanlw
 * Date: 2017/4/13
 * Time: 17:13
 * DESCRIPTION:
 */
public class RPCClient<T> {

    public static <T> T getRemoteProxyObj(final Class<?> serviceInterface, final InetSocketAddress addr) {
        //1.将本地接口调用转换成jdk的动态代理，在动态代理中实现接口的远程调用
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class[]{serviceInterface}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = null;
                ObjectOutputStream output = null;
                ObjectInputStream input = null;
                try {//2.创建Socket客户端，根据指定地址连接远程服务
                    socket = new Socket();
                    socket.connect(addr);
                    //3、将远程服务调用所需的接口类，方法名、参数列表等编码后发送给服务端
                    output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeUTF(serviceInterface.getName());
                    output.writeUTF(method.getName());
                    output.writeObject(method.getParameterTypes());
                    output.writeObject(args);
//4、同步阻塞等待服务端返回，获取应答后返回
                    input = new ObjectInputStream(socket.getInputStream());
                    return input.readObject();

                } catch (Exception e) {
                } finally {
                    if (socket != null) socket.close();
                    if (output != null) output.close();
                    if (input != null) input.close();
                }


                return null;
            }
        });


    }


}
