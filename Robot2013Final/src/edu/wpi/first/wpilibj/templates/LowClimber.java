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
    boolean isStart = true;
    public void update(ControlBoard cb) {
        climbInfo = cb.getClimber();
        if (isStart)
        {
            lowPull();
            isStart = false;
        }
        if (climbInfo.canClimb)
        {
            retract();
        }
        if (!climbInfo.canClimb)
        {
            isStart = true;
        }
    }

    public void lowPull() {
        arm1.setPistonState(true);
        
    }

    public void retract() {
       
        arm1.setPistonState(false);
    }
    
    public void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            
        }
    }
}
