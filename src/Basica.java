/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Milky Games
 */
public class Basica extends Torre {

    private int iMaloX;             //posicion en X del malo
    private int iMaloY;             //posicion en Y del malo
    
    //Imagenes del proyectil
    private URL urlImagenProy = this.getClass().getResource("balaTorreBasica.png");
    private URL urlSonidoBala = this.getClass().getResource("superShoot.wav");
    
    private Clip adcShoot;

    Proyectil pryBala;      //el proyectil de la torre basica

    /**Constructor de la clase Basica con parametros
     * 
     * @param x tipo <int> valor en x
     * @param y tipo <int> valor en y
     * @param alto tipo <int> el alto del objeto
     * @param ancho tipo <int> el ancho del objeto
     * @param malx tipo <int> la posicion inicial del enemigo en x
     * @param maly tipo <int> la posicion inicial del enemigo en y
     * @param imagen tipo <Image> la imagen del objeto
     */
    public Basica(int x, int y, int alto, int ancho, int malx, int maly, Image imagen) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        iX = x;
        iY = y;
        iAncho = ancho;
        iAlto = alto;
        imaImagen = imagen;
        iMaloX = malx;
        iMaloY = maly;
        
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(urlSonidoBala);
        adcShoot = AudioSystem.getClip();
        adcShoot.open(inputStream);

        bPurchase = true;
        creaProyectil();
    }

    /**Metodo creaProyectil que crea un objeto tipo Proyectil en pryBala
     * 
     */
    public void creaProyectil() {
        pryBala = new Proyectil(iX + iAncho / 2 - 15, iY - 10, 30, 30, iMaloX, iMaloY,
                Toolkit.getDefaultToolkit().getImage(urlImagenProy));
    }
    
    /**Metodo heredado de la clase padre Torre
     * crea el proyectil
     */
    public void Released() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        super.Released();
        pryBala.setX(iX + iAncho / 2 - 15);
        pryBala.setY(iY - 10);
        pryBala.setITorX(iX + iAncho / 2 - 15);
        pryBala.setITorY(iY - 10);
        
    }

    public class Proyectil extends Base {

        private int iMaloX;         //posicion en x del enemgio
        private int iMaloY;         //posicion en y del enemigo
        private int iTimer;         //timer de act
        private int iTorX;          //posicion en x de la torre que lo genero
        private int iTorY;          //posicion en y de la torre que lo genero
        double dMagnitud;           //magnitud de la distancia al enemigo
        double dUnitX;              //vector unitario x de magnitud
        double dUnitY;              //vector unitario y de magnitud
        boolean bDestroyed;         //booleana que dice si se detruye el obj
        
        

        /**Metodo creador con parametros de Proyectil
         * 
         * @param x de tipo <int> da la posicion X
         * @param y de tipo <int> da la posicion Y
         * @param alto de tipo <int> da la altura
         * @param ancho de tipo <int> da el ancho
         * @param imagen de tipo <Image> defina la imagen
         * @param malx de tipo <int> posicion en x del malo
         * @param maly de tipo <int> posicion en y del malo
         */
        public Proyectil(int x, int y, int alto, int ancho, int malx, int maly,
                Image imagen) {
            iX = x;
            iY = y;
            iAncho = ancho;
            iAlto = alto;
            imaImagen = imagen;
            bolVisible = true;
            
            iTorX = x;
            iTorY = y;
            iTimer = 0;
        }
        
        /**Metodo setITorX que asigna un valor a iTorX
         * 
         * @param x 
         */
         public void setITorX(int x) {
            iTorX = x;
        }

         /**Metodo setITorY que asigna un valor a iTorY
         * 
         * @param y
         */
        public void setITorY(int y) {
            iTorY = y;
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
         * @param malx de tipo <int> actualiza la posicion del enemigo en x
         * @param maly de tipo <int> actualiza la posicion del enemigo en y
         */
        public void act() {
            if (!(iX == iTorX && iY == iTorY)) {
                iX += (int) Math.round(7 * dUnitX);
                iY += (int) Math.round(7 * dUnitY);
                if (iTimer > 60) {
                    Reset();
                }
                iTimer++;
            
            }
        }
        
        /**Metodo shoot llamado por el metodo act que dice si se dispara
         * 
         * @param malx de tipo <int> actualiza la posicion del enemigo en x
         * @param maly de tipo <int> actualiza la posicion del enemigo en y
         */
        public void shoot(int malx, int maly) {      
            if (iX == iTorX && iY == iTorY) {
                iMaloX = malx;
                iMaloY = maly;
                adcShoot.setFramePosition(0);
                adcShoot.start();
              //  if (disToMalo() < 300) {
                    int vectorX = iMaloX - iX;
                    int vectorY = iMaloY - iY;
                    dMagnitud = Math.sqrt(vectorX * vectorX + vectorY * vectorY);
                    dUnitX = vectorX / dMagnitud;
                    dUnitY = vectorY / dMagnitud;
                    iX += (int) Math.round(7 * dUnitX);
                    iY += (int) Math.round(7 * dUnitY);
              //  }
            } 
            else {
                act();
            }            
        }

        
        /**Metodo disToMalo que regresa el valor de la distancia entre el Proyectil
         * y el enemigo seleccionado
         * 
         * @return <int> un valor de distancia 
         */
        public int disToMalo() {
            return (int) Math.sqrt(Math.pow(iX - iMaloX, 2) + Math.pow(iY - iMaloY, 2));
        }
        
        /**Metodo getTimer que da el valor del timer
         * 
         * @return <int> iTimer
         */
        public int getTimer() {
            return iTimer;
        }
        
        /**
         * Metodo crash que se efectua al colisionar con un enemigo
         */
        public void Crash() {
            bolVisible = false;
        }
    }
}

