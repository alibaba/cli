package com.taobao.middleware.cli.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

import com.taobao.middleware.cli.CLI;
import com.taobao.middleware.cli.CommandLine;
import com.taobao.middleware.cli.annotations.CLIConfigurator;

public class CaseSensitiveTest {
    @Test
    public void testCaseSensitive1() {
        CaseSensitiveClI caseSensitiveClI = new CaseSensitiveClI();
        CLI command = CLIConfigurator.define(CaseSensitiveClI.class, true);
        CommandLine commandLine = command.parse(Arrays.asList("-e"));
        CLIConfigurator.inject(commandLine, caseSensitiveClI);

        assertThat(command.getOptions()).hasSize(4);

        assertThat(caseSensitiveClI.isException).isTrue();
        assertThat(caseSensitiveClI.isRegEx).isFalse();
    }

    @Test
    public void testCaseSensitive2() {
        CaseSensitiveClI caseSensitiveClI = new CaseSensitiveClI();
        CLI command = CLIConfigurator.define(CaseSensitiveClI.class, true);
        CommandLine commandLine = command.parse(Arrays.asList("-E"));
        CLIConfigurator.inject(commandLine, caseSensitiveClI);

        assertThat(caseSensitiveClI.isException).isFalse();
        assertThat(caseSensitiveClI.isRegEx).isTrue();
    }

    @Test
    public void testCaseSensitive3() {
        CaseSensitiveClI caseSensitiveClI = new CaseSensitiveClI();
        CLI command = CLIConfigurator.define(CaseSensitiveClI.class, true);
        CommandLine commandLine = command.parse(Arrays.asList("-e"));
        CLIConfigurator.inject(commandLine, caseSensitiveClI);

        assertThat(caseSensitiveClI.isException && caseSensitiveClI.isRegEx).isFalse();
    }

    @Test
    public void testCaseSensitive4() {
        CaseSensitiveClI caseSensitiveClI = new CaseSensitiveClI();
        CLI command = CLIConfigurator.define(CaseSensitiveClI.class, true);
        CommandLine commandLine = command.parse(Arrays.asList("-E"));
        CLIConfigurator.inject(commandLine, caseSensitiveClI);

        assertThat(caseSensitiveClI.isException && caseSensitiveClI.isRegEx).isFalse();
    }

    @Test
    public void testCaseSensitive5() {
        CaseSensitiveClI caseSensitiveClI = new CaseSensitiveClI();
        CLI command = CLIConfigurator.define(CaseSensitiveClI.class, true);
        CommandLine commandLine = command.parse(Arrays.asList("-e", "-E"));
        CLIConfigurator.inject(commandLine, caseSensitiveClI);

        assertThat(caseSensitiveClI.isException).isTrue();
        assertThat(caseSensitiveClI.isRegEx).isTrue();
    }

    @Test
    public void testCaseSensitiveN1() {
        CaseSensitiveClI caseSensitiveClI = new CaseSensitiveClI();
        CLI command = CLIConfigurator.define(CaseSensitiveClI.class, true);
        CommandLine commandLine = command.parse(Arrays.asList("-n", "hello"));
        CLIConfigurator.inject(commandLine, caseSensitiveClI);

        assertThat(caseSensitiveClI.name).isEqualTo("hello");
        assertThat(caseSensitiveClI.NO).isNull();
    }

    @Test
    public void testCaseSensitiveN2() {
        CaseSensitiveClI caseSensitiveClI = new CaseSensitiveClI();
        CLI command = CLIConfigurator.define(CaseSensitiveClI.class, true);
        CommandLine commandLine = command.parse(Arrays.asList("-N", "2334"));
        CLIConfigurator.inject(commandLine, caseSensitiveClI);

        assertThat(caseSensitiveClI.NO).isEqualTo("2334");
        assertThat(caseSensitiveClI.name).isNull();
    }

    @Test
    public void testCaseSensitiveN3() {
        CaseSensitiveClI caseSensitiveClI = new CaseSensitiveClI();
        CLI command = CLIConfigurator.define(CaseSensitiveClI.class, true);
        CommandLine commandLine = command.parse(Arrays.asList("-N", "2334", "-n", "hello"));
        CLIConfigurator.inject(commandLine, caseSensitiveClI);

        assertThat(caseSensitiveClI.NO).isEqualTo("2334");
        assertThat(caseSensitiveClI.name).isEqualTo("hello");
    }
}
