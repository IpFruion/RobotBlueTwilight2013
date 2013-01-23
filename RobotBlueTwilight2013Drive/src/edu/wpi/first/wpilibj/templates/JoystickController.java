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
public class JoystickController extends BTController {
    
    private Joystick leftStick, rightStick, manipStick; 
    public JoystickController(){
        leftStick = new Joystick(1);
        rightStick = new Joystick(2);
        manipStick = new Joystick(3);
    }
    
    public double getLMotorSpeed() {
        return leftStick.getY();
    }

    public double getRMotorSpeed() {
        return rightStick.getY();
    }

    public boolean getShiftButton() {
        return rightStick.getRawButton(2);
    }
        
}
