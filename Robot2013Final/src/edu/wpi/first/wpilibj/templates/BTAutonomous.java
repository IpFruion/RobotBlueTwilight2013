/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
/**
 *
 * @author Luke
 */
public class BTAutonomous{
    public ControlBoard cb;
    public BTController stick;
    public DriveTrain drive;
    public CompressorInit comp;
    public Timer timer;
    public Gyro gyro;
    public Encoder encoder;
    int DRIVE_ENCODER_CHANNEL_A = 1;
    int DRIVE_ENCODER_CHANNEL_B = 2;
  public void robotInit()
    {
        stick = new BTController();
        drive = new DriveTrain();
        comp = new CompressorInit();
        timer = new Timer();
        encoder = new Encoder(DRIVE_ENCODER_CHANNEL_A, DRIVE_ENCODER_CHANNEL_B);
    }
  }
