/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Milky Games
 */
public class Super extends Torre {

    private int iMaloX;             //posicion en X del malo
    private int iMaloY;             //posicion en Y del malo
    private int iTimer;
    
    //Imagenes del proyectil
    private URL urlImagenProy = this.getClass().getResource("balaSuper.png");
    private URL urlSonidoBala = this.getClass().getResource("TorreBasicaShoot.wav");
    

    protected LinkedList<Proyectil> lklBala;      //proyectiles de la torre super
    protected LinkedList<Clip> lklShoot;

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
    public Super(int x, int y, int alto, int ancho, int malx, int maly, Image imagen) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        iX = x;
        iY = y;
        iAncho = ancho;
        iAlto = alto;
        imaImagen = imagen;
        iMaloX = malx;
        iMaloY = maly;
        iTimer = 0;

        
        lklBala = new LinkedList();
        lklShoot = new LinkedList();
        bPurchase = true;
        
        Clip adcTemp;
        
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(urlSonidoBala);
        adcTemp = AudioSystem.getClip();
        adcTemp.open(inputStream);
        
        
        for (int iI = 0; iI < 10; iI++) {
            lklShoot.add(adcTemp);
        }
    }

    /**Metodo creaProyectil que crea un objeto tipo Proyectil en pryBala
     * 
     */
    public void creaProyectil(int malx, int maly) {
        Proyectil pryBala = new Proyectil(iX + iAncho / 2 - 10,
                iY + iAlto / 2 - 10, 20, 20, malx, maly,
                Toolkit.getDefaultToolkit().getImage(urlImagenProy));
        
        lklBala.add(pryBala);
    }
    
    /**Metodo heredado de la clase padre Torre
     * crea el proyectil
     */
//    public void Released() {
//        super.Released();
//        creaProyectil();
//    }
    
    public void shoot(int malx, int maly) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (iTimer > 10) {
            creaProyectil(malx, maly);
            playShoot();
            iTimer = 0;
        }
    }
    
    public void act() {
        for (Proyectil pryBala : lklBala) {
            pryBala.act();
        }
        iTimer ++;
    }
    
    public void playShoot() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Clip adcTemp = lklShoot.remove();
        adcTemp.setFramePosition(0);
        adcTemp.start();
        
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(urlSonidoBala);
        adcTemp = AudioSystem.getClip();
        adcTemp.open(inputStream);
        lklShoot.add(adcTemp);
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
            iMaloX = malx;
            iMaloY = maly;
            int vectorX = iMaloX - iX;
            int vectorY = iMaloY - iY;
            dMagnitud = Math.sqrt(vectorX * vectorX + vectorY * vectorY);
            dUnitX = vectorX / dMagnitud;
            dUnitY = vectorY / dMagnitud;

            iTorX = x;
            iTorY = y;
            iTimer = 0;
        }
        
        /**Metodo setITorX que asigna un valor a iTorX
         * 
         * @param x 
         */
//         public void setITorX(int x) {
//            iTorX = x;
//        }
//
//         /**Metodo setITorY que asigna un valor a iTorY
//         * 
//         * @param y
//         */
//        public void setITorY(int y) {
//            iTorY = y;
//        }

        /**Metodo Reset que reinicia los valores relevantes del proyectil
         * 
         */
//        public void Reset() {
//            iX = iTorX;
//            iY = iTorY;
//            iTimer = 0;
//            setVisible(true);
//        }

        /**Metodo act usado en la clase principal para actualizar el proyectil
         * 
         * @param malx de tipo <int> actualiza la posicion del enemigo en x
         * @param maly de tipo <int> actualiza la posicion del enemigo en y
         */
        public void act() {
                iX += (int) Math.round(10 * dUnitX);
                iY += (int) Math.round(10 * dUnitY);

            }
        }
        
        /**Metodo shoot llamado por el metodo act que dice si se dispara
         * 
         * @param malx de tipo <int> actualiza la posicion del enemigo en x
         * @param maly de tipo <int> actualiza la posicion del enemigo en y
         */
//        public void shoot(int malx, int maly) {      
//            if (iX == iTorX && iY == iTorY) {
//                iMaloX = malx;
//                iMaloY = maly;
//              //  if (disToMalo() < 300) {
//                    int vectorX = iMaloX - iX;
//                    int vectorY = iMaloY - iY;
//                    dMagnitud = Math.sqrt(vectorX * vectorX + vectorY * vectorY);
//                    dUnitX = vectorX / dMagnitud;
//                    dUnitY = vectorY / dMagnitud;
//                    iX += (int) Math.round(5 * dUnitX);
//                    iY += (int) Math.round(5 * dUnitY);
//              //  }
//            } 
//            else {
//                act();
//            }            
//        }

        
        /**Metodo disToMalo que regresa el valor de la distancia entre el Proyectil
         * y el enemigo seleccionado
         * 
         * @return <int> un valor de distancia 
         */
//        public int disToMalo() {
//            return (int) Math.sqrt(Math.pow(iX - iMaloX, 2) + Math.pow(iY - iMaloY, 2));
//        }
        
        /**Metodo getTimer que da el valor del timer
         * 
         * @return <int> iTimer
         */
//        public int getTimer() {
//            return iTimer;
//        }
        
        /**
         * Metodo crash que se efectua al colisionar con un enemigo
         */
//        public void Crash() {
//            bolVisible = false;
//        }
    }


