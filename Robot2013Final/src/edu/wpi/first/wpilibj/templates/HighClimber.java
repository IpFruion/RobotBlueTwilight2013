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
public class HighClimber extends BTClimber implements Constants{
    Piston shortArm;
    Piston longArm;
    Piston tiltPiston;
    
    public HighClimber() {
        shortArm = new Piston(HIGH_SHORT_ARM_EXTEND_PORT, HIGH_SHORT_ARM_RETRACT_PORT);
        longArm = new Piston(HIGH_LONG_EXTEND_PORT, HIGH_LONG_RETRACT_PORT);
        tiltPiston = new Piston(HIGH_TILT_EXTEND_PORT, HIGH_TILT_RETRACT_PORT);
    }
    
    public void update(ControlBoard cb) {
        
        if (cb.canClimb())
        {
            
        }
        
    }
    
    public void extend() {
        shortArm.setPistonState(true);
        wait(1);
        longArm.setPistonState(true);
    }
    
    public void extendShortArm() {
        shortArm.setPistonState(true);
    }

    public void retract() {
       if(shortArm.get() && longArm.get()) {
        shortArm.setPistonState(false);
        longArm.setPistonState(false);
        }
    }
    
    public void wait(int millis) {
        try {
            Thread.sleep(millis*1000);
        } catch(Exception e) {
            
        }
    }
}
