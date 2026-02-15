package org.nikitin.entity;

import java.util.Objects;

public abstract class StatisticBase {
    StatisticBase next;

    public StatisticBase setNextStat(StatisticBase next) {
        this.next = next;
        return next;
    }

    public abstract void updateStatistic(String value);

    public abstract boolean canInspectValue(String line);

    public abstract StringBuilder buildShortStat(StringBuilder stringBuilder);

    public abstract StringBuilder buildFullStat(StringBuilder stringBuilder);

    public void enrichStatistic(String line) {
        if (canInspectValue(line)) {
            updateStatistic(line);
        } else if (next != null) {
            next.enrichStatistic(line);
        }
    }

    public final String collectShortStat() {
        var stringBuilder = new StringBuilder();
        StatisticBase current = this;

        while (Objects.nonNull(current)) {
            current.buildShortStat(stringBuilder);
            current = current.next;
        }
        return stringBuilder.toString();
    }

    public final String collectFullStat() {
        var stringBuilder = new StringBuilder();
        StatisticBase current = this;

        while (Objects.nonNull(current)) {
            current.buildFullStat(stringBuilder);
            current = current.next;
        }
        return stringBuilder.toString();
    }
}
