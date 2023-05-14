/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import edu.vanier.PhysicsSimulation.PhysicsSimulationController;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.TickLabelOrientation;
import eu.hansolo.medusa.skins.ModernSkin;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class CarSimulationWindowController extends Settings {

    /**
     * sets up all needed variables for the window and simulation also gathers
     * all buttons, sliders and texts in their respective arrayLists
     */
    @FXML
    public void initialize() {
        text = new ArrayList<>();
        pane = new ArrayList<>();
        button = new ArrayList<>();

        button.add(startBtn);
        button.add(stopBtn);
        button.add(resetBtn);
        button.add(submitBtn);
        button.add(positionGraphBtn);
        button.add(velocityGraphBtn);
        button.add(homeBtn);
        
        text.add(txt1);
        text.add(txt2);
        text.add(txt3);
        text.add(txt4);
        text.add(txt5);
        text.add(txt6);
        text.add(txt7);
        text.add(txt8);
        text.add(txt9);
        text.add(txt10);
        text.add(txt11);
        text.add(txt12);
        text.add(txt13);
        text.add(txt14);
        text.add(txt15);
        text.add(txt16);
        text.add(txt17);
        text.add(txt18);
        text.add(bluePositionLabel);
        text.add(blueVelocityLabel);
        text.add(blueAccelerationLabel);
        text.add(blueTimeLabel);
        text.add(redPositionLabel);
        text.add(redVelocityLabel);
        text.add(redAccelerationLabel);
        text.add(redTimeLabel);

        pane.add(topPane);
        pane.add(middlePane);
        pane.add(bottomPane);
        pane.add(borderPane);

        meetLine = new Line();
        setupGauges();
        setupMenuBar();

        redCarPositionGraph = new double[10][2];
        blueCarPositionGraph = new double[10][2];

        redCarVelocityGraph = new double[10][2];
        blueCarVelocityGraph = new double[10][2];

        createAnimationToUpdateData();
        timelineToUpdateSliders.play();
        disableBtns(true, true, true, false);

        blueCar = new Car(5, 82, "blue");
        redCar = new Car(5, 122);
        fileHandler = new CarsIO(redCar, blueCar);
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
            resizeLineVertical();
        });
    }

    /**
     * modifies the menu bar to only include the needed buttons include event
     * handlers for the 4 buttons in the menu bar
     */
    private void setupMenuBar() {
        FileChooser fileChooser = new FileChooser();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        menuBar.getMenus().remove(2);
        MenuItem save = new MenuItem("Save Last Run");
        MenuItem openSave = new MenuItem("Open Save");
        menuBar.getMenus().get(0).getItems().addAll(save, openSave);
        menuBar.getMenus().get(0).getItems().remove(0);

        MenuItem darkMode = new MenuItem("Dark mode");
        MenuItem lightMode = new MenuItem("light mode");
        menuBar.getMenus().get(1).getItems().remove(0);
        menuBar.getMenus().get(1).getItems().addAll(darkMode, lightMode);

        EventHandler<ActionEvent> savePressed = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                newSave = directoryChooser.showDialog(new Stage());
                fileHandler.writeDataInFile(newSave.getPath() + "\\CarSimulation.csv");
            }
        };

        EventHandler<ActionEvent> loadSaved = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                loadSave = fileChooser.showOpenDialog(new Stage());
                try {
                    fileHandler.readDataInFile(loadSave.getPath());
                    loadVisualSettingsBack();
                } catch (IOException ex) {
                    System.out.println("File Not Read Properly. ");
                }
            }
        };
        EventHandler<ActionEvent> handleDark = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                darkMode();
            }
        };

        EventHandler<ActionEvent> handleLight = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                lightMode();
            }
        };

        save.setOnAction(savePressed);
        openSave.setOnAction(loadSaved);
        darkMode.setOnAction(handleDark);
        lightMode.setOnAction(handleLight);
    }

    /**
     * takes the value of variables of the cars objects to adjust the sliders
     * after the CSV file is read
     */
    private void loadVisualSettingsBack() {
        redInitialPositionSlider.setValue(redCar.getInitialPosition());
        redFinalPositionSlider.setValue(redCar.getFinalPosition());
        redAccelerationSlider.setValue(redCar.getAcceleration());
        redInitialVelocitySlider.setValue(redCar.getInitialVelocity());
        blueInitialPositionSlider.setValue(blueCar.getInitialPosition());
        blueFinalPositionSlider.setValue(blueCar.getFinalPosition());
        blueAccelerationSlider.setValue(blueCar.getAcceleration());
        blueInitialVelocitySlider.setValue(blueCar.getInitialVelocity());
    }

    /**
     * uses the width ratio of pane and gauges to move the gauges proportionally
     * with the pane extends the street lines with pane
     */
    private void resizeLineHorizontal() {
        blueGauge.setPrefWidth(middlePane.getWidth() / 3.9);
        redGauge.setPrefWidth(middlePane.getWidth() / 3.9);
        blueGauge.setLayoutX(middlePane.getWidth() * 0.33);

        top.setEndX(middlePane.getWidth());
        bottom.setEndX(middlePane.getWidth());

        bottomPane.setTranslateX(bottomPane.getWidth() / 10); // centers bottom pane sliders and buttons

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

    /**
     * uses the height ratio of pane and gauges to move the gauges
     * proportionally to the pane
     */
    private void resizeLineVertical() {
        blueGauge.setPrefHeight(middlePane.getHeight() / 1.76);
        redGauge.setPrefHeight(middlePane.getHeight() / 1.76);
    }

    /**
     * creates the keyFrame for the timeline of the animation
     */
    private void createAnimation() {
        timeline = new Timeline(
                new KeyFrame(Duration.millis(animationDuration), e -> handleUpdateAnimation()));
        timeline.setRate(currentRate);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * creates keyFrame for the second timeline used to update the value of
     * sliders according to plane size
     */
    private void createAnimationToUpdateData() {
        timelineToUpdateSliders = new Timeline(
                new KeyFrame(Duration.millis(animationDuration2), e -> handleUpdateData()));
        timelineToUpdateSliders.setRate(currentRate2);
        timelineToUpdateSliders.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * the method keeps looping which calls all methods inside
     */
    private void handleUpdateData() {
        updateSliderMax();

    }

    /**
     * method that keeps looping used to keep calculating the car values and to
     * verify if the animation ended and if the cars meet or not
     */
    private void handleUpdateAnimation() {
        endOfSimulation();
        carsMeet();
        blueCar.calculateCurrentVelocity(blueCar.calculateCurrentDisplacement());
        blueCar.calculateCurrentTime(blueCar.calculateCurrentDisplacement());

        redCar.calculateCurrentVelocity(redCar.calculateCurrentDisplacement());
        redCar.calculateCurrentTime(redCar.calculateCurrentDisplacement());
        displayLiveStats();
        moveCar();
        updateGaugesValues();
    }

    /**
     * checks if cars meet during the simulation
     *
     * @return : true if the cars do meet, false if the cars do not meet
     */
    private boolean carsMeet() {
        if (!carsMeet && blueCar.getTranslateX() > 50) {
            if (df2.format(blueCar.getTranslateX()).equalsIgnoreCase(df2.format(redCar.getTranslateX()))
                    || df2.format(blueCar.getTranslateX() + 1).equalsIgnoreCase(df2.format(redCar.getTranslateX()))
                    || df2.format(blueCar.getTranslateX() - 1).equalsIgnoreCase(df2.format(redCar.getTranslateX()))) {

                meetLine.setTranslateX(blueCar.getTranslateX());
                meetLine.setStartY(blueCar.getLayoutY());
                meetLine.setEndY(redCar.getLayoutY() + 25);
                meetLine.setStroke(Color.CHARTREUSE);
                meetLine.setStrokeWidth(2);

                if (meetLine.getTranslateX() >= middlePane.getWidth() - 100) {
                    return false;

                } else if (Double.parseDouble(df2.format(meetLine.getTranslateX())) <= Double.parseDouble(df2.format(blueCar.getInitialPosition() + 10))) {

                    return false;

                }
                
                middlePane.getChildren().add(meetLine);
                carsMeet = true;
                meetingDistance = blueCar.getTranslateX() / 10;
                return true;
            }
        }
        return false;
    }

    /**
     * changes the max of sliders depending on the width of the pane
     */
    private void updateSliderMax() {
        redInitialPositionSlider.setMax((middlePane.getWidth() / 10 - redCar.getWidth() / 10));
        redFinalPositionSlider.setMin(redInitialPositionSlider.getValue());// this causes the final position ticks to start after the initial postion of the car
        redFinalPositionSlider.setMax(middlePane.getWidth() / 10 - redCar.getWidth() / 10);

        blueInitialPositionSlider.setMax(middlePane.getWidth() / 10 - blueCar.getWidth() / 10);
        blueFinalPositionSlider.setMin(blueInitialPositionSlider.getValue());
        blueFinalPositionSlider.setMax(middlePane.getWidth() / 10 - blueCar.getWidth() / 10);
    }

    /**
     * retrieves user input and sets these values to the cars
     */
    private void updateInput() {
        blueCar.setInitialPosition((blueInitialPositionSlider.getValue() * 10));
        blueCar.setFinalPosition(blueFinalPositionSlider.getValue() * 10);
        blueCar.setInitialVelocity(blueInitialVelocitySlider.getValue());
        blueCar.setAcceleration(blueAccelerationSlider.getValue());

        redCar.setInitialPosition(redInitialPositionSlider.getValue() * 10);
        redCar.setFinalPosition(redFinalPositionSlider.getValue() * 10);
        redCar.setInitialVelocity(redInitialVelocitySlider.getValue());
        redCar.setAcceleration(redAccelerationSlider.getValue());

        redCar.setTranslateX(redCar.getInitialPosition());
        blueCar.setTranslateX(blueCar.getInitialPosition());
    }

    /**
     * responsible for making the cars move during the animation
     */
    private void moveCar() {
        if (blueCar.getTranslateX() < blueCar.getFinalPosition()) {
            blueCar.setTranslateX(blueCar.getTranslateX() + blueCar.getCurrentVelocity() / 10);
        }
        if (redCar.getTranslateX() < redCar.getFinalPosition()) {
            redCar.setTranslateX(redCar.getTranslateX() + redCar.getCurrentVelocity() / 10);
            // divide by 10 because otherwise the accelerartion will be too fast 
        }
    }

    /**
     * designs the gauges used for both cars
     */
    private void setupGauges() {
        blueGauge = new Gauge();
        blueGauge.setSkin(new ModernSkin(blueGauge));
        blueGauge.setTitle("Blue Car");
        blueGauge.setUnit("m / s");
        blueGauge.setUnitColor(Color.CYAN);
        blueGauge.setDecimals(0);
        blueGauge.setValue(0);
        blueGauge.setAnimated(true);
        blueGauge.setAnimationDuration(1);
        blueGauge.setValueColor(Color.CYAN);
        blueGauge.setTitleColor(Color.CYAN);
        blueGauge.setSubTitleColor(Color.WHITE);
        blueGauge.setBarColor(Color.rgb(0, 214, 215));
        blueGauge.setNeedleColor(Color.CYAN);
        blueGauge.setThresholdColor(Color.PURPLE);
        blueGauge.setThreshold(40);
        blueGauge.setThresholdVisible(true);
        blueGauge.setTickLabelColor(Color.rgb(151, 151, 151));
        blueGauge.setTickMarkColor(Color.CYAN);
        blueGauge.setTickLabelOrientation(TickLabelOrientation.ORTHOGONAL);
        blueGauge.setBackgroundPaint(Color.BLACK);
        blueGaugePane.getChildren().add(blueGauge);

        redGauge = new Gauge();
        redGauge.setSkin(new ModernSkin(redGauge));
        redGauge.setTitle("Red Car");
        redGauge.setUnit("m / s");
        redGauge.setUnitColor(Color.RED);
        redGauge.setDecimals(0);
        redGauge.setValue(0);
        redGauge.setAnimated(true);
        redGauge.setAnimationDuration(1);
        redGauge.setValueColor(Color.RED);
        redGauge.setTitleColor(Color.RED);
        redGauge.setSubTitleColor(Color.RED);
        redGauge.setBarColor(Color.rgb(0, 214, 215));
        redGauge.setNeedleColor(Color.RED);
        redGauge.setThresholdColor(Color.GOLD);
        redGauge.setThreshold(40);
        redGauge.setThresholdVisible(true);
        redGauge.setTickLabelColor(Color.rgb(151, 151, 151));
        redGauge.setTickMarkColor(Color.RED);
        redGauge.setTickLabelOrientation(TickLabelOrientation.ORTHOGONAL);
        redGauge.setBackgroundPaint(BLACK);
        redGaugePane.getChildren().add(redGauge);
    }

    /**
     * makes the gauge value change according to the current velocity of the
     * cars
     */
    private void updateGaugesValues() {
        blueGauge.setValue(blueCar.getCurrentVelocity());
        redGauge.setValue(redCar.getCurrentVelocity());
    }

    /**
     * creates 10 points that are made using time and position creates 10 points
     * that are made using time and velocity all points are stored in 2d array
     * to be read when drawing the graph
     */
    private void makeGraphPoints() {
        double redFinalTime = redCar.calculateFinalTime(redCar.calculateFinalDisplacement(), redCar.calculateFinalVelocity(redCar.calculateFinalDisplacement())) / 10;
        double blueFinalTime = blueCar.calculateFinalTime(blueCar.calculateFinalDisplacement(), blueCar.calculateFinalVelocity(blueCar.calculateFinalDisplacement())) / 10;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 1; j++) {
                redCarPositionGraph[i][j] = (redCar.calculateGraphDisplacement(redFinalTime));
                redCarPositionGraph[i][j + 1] = redFinalTime;

                redCarVelocityGraph[i][j] = (redCar.calculateCurrentVelocity(redCar.calculateGraphDisplacement(redFinalTime)));
                redCarVelocityGraph[i][j + 1] = redFinalTime;

                blueCarPositionGraph[i][j] = (blueCar.calculateGraphDisplacement(blueFinalTime));
                blueCarPositionGraph[i][j + 1] = blueFinalTime;

                blueCarVelocityGraph[i][j] = (blueCar.calculateCurrentVelocity(blueCar.calculateGraphDisplacement(blueFinalTime)));
                blueCarVelocityGraph[i][j + 1] = blueFinalTime;

                redFinalTime += redCar.calculateFinalTime(redCar.calculateFinalDisplacement(), redCar.calculateFinalVelocity(redCar.calculateFinalDisplacement())) / 10;
                blueFinalTime += blueCar.calculateFinalTime(blueCar.calculateFinalDisplacement(), blueCar.calculateFinalVelocity(blueCar.calculateFinalDisplacement())) / 10;
            }
        }
    }

    /**
     * responsible for disabling and enabling sliders
     *
     * @param redInitialPosition : true disables the slider, false enables the
     * sliders
     * @param redFinalPosition: true disables the slider, false enables the
     * sliders
     * @param blueInitialPosition: true disables the slider, false enables the
     * sliders
     * @param blueFinalPosition: true disables the slider, false enables the
     * sliders
     * @param blueInitialVelocity: true disables the slider, false enables the
     * sliders
     * @param blueAcceleration: true disables the slider, false enables the
     * sliders
     * @param redInitialVelocity: true disables the slider, false enables the
     * sliders
     * @param redAcceleration : true disables the slider, false enables the
     * sliders
     */
    private void disableSliders(boolean redInitialPosition, boolean redFinalPosition, boolean blueInitialPosition, boolean blueFinalPosition, boolean blueInitialVelocity, boolean blueAcceleration, boolean redInitialVelocity, boolean redAcceleration) {
        redInitialPositionSlider.setDisable(redInitialPosition);
        redFinalPositionSlider.setDisable(redFinalPosition);
        blueInitialPositionSlider.setDisable(blueInitialPosition);
        blueFinalPositionSlider.setDisable(blueFinalPosition);
        blueInitialVelocitySlider.setDisable(blueInitialVelocity);
        blueAccelerationSlider.setDisable(blueAcceleration);
        redInitialVelocitySlider.setDisable(redInitialVelocity);
        redAccelerationSlider.setDisable(redAcceleration);
    }

    /**
     * checks if the simulation ended by checking the final position of the cars
     * displays an alert stating if the two cars meet or not
     */
    private void endOfSimulation() {
        if (blueCar.getFinalPosition() <= blueCar.getTranslateX() && redCar.getFinalPosition() <= redCar.getTranslateX()) {
            handleReset();
            if (carsMeet) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Cars meet");
                alert.setContentText("Please not that the two cars meet at " + df.format(meetingDistance) + "m");
                alert.show();
            } else {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Cars do not meet");
                alert.setContentText("Please not that the two cars meet never meet ");
                alert.show();
            }
        }
    }

    /**
     * handles the start button by starting the animation and calls the method
     * to calculate the graph points also disables buttons that should be
     * disabled
     */
    @FXML
    private void handleStart() {
        makeGraphPoints();
        timeline.play();
        disableBtns(true, false, true, true);
    }

    /**
     * handles the stop button, resets the gauges value to 0 pauses the timeLine
     * and disables buttons that are not needed
     */
    @FXML
    private void handleStop() {
        blueGauge.setValue(0);
        redGauge.setValue(0);
        disableBtns(false, true, false, true);
        timeline.pause();
    }

    /**
     * handles the reset button by stopping the timeline(animation) and disables
     * the needed sliders and buttons
     */
    @FXML
    private void handleReset() {
        disableSliders(false, false, false, false, false, false, false, false);
        disableBtns(true, true, true, false);
        timeline.stop();
    }

    /**
     * handles the submit button by calling the method that reads user input and
     * gives the values to the cars disables needed sliders and buttons resets
     * the live stats labels to 0 for the new simulation
     */
    @FXML
    private void handleSubmit() {
        redGauge.setValue(0);
        blueGauge.setValue(0);
        disableSliders(true, true, true, true, true, true, true, true);
        updateInput();
        disableBtns(false, true, true, true);
        resetLiveStats();
        carsMeet = false;
        middlePane.getChildren().remove(meetLine);
    }

    /**
     * resets all output text labels to 0 for the next simulation
     */
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
    
    /**
     * disables buttons that are not needed
     * @param start : true to disable the button, false to enable the button
     * @param stop : true to disable the button, false to enable the button
     * @param reset : true to disable the button, false to enable the button
     * @param submit : true to disable the button, false to enable the button
     */
    private void disableBtns(boolean start, boolean stop, boolean reset, boolean submit) {
        startBtn.setDisable(start);
        stopBtn.setDisable(stop);
        resetBtn.setDisable(reset);
        submitBtn.setDisable(submit);
    }
    
    /**
     * retrieves values from both cars and displays it to the user using text field
     */
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
    /**
     * responsible for changing the colors of many window elements to dark mode
     */
    private void darkMode() {
        for (int i = 0; i < text.size(); i++) {
            text.get(i).setFill(Color.GREENYELLOW);
        }

        for (int i = 0; i < pane.size(); i++) {
            pane.get(i).setStyle("-fx-background-color: #202020");
            middlePane.setStyle("-fx-border-color: blue");

        }

        for (int i = 0; i < button.size(); i++) {
            button.get(i).setStyle("-fx-background-color: blue");
            button.get(i).setTextFill(Color.GREENYELLOW);
        }

        for (int i = 0; i < dottedLines.size(); i++) {
            dottedLines.get(i).setStroke(Color.PURPLE);

        }
        top.setStroke(Color.GREENYELLOW);
        bottom.setStroke(Color.GREENYELLOW);
        windowTitle.setTextFill(Color.GREENYELLOW);
        menuBar.setStyle("-fx-background-color : greenyellow");
    }
    
    /**
     * responsible for changing the colors of many window elements to light mode
     */
    private void lightMode() {
        for (int i = 0; i < text.size(); i++) {
            text.get(i).setFill(Color.BLACK);
        }

        for (int i = 0; i < pane.size(); i++) {
            pane.get(i).setStyle("-fx-background-color: white");

            middlePane.setStyle("-fx-border-color: black");

        }

        for (int i = 0; i < button.size(); i++) {
            button.get(i).setStyle("-fx-background-color: #cccccc");
            button.get(i).setTextFill(Color.BLACK);
        }

        for (int i = 0; i < dottedLines.size(); i++) {
            dottedLines.get(i).setStroke(Color.BLACK);

        }
        top.setStroke(Color.BLACK);
        bottom.setStroke(Color.BLACK);
        windowTitle.setTextFill(Color.BLACK);
        menuBar.setStyle("-fx-background-color : #cccccc");

    }
    /**
     * handle the position graph button by creating a new window for the graph
     * @throws IOException 
     */
    @FXML
    private void handlePositionGraphBtn() throws IOException {

        Stage secondWindow = new Stage();
        secondWindow.initOwner(PhysicsSimulationController.carSimulation);
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
    
    /**
     * handle the velocity graph button by creating a new window for the graph
     * @throws IOException 
     */
    @FXML
    private void handleVelocityGraphBtn() throws IOException {
        Stage secondWindow = new Stage();
        secondWindow.initOwner(PhysicsSimulationController.carSimulation);
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
    /**
     * handles home button by closing the simulation window and returning to main menu
     */
    @FXML
    private void handleHomeButton() {
        PhysicsSimulationController.carSimulation.close();
        timeline.stop();
        timelineToUpdateSliders.stop();
    }
}
