
import java.awt.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Bifi
 */
public class Polymorph extends Malo{
    private int iState;         //dice en que estado se encuentra el virus
    private int iTimer;
    
    public Polymorph() {}
    
    public Polymorph(int x, int y, int alto, int ancho, Image imagen) {
        super(x, y, alto, ancho, 4, 2, imagen);
        iState = 0;
        iTimer = 0;
    }
    
    public void act() {
        super.act();
        iTimer ++;
        
        if (iTimer > 100) {
            if (iState == 2) {
                iState = 0;
            }
            else {
                iState ++;
            }
            iTimer = 0;
        }
            
        
        
        
//        if (Math.random()*100 < 5)
//        {
//            iState = (int) Math.random()*3;
//        }
    }
    
    public int getState() {
        return iState;
    }
}
