
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
public class Scanner extends Torre {
    
    public Scanner() {}
    
    public Scanner(int x, int y, int alto, int ancho, Image imagen) {
        iX = x;
        iY = y;
        iAncho = ancho;
        iAlto = alto;
        imaImagen = imagen;

        bPurchase = true;  
    }
    
}
