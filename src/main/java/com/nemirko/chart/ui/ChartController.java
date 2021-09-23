package com.nemirko.chart.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class ChartController {
    @FXML
    public LineChart<Integer, Integer> chart;

    private final ObservableList<XYChart.Data<Integer, Integer>> seriesData = FXCollections.observableArrayList();

    private final String expression;
    private final Integer left;
    private final Integer right;

    public ChartController(@Value("${chart.formulae}") String expression,
                           @Value("${chart.margin.left:-20}") Integer left,
                           @Value("${chart.margin.right:20}") Integer right) {
        this.expression = expression;
        this.left = left;
        this.right = right;
        if (left > right) {
            throw new IllegalArgumentException("chart.margin.left greater then chart.margin.right");
        }
    }

    @FXML
    public void initialize() {
        ObservableList<XYChart.Series<Integer, Integer>> data = FXCollections.observableArrayList();
        data.add(new XYChart.Series<>(seriesData));
        chart.setData(data);

        IntStream.range(left, right)
                .forEach(i -> seriesData.add(new XYChart.Data<>(i, func(i))));
    }

    private Integer func(Integer x) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        ctx.setVariable("x", x);
        return parser.parseExpression(expression).getValue(ctx, Integer.class);
    }
}
