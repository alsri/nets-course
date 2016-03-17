/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package pkg01mar02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author francesco
 */
public class RoundaboutF {
    
    private int[] counter = new int[4];
    private ReentrantLock[] sectorLock;
    private Condition[] sectorCondition;
    
    RoundaboutF() {
        sectorLock = new ReentrantLock[4];
        sectorCondition = new Condition[4];
        for (int i = 0; i < sectorLock.length; i++) {
            sectorLock[i] = new ReentrantLock();
            sectorCondition[i] = sectorLock[i].newCondition();
        }
        for (int i = 0; i < counter.length; i++) {
            counter[i] = 0; 
        }
    }
    
    public ReentrantLock getLock(int i) {
        return sectorLock[i];
    }
    
    public Condition getCondition(int i) {
        return sectorCondition[i];
    }
    
    public int getCount(int i) {
        return counter[i];
    }
    
    public void incCount(int i) {
        counter[i]++;
    }
    
    public void decCount(int i) {
        counter[i]--;
    }
    
}
