package org.nikitin.view;

import org.nikitin.service.handler.StatisticHandler;

public class ViewConsole implements View {

    private final StatisticHandler statHandler;

    public ViewConsole(StatisticHandler statHandler) {
        this.statHandler = statHandler;
    }

    @Override
    public void outputStatistics() {
        String result = statHandler.collectStatistic();
        System.out.println(result);
    }
}
