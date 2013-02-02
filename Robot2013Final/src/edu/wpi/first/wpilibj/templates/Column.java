/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author Dlock
 */
public class Column {
        
        private Solenoid[] support;
        private Piston pusher = new Piston(1,2);
        private DigitalInput[] inputs;
        private int numDisks = 0;
        public RadialShooter sc;
        public int supportCounter = 0;
        
        public Column()
        {
            try {
                support = new Solenoid[6];
                inputs = new DigitalInput[4];
            }
            catch(Exception e){}
            sc = new RadialShooter();
            
            for (int index = 0; index<support.length; index++)
            {
                support[index] = new Solenoid(index+1);
            }
            
            for (int index = 0; index<inputs.length; index++)
            {
                inputs[index] = new DigitalInput(index+1);
            }
            
        }
        public void update(ControlBoard cb)
        {
            if (numDisks <= 1)
            {
                collect();
                numDisks = getNumDisks();
            }
            if (cb.canShoot() && (numDisks > 0))
            {
                
                numDisks = getNumDisks();
                fireReload(cb);
            }
        }
        public void collect() {
            supportCounter = 0;
            if(inputs[0].get()) {
                support[0].set(true);
                support[1].set(true);
                if(inputs[1].get()) {
                    support[2].set(true);
                    support[3].set(true);
                    if(inputs[2].get()) {
                        support[4].set(true);
                        support[5].set(true);
                    }
                }
            }
        }
        public void fireReload(ControlBoard cb)
        {
            pusher.setPistonState(true);
            //Account for if fire pin extended
            //Quick Fix not for end final version
            try{Thread.sleep(1000);}catch(Exception e){};
            pusher.setPistonState(false);
            supportCounter++;
            switch (supportCounter) {
                case 1:
                    support[0].set(false);
                    support[1].set(false);
                    break;
                case 2:
                    support[0].set(false);
                    support[1].set(false);
                    support[2].set(false);
                    support[3].set(false);
                    break;
                case 3:
                    support[0].set(false);
                    support[1].set(false);
                    support[2].set(false);
                    support[3].set(false);
                    support[4].set(false);
                    support[5].set(false);
                    break;
            }
            
        }
        public int getNumDisks() {
            int counter = 0;
            for (int x = 0; x < inputs.length; x++) {
                if(inputs[x].get()) {
                    counter++;
                }
            }
            return counter;
        }
    }