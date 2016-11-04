package com.taobao.middleware.cli;

import com.taobao.middleware.cli.annotations.CLIConfigurator;
import com.taobao.middleware.cli.impl.DefaultCLI;

/**
 * @author bw on 04/11/2016.
 */
public class CLIs {
    /**
     * Creates an instance of {@link CLI} using the default implementation.
     *
     * @param name the name of the CLI (must not be {@code null})
     * @return the created instance of {@link CLI}
     */
    public static CLI create(String name) {
        return new DefaultCLI().setName(name);
    }

    /**
     * Creates an instance of {@link CLI} from the given Java class. It instantiates the {@link CLI} object from the
     * annotations used in the class.
     *
     * @param clazz the annotated class
     * @return the created instance of {@link CLI}
     */
    public static CLI create(Class<?> clazz) {
        return CLIConfigurator.define(clazz);
    }
}
