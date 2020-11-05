package com.company;

public final class StatsCounter {
    private int successCount_;
    private  static volatile boolean isIncreasingCount = false;
    public final int getSuccessCount() {
        return successCount_;
    }
//    public final synchronized void increaseSuccessCount(int delta) {
//
//        System.out.println("Waiting - Inserting a thread" + delta);
//        successCount_ += delta;
//        System.out.println("Done Insertion - Notifying");
//
//    }

    public final synchronized void increaseSuccessCount(int delta) throws InterruptedException {

        while (isIncreasingCount) {
            System.out.println("Waiting -to increase SuccessCount by " + delta);
            wait();
        }
        successCount_ += delta;

        isIncreasingCount = true;
        successCount_ += delta;
        isIncreasingCount = false;
        System.out.println("Done increasing SuccessCount by " + delta + " - Notifying");
        notifyAll();
    }
}
