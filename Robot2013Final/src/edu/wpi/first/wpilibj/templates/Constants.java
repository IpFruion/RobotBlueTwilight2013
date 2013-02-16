/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Tim & Derrick
 */
public interface Constants
{
    //Jags
    public static final int LEFT_JAG_PORT = 1;
    public static final int LEFT_JAG_PORT_2 = 4;
    public static final int RIGHT_JAG_PORT = 2;
    public static final int RIGHT_JAG_PORT_2 = 5;
    public static final int RADIAL_SHOOTER_MOTOR_PORT = 3;
    public static final int LINEAR_SHOOTER_MOTOR1_PORT = 3;
    public static final int LINEAR_SHOOTER_MOTOR2_PORT = 6;
    public static final int SHOOTER_PITCH_MOTOR_PORT = 7;

    
    //Joysticks
    public static final int LEFT_STICK_PORT = 1;
    public static final int RIGHT_STICK_PORT = 2;
    
    //Solenoids
    public static final int SHOOTER_EXTEND_PORT = 1;
    public static final int SHOOTER_RETRACT_PORT = 2;
    public static final int HIGH_SHORT_ARM_EXTEND_PORT = 5;
    public static final int HIGH_SHORT_ARM_RETRACT_PORT = 6;
    public static final int HIGH_LONG_EXTEND_PORT = 3;
    public static final int HIGH_LONG_RETRACT_PORT = 4;
    public static final int HIGH_TILT_EXTEND_PORT = 7;
    public static final int HIGH_TILT_RETRACT_PORT = 8;
    public static final int LOW_ARM1_EXTEND_PORT = 3;
    public static final int LOW_ARM1_RETRACT_PORT = 4;
//    public static final int LOW_ARM2_EXTEND_PORT = 13;
//    public static final int LOW_ARM2_RETRACT_PORT = 14;
    
    
    //Relay
    public static final int COMP_RELAY_PORT = 1;
    public static final int DRIVE_SHIFTER_PORT = 2;
    public static final int CLIMBING_ENHANCE_PISTON_PORT = 3;
    
    //Digital I/O Ports
    public static final int COMP_SENSOR_PORT = 1;
    public static final int LEFT_DRIVE_ENCODER_A_PORT = 2;
    public static final int LEFT_DRIVE_ENCODER_B_PORT = 3;
    public static final int RIGHT_DRIVE_ENCODER_A_PORT = 4;
    public static final int RIGHT_DRIVE_ENCODER_B_PORT = 5;
    public static final int SHOOTER_PITCH_HIGH_PORT = 6;
    public static final int SHOOTER_PITCH_LOW_PORT = 7;
    public static final int CAN_DISABLE = 12;   //TODO: implement these
    public static final int CLIMBER_TYPE = 13;
    public static final int SHOOTER_TYPE = 14;
    
    //Analog Ports
    public static final int AUTONOMOUS_SELECTION_PORT = 3;
    public static final int BATTERY_VOLTAGE_FINAL_PORT = 8;  //Note: this can't be changed
    
    //Constants
    public static final int CYCLES_FOR_VISION = 11;
    public static final double PITCH_MOTOR_SPEED = .5;
}
