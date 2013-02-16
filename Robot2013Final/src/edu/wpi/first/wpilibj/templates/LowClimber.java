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
public class LowClimber implements Constants, IClimber{
    Piston arm1;
    ClimberInfo climbInfo;
    
    
    public LowClimber() {
        arm1 = new Piston(LOW_ARM1_EXTEND_PORT, LOW_ARM1_RETRACT_PORT);
        
    }
    
    public void update(ControlBoard cb) {
        climbInfo = cb.getClimber();
        if (climbInfo.canClimb)
        {
            lowPull();
            wait(1000);
            retract();
        }
    }

    public void lowPull() {
        arm1.setPistonState(false);
        
    }

    public void retract() {
       
        arm1.setPistonState(true);
         
       
    }
    
    public void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            
        }
    }
}
