/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package pkg01mar02;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author francesco
 */
public class CarF implements Runnable {
    
    private final int start, exit;
    private int curr;
    
    private RoundaboutF rabt;
    
    private String myName;
    
    private boolean exited = false;
    
    CarF(RoundaboutF rabt) {
        Random r = new Random();
        start = r.nextInt(4);
        exit = r.nextInt(4);
        
        this.rabt = rabt;
    }

    @Override
    public void run() {
        myName = Thread.currentThread().getName();
        
        int previous = (start + 3) % 4;
        
        rabt.getLock(previous).lock();
        try {
            while (rabt.getCount(previous) > 0) {
                System.out.println(myName + " is waiting.");
                rabt.getCondition(previous).await();
            }
            rabt.getLock(start).lock();
            curr = start;
            rabt.incCount(start);
            System.out.println(myName + " enters the roundabout at section " + curr + ". It will exit at section " + exit);
            rabt.getLock(start).unlock();
        } catch (InterruptedException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rabt.getLock(previous).unlock();
        }
        
        while (!exited) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            int next = (curr + 1) % 4;
            
            if (next != exit) {
                rabt.getLock(next).lock();
                try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                rabt.getLock(curr).lock();
                System.out.println(myName + " moves from section " + curr + " to section " + (next) + ".");
                rabt.decCount(curr);
                rabt.getCondition(curr).signalAll();
                rabt.getLock(curr).unlock();
                rabt.incCount(next);
                rabt.getCondition(next).signalAll();
                rabt.getLock(next).unlock();
                curr = next;
            }
            else {
                rabt.getLock(curr).lock();
                System.out.println(myName + " exits the roundabout, section " + exit + ".");
                rabt.decCount(curr);
                rabt.getCondition(curr).signalAll();
                exited = true;
                rabt.getLock(curr).unlock();
            }
        }
    }
    
}
