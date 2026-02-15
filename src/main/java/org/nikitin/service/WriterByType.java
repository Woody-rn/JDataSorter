package org.nikitin.service;

import org.nikitin.config.Constants;
import org.nikitin.exception.AppException;
import org.nikitin.service.handler.WriterHandler;

import java.io.BufferedWriter;

public class WriterByType implements AutoCloseable {

    private final WriterHandler writerHandler;

    public WriterByType(WriterHandler writerHandler) {
        this.writerHandler = writerHandler;
    }

    public void write(String line) {
        try {
            BufferedWriter bufferWrite = writerHandler.getBufferWriteForData(line);
            bufferWrite.write(line);
            bufferWrite.newLine();
        } catch (Exception e) {
            throw new AppException(Constants.ERROR_WRITE_TO_FILE + e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        try {
            writerHandler.close();
        } catch (Exception e) {
            throw new AppException(Constants.FAILED_TO_CLOSE_WRITER, e);
        }
    }
}
