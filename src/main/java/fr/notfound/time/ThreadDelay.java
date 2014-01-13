package fr.notfound.time;

import static java.lang.Thread.sleep;

public class ThreadDelay implements Delay {
    
    private final int delayInMilliseconds;

    public ThreadDelay(int delayInMilliseconds) {
        this.delayInMilliseconds = delayInMilliseconds;
    }

    @Override public void trigger() {
        try {
            sleep(delayInMilliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
