/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mark
 */
public class CarTest {
    
    public CarTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

   
    /**
     * Test of calculateCurrentVelocity method, of class Car.
     */
    @Test
    public void testCalculateCurrentVelocity() {
        System.out.println("calculateCurrentVelocity");
        Car car = new Car();
       car.setInitialVelocity(10);
       car.setAcceleration(10);
       double h = car.calculateCurrentVelocity(80);
       assertEquals(10, h);
      
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of calculateCurrentTime method, of class Car.
     */
    @Test
    public void testCalculateCurrentTime() {
        System.out.println("calculateCurrentTime");
        double displacement = 0.0;
        Car instance = new Car();
        instance.calculateCurrentTime(displacement);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateFinalTime method, of class Car.
     */
    @Test
    public void testCalculateFinalTime() {
        System.out.println("calculateFinalTime");
        double finalDisplacement = 0.0;
        double finalVelocity = 0.0;
        Car instance = new Car();
        double expResult = 0.0;
        double result = instance.calculateFinalTime(finalDisplacement, finalVelocity);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateGraphDisplacement method, of class Car.
     */
    @Test
    public void testCalculateGraphDisplacement() {
        System.out.println("calculateGraphDisplacement");
        double displacement = 0.0;
        double time = 0.0;
        Car instance = new Car();
        double expResult = 0.0;
        double result = instance.calculateGraphDisplacement(displacement, time);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateGraphVelocity method, of class Car.
     */
    @Test
    public void testCalculateGraphVelocity() {
        System.out.println("calculateGraphVelocity");
        double time = 0.0;
        Car instance = new Car();
        instance.calculateGraphVelocity(time);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateFinalVelocity method, of class Car.
     */
    @Test
    public void testCalculateFinalVelocity() {
        System.out.println("calculateFinalVelocity");
        double finalDisplacement = 0.0;
        Car instance = new Car();
        double expResult = 0.0;
        double result = instance.calculateFinalVelocity(finalDisplacement);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateCurrentDisplacement method, of class Car.
     */
    @Test
    public void testCalculateCurrentDisplacement() {
        System.out.println("calculateCurrentDisplacement");
        Car instance = new Car();
        double expResult = 0.0;
        double result = instance.calculateCurrentDisplacement();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateFinalDisplacement method, of class Car.
     */
    @Test
    public void testCalculateFinalDisplacement() {
        System.out.println("calculateFinalDisplacement");
        Car instance = new Car();
        double expResult = 0.0;
        double result = instance.calculateFinalDisplacement();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInitialPosition method, of class Car.
     */
    @Test
    public void testGetInitialPosition() {
        System.out.println("getInitialPosition");
        Car instance = new Car();
        double expResult = 0.0;
        double result = instance.getInitialPosition();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInitialPosition method, of class Car.
     */
    @Test
    public void testSetInitialPosition() {
        System.out.println("setInitialPosition");
        double initialPosition = 0.0;
        Car instance = new Car();
        instance.setInitialPosition(initialPosition);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentPosition method, of class Car.
     */
    @Test
    public void testGetCurrentPosition() {
        System.out.println("getCurrentPosition");
        Car instance = new Car();
        double expResult = 0.0;
        double result = instance.getCurrentPosition();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentPosition method, of class Car.
     */
    @Test
    public void testSetCurrentPosition() {
        System.out.println("setCurrentPosition");
        double currentPosition = 0.0;
        Car instance = new Car();
        instance.setCurrentPosition(currentPosition);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFinalPosition method, of class Car.
     */
    @Test
    public void testGetFinalPosition() {
        System.out.println("getFinalPosition");
        Car instance = new Car();
        double expResult = 0.0;
        double result = instance.getFinalPosition();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFinalPosition method, of class Car.
     */
    @Test
    public void testSetFinalPosition() {
        System.out.println("setFinalPosition");
        double finalPosition = 0.0;
        Car instance = new Car();
        instance.setFinalPosition(finalPosition);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInitialVelocity method, of class Car.
     */
    @Test
    public void testGetInitialVelocity() {
        System.out.println("getInitialVelocity");
        Car instance = new Car();
        double expResult = 0.0;
        double result = instance.getInitialVelocity();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInitialVelocity method, of class Car.
     */
    @Test
    public void testSetInitialVelocity() {
        System.out.println("setInitialVelocity");
        double initialVelocity = 0.0;
        Car instance = new Car();
        instance.setInitialVelocity(initialVelocity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentVelocity method, of class Car.
     */
    @Test
    public void testGetCurrentVelocity() {
        System.out.println("getCurrentVelocity");
        Car instance = new Car();
        double expResult = 0.0;
        double result = instance.getCurrentVelocity();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentVelocity method, of class Car.
     */
    @Test
    public void testSetCurrentVelocity() {
        System.out.println("setCurrentVelocity");
        double currentVelocity = 0.0;
        Car instance = new Car();
        instance.setCurrentVelocity(currentVelocity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFinalVelocity method, of class Car.
     */
    @Test
    public void testGetFinalVelocity() {
        System.out.println("getFinalVelocity");
        Car instance = new Car();
        double expResult = 0.0;
        double result = instance.getFinalVelocity();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFinalVelocity method, of class Car.
     */
    @Test
    public void testSetFinalVelocity() {
        System.out.println("setFinalVelocity");
        double finalVelocity = 0.0;
        Car instance = new Car();
        instance.setFinalVelocity(finalVelocity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAcceleration method, of class Car.
     */
    @Test
    public void testGetAcceleration() {
        System.out.println("getAcceleration");
        Car instance = new Car();
        double expResult = 0.0;
        double result = instance.getAcceleration();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAcceleration method, of class Car.
     */
    @Test
    public void testSetAcceleration() {
        System.out.println("setAcceleration");
        double acceleration = 0.0;
        Car instance = new Car();
        instance.setAcceleration(acceleration);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTime method, of class Car.
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        Car instance = new Car();
        double expResult = 0.0;
        double result = instance.getTime();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTime method, of class Car.
     */
    @Test
    public void testSetTime() {
        System.out.println("setTime");
        double time = 0.0;
        Car instance = new Car();
        instance.setTime(time);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
