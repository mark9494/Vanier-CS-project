package edu.vanier.PhysicsSimulation.FreeFallSimulation;

import edu.vanier.PhysicsSimulation.PhysicsSimulationController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FreeFallLoader extends Stage {

    static Stage secondWindow;

    /**
     * Constructor which creates the window. Called in project's main to run
     * simulation.
     */
    public FreeFallLoader() {
        try {
            makeComponents();
        } catch (IOException ex) {
            Logger.getLogger(PhysicsSimulationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates the window for the simulation.
     *
     * @throws IOException
     */
    private void makeComponents() throws IOException {
        secondWindow = new Stage();
        FreeFallController controller = new FreeFallController();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/FreeFallSim.fxml"));
        loader.setController(controller);
        Pane root = loader.load();
        Scene scene = new Scene(root, 800, 600);
        secondWindow.setScene(scene);
        secondWindow.setTitle("FreeFall Simulation");
        secondWindow.sizeToScene();
        secondWindow.show();
    }
}
