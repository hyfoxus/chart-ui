package com.nemirko.chart.utils;

import com.nemirko.chart.entity.Record;

import java.util.ArrayList;
import java.util.List;

public class GeneratorUtils {
    public static final double EPSILON1 = 0.1;
    public static final double EPSILON2 = 0.02;
    public static final double EPSILON3 = 0.005;
    public static final double EPSILON4 = 0.001;

    public static List<Record> generate(double x1, double x2, double x3) {
        List<Record> list= new ArrayList<>();
        Record prev = new Record(x1, x2, x3);
        list.add(prev);
        if (prev.getCondition2() < EPSILON4) return list;
        do {
            prev = new Record(prev, EPSILON4);
            list.add(prev);
        } while (prev.getCondition1() >= EPSILON4 && prev.getCondition2() >= EPSILON4);
        return list;
    }

}
