package org.nikitin.entity;

import org.nikitin.config.Constants;

import java.math.BigDecimal;
import java.math.MathContext;

public class StatisticDouble extends StatisticBase {
    private int countDouble;
    private BigDecimal minBigDecimal;
    private BigDecimal maxBigDecimal;
    private BigDecimal sumBigDecimal = BigDecimal.ZERO;
    private boolean hasValues;

    @Override
    public void updateStatistic(String value) {
        countDouble++;
        BigDecimal parsedDouble = new BigDecimal(value);

        if (!hasValues) {
            minBigDecimal = parsedDouble;
            maxBigDecimal = parsedDouble;
            hasValues = true;
        } else {
            minBigDecimal = minBigDecimal.min(parsedDouble);
            maxBigDecimal = maxBigDecimal.max(parsedDouble);
        }
        sumBigDecimal = sumBigDecimal.add(parsedDouble);
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
                .append(Constants.MIN_VALUE).append(minBigDecimal)
                .append(Constants.MAX_VALUE).append(maxBigDecimal)
                .append(Constants.SUM_VALUE).append(sumBigDecimal)
                .append(Constants.AVG_VALUE).append(avgDouble());
    }

    private BigDecimal avgDouble() {
        if (countDouble == 0) {
            return BigDecimal.ZERO;
        }
        return sumBigDecimal.divide(
                BigDecimal.valueOf(countDouble),
                MathContext.DECIMAL64
        );
    }
}