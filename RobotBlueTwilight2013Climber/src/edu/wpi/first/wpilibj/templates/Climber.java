/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author alec
 */
public abstract class Climber implements Constants {
    
    Piston offGround = new Piston(LIFTOFF_EXTEND_PORT, LIFTOFF_RETRACT_PORT);
}
