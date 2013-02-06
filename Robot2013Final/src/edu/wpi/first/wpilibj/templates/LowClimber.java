/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author alec
 */
public class LowClimber extends BTClimber{
    Piston arm1;
    Piston arm2;
    
    public LowClimber() {
        arm1 = new Piston(3, 4);
        arm2 = new Piston(5, 6);
    }
    
    public void update(Joystick rightStick) {
        if (rightStick.getRawButton(10)) // button for extend arm
            extend();
        else if (rightStick.getRawButton(9)) // button for retract arm
            retract();
    }

    public void extend() {
        arm1.setPistonState(true);
        wait(1);
        arm2.setPistonState(true);
    }

    public void retract() {
       if(arm1.get() && arm2.get()) {
        arm1.setPistonState(false);
        arm2.setPistonState(false); 
       }
    }
    
    public void wait(int millis) {
        try {
            Thread.sleep(millis * 1000);
        } catch (Exception e) {
            
        }
    }
}
