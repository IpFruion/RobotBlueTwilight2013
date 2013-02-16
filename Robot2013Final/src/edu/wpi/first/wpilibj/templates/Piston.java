/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author alec
 */
public class Piston {
    private Solenoid extend, retract;
    private Relay forward;
    private boolean isSolenoid;
    
    public Piston(int extendPort, int retractPort){
        extend = new Solenoid(extendPort);
        retract = new Solenoid(retractPort);
        isSolenoid = true;
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
}
