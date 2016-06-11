import javafx.animation.AnimationTimer;

/**
 * Created by Kristjan on 24/04/2016.
 */
public class DelayedAnimationTimer extends AnimationTimer {
    /**
     * Delay in nanoseconds.
     */
    private long delay;
    /**
     * Previous value for "animationtimer".
     */
    private long previous = 0;
    /**
     * Time listener.
     */
    private TimerListener listener;
    /**
     * Interface.
     */
    public interface TimerListener {
        /**
         * On tick.
         */
        void onTick();
    }
     /**
     * Nanoseconds in a millisecond.
     */
    private static final int MILLISECONDS_TO_NANOSECONDS = 1000000;
    /**
     * Setter for the delay.
     * @param delay Delay in milliseconds.
     */
    public void setDelayInMs(long delay) {
        this.delay = delay * MILLISECONDS_TO_NANOSECONDS;
    }
    /**
     * Decreases the delay if need be.
     * @param multiplier multiplier that determines how much faster the timer will work.
     */
    public void decreaseDelay(int multiplier) {
       this.delay /= multiplier;
    }
    /**
     * Sets the listener.
     * @param listener the listener.
     */
    public void setListener(TimerListener listener) {
        this.listener = listener;
    }

    @Override
    public void handle(long now) {
        if (now - delay > previous) {
            previous = now;
            if (listener != null) {
                listener.onTick();
            }
        }
    }
}
