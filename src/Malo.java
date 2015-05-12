
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
public class Malo extends Base {
    protected int iTipo;      //dice que tipo de enemigo es
    protected int iSpeed;     //dice a que velocidad se mueve
    int iDireccion;             //dice la direccion del malo
    
    protected boolean bFrozen;//es verdadero si esta congelado
    
    /** 
     * Constructor vacío
     */
    public Malo() {}
    
    /**Constructor con parametros
     * 
     * @param x de tipo <int> da la posicion X
     * @param y de tipo <int> da la posicion Y
     * @param alto de tipo <int> da la altura
     * @param ancho de tipo <int> da el ancho
     * @param imagen de tipo <Image> defina la imagen
     * @param speed de tipo <int> se asigna a iSpeed
     */
    public Malo(int x, int y, int alto, int ancho, int speed, int dir, Image imagen) {
        iX = x;
        iY = y;
        iAncho = ancho;
        iAlto = alto;
        imaImagen = imagen;
        iSpeed = speed;
        iDireccion = dir;
        bFrozen = false;
    }

    /**Metodo act que se llama desde la clase principal
     * dice el movimiento del Malo de acuerdo a su posicion y su velocidad
     */
    public void act() {
        if (!bFrozen) {
            switch (iDireccion) {
                case 1: { //se mueve hacia arriba
                    this.iY = this.iY - iSpeed;
                    break;
                }
                case 2: { //se mueve hacia abajo
                    this.iY = this.iY + iSpeed;
                    break;
                }
                case 3: { //se mueve hacia izquierda
                    this.iX = this.iX - iSpeed;
                    break;
                }
                case 4: { //se mueve hacia derecha
                    this.iX = this.iX + iSpeed;
                    break;
                }
            }
            switch (iDireccion) {
                case 1: { // si se mueve hacia arriba 
                    if (this.iY < 100 && this.iX < 211) {
                        iDireccion = 4;     // se cambia la direccion para la derecha
                    } else if (this.iY > 250 && this.iY < 260 && this.iX < 500) {
                        iDireccion = 3;       // se cambia la direccion a la izq
                    }
                    break;
                }
                case 2: { // si se mueve hacia abajo
                    if (this.iY > 200 && this.iX < 110 && this.iY < 210) {
                        iDireccion = 4;     // se cambia la direccion para la derecha
                    } else if (this.iY > 250 && this.iY < 260 && this.iX < 710 && this.iX > 700) {
                        iDireccion = 3; // se cambia la direccion para la izquierda
                    } else if (this.iY > 325 && this.iY < 335 && this.iX < 610 && this.iX > 400) {
                        iDireccion = 4; // se cambia la direccion para la derecha
                    } else if (this.iY > 400 && this.iY < 410) {
                        iDireccion = 3; //se cambia la direccion para la izq
                    } else if (this.iY > 500 && this.iY < 510) {
                        iDireccion = 4; // se cambia la direccion a la derecha
                    }
                    break;
                }
                case 3: { // si se mueve hacia izquierda 
                    if (this.iX > 550 && this.iX < 560 && this.iY < 260) {
                        iDireccion = 2;       // se cambia la direccion abajo
                    } else if (this.iY < 460 && this.iX < 450 && this.iY > 400 && this.iX > 400) {
                        iDireccion = 1;     // se cambia la direccion hacia arriba
                    } else if (this.iY > 250 && this.iY < 260 && this.iX < 300) {
                        iDireccion = 2;     // se cambia la direccion hacia abajo
                    } else if (this.iY > 400 && this.iY < 410 && this.iX < 100) {
                        iDireccion = 2;     // se cambia la direccion hacia abajo
                    }
                    break;
                }
                case 4: { // si se mueve hacia derecha 
                    // si se esta saliendo del applet
                    if (this.iX > 200 && this.iY < 211 && this.iX < 210 && this.iY < 400) {
                        iDireccion = 1;       // se cambia direccion para arriba
                    } else if (this.iX > 700 && this.iX < 710 && this.iY < 110 && this.iY < 400) {
                        iDireccion = 2;     // se cambia direccion para abajo
                    } else if (this.iX > 700 && this.iX < 710) {
                        iDireccion = 2; //se cambia la direccion para abajo
                    }
                    break;
                }
            }
        }
    }
    public int getDireccion() {
        return iDireccion;
    }
    
    public void freeze() {
        bFrozen = true;
    }
    
    public void unfreeze() {
        bFrozen = false;
    }
    
    public boolean isFrozen() {
        return bFrozen;
    }
}
