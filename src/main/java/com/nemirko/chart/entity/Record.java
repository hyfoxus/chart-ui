package com.nemirko.chart.entity;

import com.nemirko.chart.utils.FunctionUtils;

public class Record {
    private final double x1;
    private final double x2;
    private final double x3;
    private final double condition1;
    private final double condition2;

    public Record(double x1, double x2, double x3, Record previous) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        double f1 = FunctionUtils.f1(x1, x2, x3);
        double f2 = FunctionUtils.f2(x1, x2, x3);
        double f3 = FunctionUtils.f3(x1, x2, x3);
        double diff1 = x1 - previous.x1;
        double diff2 = x2 - previous.x2;
        double diff3 = x3 - previous.x3;
        condition1 = Math.abs(Math.sqrt(diff1 * diff1 + diff2 * diff2 + diff3 * diff3));
        condition2 = Math.abs(Math.sqrt(f1 * f1 + f2 * f2 + f3 * f3));
    }

    public Record(Record previous, double epsilon) {
        x1 = previous.getX1() +
                FunctionUtils.lambda1(previous.getX1(), previous.getX2(), previous.getX3(), epsilon) *
                FunctionUtils.f1(previous.getX1(), previous.getX2(), previous.getX3());
        x2 = previous.getX2() +
                FunctionUtils.lambda2(previous.getX1(), previous.getX2(), previous.getX3(), epsilon) *
                        FunctionUtils.f2(previous.getX1(), previous.getX2(), previous.getX3());
        x3 = previous.getX3() +
                FunctionUtils.lambda3(previous.getX1(), previous.getX2(), previous.getX3(), epsilon) *
                        FunctionUtils.f3(previous.getX1(), previous.getX2(), previous.getX3());

        double f1 = FunctionUtils.f1(x1, x2, x3);
        double f2 = FunctionUtils.f2(x1, x2, x3);
        double f3 = FunctionUtils.f3(x1, x2, x3);
        double diff1 = x1 - previous.x1;
        double diff2 = x2 - previous.x2;
        double diff3 = x3 - previous.x3;
        condition1 = Math.abs(Math.sqrt(diff1 * diff1 + diff2 * diff2 + diff3 * diff3));
        condition2 = Math.abs(Math.sqrt(f1 * f1 + f2 * f2 + f3 * f3));
    }

    public Record(double x1, double x2, double x3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        condition1 = 0;
        double f1 = FunctionUtils.f1(x1, x2, x3);
        double f2 = FunctionUtils.f2(x1, x2, x3);
        double f3 = FunctionUtils.f3(x1, x2, x3);
        condition2 = Math.abs(Math.sqrt(f1 * f1 + f2 * f2 + f3 * f3));
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getX3() {
        return x3;
    }

    public double getCondition1() {
        return condition1;
    }

    public double getCondition2() {
        return condition2;
    }


}
