package org.nikitin.entity;

import org.nikitin.config.Constants;

public class StatisticInteger extends StatisticBase {
    private int countInt;
    private long minInt;
    private long maxInt;
    private long sumInt;
    private boolean hasValues;

    @Override
    public void updateStatistic(String value) {
        countInt++;
        int parsedInt = Integer.parseInt(value);

        if (!hasValues) {
            minInt = parsedInt;
            maxInt = parsedInt;
            hasValues = true;
        } else {
            minInt = Math.min(minInt, parsedInt);
            maxInt = Math.max(maxInt, parsedInt);
        }
        sumInt += parsedInt;
    }

    @Override
    public boolean canInspectValue(String line) {
        return line.matches(Constants.REGEX_INTEGER_NUMBER);
    }

    @Override
    public StringBuilder buildShortStat(StringBuilder stringBuilder) {
        return stringBuilder.append(Constants.STATISTIC_INTEGER_NUMBER)
                .append(Constants.COUNT_NUMBER).append(countInt);
    }

    @Override
    public StringBuilder buildFullStat(StringBuilder stringBuilder) {
        return buildShortStat(stringBuilder)
                .append(Constants.MIN_VALUE).append(minInt)
                .append(Constants.MAX_VALUE).append(maxInt)
                .append(Constants.SUM_VALUE).append(sumInt)
                .append(Constants.AVG_VALUE).append(avgInt());
    }

    private long avgInt() {
        return (countInt == 0)
                ? 0
                : sumInt / countInt;
    }
}
