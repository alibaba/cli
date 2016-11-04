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

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Some utilities methods to ease reflection calls.
 *
 * @author Clement Escoffier <clement@apache.org>
 */
public class ReflectionUtils {

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot instantiate " + clazz.getName(), e);
        }
    }

    public static boolean isSetter(Method method) {
        return method.getName().startsWith("set") && method.getParameterTypes().length == 1;
    }

    public static List<Method> getSetterMethods(Class<?> clazz) {
        List<Method> setters = new LinkedList<Method>();
        for (Method method : clazz.getMethods()) {
            if (ReflectionUtils.isSetter(method)) {
                setters.add(method);
            }
        }
        return setters;
    }

    public static boolean isMultiple(Method setter) {
        final Class<?> type = setter.getParameterTypes()[0];
        return type.isArray() || List.class.isAssignableFrom(type) || Set.class.isAssignableFrom(type)
                || Collection.class.isAssignableFrom(type);
    }

    public static Class getComponentType(Method method, int index) {
        Class<?> type = method.getParameterTypes()[index];
        if (type.isArray()) {
            return type.getComponentType();
        }

        Type genericType = method.getGenericParameterTypes()[index];
        if (genericType != null) {
            return (Class) ((ParameterizedType) genericType).getActualTypeArguments()[0];
        }

        Type superType = type.getGenericSuperclass();
        if (superType instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) superType).getActualTypeArguments()[0];
        }

        return null;
    }
}
