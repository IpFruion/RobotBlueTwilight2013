/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.AnalogModule;

/**
 *
 * @author alec
 */
public class Piston {
    private Solenoid extend, retract;
    private Relay forward;
    private boolean isSolenoid;
    private AnalogChannel ai;
    private int pressureReaderport;
    private double psi;
    
    
    public Piston(int extendPort, int retractPort){
        //System.out.println("Piston Init");
        extend = new Solenoid(extendPort);
        retract = new Solenoid(retractPort);
        isSolenoid = true;
        pressureReaderport = -1;
    }
    public Piston(int extendPort, int retractPort, int readerport){
        extend = new Solenoid(extendPort);
        retract = new Solenoid(retractPort);
        isSolenoid = true;
        pressureReaderport = readerport;
        ai = new AnalogChannel(pressureReaderport);
    }
    
    public Piston (int port) {
        forward = new Relay(port);
        forward.set(Relay.Value.kReverse);
        isSolenoid = false;
    }
    
    public void setPistonState(boolean state)
    {
        if (isSolenoid) {
            extend.set(state);
            retract.set(!state);
        }
        else {
            if (state) {
                forward.set(Relay.Value.kForward);
            }
            else {
                forward.set(Relay.Value.kReverse);
            }
        }
    }
    
    public boolean get()
    {
        if (isSolenoid) {
            return extend.get();
        }
        else {
            if (forward.get() == Relay.Value.kForward) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    
    public double getPSI()
    {
        if (pressureReaderport != -1)
        {
            psi = ai.getValue();
            //psi = (psi*29.54)-10.41;
            return psi;
        }
        return -1;
    }
    
}
