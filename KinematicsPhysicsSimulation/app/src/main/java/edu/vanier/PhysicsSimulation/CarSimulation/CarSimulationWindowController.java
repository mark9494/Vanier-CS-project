/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author antho
 */
public class CarSimulationWindowController extends Settings {

    public double oldPaneHeight;
    public double initialPaneHeight;

    @FXML
    public void initialize() {

        redCarPositionGraph = new double[10][2];
        blueCarPositionGraph = new double[10][2];

        redCarVelocityGraph = new double[10][2];
        blueCarVelocityGraph = new double[10][2];

        createAnimationToUpdateData();
        timelineToUpdateSliders.play();
        disableBtns(true, true, true, false);

        blueCar = new Car(5, 82, "blue");
        redCar = new Car(5, 122);

        middlePane.getChildren().addAll(blueCar, redCar);

        createAnimation();

        dottedLines = new ArrayList<>();
        allLines = new ArrayList<>();
        cars = new ArrayList<>();

        cars.add(blueCar);
        cars.add(redCar);

        dottedLines.add(line1);
        dottedLines.add(line2);
        dottedLines.add(line3);
        dottedLines.add(line4);
        dottedLines.add(line5);
        dottedLines.add(line6);
        dottedLines.add(line7);

        allLines.addAll(dottedLines);
        allLines.add(top);
        allLines.add(bottom);
        
        middlePane.widthProperty().addListener((obs, oldVal, newVal) -> {
            resizeLineHorizontal();

        });
        middlePane.heightProperty().addListener((obs, oldVal, newVal) -> {
            resizeLineVertical(oldPaneHeight);
            oldPaneHeight = middlePane.getHeight();
        });
    }

    public void resizeLineHorizontal() {

        top.setEndX(middlePane.getWidth());
        bottom.setEndX(middlePane.getWidth());
       
        
       bottomPane.setTranslateX(bottomPane.getWidth()/10); // centers bottom pane sliders and buttons
        
        double lengthOfLine = middlePane.getWidth() / 20;
        double gapBetweenLines = middlePane.getWidth() / 40;

        for (int i = 0; i < dottedLines.size(); i++) {
            if (i != 0) {
                dottedLines.get(i).setStartX(dottedLines.get(i - 1).getEndX() + gapBetweenLines);
                dottedLines.get(i).setEndX(dottedLines.get(i).getStartX() + lengthOfLine);
            } else {
                dottedLines.get(i).setStartX(gapBetweenLines);
                dottedLines.get(i).setEndX(gapBetweenLines + lengthOfLine);
            }
        }
    }

    public void resizeLineVertical(double oldHeight) {
//        if (middlePane.getHeight() > initialPaneHeight) {
//
//            if (oldHeight > middlePane.getHeight()) {

//                for (int i = 0; i < cars.size(); i++) {
//
//                    cars.get(i).setLayoutY(cars.get(i).getLayoutY() - middlePane.getHeight() / 100);
//                }
//                for (int i = 0; i < allLines.size(); i++) {
//
//                    allLines.get(i).setLayoutY(allLines.get(i).getLayoutY() - middlePane.getHeight() / 100);
//                }
//            } else {
//                //TODO need to find a way to know if I should add or substract the panes height depending on the pane getting bigger or smaller
//                for (int i = 0; i < cars.size(); i++) {
//
//                    cars.get(i).setLayoutY(cars.get(i).getLayoutY() + middlePane.getHeight() / 100);
//                }
//                for (int i = 0; i < allLines.size(); i++) {
//
//                    allLines.get(i).setLayoutY(allLines.get(i).getLayoutY() + middlePane.getHeight() / 100);
             //   }
           // }
       // }
    }

    private void createAnimation() {

        timeline = new Timeline(
                new KeyFrame(Duration.millis(animationDuration), e -> handleUpdateAnimation()));
        timeline.setRate(currentRate);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void createAnimationToUpdateData() {

        timelineToUpdateSliders = new Timeline(
                new KeyFrame(Duration.millis(animationDuration2), e -> handleUpdateData()));
        timelineToUpdateSliders.setRate(currentRate2);
        timelineToUpdateSliders.setCycleCount(Timeline.INDEFINITE);
    }

    private void handleUpdateData() {
        updateSliderMax();
    }

    private void handleUpdateAnimation() {
        blueCar.calculateCurrentVelocity(blueCar.calculateCurrentDisplacement());
        blueCar.calculateCurrentTime(blueCar.calculateCurrentDisplacement());

        //System.out.println(blueCar.getCurrentVelocity());
        redCar.calculateCurrentVelocity(redCar.calculateCurrentDisplacement());
        redCar.calculateCurrentTime(redCar.calculateCurrentDisplacement());
        displayLiveStats();
        moveCar();

    }

    public void updateSliderMax() {
        redInitialPositionSlider.setMax((middlePane.getWidth() / 10));
        redFinalPositionSlider.setMin(redInitialPositionSlider.getValue());// this causes the final position ticks to start after the initial postion of the car
        redFinalPositionSlider.setMax(middlePane.getWidth() / 10 - redCar.getWidth() / 10);

        blueInitialPositionSlider.setMax(middlePane.getWidth() / 10);
        blueFinalPositionSlider.setMin(blueInitialPositionSlider.getValue());
        blueFinalPositionSlider.setMax(middlePane.getWidth() / 10 - blueCar.getWidth() / 10);
    }

    public void updateInput() {
        if (blueInitialPositionSlider.getValue() > blueInitialPositionSlider.getMax() - blueCar.getWidth()) {
            blueCar.setInitialPosition((blueInitialPositionSlider.getValue() * 10)); // this prevents the car from going outside the pane
        } else {
            blueCar.setInitialPosition((blueInitialPositionSlider.getValue() * 10));
        }

        blueCar.setFinalPosition(blueFinalPositionSlider.getValue() * 10);
        blueCar.setInitialVelocity(blueInitialVelocitySlider.getValue());
        blueCar.setAcceleration(blueAccelerationSlider.getValue());

        if (redInitialPositionSlider.getValue() > redInitialPositionSlider.getMax() - redCar.getWidth()) {
            redCar.setInitialPosition(redInitialPositionSlider.getValue() * 10);
        } else {
            redCar.setInitialPosition(redInitialPositionSlider.getValue() * 10);
        }

        redCar.setFinalPosition(redFinalPositionSlider.getValue() * 10);
        redCar.setInitialVelocity(redInitialVelocitySlider.getValue());
        redCar.setAcceleration(redAccelerationSlider.getValue());

        redCar.setTranslateX(redCar.getInitialPosition());
        blueCar.setTranslateX(blueCar.getInitialPosition());
    }

    private void moveCar() {

        if (blueCar.getTranslateX() < blueCar.getFinalPosition()) {
            blueCar.setTranslateX(blueCar.getTranslateX() + blueCar.getCurrentVelocity() / 10);

        }
        if (redCar.getTranslateX() < redCar.getFinalPosition()) {// if I make a for loop, i wont have access to getFinalPosition (Ask teacher why)
            redCar.setTranslateX(redCar.getTranslateX() + redCar.getCurrentVelocity() / 10);// divide by 10 because otherwise the accelerartion will be too fast 
        }
//      if(blueCar.getTranslateX() < blueCar.getFinalPosition() && redCar.getTranslateX() < redCar.getFinalPosition()){
//         return true; 
//      }else{
//      return false;    
//      }

    }

    private void makeGraphPoints() {
        double redFinalTime = redCar.calculateFinalTime(redCar.calculateFinalDisplacement(), redCar.calculateFinalVelocity(redCar.calculateFinalDisplacement())) / 10;
        double blueFinalTime = blueCar.calculateFinalTime(blueCar.calculateFinalDisplacement(), blueCar.calculateFinalVelocity(blueCar.calculateFinalDisplacement())) / 10;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 1; j++) {
                redCarPositionGraph[i][j] = (redCar.calculateGraphDisplacement(redCar.calculateCurrentDisplacement(), redFinalTime));
                redCarPositionGraph[i][j + 1] = redFinalTime;

                redCarVelocityGraph[i][j] = (redCar.calculateCurrentVelocity(redCar.calculateGraphDisplacement(redCar.calculateCurrentDisplacement(), redFinalTime)));
                redCarVelocityGraph[i][j + 1] = redFinalTime;
                // redCarVelocityGraph[i][j] = (redCar.calculateCurrentVelocity(redCar.calculateGraphDisplacement(redFinalTime)));
                //System.out.println(redCarPositionGraph[i][j]);
                //System.out.println(redCarPositionGraph[i][j+1]);

                blueCarPositionGraph[i][j] = (blueCar.calculateGraphDisplacement(blueCar.calculateCurrentDisplacement(), blueFinalTime));//the time going in the method is correct
                blueCarPositionGraph[i][j + 1] = blueFinalTime;

                blueCarVelocityGraph[i][j] = (blueCar.calculateCurrentVelocity(blueCar.calculateGraphDisplacement(blueCar.calculateCurrentDisplacement(), blueFinalTime)));
                blueCarVelocityGraph[i][j + 1] = blueFinalTime;

                redFinalTime += redCar.calculateFinalTime(redCar.calculateFinalDisplacement(), redCar.calculateFinalVelocity(redCar.calculateFinalDisplacement())) / 10;
                blueFinalTime += blueCar.calculateFinalTime(blueCar.calculateFinalDisplacement(), blueCar.calculateFinalVelocity(blueCar.calculateFinalDisplacement())) / 10;

            }
        }

    }

    @FXML
    private void handleStart() {
        makeGraphPoints();
        timeline.play();
        disableBtns(true, false, true, true);

    }

    @FXML
    private void handleStop() {

        disableBtns(false, true, false, true);
        timeline.pause();
    }

    @FXML
    private void handleReset() {

        disableBtns(true, true, true, false);
        timeline.stop();
    }

    @FXML
    private void handleSubmit() {

        updateInput();
        disableBtns(false, true, true, true);
        resetLiveStats();
    }

    private void resetLiveStats() {
        //blue car
        bluePositionLabel.setText("0");
        blueVelocityLabel.setText("0");
        blueAccelerationLabel.setText("0");
        blueTimeLabel.setText("0");
        //red car
        redPositionLabel.setText("0");
        redVelocityLabel.setText("0");
        redAccelerationLabel.setText("0");
        redTimeLabel.setText("0");
    }

    private void disableBtns(boolean start, boolean stop, boolean reset, boolean submit) {

        startBtn.setDisable(start);
        stopBtn.setDisable(stop);
        resetBtn.setDisable(reset);
        submitBtn.setDisable(submit);
    }

    private void displayLiveStats() {
        //blue car
        bluePositionLabel.setText(df.format(blueCar.getTranslateX() / 10) + "");
        blueVelocityLabel.setText(df.format(blueCar.getCurrentVelocity()) + "");
        blueAccelerationLabel.setText(df.format(blueCar.getAcceleration()) + "");
        blueTimeLabel.setText(df.format(blueCar.getTime()) + "");
        //red car
        redPositionLabel.setText(df.format(redCar.getTranslateX() / 10) + "");
        redVelocityLabel.setText(df.format(redCar.getCurrentVelocity()) + "");
        redAccelerationLabel.setText(df.format(redCar.getAcceleration()) + "");
        redTimeLabel.setText(df.format(redCar.getTime()) + "");
    }

    @FXML
    private void handlePositionGraphBtn() throws IOException {

        Stage secondWindow = new Stage();

        PositionGraphWindowController positionGraphWindow = new PositionGraphWindowController(blueCarPositionGraph, redCarPositionGraph);

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/PositionGraphCarSimulation.fxml"));
        loader.setController(positionGraphWindow);
        Pane root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        secondWindow.setScene(scene);

        secondWindow.setTitle("Position Graph");
        secondWindow.sizeToScene();
        secondWindow.show();
    }

    @FXML
    private void handleVelocityGraphBtn() throws IOException {
        Stage secondWindow = new Stage();

        VelocityGraphWindowController velocityGraphWindow = new VelocityGraphWindowController(blueCarVelocityGraph, redCarVelocityGraph);

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/VelocityGraphCarSimulation.fxml"));
        loader.setController(velocityGraphWindow);
        Pane root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        secondWindow.setScene(scene);

        secondWindow.setTitle("Velocity Graph");
        secondWindow.sizeToScene();
        secondWindow.show();

    }

}
