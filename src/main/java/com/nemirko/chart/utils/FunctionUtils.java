package com.nemirko.chart.utils;

public final class FunctionUtils {
    public static double f1(double x1, double x2, double x3) {
        return -6.47 - 2.38 * x1 + x2 * Math.sin(0.17 * x3);
    }

    public static double f2(double x1, double x2, double x3) {
        return -3.68 + 1.6 * x2 + x3 * Math.sin(0.32 * x1);
    }

    public static double f3(double x1, double x2, double x3) {
        return 1.6 + 0.48 * x3 + x1 * Math.sin(0.12 * x2);
    }

    public static double lambda1(double x1, double x2, double x3, double epsilon) {
        return -Math.signum((f1(x1 + epsilon, x2, x3) - f1(x1 - epsilon, x2, x3)) / (2 * epsilon)) / (1 + Math.abs((f1(x1 + epsilon, x2, x3) - f1(x1 - epsilon, x2, x3)) / (2 * epsilon)));
    }

    public static double lambda2(double x1, double x2, double x3, double epsilon) {
        return -Math.signum((f2(x1, x2 + epsilon, x3) - f2(x1, x2 - epsilon, x3)) / (2 * epsilon)) / (1 + Math.abs((f2(x1, x2 + epsilon, x3) - f2(x1, x2 - epsilon, x3)) / (2 * epsilon)));
    }

    public static double lambda3(double x1, double x2, double x3, double epsilon) {
        return -Math.signum((f3(x1, x2, x3 + epsilon) - f3(x1, x2, x3 - epsilon)) / (2 * epsilon)) / (1 + Math.abs((f3(x1, x2, x3 + epsilon) - f3(x1, x2, x3 - epsilon)) / (2 * epsilon)));
    }
}
