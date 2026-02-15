package org.nikitin.service.handler;

import org.nikitin.config.Constants;
import org.nikitin.config.DataType;
import org.nikitin.exception.AppException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class WriterPool implements AutoCloseable {
    private final Map<DataType, BufferedWriter> activeWriters;

    public WriterPool(Class<DataType> clazz) {
        this.activeWriters = new EnumMap<>(clazz);
    }

    public BufferedWriter getWriterByType(DataType dataType) {
        return activeWriters.get(dataType);

    }

    public void initBufferWriterForType(DataType dataType,
                                        Path pathOutputFile,
                                        StandardOpenOption[] options) throws IOException {
        activeWriters.put(dataType, Files.newBufferedWriter(pathOutputFile, options));

    }

    @Override
    public void close() {
        activeWriters.values().stream()
                .filter(Objects::nonNull)
                .forEach(writer -> {
                    try {
                        writer.flush();
                        writer.close();
                    } catch (IOException e) {
                        throw new AppException(Constants.FAILED_TO_CLOSE_WRITER, e);
                    }
                });
        activeWriters.clear();
    }
}
