package com.niklasarndt.aoc.twentytwenty;

import java.util.concurrent.atomic.AtomicLong;

public class Timer {
    //One static instance which can be called using Timer.instance().
    private static final Timer instance = new Timer();
    private final AtomicLong start = new AtomicLong();
    private final AtomicLong halt = new AtomicLong();

    public Timer() {
        start();
    } //Starts counting when object is created

    public static Timer instance() {
        return instance;
    }

    public void start() { //Reset the timer
        start.set(System.currentTimeMillis());
    }

    public long stop() { //Return the duration and reset the value
        long halt = this.halt.getAndSet(0);

        return System.currentTimeMillis() - start.getAndSet(System.currentTimeMillis()) + halt;
    }

    public long stopNoReset() { //Return the duration without resetting the value.
        return System.currentTimeMillis() - start.get();
    }

    public void halt() {
        halt.set(System.currentTimeMillis() - start.get());
    }
}
