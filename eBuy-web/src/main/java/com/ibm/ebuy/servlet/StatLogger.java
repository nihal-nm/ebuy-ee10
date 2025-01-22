package com.ibm.ebuy.servlet;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StatLogger {
    private int targetNumber = 1000;
    private double totalTime = 0;
    private double currentTime = 0;
    private double maxTime = Double.NEGATIVE_INFINITY;
    private double minTime = Double.POSITIVE_INFINITY;
    private int checkoutCount = 0;
	private double time = System.currentTimeMillis();

    private final static Logger log = Logger.getLogger("ebuy");

    public void log(String message) {
       log.log(Level.INFO, message);
    }

    public StatLogger(int n) {
        this.targetNumber = n;
    }

    private void setCheckoutCount() {
        this.checkoutCount += 1;
        this.setCurrentTime();
    }

    private void setCurrentTime() {
        this.currentTime = (System.currentTimeMillis() - time);
        this.totalTime += this.currentTime;
        setMinTime(this.currentTime);
        setMaxTime(this.currentTime);
        setTime();
    }

    private void setTime() {
        this.time = System.currentTimeMillis();
    }

    private void setMinTime(double inTime) {
        if (inTime < this.minTime) {
            this.minTime = inTime;
        }
    }

    private void setMaxTime(double inTime) {
        if (inTime > this.maxTime) {
            this.maxTime = inTime;
        }
    }

    private boolean reachedTarget() {
        if (this.checkoutCount == this.targetNumber) {
            return true;
        }
        else {
            return false;
        }
    }

    private void resetValues() {
        this.totalTime = 0;
        this.maxTime = Double.NEGATIVE_INFINITY;
        this.minTime = Double.POSITIVE_INFINITY;
        this.checkoutCount = 0;
        setTime();
    }

    private void printStatLog() {
        double averageTime = this.totalTime / this.targetNumber;
        this.log(String.format("Books Purchased: %s Time (in seconds): Total: %s Max: %s Min: %s Avg: %s", this.checkoutCount, this.totalTime / 1000, this.maxTime / 1000, this.minTime / 1000, averageTime / 1000));
    }

    public void incrementBuyCounter() {
        this.setCheckoutCount();

        if (reachedTarget()) {
            printStatLog();
            resetValues();
        }
    }
}
