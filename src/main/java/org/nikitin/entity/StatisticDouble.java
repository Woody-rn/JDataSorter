package org.nikitin.entity;

import org.nikitin.config.Constants;

public class StatisticDouble extends StatisticBase {
    private int countDouble;
    private double minDouble;
    private double maxDouble;
    private double sumDouble;
    private boolean hasValues;

    @Override
    public void updateStatistic(String value) {
        countDouble++;
        double parsedDouble = Double.parseDouble(value);

        if (!hasValues) {
            minDouble = parsedDouble;
            maxDouble = parsedDouble;
            hasValues = true;
        } else {
            minDouble = Math.min(minDouble, parsedDouble);
            maxDouble = Math.max(maxDouble, parsedDouble);
        }
        sumDouble += parsedDouble;
    }

    @Override
    public boolean canInspectValue(String line) {
        return line.matches(Constants.REGEX_FLOATING_POINT_NUMBER);
    }

    @Override
    public StringBuilder buildShortStat(StringBuilder stringBuilder) {
        return stringBuilder.append(Constants.STATISTIC_FLOATING_POINT_NUMBER)
                .append(Constants.COUNT_NUMBER).append(countDouble);
    }

    @Override
    public StringBuilder buildFullStat(StringBuilder stringBuilder) {
        return buildShortStat(stringBuilder)
                .append(Constants.MIN_VALUE).append(minDouble)
                .append(Constants.MAX_VALUE).append(maxDouble)
                .append(Constants.SUM_VALUE).append(sumDouble)
                .append(Constants.AVG_VALUE).append(avgDouble());
    }

    private double avgDouble() {
        return sumDouble / countDouble;
    }
}
