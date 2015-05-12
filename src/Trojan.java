
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
public class Trojan extends Malo{
    boolean bDiscovered;  //dice si es visible para las torres;
    
    public Trojan() {}
    
    public Trojan(int x, int y, int alto, int ancho, Image imagen) {
        super(x, y, alto, ancho, 2, 2, imagen);
        bDiscovered = false;
    }
    
    public void discovered() {
        bDiscovered = true;
    }
    
    public void hidden() {
        bDiscovered = false;
    }
    
    public boolean isDiscover() {
        return bDiscovered;
    }
}
