
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
public class Worm extends Malo{
    private int iHits;
    
    public Worm() {}
    
    public Worm(int x, int y, int alto, int ancho, Image imagen) {
        super(x, y, alto, ancho, 3, 2, imagen);
        iHits = 3;
    }
    
    public int getHit() {
        return iHits;
    }
    
    public void hit() {
        iHits --;
    }
}
