package org.nikitin.controller;

import org.nikitin.service.DataFileProcessor;
import org.nikitin.view.View;

import java.nio.file.Path;
import java.util.List;

public class Controller {

    private final DataFileProcessor dataFileProcessor;
    private final View view;

    public Controller(DataFileProcessor parserFile,
                      View view) {
        this.dataFileProcessor = parserFile;
        this.view = view;
    }

    public void execute(List<Path> inputFiles) {
        dataFileProcessor.processFiles(inputFiles);
        view.outputStatistics();
    }
}
