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
/**
 *
 * @author antho
 */
public class CarSimulationWindowController extends Settings {

    
    
    
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
    private void setupMenuBar(){
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
    private void resizeLineHorizontal() {
        blueGauge.setPrefWidth(middlePane.getWidth()/3.9);
        redGauge.setPrefWidth(middlePane.getWidth()/3.9);  
        blueGauge.setLayoutX(middlePane.getWidth()*0.33);
        
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

    private void resizeLineVertical() {
        
       
        blueGauge.setPrefHeight(middlePane.getHeight()/1.76);
        redGauge.setPrefHeight(middlePane.getHeight()/1.76);
       
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
    private boolean carsMeet(){
        if(!carsMeet && blueCar.getTranslateX()>50){
      if(df2.format(blueCar.getTranslateX()).equalsIgnoreCase(df2.format(redCar.getTranslateX()))
         || df2.format(blueCar.getTranslateX()+1).equalsIgnoreCase(df2.format(redCar.getTranslateX())) 
         || df2.format(blueCar.getTranslateX()-1).equalsIgnoreCase(df2.format(redCar.getTranslateX()))){
          
          meetLine.setTranslateX(blueCar.getTranslateX());
          meetLine.setStartY(blueCar.getLayoutY());
          meetLine.setEndY(redCar.getLayoutY()+25);
          meetLine.setStroke(Color.CHARTREUSE);
          meetLine.setStrokeWidth(2);
          
          if(meetLine.getTranslateX() >= middlePane.getWidth()-100){ 
             return false; 
              
          }else if(Double.parseDouble(df2.format(meetLine.getTranslateX())) <= Double.parseDouble(df2.format(blueCar.getInitialPosition()+10))) {
              
              return false;
              
          }
          System.out.println("meet " +Double.parseDouble(df2.format(meetLine.getTranslateX())));
          System.out.println("initial " + Double.parseDouble(df2.format(blueCar.getInitialPosition()+20)));
          middlePane.getChildren().add(meetLine);
          carsMeet =true;
          meetingDistance = blueCar.getTranslateX()/10;
          return true;
          
        }
        }
      return false;
        
    }
    private void updateSliderMax() {
        redInitialPositionSlider.setMax((middlePane.getWidth() / 10- redCar.getWidth() / 10));
        redFinalPositionSlider.setMin(redInitialPositionSlider.getValue());// this causes the final position ticks to start after the initial postion of the car
        redFinalPositionSlider.setMax(middlePane.getWidth() / 10 - redCar.getWidth() / 10);

        blueInitialPositionSlider.setMax(middlePane.getWidth() / 10- blueCar.getWidth() / 10);
        blueFinalPositionSlider.setMin(blueInitialPositionSlider.getValue());
        blueFinalPositionSlider.setMax(middlePane.getWidth() / 10 - blueCar.getWidth() / 10);
    }

    private void updateInput() {
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
        if (redCar.getTranslateX() < redCar.getFinalPosition()) {
            redCar.setTranslateX(redCar.getTranslateX() + redCar.getCurrentVelocity() / 10);// divide by 10 because otherwise the accelerartion will be too fast 
        }
    }
    
        
    private void setupGauges(){
         
        blueGauge = new Gauge();
        
        blueGauge.setSkin(new ModernSkin(blueGauge));  //ModernSkin : you guys can change the skin
        blueGauge.setTitle("Blue Car");  //title
        blueGauge.setUnit("m / s");  //unit
        blueGauge.setUnitColor(Color.CYAN);
        blueGauge.setDecimals(0); 
        blueGauge.setValue(0); //default position of needle on gauage
        blueGauge.setAnimated(true);
        blueGauge.setAnimationDuration(1); 
        blueGauge.setValueColor(Color.CYAN); 
        blueGauge.setTitleColor(Color.CYAN); 
        blueGauge.setSubTitleColor(Color.WHITE); 
        blueGauge.setBarColor(Color.rgb(0, 214, 215)); 
        blueGauge.setNeedleColor(Color.CYAN); 
        blueGauge.setThresholdColor(Color.PURPLE);  //color will become red if it crosses threshold value
        blueGauge.setThreshold(40);        
        blueGauge.setThresholdVisible(true);
        blueGauge.setTickLabelColor(Color.rgb(151, 151, 151)); 
        blueGauge.setTickMarkColor(Color.CYAN); 
        blueGauge.setTickLabelOrientation(TickLabelOrientation.ORTHOGONAL);
        blueGauge.setBackgroundPaint(Color.BLACK);
        blueGaugePane.getChildren().add(blueGauge);
        
        redGauge = new Gauge();
        
        redGauge.setSkin(new ModernSkin(redGauge));  //ModernSkin : you guys can change the skin
        redGauge.setTitle("Red Car");  //title
        redGauge.setUnit("m / s");  //unit
        redGauge.setUnitColor(Color.RED);
        redGauge.setDecimals(0); 
        redGauge.setValue(0); //default position of needle on gauage
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
    
    private void updateGaugesValues(){
      
       blueGauge.setValue(blueCar.getCurrentVelocity());
       redGauge.setValue(redCar.getCurrentVelocity());
        
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
                

                blueCarPositionGraph[i][j] = (blueCar.calculateGraphDisplacement(blueCar.calculateCurrentDisplacement(), blueFinalTime));//the time going in the method is correct
                blueCarPositionGraph[i][j + 1] = blueFinalTime;

                blueCarVelocityGraph[i][j] = (blueCar.calculateCurrentVelocity(blueCar.calculateGraphDisplacement(blueCar.calculateCurrentDisplacement(), blueFinalTime)));
                blueCarVelocityGraph[i][j + 1] = blueFinalTime;

                redFinalTime += redCar.calculateFinalTime(redCar.calculateFinalDisplacement(), redCar.calculateFinalVelocity(redCar.calculateFinalDisplacement())) / 10;
                blueFinalTime += blueCar.calculateFinalTime(blueCar.calculateFinalDisplacement(), blueCar.calculateFinalVelocity(blueCar.calculateFinalDisplacement())) / 10;

            }
        }

    }
    
    private void disableSliders(boolean redInitialPosition, boolean redFinalPosition, boolean blueInitialPosition, boolean blueFinalPosition, boolean blueInitialVelocity, boolean blueAcceleration, boolean redInitialVelocity, boolean redAcceleration){
      redInitialPositionSlider.setDisable(redInitialPosition);
      redFinalPositionSlider.setDisable(redFinalPosition);
      blueInitialPositionSlider.setDisable(blueInitialPosition);
      blueFinalPositionSlider.setDisable(blueFinalPosition);
      blueInitialVelocitySlider.setDisable(blueInitialVelocity);
      blueAccelerationSlider.setDisable(blueAcceleration);
      redInitialVelocitySlider.setDisable(redInitialVelocity);
      redAccelerationSlider.setDisable(redAcceleration);
    }
    
    private void endOfSimulation(){
        
      if(blueCar.getFinalPosition() <= blueCar.getTranslateX() && redCar.getFinalPosition() <= redCar.getTranslateX()){
          handleReset();
          if(carsMeet){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cars meet");
            alert.setContentText("Please not that the two cars meet at " +df.format(meetingDistance) + "m");
            alert.show();
          }else{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cars do not meet");
            alert.setContentText("Please not that the two cars meet never meet ");   
            alert.show();
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
        blueGauge.setValue(0);
        redGauge.setValue(0);
        disableBtns(false, true, false, true);
        timeline.pause();
    }

    @FXML
    private void handleReset() {
        disableSliders(false, false, false, false, false, false, false, false);
        disableBtns(true, true, true, false);
        timeline.stop(); 
    }

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
    
    
    private void darkMode(){
       for(int i = 0; i < text.size(); i++){
          text.get(i).setFill(Color.BROWN);
       } 
       
       for(int i = 0; i<pane.size(); i++){
         pane.get(i).setStyle("-fx-background-color: #202020");
         middlePane.setStyle("-fx-border-color: blue");
         
       }
       
       for(int i = 0; i<button.size(); i++){
         button.get(i).setStyle("-fx-background-color: blue");
         button.get(i).setTextFill(Color.AQUA);
       }
       
       for(int i = 0; i<dottedLines.size(); i++){
         dottedLines.get(i).setStroke(Color.PURPLE);
         
       }
       top.setStroke(Color.GREENYELLOW);
       bottom.setStroke(Color.GREENYELLOW);
      windowTitle.setTextFill(Color.GREENYELLOW);
    }

    
    private void lightMode(){
      for(int i = 0; i < text.size(); i++){
          text.get(i).setFill(Color.BLACK);
       } 
       
       for(int i = 0; i<pane.size(); i++){
         pane.get(i).setStyle("-fx-background-color: white");
         
         middlePane.setStyle("-fx-border-color: black");
         
       }
       
       for(int i = 0; i<button.size(); i++){
         button.get(i).setStyle("-fx-background-color: white");
         button.get(i).setTextFill(Color.BLACK);
       }
       
       for(int i = 0; i<dottedLines.size(); i++){
         dottedLines.get(i).setStroke(Color.BLACK);
         
       }
       top.setStroke(Color.BLACK);
       bottom.setStroke(Color.BLACK);
       windowTitle.setTextFill(Color.BLACK); 
    }
    
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
    
    
    @FXML
    private void handleHomeButton(){
      PhysicsSimulationController.carSimulation.close();  
      timeline.stop();
      timelineToUpdateSliders.stop();
    }
    
    

}
