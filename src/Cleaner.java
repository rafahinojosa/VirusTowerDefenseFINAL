
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Bifi
 */
public class Cleaner extends Torre{
    private int iTimer;         //dice el tiempo que pasa entre un disparo y otro
    
    //Imagenes del proyectil (TEMP)
    private URL urlImagenProy = this.getClass().getResource("balaTorreCleaner.png");
    
    //los proyectiles de la torre cleaner se declaran
    protected LinkedList<Proyectil> prySpray;
    
    public Cleaner() {}
    
    /**Contructor con parametros
     * 
     * @param x de tipo <int> da la posicion X
     * @param y de tipo <int> da la posicion Y
     * @param alto de tipo <int> da la altura
     * @param ancho de tipo <int> da el ancho
     * @param imagen de tipo <Image> defina la imagen
     */
    public Cleaner(int x, int y, int alto, int ancho, Image imagen) {
        iX = x;
        iY = y;
        iAncho = ancho;
        iAlto = alto;
        imaImagen = imagen;
        iTimer = 0;

        prySpray = new LinkedList();
        bPurchase = true;
    }

    public void Released() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        super.Released();
        creaSpray();
    }
    
    /**Metodo creaSpray que crea una lista con 8 proyectiles
     * 
     */
    public void creaSpray() {
        for (int iI = 0; iI < 8 ; iI++) {
            Proyectil pryTemp = new Proyectil(iX + iAncho / 2 - 10, iY + iAlto / 2 - 10, 20, 20, iI,
                    Toolkit.getDefaultToolkit().getImage(urlImagenProy));

            prySpray.add(pryTemp);
                    
        }
        
    }
    
    public class Proyectil extends Base {
        int iTorX;
        int iTorY;
        int iSpeedX;
        int iSpeedY;
        int iTimer;
        int iDireccion;
        /* Dice la direccion donde:
        *         0 1 2
        *          \|/
        *         3-C-4
        *          /|\
        *         5 6 7
        */
        
        public Proyectil() {}
        
        /** constructor con parametros
         * 
         * @param x
         * @param y
         * @param ancho
         * @param alto
         * @param dir
         * @param ima 
         */
        
        public Proyectil(int x, int y, int ancho, int alto, int dir, Image ima) {
            iX = x;
            iY = y;
            iTorX = x;
            iTorY = y;
            iAncho = ancho;
            iAlto = alto;
            iTimer = 0;
            imaImagen = ima;
            bolVisible = true;
            iDireccion = dir;

            
            switch(iDireccion) {
                case 0:
                    iSpeedX = -2;
                    iSpeedY = -2;
                    break;
                case 1:
                    iSpeedX = 0;
                    iSpeedY = -3;
                    break;
                case 2:
                    iSpeedX = 2;
                    iSpeedY = -2;
                    break;
                case 3:
                    iSpeedX = -3;
                    iSpeedY = 0;
                    break;
                case 4:
                    iSpeedX = 3;
                    iSpeedY = 0;
                    break;
                case 5:
                    iSpeedX = -2;
                    iSpeedY = 2;
                    break;
                case 6:
                    iSpeedX = 0;
                    iSpeedY = 3;
                    break;
                case 7:
                    iSpeedX = 2;
                    iSpeedY = 2;
                    break;
            }
        }
        
        /**Metodo Reset que reinicia los valores relevantes del proyectil
         * 
         */
        public void Reset() {
            iX = iTorX;
            iY = iTorY;
            iTimer = 0;
            setVisible(true);
        }
        
        /**Metodo act usado en la clase principal para actualizar el proyectil
         * 
         */
        public void act() {
            iTimer++;
            if (iTimer < 30) {
                iX += iSpeedX;
                iY += iSpeedY;
            }
            else if (iTimer > 30 && iTimer < 80) 
                die();
            else if (iTimer > 120) {
                Reset();
            }
            
        }
        
        public int getTimer() {
            return iTimer;
        }
    }
}
