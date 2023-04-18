package io.github.xBlackPoison357x.Information.Runnables;

public class Tps implements Runnable {
    private static final int TICKS_TO_KEEP = 600;
    private static final int TICKS_IN_MINUTE = 1200;
    private static int tickCount = 0;
    private static long[] tickTimestamps = new long[TICKS_TO_KEEP];

    public static double getTPS() {
        return getTPS(TICKS_IN_MINUTE);
    }

    public static double getTPS(int ticks) {
        if (tickCount < ticks) {
            return 20.0D;
        }
        int target = (tickCount - 1 - ticks) % TICKS_TO_KEEP;
        long elapsed = System.currentTimeMillis() - tickTimestamps[target];

        return ticks / (elapsed / 1000.0D);
    }

    public static double get1MinTPS() {
        return getTPS(TICKS_IN_MINUTE); // 1 minute in ticks
    }

    public static double get5MinTPS() {
        return getTPS(5 * TICKS_IN_MINUTE); // 5 minutes in ticks
    }

    public static double get15MinTPS() {
        return getTPS(15 * TICKS_IN_MINUTE); // 15 minutes in ticks
    }

    public static long getElapsed(int tickID) {
        if (tickCount - tickID >= TICKS_TO_KEEP) {
            return -1L;
        }
        long time = tickTimestamps[tickID % TICKS_TO_KEEP];
        return System.currentTimeMillis() - time;
    }

    @Override
    public void run() {
        tickTimestamps[tickCount % TICKS_TO_KEEP] = System.currentTimeMillis();
        tickCount++;
    }
}
