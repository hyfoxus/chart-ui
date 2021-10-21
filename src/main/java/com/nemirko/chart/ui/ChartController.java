package com.nemirko.chart.ui;

import com.nemirko.chart.entity.Record;
import com.nemirko.chart.utils.GeneratorUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChartController {
    @FXML
    public LineChart<Double, Double> chart;

    private final ObservableList<XYChart.Data<Double, Double>> seriesData1 = FXCollections.observableArrayList();
    private final ObservableList<XYChart.Data<Double, Double>> seriesData2 = FXCollections.observableArrayList();
    private final ObservableList<XYChart.Data<Double, Double>> seriesData3 = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        ObservableList<XYChart.Series<Double, Double>> data = FXCollections.observableArrayList();
        XYChart.Series<Double, Double> series1 = new XYChart.Series<>(seriesData1);
        series1.setName("X1");
        XYChart.Series<Double, Double> series2 = new XYChart.Series<>(seriesData2);
        series2.setName("X2");
        XYChart.Series<Double, Double> series3 = new XYChart.Series<>(seriesData3);
        series3.setName("X3");

        data.add(series1);
        data.add(series2);
        data.add(series3);
        chart.setData(data);

        List<Record> records = GeneratorUtils.generate(-4.6, 2.8, -4.7, 0.001);
        for (int i = 0; i < records.size(); i++) {
            seriesData1.add(new XYChart.Data<>((double)i, records.get(i).getX1()));
            seriesData2.add(new XYChart.Data<>((double)i, records.get(i).getX2()));
            seriesData3.add(new XYChart.Data<>((double)i, records.get(i).getX3()));
        }

    }

}
