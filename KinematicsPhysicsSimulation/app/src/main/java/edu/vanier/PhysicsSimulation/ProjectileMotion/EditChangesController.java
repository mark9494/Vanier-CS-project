/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author 2161743
 */
public class EditChangesController {

    @FXML
    Button btnDone;
    @FXML
    RadioButton rb1;
    @FXML
    RadioButton rb2;
    @FXML
    RadioButton rb3;
    @FXML
    RadioButton rb4;
    @FXML
    ImageView iv1;
    @FXML
    ImageView iv2;
    @FXML
    ImageView iv3;
    @FXML
    ImageView iv4;

    static boolean isBall = false;

    public void initialize() {
        if (isBall) {
            Image i1 = new Image("/images/basketball.png");
            iv1.setImage(i1);

            Image i2 = new Image("/images/soccerBall.png");
            iv2.setImage(i2);

            Image i3 = new Image("/images/basketball.png");
            iv3.setImage(i3);

            Image i4 = new Image("/images/basketball.png");
            iv4.setImage(i4);
        }
    }

    @FXML
    public void handleDone() {
        if (isBall) {

        } else {

        }
        ProjectileMotionSettings.editorStage.close();
    }

    @FXML
    public void handleRb1() {
        rb2.setSelected(false);
        rb3.setSelected(false);
        rb4.setSelected(false);
    }

    @FXML
    public void handleRb2() {
        rb1.setSelected(false);
        rb3.setSelected(false);
        rb4.setSelected(false);
    }

    @FXML
    public void handleRb3() {
        rb2.setSelected(false);
        rb1.setSelected(false);
        rb4.setSelected(false);
    }

    @FXML
    public void handleRb4() {
        rb2.setSelected(false);
        rb3.setSelected(false);
        rb1.setSelected(false);
    }
}
