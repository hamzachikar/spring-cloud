package com.microservices.limitsservice.beans;



public class LimitsConfiguration {
    private int maximum;
    private int minimun;

    public LimitsConfiguration() { }
    public LimitsConfiguration(int maximum, int minimun) {
        this.maximum = maximum;
        this.minimun = minimun;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public int getMinimun() {
        return minimun;
    }

    public void setMinimun(int minimun) {
        this.minimun = minimun;
    }
}
