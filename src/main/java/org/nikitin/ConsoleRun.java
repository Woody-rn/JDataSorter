package org.nikitin;

import org.nikitin.config.Config;
import org.nikitin.config.Constants;
import org.nikitin.controller.Controller;
import org.nikitin.entity.StatisticDouble;
import org.nikitin.entity.StatisticInteger;
import org.nikitin.entity.StatisticString;
import org.nikitin.service.DataFileProcessor;
import org.nikitin.service.WriterByType;
import org.nikitin.service.handler.StatisticHandler;
import org.nikitin.service.handler.WriterHandler;
import org.nikitin.service.handler.WriterPool;
import org.nikitin.view.ViewConsole;

public class ConsoleRun {

    private final Config config;

    public ConsoleRun(Config config) {
        this.config = config;
    }

    public void run() {
        Controller controller = createController(config);
        controller.execute(config.getInputFiles());

    }

    private Controller createController(Config config) {
        var statChain = new StatisticInteger();
        statChain.setNextStat(new StatisticDouble())
                .setNextStat(new StatisticString());

        var writerPool = new WriterPool(Constants.DATA_TYPE_CLASS);

        var statHandler = new StatisticHandler(
                config.getStatisticsOptions(),
                statChain);
        var writerHandler = new WriterHandler(
                config.getFileOutputOptions(),
                writerPool);

        var writerByType = new WriterByType(writerHandler);
        var fileProcessor = new DataFileProcessor(writerByType, statHandler);
        var view = new ViewConsole(statHandler);

        return new Controller(fileProcessor, view);
    }
}
