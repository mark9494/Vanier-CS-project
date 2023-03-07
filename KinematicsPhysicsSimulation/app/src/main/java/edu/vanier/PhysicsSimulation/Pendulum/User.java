/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.PhysicsSimulation.Pendulum;

import javax.management.ConstructorParameters;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 *
 * @author Youssif
 */
//@Getter
//@Setter
@ToString
@EqualsAndHashCode

public class User {
    
    @Getter
    private String name;
    
    private String lastName;

    @Setter
    private String gender,nationality;
    
    
}
