/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Dlock
 */
public abstract class BTClimber {
   public abstract void update(Joystick rightStick); 
   public abstract void extend();
   public abstract void retract();
   public void init() {
       // evaluate switch
       // if (switch) instantiate High Climber
       // else instantiate other climber system
   }
}
