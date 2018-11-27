/*
 *  Copyright (c) 2011-2013 The original author or authors
 *  ------------------------------------------------------
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *       The Eclipse Public License is available at
 *       http://www.eclipse.org/legal/epl-v10.html
 *
 *       The Apache License v2.0 is available at
 *       http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

package com.taobao.middleware.cli.impl;

import com.taobao.middleware.cli.annotations.Description;
import com.taobao.middleware.cli.annotations.Name;
import com.taobao.middleware.cli.annotations.Summary;

@Summary("A case sensitive command")
@Description("test")
@Name("case sensitive")
public class CaseSensitiveClI {

    public boolean isException;
    public boolean isRegEx;

    public String name;
    public String NO;

    @com.taobao.middleware.cli.annotations.Option(shortName = "n", longName = "name")
    @Description("name")
    public void setName(String name) {
        this.name = name;
    }

    @com.taobao.middleware.cli.annotations.Option(shortName = "N", longName = "NO")
    @Description("NO")
    public void setNO(String NO) {
        this.NO = NO;
    }

    @com.taobao.middleware.cli.annotations.Option(shortName = "e", longName = "exception", flag = true)
    @Description("Watch after throw exception")
    public void setException(boolean exception) {
        isException = exception;
    }

    @com.taobao.middleware.cli.annotations.Option(shortName = "E", longName = "regex", flag = true)
    @Description("Enable regular expression to match (wildcard matching by default)")
    public void setRegEx(boolean regEx) {
        isRegEx = regEx;
    }
}
