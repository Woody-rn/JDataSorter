package org.nikitin.config;

import lombok.Getter;
import picocli.CommandLine;

import java.nio.file.Path;
import java.util.List;

import static picocli.CommandLine.Parameters;

@Getter
@CommandLine.Command
public class Config {

    @CommandLine.Mixin
    private StatisticsOptions statisticsOptions;

    @CommandLine.Mixin
    private FileOutputOptions fileOutputOptions;

    @Parameters(description = "Input files to process",
            paramLabel = "INPUT_FILES",
            arity = "1..*")
    private List<Path> inputFiles;
}
