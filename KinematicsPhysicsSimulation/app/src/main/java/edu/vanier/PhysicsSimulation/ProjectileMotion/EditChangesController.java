package edu.vanier.PhysicsSimulation.ProjectileMotion;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;

public class EditChangesController {

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

    Image i1, i2, i3, i4;
    static boolean isBall = false;

    /**
     * Sets up the images for the user to chose from, depending on if the user
     * wants to change the ball or the background.
     */
    public void initialize() {
        if (isBall) {
            i1 = new Image("/images/basketball.png");
            iv1.setImage(i1);

            i2 = new Image("/images/soccerBall.png");
            iv2.setImage(i2);

            i3 = new Image("/images/Baseball.png");
            iv3.setImage(i3);

            i4 = new Image("/images/wiffleBall.png");
            iv4.setImage(i4);
        } else {
            i1 = new Image("/images/background2.jpg");
            iv1.setImage(i1);

            i2 = new Image("/images/background1.jpg");
            iv2.setImage(i2);

            i3 = new Image("/images/background3.jpg");
            iv3.setImage(i3);

            i4 = new Image("/images/background4.jpg");
            iv4.setImage(i4);
        }
    }

    /**
     * Changes the image of the previous item to whichever the user chooses when
     * they press done.
     */
    @FXML
    public void handleDone() {
        if (isBall) {
            if (rb1.isSelected()) {
                ProjectileMotionSettings.ball.setFill(new ImagePattern(i1));
            }
            if (rb2.isSelected()) {
                ProjectileMotionSettings.ball.setFill(new ImagePattern(i2));
            }
            if (rb3.isSelected()) {
                ProjectileMotionSettings.ball.setFill(new ImagePattern(i3));
            }
            if (rb4.isSelected()) {
                ProjectileMotionSettings.ball.setFill(new ImagePattern(i4));
            }

        } else {
            ProjectileMotionSettings.changeBackground = true;
            if (rb1.isSelected()) {
                ProjectileMotionSettings.defaultBackgroundFilePath = i1.getUrl();
            }
            if (rb2.isSelected()) {
                ProjectileMotionSettings.defaultBackgroundFilePath = i2.getUrl();
            }
            if (rb3.isSelected()) {
                ProjectileMotionSettings.defaultBackgroundFilePath = i3.getUrl();
            }
            if (rb4.isSelected()) {
                ProjectileMotionSettings.defaultBackgroundFilePath = i4.getUrl();
            }
        }
        ProjectileMotionSettings.editorStage.close();
    }

    /**
     * Disables the other radio buttons, so there is only one selected at a
     * time.
     */
    @FXML
    public void handleRb1() {
        rb2.setSelected(false);
        rb3.setSelected(false);
        rb4.setSelected(false);
    }

    /**
     * Disables the other radio buttons, so there is only one selected at a
     * time.
     */
    @FXML
    public void handleRb2() {
        rb1.setSelected(false);
        rb3.setSelected(false);
        rb4.setSelected(false);
    }

    /**
     * Disables the other radio buttons, so there is only one selected at a
     * time.
     */
    @FXML
    public void handleRb3() {
        rb2.setSelected(false);
        rb1.setSelected(false);
        rb4.setSelected(false);
    }

    /**
     * Disables the other radio buttons, so there is only one selected at a
     * time.
     */
    @FXML
    public void handleRb4() {
        rb2.setSelected(false);
        rb3.setSelected(false);
        rb1.setSelected(false);
    }

    /**
     * Allows the user to click the image to activate the button, instead of
     * having to click the radio button only, and disables all other radio
     * buttons. Adds user friendliness.
     */
    @FXML
    public void handleClickIv1() {
        if (rb1.isSelected()) {
            rb1.setSelected(false);
            rb2.setSelected(false);
            rb3.setSelected(false);
            rb4.setSelected(false);
            return;
        }
        rb1.setSelected(true);
        rb2.setSelected(false);
        rb3.setSelected(false);
        rb4.setSelected(false);
    }

    /**
     * Allows the user to click the image to activate the button, instead of
     * having to click the radio button only, and disables all other radio
     * buttons. Adds user friendliness.
     */
    @FXML
    public void handleClickIv2() {
        if (rb2.isSelected()) {
            rb2.setSelected(false);
            rb1.setSelected(false);
            rb3.setSelected(false);
            rb4.setSelected(false);
            return;
        }
        rb2.setSelected(true);
        rb1.setSelected(false);
        rb3.setSelected(false);
        rb4.setSelected(false);
    }

    /**
     * Allows the user to click the image to activate the button, instead of
     * having to click the radio button only, and disables all other radio
     * buttons. Adds user friendliness.
     */
    @FXML
    public void handleClickIv3() {
        if (rb3.isSelected()) {
            rb3.setSelected(false);
            rb2.setSelected(false);
            rb1.setSelected(false);
            rb4.setSelected(false);
            return;
        }
        rb3.setSelected(true);
        rb2.setSelected(false);
        rb1.setSelected(false);
        rb4.setSelected(false);
    }

    /**
     * Allows the user to click the image to activate the button, instead of
     * having to click the radio button only, and disables all other radio
     * buttons. Adds user friendliness.
     */
    @FXML
    public void handleClickIv4() {
        if (rb4.isSelected()) {
            rb4.setSelected(false);
            rb2.setSelected(false);
            rb3.setSelected(false);
            rb1.setSelected(false);
            return;
        }
        rb4.setSelected(true);
        rb2.setSelected(false);
        rb3.setSelected(false);
        rb1.setSelected(false);
    }
}
