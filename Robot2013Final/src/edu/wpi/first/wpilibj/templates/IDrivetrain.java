/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Robotics
 */
public interface IDrivetrain {
    
    public abstract void update(ControlBoard cb);
    public abstract boolean yawSet(int centerX);
    
}
