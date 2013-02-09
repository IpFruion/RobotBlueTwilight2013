/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Tim
 */
public class DriveTrain implements Constants
{
    Jaguar leftJag = new Jaguar(LEFT_JAG_PORT);
    Jaguar rightJag = new Jaguar(RIGHT_JAG_PORT);
    public Motor left, right;
    
    public void update(Joystick leftStick, Joystick rightStick)
    {
        double rightValue = rightStick.getY();
        double leftValue = leftStick.getY();
        left.set(leftValue);
        right.set(rightValue * -1);
    }
}
