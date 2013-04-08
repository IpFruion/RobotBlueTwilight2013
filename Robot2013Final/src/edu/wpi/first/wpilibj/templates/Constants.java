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
    public static final int RADIAL_SHOOTER_MOTOR1_PORT = 6;
    public static final int RADIAL_SHOOTER_MOTOR2_PORT = 3;
    public static final int LINEAR_SHOOTER_MOTOR1_PORT = 6;
    public static final int LINEAR_SHOOTER_MOTOR2_PORT = 3;
    public static final int SHOOTER_FLIP_MOTOR_PORT = 7;
    public static final int RAD_PISTON_MOTOR_PORT = 7;

    
    //Joysticks
    public static final int LEFT_STICK_PORT = 1;
    public static final int RIGHT_STICK_PORT = 2;
    public static final int XBOX_CONTROLLER_PORT = 1;
    
    //Solenoids
    public static final int PITCH_EXTEND_PORT = 1;
    public static final int PITCH_RETRACT_PORT = 2;
    public static final int HIGH_SHORT_ARM_EXTEND_PORT = 5;
    public static final int HIGH_SHORT_ARM_RETRACT_PORT = 6;
    public static final int HIGH_LONG_EXTEND_PORT = 3;
    public static final int HIGH_LONG_RETRACT_PORT = 4;
    public static final int HIGH_TILT_EXTEND_PORT = 7;
    public static final int HIGH_TILT_RETRACT_PORT = 8;
    public static final int LOW_ARM1_EXTEND_PORT = 3;
    public static final int LOW_ARM1_RETRACT_PORT = 4;
    
    public static final int RADIAL_PITCH_PRESSURE_READER = 1;
    public static final int LOW_CLIMBER_PRESSURE_READER = 2;
    public static final int LINEAR_SHOOTER_PRESSURE_READER = 3;
    public static final int HIGH_CLIMBER_PRESSURE_READER = 4;
    
    
    //Relay
    public static final int COMP_RELAY_PORT = 3;
    public static final int DRIVE_SHIFTER_PORT = 2;
    public static final int CLIMBING_ENHANCE_PISTON_PORT = 1;
    
    //Digital I/O Ports
    public static final int COMP_SENSOR_PORT = 1;
    public static final int LEFT_DRIVE_ENCODER_A_PORT = 2;
    public static final int LEFT_DRIVE_ENCODER_B_PORT = 3;
    public static final int RIGHT_DRIVE_ENCODER_A_PORT = 4;
    public static final int RIGHT_DRIVE_ENCODER_B_PORT = 5;
    public static final int SHOOTER_PITCH_HIGH_PORT = 6;
    public static final int SHOOTER_PITCH_LOW_PORT = 7;
    public static final int DIGITAL_INPUT_SHOOTER = 14;
    public static final int DIGITAL_INPUT_CLIMBER = 13;
    public static final int DIGITAL_INPUT_DRIVE_TRAIN = 12;
    public static final int DIGITAL_INPUT_SENSE_DISK = 8; // probably needs to be changed
    
    //Analog Ports
    public static final int AUTONOMOUS_SELECTION_PORT = 3;
    public static final int BATTERY_VOLTAGE_FINAL_PORT = 8;  //Note: this can't be changed
    
    //Constants
    public static final int CYCLES_FOR_VISION = 11;
    public static final double PITCH_MOTOR_SPEED = .5;
    public static final double SHOOT_MOTOR_SPEED_LOW = .55;
    public static final double SHOOT_MOTOR_SPEED_HIGH = .85;
    //public static final boolean USE_CAN = false;
    public static final double RELOAD_SPEED = .5;
    
    //Controller Constants 
    //I WOULD NOT CHANGE THESE
    public static final int BACK = 7;
    public static final int START = 8;
    public static final int LEFT_STICK_BUTTON = 9;
    public static final int RIGHT_STICK_BUTTON = 10;
    public static final int TRIGGER_AXIS = 3;
    public static final int RIGHT_TRIGGER = -1;
    public static final int LEFT_TRIGGER = 1;
    public static final int LEFT_STICK = 2;
    public static final int RIGHT_STICK = 5;
    public static final int LEFT_BUMPER = 5;
    public static final int RIGHT_BUMPER = 6;
    public static final int Y_BUTTON = 4;
    public static final int X_BUTTON = 3;
    public static final int A_BUTTON = 1;
    public static final int B_BUTTON = 2;
}
