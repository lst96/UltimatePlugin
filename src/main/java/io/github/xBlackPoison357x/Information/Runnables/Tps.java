package io.github.xBlackPoison357x.Information.Runnables;

import io.github.xBlackPoison357x.UltimatePlugin.UltimatePlugin;

public class Tps {
    private final UltimatePlugin plugin;
    private long lastTick = 0;
    private long[] tickTimes = new long[1200];

    public Tps(UltimatePlugin plugin) {
        this.plugin = plugin;
        this.lastTick = System.currentTimeMillis();
        this.plugin.getServer().getScheduler().runTaskTimer(plugin, this::tick, 1L, 1L);
    }

    public double get1minTPS() {
        return getTPS(0, 60);
    }

    public double get5minTPS() {
        return getTPS(0, 300);
    }

    public double get15minTPS() {
        return getTPS(0, 900);
    }





    private synchronized double getTPS(int startTick, int endTick) {
        if (startTick < 0 || startTick >= tickTimes.length || endTick < 0 || endTick >= tickTimes.length || startTick >= endTick) {
            throw new IllegalArgumentException("Invalid start/end ticks");
        }
        long elapsedTicks = endTick - startTick;
        long elapsedMillis = elapsedTicks * 50L;
        long sum = 0;
        for (int i = startTick; i < endTick; i++) {
            sum += tickTimes[i];
        }
        if (sum == 0) {
            return 20.0;
        }
        double average = ((double) sum) / ((double) elapsedMillis);
        return Math.min(20.0, average);
    }

    private synchronized void tick() {
        long currentTick = System.currentTimeMillis() / 50;
        int tickIndex = (int) (currentTick % tickTimes.length);
        tickTimes[tickIndex] = System.currentTimeMillis() - lastTick;
        lastTick = System.currentTimeMillis();
    }
}