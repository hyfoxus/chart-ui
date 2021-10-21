package com.nemirko.chart.utils;

import com.nemirko.chart.entity.Record;

import java.util.ArrayList;
import java.util.List;

public class GeneratorUtils {
    private static final double EPSILON = 0.001;

    public static List<Record> generate(double x1, double x2, double x3, double epsilon) {
        List<Record> list= new ArrayList<>();
        Record prev = new Record(x1, x2, x3);
        list.add(prev);
        if (prev.getCondition2() < EPSILON) return list;
        do {
            prev = new Record(prev, EPSILON);
            list.add(prev);
        } while (prev.getCondition1() >= EPSILON && prev.getCondition2() >= EPSILON);
        return list;
    }

}
