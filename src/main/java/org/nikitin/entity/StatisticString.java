package org.nikitin.entity;

import org.nikitin.config.Constants;

public class StatisticString extends StatisticBase {
    private int countStr;
    private int minStrLength;
    private int maxStrLength;
    private boolean hasValues;

    @Override
    public void updateStatistic(String value) {
        countStr++;
        if (!hasValues) {
            minStrLength = value.length();
            hasValues = true;
        } else{
            minStrLength = Math.min(minStrLength, value.length());
        }
        maxStrLength = Math.max(maxStrLength, value.length());
    }

    @Override
    public boolean canInspectValue(String line) {
        return !line.matches(Constants.REGEX_INTEGER_NUMBER)
                && !line.matches(Constants.REGEX_FLOATING_POINT_NUMBER);
    }

    @Override
    public StringBuilder buildShortStat(StringBuilder stringBuilder) {
        return stringBuilder.append(Constants.STATISTIC_STRING)
                .append(Constants.COUNT_NUMBER).append(countStr);
    }

    @Override
    public StringBuilder buildFullStat(StringBuilder stringBuilder) {
        return buildShortStat(stringBuilder)
                .append(Constants.MIN_LENGTH_LINE).append(minStrLength)
                .append(Constants.MAX_LENGTH_LINE).append(maxStrLength);
    }
}
