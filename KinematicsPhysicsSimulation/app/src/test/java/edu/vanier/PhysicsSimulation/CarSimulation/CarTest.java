/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.vanier.PhysicsSimulation.CarSimulation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mark
 */
public class CarTest {

    /**
     * Test of calculateCurrentVelocity method, of class Car.
     */
    @Test
    public void testCalculateCurrentVelocity() {
        System.out.println("calculateCurrentVelocity");
        Car car = new Car();
       car.setInitialVelocity(20);
       car.setAcceleration(20);
       double result = car.calculateCurrentVelocity(40);
       double expResult = 44.721;
       assertEquals(expResult, result,0.01);
  
    }
    @Test
    public void testCalculateCurrentVelocity2() {
        System.out.println("calculateCurrentVelocity");
        Car car = new Car();
       car.setInitialVelocity(20);
       car.setAcceleration(0);
       double result = car.calculateCurrentVelocity(40);
       double expResult = 20;
       assertEquals(expResult, result,0.01);
    }
    
     @Test
    public void testCalculateCurrentVelocity3() {
        System.out.println("calculateCurrentVelocity");
        Car car = new Car();
       car.setInitialVelocity(0);
       car.setAcceleration(25);
       double result = car.calculateCurrentVelocity(20);
       double expResult = 31.622;
       assertEquals(31.622, result,0.01);
    }

    /**
     * Test of calculateCurrentTime method, of class Car.
     */
    @Test
    public void testCalculateCurrentTime() {
        System.out.println("calculateCurrentTime");
        double displacement = 50;
        Car car = new Car();
        car.setInitialVelocity(0);
        car.setAcceleration(20);
        car.setFinalPosition(1000);
        car.setInitialPosition(500);
        double result = car.calculateCurrentTime(displacement);
        double expResult = 2.23;
        assertEquals(expResult,result ,0.01);
    }
    @Test
    public void testCalculateCurrentTime2() {
        System.out.println("calculateCurrentTime");
        double displacement = 50;
        Car car = new Car();
        car.setInitialVelocity(20);
        car.setAcceleration(20);
        car.setFinalPosition(1000);
        car.setInitialPosition(500); 
        double result = car.calculateCurrentTime(displacement);
        double expResult = 1.44;
        assertEquals(expResult,result ,0.01);
    }
 

    /**
     * Test of calculateFinalTime method, of class Car.
     */
    @Test
    public void testCalculateFinalTime1() {
        System.out.println("calculateFinalTime");
        double finalDisplacement = 22;
        double finalVelocity = 22.0;
        Car car = new Car();
        car.setInitialVelocity(20);
        double expResult = 1.04;
        double result = car.calculateFinalTime(finalDisplacement,
                finalVelocity);
        assertEquals(expResult, result, 0.01);
    }
    @Test
    public void testCalculateFinalTime2() {
        System.out.println("calculateFinalTime");
        double finalDisplacement = 200;
        double finalVelocity = 5;
        Car car = new Car();
        car.setInitialVelocity(0);
        double expResult = 80;
        double result = car.calculateFinalTime(finalDisplacement,
                finalVelocity);
        assertEquals(expResult, result, 0.01);
    }

    
    @Test
    public void testCalculateGraphDisplacement() {
        System.out.println("calculateGraphDisplacement");
        double displacement = 54;
        double time = 23;
        Car car = new Car();
        car.setInitialVelocity(0);
        car.setAcceleration(0);
        double expResult = 0.0;
        double result = car.calculateGraphDisplacement(displacement, time);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of calculateFinalVelocity method, of class Car.
     */
    @Test
    public void testCalculateFinalVelocity() {
        System.out.println("calculateFinalVelocity");
        double finalDisplacement = 87;
        Car car = new Car();
        car.setInitialVelocity(11);
        car.setAcceleration(54);
        double expResult = 97.55;
        double result = car.calculateFinalVelocity(finalDisplacement);
        assertEquals(expResult, result, 0.1);
        
    }
    @Test
    public void testCalculateFinalVelocity2() {
        System.out.println("calculateFinalVelocity");
        double finalDisplacement = 20;
        Car car = new Car();
        car.setInitialVelocity(0);
        car.setAcceleration(0);
        double expResult = 0;
        double result = car.calculateFinalVelocity(finalDisplacement);
        assertEquals(expResult, result, 0.1);
        
    }

   
    @Test
    public void testCalculateFinalDisplacement() {
        System.out.println("calculateFinalDisplacement");
        Car car = new Car();
        car.setFinalPosition(5000);
        car.setInitialPosition(1000);
        double expResult = 400;
        double result = car.calculateFinalDisplacement();
        assertEquals(expResult, result, 0.1);    
    }

    /**
     * Test of getInitialPosition method, of class Car.
     */
    
}
