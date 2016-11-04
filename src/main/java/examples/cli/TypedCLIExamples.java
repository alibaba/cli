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

package examples.cli;

import com.taobao.middleware.cli.CLI;
import com.taobao.middleware.cli.CLIs;
import com.taobao.middleware.cli.CommandLine;
import com.taobao.middleware.cli.TypedArgument;
import com.taobao.middleware.cli.TypedOption;
import com.taobao.middleware.cli.annotations.CLIConfigurator;
import com.taobao.middleware.cli.converters.Converter;

import java.io.File;
import java.util.List;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class TypedCLIExamples {

    public void example1() {
        CLI cli = CLIs.create("copy")
                .setSummary("A command line interface to copy files.")
                .addOption(new TypedOption<Boolean>()
                        .setType(Boolean.class)
                        .setLongName("directory")
                        .setShortName("R")
                        .setDescription("enables directory support")
                        .setFlag(true))
                .addArgument(new TypedArgument<File>()
                        .setType(File.class)
                        .setIndex(0)
                        .setDescription("The source")
                        .setArgName("source"))
                .addArgument(new TypedArgument<File>()
                        .setType(File.class)
                        .setIndex(0)
                        .setDescription("The destination")
                        .setArgName("target"));
    }

    public void example2(CLI cli, List<String> userCommandLineArguments) {
        CommandLine commandLine = cli.parse(userCommandLineArguments);
        boolean flag = commandLine.getOptionValue("R");
        File source = commandLine.getArgumentValue("source");
        File target = commandLine.getArgumentValue("target");
    }

    public void example3() {
        CLI cli = CLIs.create("some-name")
                .addOption(new TypedOption<Person>()
                        .setType(Person.class)
                        .setConverter(new PersonConverter())
                        .setLongName("person"));
    }

    public void example4(List<String> userCommandLineArguments) {
        CLI cli = CLIs.create(AnnotatedCli.class);
        CommandLine commandLine = cli.parse(userCommandLineArguments);
        AnnotatedCli instance = new AnnotatedCli();
        CLIConfigurator.inject(commandLine, instance);
    }

    private class Person {

    }

    private class PersonConverter implements Converter<Person> {

        @Override
        public Person fromString(String s) {
            return null;
        }
    }


}
