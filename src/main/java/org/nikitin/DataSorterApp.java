package org.nikitin;

import org.nikitin.config.Config;
import picocli.CommandLine;

public class DataSorterApp {
    public static void main(String[] args) {
        Config config = parseConfig(args);
        var consoleRun = new ConsoleRun(config);
        consoleRun.run();
    }

    private static Config parseConfig(String[] args) {
        var config = new Config();
        var commandLine = new CommandLine(config);
        commandLine.parseArgs(args);

        return config;
    }
}