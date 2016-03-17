/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package pkg01mar02;

/**
 *
 * @author francesco
 */
public class MainF {
    
    static RoundaboutF rabt;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        rabt = new RoundaboutF();
        
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new CarF(rabt));
            t.start();     
        }
    }
    
}
