package org.nikitin.entity;

import org.nikitin.config.Constants;

import java.math.BigInteger;

public class StatisticInteger extends StatisticBase {
    private int countInt;
    private BigInteger minBigInt;
    private BigInteger maxBigInt;
    private BigInteger sumBigInt = BigInteger.ZERO;
    private boolean hasValues;

    @Override
    public void updateStatistic(String value) {
        countInt++;
        BigInteger parsedInt = new BigInteger(value);

        if (!hasValues) {
            minBigInt = parsedInt;
            maxBigInt = parsedInt;
            hasValues = true;
        } else {
            minBigInt = minBigInt.min(parsedInt);
            maxBigInt = maxBigInt.max(parsedInt);
        }
        sumBigInt = sumBigInt.add(parsedInt);
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
                .append(Constants.MIN_VALUE).append(minBigInt)
                .append(Constants.MAX_VALUE).append(maxBigInt)
                .append(Constants.SUM_VALUE).append(sumBigInt)
                .append(Constants.AVG_VALUE).append(avgInt());
    }

    private BigInteger avgInt() {
        if (countInt == 0) {
            return BigInteger.ZERO;
        }
        return sumBigInt.divide(BigInteger.valueOf(countInt));
    }
}