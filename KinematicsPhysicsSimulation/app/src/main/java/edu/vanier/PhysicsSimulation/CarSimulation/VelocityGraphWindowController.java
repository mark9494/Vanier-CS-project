/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import static edu.vanier.PhysicsSimulation.CarSimulation.Settings.df;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Mark
 */
public class VelocityGraphWindowController extends CarSimulationWindowController {

    @FXML
    private LineChart<?, ?> blueLineChart;

    @FXML
    private CategoryAxis blueX;

    @FXML
    private NumberAxis blueY;

    @FXML
    private LineChart<?, ?> redLineChart;

    @FXML
    private CategoryAxis redX;

    @FXML
    private NumberAxis redY;

    private Car blue;
    private Car red;
    
    /**
     * transfers the values of the 2d array generated in the carSimulationWindow
     * class into this one in order for initialize() to draw the graph
     *
     * @param blueCarPoints : array that contains velocity and time points of
     * blue car
     * @param redCarPoints : array that contains velocity and time points of red
     * car
     */
    public VelocityGraphWindowController(double[][] blueCarPoints, double[][] redCarPoints) {
        this.redCarVelocityGraph = redCarPoints;
        this.blueCarVelocityGraph = blueCarPoints;
    }
    
    /**
     * creates a velocity graph and sets up its points by reading the values of the 2d
     * array
     */
    @FXML
    @Override
    public void initialize() {
        XYChart.Series series = new XYChart.Series();

        series.getData().add(new XYChart.Data(df.format(blueCarVelocityGraph[0][1]) + "", Double.parseDouble((df.format(blueCarVelocityGraph[0][0])))));
        series.getData().add(new XYChart.Data(df.format(blueCarVelocityGraph[1][1]) + "", Double.parseDouble((df.format(blueCarVelocityGraph[1][0])))));
        series.getData().add(new XYChart.Data(df.format(blueCarVelocityGraph[2][1]) + "", Double.parseDouble((df.format(blueCarVelocityGraph[2][0])))));
        series.getData().add(new XYChart.Data(df.format(blueCarVelocityGraph[3][1]) + "", Double.parseDouble((df.format(blueCarVelocityGraph[3][0])))));
        series.getData().add(new XYChart.Data(df.format(blueCarVelocityGraph[4][1]) + "", Double.parseDouble((df.format(blueCarVelocityGraph[4][0])))));
        series.getData().add(new XYChart.Data(df.format(blueCarVelocityGraph[5][1]) + "", Double.parseDouble((df.format(blueCarVelocityGraph[5][0])))));
        series.getData().add(new XYChart.Data(df.format(blueCarVelocityGraph[6][1]) + "", Double.parseDouble((df.format(blueCarVelocityGraph[6][0])))));
        series.getData().add(new XYChart.Data(df.format(blueCarVelocityGraph[7][1]) + "", Double.parseDouble((df.format(blueCarVelocityGraph[7][0])))));
        series.getData().add(new XYChart.Data(df.format(blueCarVelocityGraph[8][1]) + "", Double.parseDouble((df.format(blueCarVelocityGraph[8][0])))));
        series.getData().add(new XYChart.Data(df.format(blueCarVelocityGraph[9][1]) + "", Double.parseDouble((df.format(blueCarVelocityGraph[9][0])))));

        XYChart.Series series2 = new XYChart.Series();

        series2.getData().add(new XYChart.Data(df.format(redCarVelocityGraph[0][1]) + "", Double.parseDouble((df.format(redCarVelocityGraph[0][0])))));
        series2.getData().add(new XYChart.Data(df.format(redCarVelocityGraph[1][1]) + "", Double.parseDouble((df.format(redCarVelocityGraph[1][0])))));
        series2.getData().add(new XYChart.Data(df.format(redCarVelocityGraph[2][1]) + "", Double.parseDouble((df.format(redCarVelocityGraph[2][0])))));
        series2.getData().add(new XYChart.Data(df.format(redCarVelocityGraph[3][1]) + "", Double.parseDouble((df.format(redCarVelocityGraph[3][0])))));
        series2.getData().add(new XYChart.Data(df.format(redCarVelocityGraph[4][1]) + "", Double.parseDouble((df.format(redCarVelocityGraph[4][0])))));
        series2.getData().add(new XYChart.Data(df.format(redCarVelocityGraph[5][1]) + "", Double.parseDouble((df.format(redCarVelocityGraph[5][0])))));
        series2.getData().add(new XYChart.Data(df.format(redCarVelocityGraph[6][1]) + "", Double.parseDouble((df.format(redCarVelocityGraph[6][0])))));
        series2.getData().add(new XYChart.Data(df.format(redCarVelocityGraph[7][1]) + "", Double.parseDouble((df.format(redCarVelocityGraph[7][0])))));
        series2.getData().add(new XYChart.Data(df.format(redCarVelocityGraph[8][1]) + "", Double.parseDouble((df.format(redCarVelocityGraph[8][0])))));
        series2.getData().add(new XYChart.Data(df.format(redCarVelocityGraph[9][1]) + "", Double.parseDouble((df.format(redCarVelocityGraph[9][0])))));

        blueLineChart.getData().addAll(series);
        redLineChart.getData().addAll(series2);
    }

}
