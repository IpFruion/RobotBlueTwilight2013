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
public class HighClimber extends BTClimber{
    Piston shortArm;
    Piston longArm;
    
    public HighClimber() {
        shortArm = new Piston(3, 4);
        longArm = new Piston(5, 6);
    }
    
    public void update(Joystick rightStick) {
        if (rightStick.getRawButton(10)) { // button for extend arm
            extend();
        }
        else if (rightStick.getRawButton(9)) { // button for retract arm
            retract(); 
        }
        else if (rightStick.getRawButton(11)) { // button for extend short arm last level
            extendShortArm();
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
