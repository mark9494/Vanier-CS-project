/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.vanier.PhysicsSimulation.ProjectileMotion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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
        pms.accelerationY = 1;
        pms.initVelocityY = -3.42;
        pms.setDeltaY(670);

        double expResult = 33.36;
        pms.setTime();
        double result = pms.time;
        assertEquals(expResult, result, 0.1);
    }

    @Test
    public void testSetTime2() {
        ProjectileMotionSettings pms = new ProjectileMotionSettings();
        pms.accelerationY = 5;
        pms.initVelocityY = -100;
        pms.setDeltaY(300);

        double expResult = 2.8;
        pms.setTime();
        double result = pms.time;
        assertEquals(expResult, result, 0.1);
    }

    @Test
    public void testSetTime3() {
        ProjectileMotionSettings pms = new ProjectileMotionSettings();
        pms.accelerationY = 3;
        pms.initVelocityY = -10;
        pms.setDeltaY(200);

        double expResult = 8.685;
        pms.setTime();
        double result = pms.time;
        assertEquals(expResult, result, 0.1);
    }

}
