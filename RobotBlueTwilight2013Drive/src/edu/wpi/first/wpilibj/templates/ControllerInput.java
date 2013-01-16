/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;


/**
 *
 * @author Dlock & others
 */

public class ControllerInput extends BTController {

    public double getLMotorSpeed() {
        
    }

    public double getRMotorSpeed() {
    }

    
    
    
    
    int[] s;
    
    
    boolean isCorrect;
    public ControllerInput()
    {
        
        if ((isJoy == true)&&(isDrive.equalsIgnoreCase("Drive") == true))
        {
            JJL = new Joystick(Port[0]);
            JJR = new Joystick(Port[1]);
        }
        if ((isJoy == true)&&(isDrive.equalsIgnoreCase("Manip") == true))
        {
            JJL = new Joystick(Port[0]);
        }
        
        isCorrect = isJoy;
    }
    
    public double getLY()
    {
       if(isCorrect == true)
       {
           return  JJL.getY();
       }
       //Add controller config
       return 0.;
    }
    public double getRY()
    {
       if(isCorrect == true)
       {
           return  JJR.getY();
       }
       //Add controller config
       return 0.;
    }
    public double getLX()
    {
       if(isCorrect == true)
       {
           return  JJL.getX();
       }
       //Add controller config
       return 0.;
    }
    public double getRX()
    {
       if(isCorrect == true)
       {
           return  JJR.getX();
       }
       //Add controller config
       return 0.;
    }

    public boolean getShiftButton() {
    }
    
}
