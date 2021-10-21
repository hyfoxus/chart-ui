package com.nemirko.chart.utils;

import com.nemirko.chart.entity.Record;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorUtilsTest {

    @Test
    void testGeneration() {
        List<Record> list = GeneratorUtils.generate(-4.6, 2.8, -4.7, 0.001);
        assertEquals(list.size(), 30);
        double epsilon = 0.00001d;
        assertTrue(Math.abs(list.get(11).getX1() + 2.88984) < epsilon);
    }

}