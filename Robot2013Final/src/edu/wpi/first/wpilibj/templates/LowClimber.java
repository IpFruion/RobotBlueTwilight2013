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
public class LowClimber extends BTClimber implements Constants{
    Piston arm1;
    Piston arm2;
    
    public LowClimber() {
        arm1 = new Piston(LOW_ARM1_EXTEND_PORT, LOW_ARM1_RETRACT_PORT);
        arm2 = new Piston(LOW_ARM2_EXTEND_PORT, LOW_ARM2_RETRACT_PORT);
    }
    
    public void update(ControlBoard cb) {
        if (cb.canClimb())
        {
            lowPull();
        }
    }

    public void lowPull() {
        arm1.setPistonState(true);
        arm2.setPistonState(true);
        wait(1);
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
