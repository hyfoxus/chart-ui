package com.nemirko.chart.ui;

import com.nemirko.chart.entity.Record;
import com.nemirko.chart.utils.GeneratorUtils;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.nemirko.chart.utils.GeneratorUtils.*;

@Component
public class ChartController {

    @FXML
    public LineChart<Double, Double> chart;
    @FXML
    public LineChart<Double, Long> iterationChart;
    @FXML
    public TableView<Record> tableView;

    private final ObservableList<XYChart.Data<Double, Double>> seriesData1 = FXCollections.observableArrayList();
    private final ObservableList<XYChart.Data<Double, Double>> seriesData2 = FXCollections.observableArrayList();
    private final ObservableList<XYChart.Data<Double, Double>> seriesData3 = FXCollections.observableArrayList();
    private final ObservableList<XYChart.Data<Double, Long>> iterationData = FXCollections.observableArrayList();
    private final ObservableList<Record> tableData = FXCollections.observableArrayList();

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

        List<Record> records = GeneratorUtils.generate(-4.6, 2.8, -4.7);
        for (int i = 0; i < records.size(); i++) {
            seriesData1.add(new XYChart.Data<>((double)i, records.get(i).getX1()));
            seriesData2.add(new XYChart.Data<>((double)i, records.get(i).getX2()));
            seriesData3.add(new XYChart.Data<>((double)i, records.get(i).getX3()));
        }
        long count1 = records.stream()
                .skip(1)
                .filter(i -> i.getCondition1() >= EPSILON1 && i.getCondition2() >= EPSILON1)
                .count();
        iterationData.add(new XYChart.Data<>(Math.log10(EPSILON1), count1));
        long count2 = records.stream()
                .skip(1)
                .filter(i -> i.getCondition1() >= EPSILON2 && i.getCondition2() >= EPSILON2)
                .count();
        iterationData.add(new XYChart.Data<>(Math.log10(EPSILON2), count2));
        long count3 = records.stream()
                .skip(1)
                .filter(i -> i.getCondition1() >= EPSILON3 && i.getCondition2() >= EPSILON3)
                .count();
        iterationData.add(new XYChart.Data<>(Math.log10(EPSILON3), count3));
        long count4 = records.size() - 1;
        iterationData.add(new XYChart.Data<>(Math.log10(EPSILON4), count4));

        ObservableList<XYChart.Series<Double, Long>> data2 = FXCollections.observableArrayList();
        XYChart.Series<Double, Long> seriesLog = new XYChart.Series<>(iterationData);
        seriesLog.setName("Iterations");
        data2.add(seriesLog);
        iterationChart.setData(data2);

        TableColumn<Record, Void> indexCol = new TableColumn<>("idx");
        indexCol.setCellFactory(col -> {
            TableCell<Record, Void> cell = new TableCell<>();
            cell.textProperty().bind(Bindings.createStringBinding(() -> {
                if (cell.isEmpty()) {
                    return null ;
                } else {
                    return Integer.toString(cell.getIndex());
                }
            }, cell.emptyProperty(), cell.indexProperty()));
            return cell ;
        });

        TableColumn<Record, String> column1 = new TableColumn<>("x1");
        column1.setCellValueFactory(new PropertyValueFactory<>("x1"));
        TableColumn<Record, String> column2 = new TableColumn<>("x2");
        column2.setCellValueFactory(new PropertyValueFactory<>("x2"));
        TableColumn<Record, String> column3 = new TableColumn<>("x3");
        column3.setCellValueFactory(new PropertyValueFactory<>("x3"));
        TableColumn<Record, String> columnCond1 = new TableColumn<>("condition1");
        columnCond1.setCellValueFactory(new PropertyValueFactory<>("condition1"));
        TableColumn<Record, String> columnCond2 = new TableColumn<>("condition2");
        columnCond2.setCellValueFactory(new PropertyValueFactory<>("condition2"));

        tableView.getColumns().add(indexCol);
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(columnCond1);
        tableView.getColumns().add(columnCond2);

        tableData.addAll(records);
        tableView.setItems(tableData);
    }



}
