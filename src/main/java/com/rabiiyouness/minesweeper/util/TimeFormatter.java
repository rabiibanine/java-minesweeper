package com.rabiiyouness.minesweeper.util;

public final class TimeFormatter {
    private TimeFormatter() {
    }

    public static String format(int seconds) {
        int safe = Math.max(0, seconds);
        return String.format("%03d", Math.min(999, safe));
    }

    public static String formatReadable(double seconds) {
        if (seconds <= 0) {
            return "0s";
        }
        int whole = (int) Math.round(seconds);
        int minutes = whole / 60;
        int secs = whole % 60;
        return minutes > 0 ? minutes + "m " + secs + "s" : secs + "s";
    }
}
