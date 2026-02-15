package org.nikitin.service.handler;

import lombok.Getter;
import org.nikitin.config.Constants;
import org.nikitin.config.StatisticsOptions;
import org.nikitin.entity.StatisticBase;

@Getter
public class StatisticHandler {

    private final StatisticsOptions statOptions;
    private final StatisticBase statBase;

    public StatisticHandler(StatisticsOptions statisticsOptions,
                            StatisticBase statisticBase) {
        this.statOptions = statisticsOptions;
        this.statBase = statisticBase;
    }

    public void addValue(String line) {
        statBase.enrichStatistic(line);
    }


    public String collectStatistic() {
        if (statOptions.isShortStatistics()) {
            return statBase.collectShortStat();
        } else if (statOptions.isFullStatistics()) {
            return statBase.collectFullStat();
        }
        return Constants.EMPTY_STRING;
    }
}
