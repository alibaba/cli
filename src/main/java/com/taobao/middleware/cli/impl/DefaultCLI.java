/*
 *  Copyright (c) 2011-2015 The original author or authors
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

import com.taobao.middleware.cli.Argument;
import com.taobao.middleware.cli.CLI;
import com.taobao.middleware.cli.CommandLine;
import com.taobao.middleware.cli.Option;
import com.taobao.middleware.cli.UsageMessageFormatter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * Default implementation of {@link CLI}.
 *
 * @author Clement Escoffier <clement@apache.org>
 */
public class DefaultCLI implements CLI {

    protected String name;
    protected String description;
    protected String summary;
    protected boolean hidden;

    protected List<Option> options = new ArrayList<Option>();
    private List<Argument> arguments = new ArrayList<Argument>();

    /**
     * Parses the user command line interface and create a new {@link CommandLine} containing extracting values.
     *
     * @param arguments the arguments
     * @return the creates command line
     */
    @Override
    public CommandLine parse(List<String> arguments) {
        return new DefaultParser().parse(this, arguments);
    }

    /**
     * Parses the user command line interface and create a new {@link CommandLine} containing extracting values.
     *
     * @param arguments the arguments
     * @param validate  enable / disable parsing validation
     * @return the creates command line
     */
    @Override
    public CommandLine parse(List<String> arguments, boolean validate) {
        return new DefaultParser().parse(this, arguments, validate);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CLI setName(String name) {
        Objects.requireNonNull(name);
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Invalid command name");
        }
        this.name = name;
        return this;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public CLI setDescription(String desc) {
        Objects.requireNonNull(desc);
        description = desc;
        return this;
    }

    @Override
    public String getSummary() {
        return summary;
    }

    @Override
    public CLI setSummary(String summary) {
        Objects.requireNonNull(summary);
        this.summary = summary;
        return this;
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public CLI setHidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    @Override
    public List<Option> getOptions() {
        return options;
    }

    @Override
    public CLI addOption(Option option) {
        Objects.requireNonNull(option);
        options.add(option);
        return this;
    }

    @Override
    public CLI addOptions(List<Option> options) {
        Objects.requireNonNull(options);
        for (Option option : options) {
            addOption(option);
        }
        return this;
    }

    @Override
    public CLI setOptions(List<Option> options) {
        Objects.requireNonNull(options);
        this.options = new ArrayList<Option>();
        return addOptions(options);
    }

    @Override
    public List<Argument> getArguments() {
        return arguments;
    }

    @Override
    public CLI addArgument(Argument arg) {
        Objects.requireNonNull(arg);
        arguments.add(arg);
        return this;
    }

    @Override
    public CLI addArguments(List<Argument> args) {
        Objects.requireNonNull(args);
        for (Argument arg : args) {
            addArgument(arg);
        }
        return this;
    }

    @Override
    public CLI setArguments(List<Argument> args) {
        Objects.requireNonNull(args);
        arguments = new ArrayList<Argument>(args);
        return this;
    }

    @Override
    public Option getOption(String name) {
        Objects.requireNonNull(name);
        // The option by name look up is a three steps lookup:
        // first check by long name
        // then by short name
        // finally by arg name
        for (Option option : options) {
            if (name.equalsIgnoreCase(option.getLongName())) {
                return option;
            }
        }

        for (Option option : options) {
            if (name.equalsIgnoreCase(option.getShortName())) {
                return option;
            }
        }

        for (Option option : options) {
            if (name.equalsIgnoreCase(option.getArgName())) {
                return option;
            }
        }

        return null;
    }

    @Override
    public Argument getArgument(String name) {
        Objects.requireNonNull(name);
        for (Argument arg : arguments) {
            if (name.equalsIgnoreCase(arg.getArgName())) {
                return arg;
            }
        }
        return null;
    }

    @Override
    public Argument getArgument(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Given index cannot be negative");
        }
        for (Argument arg : arguments) {
            if (index == arg.getIndex()) {
                return arg;
            }
        }
        return null;
    }


    @Override
    public CLI removeOption(String name) {
        for (Iterator<Option> iterator = options.iterator(); iterator.hasNext(); ) {
            Option option = iterator.next();
            if (option.getLongName().equals(name) || option.getShortName().equals(name)) {
                iterator.remove();
            }
        }
        return this;
    }

    @Override
    public CLI removeArgument(int index) {
        for (Argument arg : new TreeSet<Argument>(arguments)) {
            if (arg.getIndex() == index) {
                arguments.remove(arg);
                return this;
            }
        }
        return this;
    }

    @Override
    public CLI usage(StringBuilder builder) {
        new UsageMessageFormatter().usage(builder, this);
        return this;
    }

    @Override
    public CLI usage(StringBuilder builder, String prefix) {
        new UsageMessageFormatter().usage(builder, prefix, this);
        return this;
    }
}
