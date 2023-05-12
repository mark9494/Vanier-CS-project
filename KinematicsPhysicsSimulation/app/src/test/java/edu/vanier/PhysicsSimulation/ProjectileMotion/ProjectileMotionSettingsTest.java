/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 2161743
 */
public class ProjectileMotionSettingsTest {

    public ProjectileMotionSettingsTest() {
    }

    @Test
    public void testSetTime() {
        ProjectileMotionSettings pms = new ProjectileMotionSettings();
        pms.accelerationY = 3;
        pms.initVelocityY = 10;
        pms.setDeltaY(30);

        double expResult = 1.6560973568579596;
        pms.setTime();
        double result = pms.time;
        assertEquals(expResult, result, 0.6);
    }
    public void testSetTime2() {
        ProjectileMotionSettings pms = new ProjectileMotionSettings();
        pms.accelerationY = 10;
        pms.initVelocityY = 3;
        pms.setDeltaY(80);

        double expResult = 3.7112342240263154;
        pms.setTime();
        double result = pms.time;
        assertEquals(expResult, result, 0.6);
    }
    public void testSetTime3() {
        ProjectileMotionSettings pms = new ProjectileMotionSettings();
        pms.accelerationY = 40;
        pms.initVelocityY = 80;
        pms.setDeltaY(900);

        double expResult = 5;
        pms.setTime();
        double result = pms.time;
        assertEquals(expResult, result, 0.5);
    }

}
