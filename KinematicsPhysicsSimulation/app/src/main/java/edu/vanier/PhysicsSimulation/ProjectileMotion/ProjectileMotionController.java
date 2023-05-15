package edu.vanier.PhysicsSimulation.ProjectileMotion;

import edu.vanier.PhysicsSimulation.PhysicsSimulationController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ProjectileMotionController extends ProjectileMotionSettings {

    /**
     * Initializes the application, setting up the menu bar, instantiating
     * objects and styling nodes.
     */
    @FXML
    public void initialize() {
        FileChooser fileChooser = new FileChooser();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        disableButtons(true, false);
        menuBar.getMenus().remove(2);
        MenuItem save = new MenuItem("Save Last Run");
        MenuItem openSave = new MenuItem("Open Save");
        MenuItem changeBallPicture = new MenuItem("Change Ball");
        MenuItem changeBackgroundPicture = new MenuItem("Change Background");
        menuBar.getMenus().get(0).getItems().addAll(save, openSave);
        menuBar.getMenus().get(1).getItems().addAll(changeBallPicture, changeBackgroundPicture);
        btnBegin.setStyle("-fx-background-color: #66FF00; -fx-border-color: black");
        btnReset.setStyle("-fx-background-color: #f7435d; -fx-border-color: black");

        EventHandler<ActionEvent> savePressed = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                newSave = directoryChooser.showDialog(new Stage());
                IO.writeDataInFile(newSave.getPath() + "\\ProjectileMotionData.csv");
            }
        };
        EventHandler<ActionEvent> loadSaved = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                loadSave = fileChooser.showOpenDialog(new Stage());
                try {
                    IO.readDataInFile(loadSave.getPath());
                    loadVisualSettingsBack();
                } catch (IOException ex) {
                    System.out.println("File Not Read Properly. ");
                }
            }
        };
        EventHandler<ActionEvent> changeBallImage = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                EditChangesController.isBall = true;
                try {
                    openEditWindow();
                } catch (IOException ex) {
                    Logger.getLogger(ProjectileMotionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        EventHandler<ActionEvent> changeBackgroundImage = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                EditChangesController.isBall = false;
                try {
                    openEditWindow();
                } catch (IOException ex) {
                    Logger.getLogger(ProjectileMotionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        setDefaultBackGround();
        updateBackground();
        save.setOnAction(savePressed);
        openSave.setOnAction(loadSaved);
        changeBallPicture.setOnAction(changeBallImage);
        changeBackgroundPicture.setOnAction(changeBackgroundImage);
        wind = new Wind();
        cannon = new Cannon();
        cannon.setTranslateY(25);
        cannon.setTranslateX(100);
        landingArea = new LandingArea();
        ball = new Ball();
        setBallDefaultLocation();
        motionPane.getChildren().addAll(landingArea, ball, cannon);
        createAnimation();
        timelineRectangleAndBall.play();
        timelinePaneResize.play();

        int defaultPaneWidth = 980;
        int defaultPaneHeight = 200;
        landingArea.randomSpawn(defaultPaneWidth - landingArea.getWidth(),
                cannon.getTranslateX() + cannon.getWIDTH() + 25, defaultPaneHeight);
    }

    /**
     * Set the Sliders back to their values and the wind value from the saved
     * simulation data.
     */
    public void loadVisualSettingsBack() {
        sldInitialVelocity.setValue(initialVelocity);
        sldAccelerationY.setValue(accelerationY);
        sldRampAngle.setValue(cannonAngle);
        if (isWind) {
            CBoxWind.setSelected(true);
            windBox.setOpacity(100);
            wind.setAngle(Wind.angle);
            wind.setIntensity(Wind.intensity);
            windArrow.setRotate(-Wind.angle * 180 / Math.PI);
            setIntensity();
        }
    }

    /**
     * Sets the ball in the default spawn inside the cannon.
     */
    public void setBallDefaultLocation() {
        ball.setTranslateX(cannon.getCornerX() + ball.getRadius());
        ball.setTranslateY(cannon.getCornerY() - ball.getRadius() - 20);
    }

    /**
     * Creates the necessary timelines for the animation.
     */
    public void createAnimation() {
        animationDuration = 15;
        currentRate = 4;
        double currentRateBall = 5000;

        timelineBall = new Timeline(
                new KeyFrame(Duration.seconds(100),
                        e -> handleUpdateAnimationBall()));
        timelineBall.setRate(currentRateBall);
        timelineBall.setCycleCount(Timeline.INDEFINITE);

        timelineRectangleAndBall = new Timeline(
                new KeyFrame(Duration.millis(animationDuration),
                        e -> handleUpdateAnimation()));
        timelineRectangleAndBall.setRate(currentRate);
        timelineRectangleAndBall.setCycleCount(Timeline.INDEFINITE);

        timelinePaneResize = new Timeline(
                new KeyFrame(Duration.millis(animationDuration),
                        e -> handlePaneResizeAffects()));
        timelinePaneResize.setRate(currentRate);
        timelinePaneResize.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Disables buttons, making them not able to activate.
     *
     * @param reset True disables the button, False enables the button.
     * @param begin True disables the button, False enables the button.
     */
    private void disableButtons(boolean reset, boolean begin) {
        btnBegin.setDisable(begin);
        btnReset.setDisable(reset);
    }

    /**
     * When the home button is pressed, all variables are set to zero, timelines
     * are closed and stage is closed.
     */
    @FXML
    public void handleHomeButton() {
        PhysicsSimulationController.projectileMotion.close();
        wind.setIntensity(0);
        resetParameters();
        timelineRectangleAndBall.stop();
        timelinePaneResize.stop();
        timer.cancel();
    }

    /**
     * Starts the animation, and calculates the kinematics for the animation.
     */
    @FXML
    public void handleBegin() {
        disableButtons(true, true);
        timelineRectangleAndBall.stop();
        generateParameters();
        timelineBall.play();
        lblTime.setText("" + df.format(time));
        lblPosition.setText("" + df.format(finalPosition));
    }

    /**
     * Stops the animation, resets all the variables and sets the application
     * back to its original state.
     */
    @FXML
    public void handleResetButton() {
        removeWinLoseAnnouncement();
        disableButtons(true, false);
        setBallDefaultLocation();
        resetParameters();
        moveCannonAndBall();
        timelineRectangleAndBall.play();
        landingArea.randomSpawn(motionPane.getWidth() - landingArea.getWidth(),
                cannon.getTranslateX() + cannon.getWIDTH() + 25, motionPane.getHeight());
    }

    /**
     * Timeline loop which allows the cannon the move according to the slider
     * and for the wind to appear.
     */
    private void handleUpdateAnimation() {
        handleWindProperties();
        moveCannonAndBall();
        ball.setRotate(0);
    }

    /**
     * Timeline loop which allows the ball to move during the animation and
     * detects when it reaches the bottom, and if it lands in the landing area.
     */
    private void handleUpdateAnimationBall() {
        ball.setRotate(ball.getRotate() - Math.PI * 3);
        moveBallX();
        moveBallY();
        endOfMotion();
        ballInLandingArea();
    }

    /**
     * Sets up the wind settings when the user checks the wind box.
     */
    public void handleWindProperties() {
        if (!CBoxWind.isSelected()) {
            isWind = false;
            windBox.setOpacity(0);
            wind.setIntensity(0);
        } else if (isWind) {
            return;
        } else {
            isWind = true;
            windBox.setOpacity(100);
            wind.setAngle(wind.randomWindAngle());
            wind.setIntensity(wind.randomIntensity());
            windArrow.setRotate(-wind.getAngle() * 180 / Math.PI);
            setIntensity();
        }
    }

    /**
     * Styling of the progress bar according to the 3 intensity levels.
     */
    private void setIntensity() {
        if (wind.getIntensity() == 1) {
            windIntensity.setProgress(0.33);
            windIntensity.setStyle("-fx-accent: green;");

        } else if (wind.getIntensity() == 2) {
            windIntensity.setProgress(0.66);
            windIntensity.setStyle("-fx-accent: yellow;");

        } else {
            windIntensity.setProgress(1);
            windIntensity.setStyle("-fx-accent: red;");
        }
    }

    /**
     * Sets the position of the ball when the cannon moves to sit inside the
     * cannon.
     */
    private void moveCannonAndBall() {
        setCannonAngle();
        cannon.setRotate(cannon.getAngle());
        ball.setTranslateX(cannon.getCornerX() + ball.getRadius() + 20);
        ball.setTranslateY(cannon.getCornerY() - ball.getRadius() + 42);
    }

    /**
     * Calculates all the variables in order to finalize the animation.
     */
    private void generateParameters() {
        setDeltaY(motionPane.getHeight());
        setInitialVelocity();
        setAccelerationY();
        setCannonAngle();
        setVelocityX();
        setVelocityY();
        setTime();
        setDeltaX();
        ball.setDy(initVelocityY);
        ball.setDx(initVelocityX);
    }

    /**
     * Sets all variables to zero, when program is ready to run a new time.
     */
    private void resetParameters() {
        time = 0;
        finalPosition = 0;
        deltaX = 0;
        lblTime.setText("");
        lblPosition.setText("");
    }

    /**
     * Horizontal translation of the ball during the animation.
     */
    private void moveBallX() {
        ball.setTranslateX(ball.getTranslateX() + ball.getDx() + wind.getForceWindX());
    }

    /**
     * Vertical translate of the ball during the animation.
     */
    private void moveBallY() {
        ball.setDy(ball.getDy() - accelerationY + wind.getForceWindY());
        ball.setTranslateY(ball.getTranslateY() - ball.getDy());
    }

    /**
     * Detects when the ball has reached the bottom of the pane, indicating the
     * end of the animation.
     */
    private void endOfMotion() {
        if (ball.getTranslateY() + ball.getRadius() >= motionPane.getHeight()) {
            ball.setTranslateY(motionPane.getHeight() - ball.getRadius());
            timelineBall.pause();
            finalPosition = ball.getTranslateX();
            return;
        }
        if (ball.getTranslateX() > motionPane.getWidth() || ball.getTranslateY() < 0) {
            ball.setTranslateY(motionPane.getHeight() - ball.getRadius());
            timelineBall.pause();
            finalPosition = ball.getTranslateX();
        }
    }

    /**
     * Detects if the coordinates of the ball are within those of the landing
     * area to detect if it is within it.
     */
    private void ballInLandingArea() {
        boolean ballLanded;
        if (finalPosition != 0) {
            if (finalPosition > landingArea.getLeftX() && finalPosition < landingArea.
                    getRightX()) {
                ballLanded = true;
                disableButtons(false, true);
            } else {
                ballLanded = false;
            }
            if (ballLanded) {
                lblPosition.setText("" + df.format(ball.getTranslateX() - cannon.getCornerX()));
                winAnnouncement();
            } else {
                lblPosition.setText("" + df.format(ball.getTranslateX() - cannon.getCornerX()));
                LostAnnouncement();
                disableButtons(false, true);
            }
        }
    }

    /**
     * Resizing nodes in pane according to the new size of the window.
     */
    private void handlePaneResizeAffects() {
        sldInitialVelocity.setMax(motionPane.getWidth() / 20);
        hboxBottom.setTranslateX(motionPane.getWidth() / 6);
        windBox.setTranslateX(motionPane.getWidth() - 1000);
        landingArea.setTranslateY(motionPane.getHeight() - landingArea.INIT_HEIGHT);
    }

    /**
     * Opens a label announcing to the user that the ball landed in the landing
     * area.
     */
    private void winAnnouncement() {
        winAnnouncement = new VBox();
        win = new Label();
        win.setTextFill(Color.GREEN);
        win.setFont(new Font(100));
        win.setText("Scored!");
        winAnnouncement.setTranslateX(motionPane.getWidth() / 3);
        winAnnouncement.setTranslateY(motionPane.getHeight() / 3);
        winAnnouncement.getChildren().add(win);
        motionPane.getChildren().add(winAnnouncement);
    }

    /**
     * Opens a label announcing to the user that the ball has not landed in the
     * landing area.
     */
    private void LostAnnouncement() {
        loseAnnouncement = new VBox();
        lose = new Label();
        lose.setTextFill(Color.RED);
        lose.setFont(new Font(100));
        lose.setText("Missed");
        loseAnnouncement.setTranslateX(motionPane.getWidth() / 3);
        loseAnnouncement.setTranslateY(motionPane.getHeight() / 3);
        loseAnnouncement.getChildren().add(lose);
        motionPane.getChildren().add(loseAnnouncement);
    }

    /**
     * Removes the win/lose announcement for the next run.
     */
    private void removeWinLoseAnnouncement() {
        motionPane.getChildren().remove(winAnnouncement);
        motionPane.getChildren().remove(loseAnnouncement);
    }

    /**
     * Sets the scene of the window for the user to edit the images of the
     * application.
     *
     * @throws IOException
     */
    private void openEditWindow() throws IOException {
        editorStage = new Stage();
        EditChangesController mainController = new EditChangesController();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/editImages.fxml"));
        loader.setController(mainController);
        Pane root = loader.load();
        Scene scene = new Scene(root);
        editorStage.initOwner(PhysicsSimulationController.projectileMotion);
        editorStage.setScene(scene);
        editorStage.setTitle("Editor");
        editorStage.sizeToScene();
        editorStage.show();
    }
}
