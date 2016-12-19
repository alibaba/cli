package com.taobao.middleware.cli.impl;

import com.taobao.middleware.cli.Argument;

import java.util.Comparator;

/**
 * @author wangtao 2016-12-14 16:55.
 */
public class ArgumentComparator implements Comparator<Argument> {

    @Override
    public int compare(Argument o1, Argument o2) {
        if (o1.getIndex() == o2.getIndex()) {
            return 1;
        }
        return Integer.valueOf(o1.getIndex()).compareTo(o2.getIndex());
    }


}
