package org.nikitin.config;

import lombok.Getter;
import org.nikitin.exception.AppException;
import picocli.CommandLine;

@Getter
public class StatisticsOptions {

    private boolean shortStatistics;
    private boolean fullStatistics;

    @CommandLine.Option(names = "-s",
            description = "Short statistic (only count of elements)")
    public void setShortStatistics(boolean shortStatistics) {
        this.shortStatistics = shortStatistics;
        validate();
    }

    @CommandLine.Option(names = "-f",
            description = "Full statistic (count, mix, max, avg)")
    public void setFullStatistics(boolean fullStatistics) {
        this.fullStatistics = fullStatistics;
        validate();
    }

    private void validate() {
        if (fullStatistics && shortStatistics) {
            throw new AppException(Constants.OPTIONS_STATISTIC_CANNOT_BE_TOGETHER);
        }
    }
}
