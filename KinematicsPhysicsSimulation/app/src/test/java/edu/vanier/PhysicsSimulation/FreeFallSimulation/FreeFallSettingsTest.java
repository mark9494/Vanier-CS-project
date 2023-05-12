/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.vanier.PhysicsSimulation.FreeFallSimulation;

import javafx.scene.shape.Rectangle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author antho
 */
public class FreeFallSettingsTest {

    public FreeFallSettingsTest() {
    }

    /**
     * Test of returnFinalVelocity method, of class FreeFallSettings.
     */
    @Test
    public void testReturnFinalVelocity() {
        FreeFallController ffc = new FreeFallController();
        double result = ffc.returnFinalVelocity(9.8, 200);
        double expResult = 62.6;
        assertEquals(expResult, result, 0.1);
    }

    @Test
    public void testReturnFinalVelocity2() {
        FreeFallController ffc = new FreeFallController();
        double result = ffc.returnFinalVelocity(5, 400);
        double expResult = 63.24;
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of returnTime method, of class FreeFallSettings.
     */
    @Test
    public void testReturnTime() {
        FreeFallController ffc = new FreeFallController();
        ffc.building = new Rectangle();
        ffc.building.setHeight(200);
        double result = ffc.returnTime(ffc.returnFinalVelocity(5, 200), 5, 0);
        double expResult = 8.94;
        assertEquals(expResult, result, 0.1);
    }

    @Test
    public void testReturnTime2() {
        FreeFallController ffc = new FreeFallController();
        ffc.building = new Rectangle();
        ffc.building.setHeight(800);
        double result = ffc.returnTime(ffc.returnFinalVelocity(15, 800), 15, 0);
        double expResult = 10.32;
        assertEquals(expResult, result, 0.1);
    }

}
