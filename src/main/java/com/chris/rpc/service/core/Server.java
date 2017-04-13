package com.chris.rpc.service.core;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: yuanliwei@le.com
 * Date: 2017/4/13
 * Time: 15:02
 * DESCRIPTION:
 */
public interface Server {

    public void start() throws IOException;

    public void stop();

    public void register(Class serviceInterface, Class impl);

    public boolean isRunning();

    public int getPort();
}
