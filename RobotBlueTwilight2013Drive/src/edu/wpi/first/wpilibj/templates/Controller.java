/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;


/**
 *
 * @author Dlock
 */
import edu.wpi.first.wpilibj.Joystick;
public class Controller implements Constants {
    
    Joystick JJ;
    boolean isCorrect;
    public Controller(int Port, boolean isJoy)
    {
        if (isJoy == true)
        {
            JJ = new Joystick(Port);
        }
        
        isCorrect = isJoy;
        
    }
    
    public double getY()
    {
       if(isCorrect == true)
       {
           return  JJ.getY();
       }
       //Add controller config
       return 0.;
    }
    public double getX()
    {
       if(isCorrect == true)
       {
           return  JJ.getX();
       }
       //Add controller config
       return 0.;
    }
    
}
