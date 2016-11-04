package com.taobao.middleware.cli;

import com.taobao.middleware.cli.impl.DefaultCommandLine;

/**
 * @author bw on 04/11/2016.
 */
public class CommandLines {
    /**
     * Creates a command line object from the {@link CLI}. This object is intended to be used by
     * the parser to set the argument and option values.
     *
     * @param cli the CLI definition
     * @return the command line object
     */
    public static CommandLine create(CLI cli) {
        return new DefaultCommandLine(cli);
    }
}
