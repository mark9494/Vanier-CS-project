package edu.vanier.PhysicsSimulation.ProjectileMotion;

import edu.vanier.PhysicsSimulation.PhysicsSimulationController;
import static edu.vanier.PhysicsSimulation.ProjectileMotion.ProjectileMotionSettings.timelinePaneResize;
import static edu.vanier.PhysicsSimulation.ProjectileMotion.ProjectileMotionSettings.timelineRectangleAndBall;
import static edu.vanier.PhysicsSimulation.ProjectileMotion.ProjectileMotionSettings.timer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class ProjectileMotion {

    /**
     * Constructor which starts the simulation by opening a new scene.
     *
     * @throws Exception
     */
    public ProjectileMotion() throws Exception {
        start();
    }

    /**
     * Starts the application by setting the scene and connecting the FXML file
     * and the controller.
     *
     * @throws Exception
     */
    public void start() throws Exception {
        PhysicsSimulationController.projectileMotion.setOnCloseRequest(e -> projectileMotionClosed());
        ProjectileMotionController mainController = new ProjectileMotionController();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(
                "/fxml/ProjectileMotion.fxml"));
        loader.setController(mainController);
        Pane root = loader.load();
        Scene scene = new Scene(root, 1000, 800);
        PhysicsSimulationController.projectileMotion.setScene(scene);
        PhysicsSimulationController.projectileMotion.setTitle(
                "Projectile Motion");
        PhysicsSimulationController.projectileMotion.sizeToScene();
        PhysicsSimulationController.projectileMotion.show();
    }

    /**
     * Closes the timelines and timer when the application closes.
     */
    public void projectileMotionClosed() {
        timelineRectangleAndBall.stop();
        timelinePaneResize.stop();
        timer.cancel();
    }
}
