package com.taobao.middleware.cli.impl;

/**
 * @author bw on 04/11/2016.
 */
public class Objects {
    public static <T> T requireNonNull(T obj) {
        if (obj == null)
            throw new NullPointerException();
        return obj;
    }
}
