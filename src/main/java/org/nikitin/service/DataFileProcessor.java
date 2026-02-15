package org.nikitin.service;

import org.nikitin.config.Constants;
import org.nikitin.exception.AppException;
import org.nikitin.service.handler.StatisticHandler;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class DataFileProcessor {

    private final WriterByType writerByType;
    private final StatisticHandler statHandler;

    public DataFileProcessor(WriterByType writerByType,
                             StatisticHandler statHandler) {
        this.writerByType = writerByType;
        this.statHandler = statHandler;
    }

    public void processFiles(List<Path> inputFiles) {
        try (writerByType) {
            inputFiles.forEach(this::processSingleFile);
        }
    }

    private void processSingleFile(Path inputFile) {
        try {
            scanFile(inputFile);
        } catch (Exception e) {
            System.out.println("Skip file " + inputFile + e.getMessage());
        }
    }

    private void scanFile(Path inputFilesPath) {
        try (BufferedReader reader = Files.newBufferedReader(inputFilesPath)) {
            String line;
            while (Objects.nonNull(line = reader.readLine())) {
                writerByType.write(line);
                statHandler.addValue(line);
            }
        } catch (Exception e) {
            throw new AppException(Constants.ERROR_READING_FILE + e.getMessage(), e);
        }
    }
}
