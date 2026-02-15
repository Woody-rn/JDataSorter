package org.nikitin.service.handler;

import lombok.Getter;
import org.nikitin.config.Constants;
import org.nikitin.config.DataType;
import org.nikitin.config.FileOutputOptions;
import org.nikitin.exception.AppException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class WriterHandler implements AutoCloseable {

    private final FileOutputOptions fileOutputOptions;
    private final WriterPool writerPool;
    private boolean isCreateDirectories;

    public WriterHandler(FileOutputOptions fileOutputOptions, WriterPool writerPool) {
        this.fileOutputOptions = fileOutputOptions;
        this.writerPool = writerPool;
    }

    public BufferedWriter getBufferWriteForData(String data) throws IOException {
        DataType dataType = determineDataType(data);
        createOutputDirectories();
        createBufferWriterOnlyElseNotExist(dataType);

        return writerPool.getWriterByType(dataType);
    }

    private StandardOpenOption[] getOptions() {
        List<StandardOpenOption> options = new ArrayList<>();
        options.add(StandardOpenOption.CREATE);
        options.add(StandardOpenOption.WRITE);
        if (fileOutputOptions.isAppendMode()) {
            options.add(StandardOpenOption.APPEND);
        } else {
            options.add(StandardOpenOption.TRUNCATE_EXISTING);
        }
        return options.toArray(new StandardOpenOption[0]);
    }

    private void createBufferWriterOnlyElseNotExist(DataType dataType) throws IOException {
        if (Objects.isNull(writerPool.getWriterByType(dataType))) {
            String fileName = getFileName(dataType);
            Path pathOutputFile = buildOutputPath(fileName);

            writerPool.initBufferWriterForType(dataType, pathOutputFile, getOptions());
        }
    }

    private void createOutputDirectories() throws IOException {
        if (!isCreateDirectories) {
            Files.createDirectories(fileOutputOptions.getDirOutputPath());
            isCreateDirectories = true;
        }
    }

    private Path buildOutputPath(String fileName) {
        String fullFileName = fileOutputOptions.getPrefix() + fileName;
        return fileOutputOptions.getDirOutputPath()
                .resolve(fullFileName);
    }

    private String getFileName(DataType dataType) {
        return switch (dataType) {
            case INTEGER -> Constants.INTEGERS_OUTPUT_FILE_NAME;
            case FLOAT -> Constants.FLOATS_OUTPUT_FILE_NAME;
            case STRING -> Constants.STRINGS_OUTPUT_FILE_NAME;
        };
    }

    private DataType determineDataType(String data) {
        if (data.matches(Constants.REGEX_INTEGER_NUMBER)) {
            return DataType.INTEGER;
        }
        if (data.matches(Constants.REGEX_FLOATING_POINT_NUMBER)) {
            return DataType.FLOAT;
        }
        return DataType.STRING;
    }

    @Override
    public void close() {
        try {
            writerPool.close();
        } catch (Exception e) {
            throw new AppException(Constants.FAILED_TO_CLOSE_WRITER, e);
        }
    }
}
