/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.Pendulum;

import edu.vanier.PhysicsSimulation.PhysicsSimulationController;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Youssif
 */
@Getter
@Setter
public class PendulumController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private CheckBox DampingCheckBox;

    @FXML
    private Pane Base, animationPane;

    @FXML
    private MenuBar menu;

    @FXML
    private Menu editItem;

    @FXML
    private MenuItem saveSettings, openSavedSettings;

    @FXML
    private MenuItem darkMode, lightMode;

    @FXML
    private Canvas canvas;

    @FXML
    private Slider massSlider, gravitySlider;

    @FXML
    private Button playBtn, stopBtn, pauseBtn, graphBtn;

    @FXML
    private ImageView homeBtn;

    @FXML
    private Separator vSeparator, hSeparator;

    @FXML
    private Circle circle;

    private GraphicsContext gc;
    
    @FXML
    private ImageView home;

    private double maxAngle;
    private double length;
    private double mass;
    private int damping;
    private double gravity;
    private double angularFrequency;
    private double duration;

    private int currentNumCyc;

    private double mouseX;
    private double mouseY;

    private static double circleX;
    private static double circleY;

    private GraphLoader graph;
    private PendulumIO writting = new PendulumIO();

    private List<Particle> particles = new ArrayList<>();

    private ArrayList<Polyline> paths = new ArrayList<>();

    private final EventHandler<MouseEvent> MouseMoved = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            mouseX = event.getX();
            mouseY = event.getY();
        }
    };

    private final EventHandler<MouseEvent> eventMousePressed = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            circle.setCursor(Cursor.CLOSED_HAND);
        }
    };

    private final EventHandler<MouseEvent> eventMouseDragged = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            double x = (event.getSceneX() /*- circle.getRadius()*2*/) - (canvas.getWidth() / 1.5 - 100);
            double y = event.getSceneY() - canvas.getHeight() / 2;
            double placement = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
            if (placement <= 300 && placement >= 100 && (event.getSceneY() - canvas.getHeight() / 2) >= 0) {
                circle.setTranslateX(event.getSceneX());
                circle.setTranslateY(event.getSceneY());
                setLength(placement);
                setMaxAngle(((Math.abs(Math.atan(x / y))) * 180) / Math.PI);
            }
        }
    };

    private final EventHandler<MouseEvent> eventMouseReleased = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            circle.setCursor(Cursor.OPEN_HAND);
        }
    };
    private final EventHandler<MouseEvent> eventMouseEnteredTarget = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            circle.setCursor(Cursor.OPEN_HAND);
        }
    };

    /**
     * This method is to help us draw the string that holds the bob. Every time
     * its position is updated the method clears everything on the canvas and
     * draw a new line.
     *
     * @param gc
     */
    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        double startY = circle.getTranslateY();
        double startX = circle.getTranslateX();
        double endY = canvas.getHeight() / 2;
        double endX = canvas.getWidth() / 1.5 - 100;

        gc.strokeLine(startX, startY, endX, endY);
    }

    /**
     * This method is used to create a path that the pendulum will follow
     *
     * @return PolyLine
     */
    private Polyline createPath() {
        double RADIUS = length;
        double opposite = (canvas.getHeight() / 2 - circle.getTranslateY());
        double adjacent = ((canvas.getWidth() / 1.5 - 100) - circle.getTranslateX());
        double startAngle = Math.toDegrees(Math.atan(opposite / adjacent));
        double ARC_ANGLE = 90 - Math.abs(startAngle);
        maxAngle = ARC_ANGLE;
        double startX = canvas.getWidth() / 1.5 - 100;
        double startY = canvas.getHeight() / 2;
        Polyline polyline = new Polyline();
        if (startAngle >= 0) {
            for (int i = 1; i < 2 * ARC_ANGLE; i++) {
                double angle = Math.toRadians(i + startAngle);
                double x = startX + RADIUS * Math.cos(angle);
                double y = startY + RADIUS * Math.sin(angle);
                polyline.getPoints().addAll(x, y);
            }
        } else if (startAngle < 0) {
            for (int i = 1; i < 2 * ARC_ANGLE; i++) {
                double angle = Math.toRadians(i + Math.abs(startAngle));
                double x = startX - RADIUS * Math.cos(angle);
                double y = startY + RADIUS * Math.sin(angle);
                polyline.getPoints().addAll(x, y);
            }
        }
        return polyline;
    }

    private Polyline createPath2() {
        double RADIUS = length;
        double opposite = (canvas.getHeight() / 2 - circle.getTranslateY());
        double adjacent = ((canvas.getWidth() / 1.5 - 100) - circle.getTranslateX());
        double startAngle = Math.toDegrees(Math.atan(opposite / adjacent));
        double ARC_ANGLE = 90 - Math.abs(startAngle);
        if (ARC_ANGLE < 2) {
            return null;
        }
        maxAngle = ARC_ANGLE - ARC_ANGLE * 0.1;
        ARC_ANGLE = ARC_ANGLE - ARC_ANGLE * 0.1;
        double startX = canvas.getWidth() / 1.5 - 100;
        double startY = canvas.getHeight() / 2;
        Polyline polyline = new Polyline();
        if (startAngle >= 0) {
            for (int i = 1; i < 2 * ARC_ANGLE; i++) {
                double angle = Math.toRadians(i + startAngle);
                double x = startX + RADIUS * Math.cos(angle);
                double y = startY + RADIUS * Math.sin(angle);
                polyline.getPoints().addAll(x, y);
            }
        } else if (startAngle < 0) {
            for (int i = 1; i < 2 * ARC_ANGLE; i++) {
                double angle = Math.toRadians(i + Math.abs(startAngle));
                double x = startX - RADIUS * Math.cos(angle);
                double y = startY + RADIUS * Math.sin(angle);
                polyline.getPoints().addAll(x, y);
            }
        }

        return polyline;
    }

    private void clickAndDrag() {
        circle.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, eventMouseEnteredTarget);

        circle.addEventHandler(MouseEvent.MOUSE_PRESSED, eventMousePressed);

        circle.addEventHandler(MouseEvent.MOUSE_DRAGGED, eventMouseDragged);

        circle.addEventHandler(MouseEvent.MOUSE_RELEASED, eventMouseReleased);

    }

    private void removeClickAndDrag() {
        circle.removeEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, eventMouseEnteredTarget);

        circle.removeEventHandler(MouseEvent.MOUSE_PRESSED, eventMousePressed);

        circle.removeEventHandler(MouseEvent.MOUSE_DRAGGED, eventMouseDragged);

        circle.removeEventHandler(MouseEvent.MOUSE_RELEASED, eventMouseReleased);
    }
    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            onUpdate();
        }
    };

    public void setSlider() {

        massSlider.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setMass(massSlider.getValue());
            }
        });
        gravitySlider.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setGravity(gravitySlider.getValue());
                if (damping == 0) {
                    setAngularFrequency(Math.sqrt(gravity / length));
                } else {// TODO: Setup the Angular Frequency when there is damping}
                }
            }
        });
        
        DampingCheckBox.selectedProperty().addListener(listener ->{
            if (DampingCheckBox.isSelected() == true) {
                for (int y = 0; y < canvas.getHeight() / 10; y++) {
                    for (int x = 0; x < canvas.getWidth() / 10; x++) {
                        particles.add(new Particle(x * 10, y * 10, Color.BLUE));
                    }
                }
                timer.start();
            }
                    
            
        });

    }
    boolean firstTime = true;

    private void onUpdate() {
        
        if (firstTime) {
            circle.setTranslateX(circle.getTranslateX() + 1);
            circle.setTranslateY(circle.getTranslateY() + 1);
            firstTime = false;
        }
        if (DampingCheckBox.isSelected() == true) {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            Point2D circlePosition = new Point2D(circleX, circleY);

            particles.forEach(p -> {
                p.update(circlePosition);

                gc.setFill(p.color);

                gc.fillOval(p.x - 1, p.y - 1, 2, 2);

            });
            double startY = circle.getTranslateY();
            double startX = circle.getTranslateX();
            double endY = canvas.getHeight() / 2;
            double endX = canvas.getWidth() / 1.5 - 100;

            gc.strokeLine(startX, startY, endX, endY);
        } else if (DampingCheckBox.isSelected() == false) {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            draw(gc);
        }

    }

    private void disableSlider(boolean b) {

        massSlider.setDisable(b);
        DampingCheckBox.setDisable(b);
        gravitySlider.setDisable(b);
    }

    private void setValues() {
        maxAngle = 90;
        length = 100;
        mass = massSlider.getValue();
        gravity = gravitySlider.getValue();
        angularFrequency = Math.sqrt(gravity / length);
        duration = 2;
    }

    private void setPlayBtn() {
        playBtn.setOnAction((e) -> {
            if (DampingCheckBox.isSelected()) {
                Polyline path = createPath2();
                Animation anim = animate(path, 1, true, duration);
                anim.play();
                setBtns(anim);
            } else if (DampingCheckBox.isSelected() == false) {
                Polyline path = createPath();
                Animation anim = animate(path, Animation.INDEFINITE, true, duration);
                anim.play();
                setBtns(anim);
            }
            saveSettings.setDisable(true);
            openSavedSettings.setDisable(true);

            playBtn.setDisable(true);
            pauseBtn.setDisable(false);
            stopBtn.setDisable(false);
            graphBtn.setDisable(false);
            disableSlider(true);
            removeClickAndDrag();
        });
    }

    public void setBtns(Animation animation) {
        playBtn.setOnAction((e) -> {
            if (pauseBtn.isDisable() == false) {
                animation.play();
            } else {
                animation.play();
                pauseBtn.setDisable(false);
                stopBtn.setDisable(false);
            }
            disableSlider(true);
        });

        pauseBtn.setOnAction((e) -> {
            animation.pause();
            playBtn.setDisable(false);
            pauseBtn.setDisable(true);
        });

        stopBtn.setOnAction((e) -> {
            animation.stop();
            pauseBtn.setDisable(true);
            playBtn.setDisable(false);
            stopBtn.setDisable(true);
            saveSettings.setDisable(false);
            openSavedSettings.setDisable(false);
            disableSlider(false);
            setPlayBtn();
            clickAndDrag();
        });

        graphBtn.setOnAction((e) -> {
            graph = new GraphLoader();
            graph.show();
        });

    }

    public void circleProperties() {

        circle.translateXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                draw(gc);
                circleX = circle.getTranslateX();
                firstTime = false;
            }

        });
        circle.translateYProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                draw(gc);
                circleY = circle.getTranslateY();
                firstTime = false;
            }

        });
    }
    /**
     * This method is used only to create a path animation for the pendulum
     *
     * @param path this path is taken from the creatPath() method
     * @param numbCycles
     * @param isAutoReverse
     * @param duration
     * @return Animation animation
     */
    private PathTransition animation = new PathTransition();

    public Animation animate(Polyline path, int numbCycles, boolean isAutoReverse,
            double duration) {

        if (DampingCheckBox.isSelected()) {
            animation.setCycleCount(numbCycles);
        } else {
            animation.setCycleCount(Animation.INDEFINITE);
        }
        animation.setAutoReverse(isAutoReverse);
        animation.setDuration(Duration.seconds(duration));
        animation.setPath(path);
        animation.setNode(circle);
        animation.setInterpolator(Interpolator.EASE_BOTH);
        animation.setRate(3 * this.getAngularFrequency());
        animation.setOnFinished((finish) -> {
            Polyline path2 = createPath2();
            if (path2 == null) {

            } else if (path2 != null) {
                Animation anim = animate(path2, 1, false, duration);
                anim.play();
            }
        }
        );
        return animation;
    }

    public void loadValues() {
        circle.setTranslateX(writting.getCircleX());
        circle.setTranslateY(writting.getCircleY());
        gravitySlider.setValue(writting.getGravityVal());
        massSlider.setValue(writting.getMassVal());
        DampingCheckBox.setSelected(writting.isChecked());
        
    }
    
    public void setupTheMenu(){
        darkMode.setOnAction((e) -> {
            borderPane.getStylesheets().remove(0);
            borderPane.getStylesheets().add(getClass().getResource("/css/darkStyle.css").toString());
        });

        lightMode.setOnAction((e) -> {
            borderPane.getStylesheets().remove(0);
            borderPane.getStylesheets().add(getClass().getResource("/css/pendulumAnimationStyle.css").toString());
        });
        saveSettings.setOnAction((e) -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            try {
                File toSave = directoryChooser.showDialog(new Stage());
                writting = new PendulumIO(circle, DampingCheckBox, gravitySlider, massSlider);
                writting.writeDataInFile(toSave.getPath() + "\\PendulumAnimation.csv");
            } catch (Exception exception) {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Improper file");
                a.setContentText("Choose a proper file to save the settings");
                a.showAndWait();
            }

        });
        openSavedSettings.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File fileToRead = fileChooser.showOpenDialog(new Stage());
            try {
                writting.readDataInFile(fileToRead.getPath());
                loadValues();
            } catch (Exception excep) {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Improper file");
                a.setContentText("Choose a proper file to read the settings");
                a.showAndWait();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Adjusting the enviornment and the nodes:
        Base.setLayoutX(0);
        Base.setLayoutY(0);
        animationPane.setLayoutX(0);
        animationPane.setLayoutY(0);
        circleX = circle.getTranslateX();
        circleY = circle.getTranslateY();
        canvas.setLayoutX(animationPane.getLayoutX());
        canvas.setLayoutY(animationPane.getLayoutY());
        circle.setTranslateX(canvas.getWidth() / 1.5);
        circle.setTranslateY(canvas.getHeight() / 2);

        gc = canvas.getGraphicsContext2D();

        setupTheMenu();
        //Setting up the sliders to get the inputted data
        setSlider();

        setValues();
        draw(gc);
        toString();
        //Setting up the buttons
        playBtn.setDisable(false);
        pauseBtn.setDisable(true);
        stopBtn.setDisable(true);
        graphBtn.setDisable(true);
        clickAndDrag();
        setPlayBtn();

        circleProperties();
        graphBtn.setOnAction((e) -> {
            GraphController g = new GraphController();
        });
        
        home.pressedProperty().addListener(listener -> {
            PhysicsSimulationController.anim.close();
            stopAnimations();
        });
    }

    public static double getCurrentXPosition() {
        return circleX - 325;
    }

    public static double getCurrentYPosition() {
        return circleY - (350);
    }

    public void stopAnimations() {
        animation.stop();
        timer.stop();
    }

    @Override
    public String toString() {
        return "PendulumController{" + ", maxAngle=" + maxAngle
                + ", length=" + length + ", mass=" + mass + ", damping="
                + damping + ", gravity=" + gravity + ", angularFrequency="
                + angularFrequency + ", duration=" + duration + '}';
    }
}
