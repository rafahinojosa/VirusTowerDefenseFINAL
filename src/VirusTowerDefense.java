/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import static javax.sound.sampled.Clip.LOOP_CONTINUOUSLY;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 *
 * @author Milky Games
 */
public class VirusTowerDefense extends JFrame implements Runnable, KeyListener,
        MouseListener, MouseMotionListener {
    
    // definir todas las variables del juego
    private Base basMenu;       //variable para el menu;
    private Base basBotonMenu;      //variable para el boton
    private Base basBasica;
    private Base basFirewall;
    private Base basQuarintine;
    private Base basScanner;
    private Base basCleaner;
    private Base basSuper;
    private Base basBotonJugar;
    private Base basBotonInstr;
    private Base basBotonCreditos;
    private Base basBotonAtras;
    private Base basBotonAdelante;
    private Base basBotonlvlStart;
    //Variables para la informacion del precio de las torres
    private Base basInfoBasica;
    private Base basInfoCleaner;
    private Base basInfoSuper;
    private Base basInfoQuarintine;
    private Base basInfoScanner;
    private Base basGameOver;
    
    private LinkedList<Basica> lklBasica; //lista de torres que se van creando
    private LinkedList<Quarintine> lklQuarintine;
    private LinkedList<Scanner> lklScanner;
    private LinkedList<Cleaner> lklCleaner;
    private LinkedList<Super> lklSuper;
    private LinkedList<Malo> lklMalos;  // lista de enemigos
    private LinkedList<Base> lklCamino;       //dice en donde esta el camino
    private LinkedList<Clip> lklMaloDie;

    private Image imaLogo;          //imagen del logo
    private Image imaPantallaInicio;      //primer pantalla
    private Image imaPantallaPuntos;
    private Image imaPantallaAcerca;
    private Image imaPnatallaInstr;
    private Image imaPnatallaInstr2;
    private Image imaPnatallaInstr3;
    private Image imaCreditos;
    private Image imaPuntos;
    private Image imaInst;
    private Image imaAtras;
    private Image imaAdelante;
    
    private Clip adcBGMusic;

    private int iPosX;          // variable usada para almacenar pos en x
    private int iPosY;          // variable usada para almacenar pos en Y
    private int iDeltaX;        // variable usada para almacenar cambios en x
    private int iDeltaY;        // variable usada para almacenar cambios en y
    private int iPuntos;
    private int iVidas;
    private int iLevel;
    private int iLevel30;       //dice cuantas veces se repite el nivel 30
    public static int DELAY = 27;  // es el delay que mide el tiempo
    
    private boolean bGameOver;
    private boolean bBasicaClick;     // variable que dice si esta presionado el mouse
    private boolean bFirewallClick;
    private boolean bQuarintineClick;
    private boolean bScannerClick;
    private boolean bCleanerClick;
    private boolean bSuperClick;
    private boolean bBotonMenuClick;        // booleana que dice si se presiona el boton
    private boolean bPause;             // booelana que dice si esta en pausa el juego
    private boolean bMusicOn;             // dice si el sonido esta encendido
    private boolean bRange;            // dice si un enemigo esta en rango
    private boolean bPlay;              //dice si se inicion el nivel
    private boolean bInicio;
    private boolean bInGame;
    private boolean bLogo;
    private boolean bPuntos;
    private boolean bCreditos;
    private boolean bInstr;
    private boolean bInstr2;
    private boolean bInstr3;
    private boolean bClickStart;
    private boolean bClickJugar;
    private boolean bClickInstr;
    private boolean bClickAcerca;    private boolean bClickPuntos;
    private boolean bClickAtras;
    private boolean bClickAdelante;
    private boolean bDrop;              //dice si la pieza actual se puede soltar
    private boolean bTrojanDiscovered; //dice si se descurbrio un trojan
    private boolean bPreciobas;
    private boolean bPrecioCleaner;
    private boolean bPrecioScanner;
    private boolean bPrecioSuper;
    private boolean bPrecioQuarintine;
    
    private long tiempoActual; // guarda el tiempo del juego


    /* objetos para manejar el buffer del Applet y este no parpadee */
    private Image imaImagenApplet;   // Imagen a proyectar en Applet	
    private Graphics graGraficaApplet;  // Objeto grafico de la Imagen

    // imagen del objeto torre basica
    URL urlImagenBasica = this.getClass().getResource("basica.png");
    
    // imagen del objeto torre quarintine
    URL urlImagenQuarintine = this.getClass().getResource("quarintine.png");
    
    // imagen del objeto torre scanner
    URL urlImagenScanner = this.getClass().getResource("scannertemp.png");
    
    // imagen del objeto torre cleaner
    URL urlImagenCleaner = this.getClass().getResource("cleaner.png");
    
    // imagen del objeto torre super
    URL urlImagenSuper = this.getClass().getResource("super.png");

    // imagen del objeto MALO malware
    URL urlImagenMalware = this.getClass().getResource("malware.png");
    
    // imagen del objeto MALO Trojan
    URL urlImagenTrojan = this.getClass().getResource("trojan.png");
            
    // imagen del objeto MALO Polymorph
    URL urlImagenPoly = this.getClass().getResource("poly1.png");
    
    // imagen del objeto MALO Worm
    URL urlImagenWorm = this.getClass().getResource("worm.png");

    //Imagen para el "menu" 
    URL urlImagenMenu = this.getClass().getResource("PopUpMenu.png");
    
    //Imagen para el "boton del menu" 
    URL urlImagenBotonMenu = this.getClass().getResource("menuboton.png");
    
    URL urlLogo = this.getClass().getResource("logo.png");

    URL urlPantallaInicio = this.getClass().getResource("pantallas1.png");

    URL urlPantallaAcerca = this.getClass().getResource("pantallas3.png");
    
    URL urlPantallaInstr = this.getClass().getResource("instrucciones1.png");
    URL urlPantallaInstr2 = this.getClass().getResource("instrucciones2.png");
    URL urlPantallaInstr3 = this.getClass().getResource("instrucciones3.png");

    URL urlJugar = this.getClass().getResource("Jugar.png");
    
    URL urlPuntos = this.getClass().getResource("HighScore.png");
    
    URL urlInstr = this.getClass().getResource("Instrucciones.png");
    
    URL urlCreditos = this.getClass().getResource("AcercaDe.png");

    URL urlAtras = this.getClass().getResource("atras.png");
    
    URL urlAdelante = this.getClass().getResource("adelante.png");
    
    URL urlStart = this.getClass().getResource("Start.png");
    
    URL urlImagenPrecioBasica = this.getClass().getResource("PrecioBasica.png");
    
    URL urlImagenPrecioCleaner = this.getClass().getResource("PrecioCleaner.png");
    
    URL urlImagenPrecioSuper = this.getClass().getResource("PrecioSuper.png");
    
    URL urlImagenPrecioScanner = this.getClass().getResource("PrecioScanner.png");
    
    URL urlImagenPrecioQuarintine = this.getClass().getResource("PrecioQuarintine.png");
    
    URL urlImagenPantallaGameOver = this.getClass().getResource("GameOver.png");
    
    URL urlSonidoMaloDie1 = this.getClass().getResource("maloDie.wav");
    
    URL urlSonidoMaloDie2 = this.getClass().getResource("maloDie2.wav");
    
    URL urlSonidoMaloDie3 = this.getClass().getResource("maloDie3.wav");
    
    URL urlBGMusic = this.getClass().getResource("bgMusic.wav");
    //Imagen para el boton de sonido
    URL urlImagenSonidoBoton;
    
    public VirusTowerDefense() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        init();
        start();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /** Metodo init de la clase Applet 
     * 
     * Inicializa todo lo relevante para el juego
     */
    public void init() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // define el tamano del juego
        setSize(800, 600);
        
        iPuntos = 170;
        iVidas = 50;
        iLevel = 1;
        iLevel30 = 3;
        
        bPlay = false;
        bMusicOn = true;
        bLogo = true;
        bPuntos = false;
        bInicio = true;
        bCreditos = false;
        
        lklBasica = new LinkedList();
        lklCleaner = new LinkedList();
        lklScanner = new LinkedList();
        lklQuarintine = new LinkedList();
        lklSuper = new LinkedList();
        lklMalos = new LinkedList();
        lklCamino  = new LinkedList();
        lklMaloDie = new LinkedList();
        
        creaCamino();
        
        imaLogo = Toolkit.getDefaultToolkit().getImage(urlLogo);
        imaPantallaInicio = Toolkit.getDefaultToolkit().getImage(urlPantallaInicio);
        imaPantallaAcerca = Toolkit.getDefaultToolkit().getImage(urlPantallaAcerca);
        imaPnatallaInstr = Toolkit.getDefaultToolkit().getImage(urlPantallaInstr);
        imaPnatallaInstr2 = Toolkit.getDefaultToolkit().getImage(urlPantallaInstr2);
        imaPnatallaInstr3 = Toolkit.getDefaultToolkit().getImage(urlPantallaInstr3);
        imaCreditos = Toolkit.getDefaultToolkit().getImage(urlCreditos);
        imaPuntos = Toolkit.getDefaultToolkit().getImage(urlPuntos);
        imaInst = Toolkit.getDefaultToolkit().getImage(urlInstr);
        imaAtras = Toolkit.getDefaultToolkit().getImage(urlAtras);
        imaAdelante = Toolkit.getDefaultToolkit().getImage(urlAdelante);
        
        basBotonlvlStart = new Base(300, 400, 80, 180, Toolkit.getDefaultToolkit().getImage(urlStart));
        basBotonlvlStart.setVisible(false);
        basBotonJugar = new Base(300, 400, 80, 180, Toolkit.getDefaultToolkit().getImage(urlJugar));
        basBotonInstr = new Base(300, 500, 80, 180, Toolkit.getDefaultToolkit().getImage(urlInstr));
        basBotonCreditos = new Base(500, 400, 80, 180, Toolkit.getDefaultToolkit().getImage(urlCreditos));
        basBotonAtras = new Base(20, 60, 50, 50, Toolkit.getDefaultToolkit().getImage(urlAtras));         
        basBotonAdelante = new Base(730, 60, 50, 50, Toolkit.getDefaultToolkit().getImage(urlAdelante));
        basGameOver = new Base(0,0,getHeight(),getWidth(),Toolkit.getDefaultToolkit().getImage(urlImagenPantallaGameOver));
        
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(urlBGMusic);
        adcBGMusic = AudioSystem.getClip();
        adcBGMusic.open(inputStream);

        for(int iI = 0; iI < 10; iI ++) {
            Clip adcTemp;

            int iSelector = (int) (Math.random() * 3 + 1);

            switch (iSelector) {
                case 1:
                    inputStream = AudioSystem.getAudioInputStream(urlSonidoMaloDie1);
                    adcTemp = AudioSystem.getClip();
                    adcTemp.open(inputStream);
                    lklMaloDie.add(adcTemp);
                    break;
                case 2:
                    inputStream = AudioSystem.getAudioInputStream(urlSonidoMaloDie2);
                    adcTemp = AudioSystem.getClip();
                    adcTemp.open(inputStream);
                    lklMaloDie.add(adcTemp);
                    break;
                case 3:
                    inputStream = AudioSystem.getAudioInputStream(urlSonidoMaloDie3);
                    adcTemp = AudioSystem.getClip();
                    adcTemp.open(inputStream);
                    lklMaloDie.add(adcTemp);
                    break;
            }
        }
        // se crea la torre basica del menu
        basBasica = new Base(900, 900, 50, 60,
                Toolkit.getDefaultToolkit().getImage(urlImagenBasica));
        basBasica.setX(705);
        basBasica.setY(50);
        
        //se crea la informacion de la torre basica del menu
        basInfoBasica = new Base(500,500, 50, 150, 
                Toolkit.getDefaultToolkit().getImage(urlImagenPrecioBasica));
        basInfoBasica.setX(615);
        basInfoBasica.setY(80);
        basInfoBasica.setVisible(false);
        
        // se crea la torre cleaner del menu
        basCleaner = new Base(900, 900, 50, 70,
                Toolkit.getDefaultToolkit().getImage(urlImagenCleaner));
        basCleaner.setX(705);
        basCleaner.setY(155);
        
        //Se crea la informacion de la torre cleaner del menu
        basInfoCleaner = new Base(900, 900, 50, 150,
                Toolkit.getDefaultToolkit().getImage(urlImagenPrecioCleaner));
        basInfoCleaner.setX(715);
        basInfoCleaner.setY(80);
        basInfoCleaner.setVisible(false);
        
        // se crea la torre scanner del menu
        basScanner = new Base(900, 900, 50, 50,
                Toolkit.getDefaultToolkit().getImage(urlImagenScanner));
        basScanner.setX(705);
        basScanner.setY(270);
        
        //Se crea la informacion de la torre Scanner del menu
        basInfoScanner = new Base(900, 900, 50, 150,
                Toolkit.getDefaultToolkit().getImage(urlImagenPrecioScanner));
        basInfoScanner.setX(715);
        basInfoScanner.setY(80);
        basInfoScanner.setVisible(false);
        
        // se crea la torre quarintine del menu
        basQuarintine = new Base(900, 900, 60, 60,
                Toolkit.getDefaultToolkit().getImage(urlImagenQuarintine));
        basQuarintine.setX(705);
        basQuarintine.setY(385);
        
        //Se crea la informacion de la torre Quarintine del menu
        basInfoQuarintine = new Base(900, 900, 50, 150,
                Toolkit.getDefaultToolkit().getImage(urlImagenPrecioQuarintine));
        basInfoQuarintine.setX(715);
        basInfoQuarintine.setY(80);
        basInfoQuarintine.setVisible(false);
        
        // se crea la torre super del menu
        basSuper = new Base(900, 900, 60, 60,
                Toolkit.getDefaultToolkit().getImage(urlImagenSuper));
        basSuper.setX(705);
        basSuper.setY(500);
        
        //Se crea la informacion de la torre super del menu
        basInfoSuper = new Base(900, 900, 50, 150,
                Toolkit.getDefaultToolkit().getImage(urlImagenPrecioSuper));
        basInfoSuper.setX(715);
        basInfoSuper.setY(80);
        basInfoSuper.setVisible(false);
        
        nextLevel();
        
        
        // se crea el objeto para boton
        basBotonMenu = new Base(640, 200, 100, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenBotonMenu));
        
         // se crea el objeto para menu
        basMenu = new Base(670, 0, 600, 130,
                Toolkit.getDefaultToolkit().getImage(urlImagenMenu));
        
        menuAction();
        
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        
    }
    
    /**Metodo start de la clase Applet
     * 
     * Inicia el hilo del juego
     */
    public void start() {
        // Declaras un hilo
        Thread th = new Thread(this);
        // Empieza el hilo
        th.start();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // TODO code application logic here
        VirusTowerDefense juego = new VirusTowerDefense();
        juego.setVisible(true);
        juego.setResizable(false);
    }
    
    /**Metodo run de la clase Applet
     * 
     * Es el ciclo que corre el juego
     */
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        tiempoActual = System.currentTimeMillis();
        
       

        while (!bGameOver) {
            
          //  adcBGMusic.loop();

            if (!bPause) {
                repaint();
                try {
                    actualiza();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(VirusTowerDefense.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    checaColision();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(VirusTowerDefense.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            beforeTime = System.currentTimeMillis();
        }
    }
    
    /**Metodo actualiza
     * 
     * Es el metodo que actualiza las posiciones y estados de todos los objetos
     * del juego cada vez que se corre el ciclo de run
     * 
     */
    public void actualiza() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        
        if (iVidas <= 0) {
            bGameOver = true;
        }
            
            if (bClickInstr) {
                bPuntos = false;
                bInstr = true;
                bInicio = false;
                bCreditos = false;
                basBotonJugar.setVisible(false);
                basBotonInstr.setVisible(false);
                basBotonCreditos.setVisible(false);
            }
            if (bClickAcerca) {
                bPuntos = false;
                bInstr = false;
                bInicio = false;
                bCreditos = true;
                basBotonJugar.setVisible(false);
                basBotonInstr.setVisible(false);
                basBotonCreditos.setVisible(false);

            }
            
            if (bClickAtras) {
                bInicio = true;
                bPuntos = false; 
                bCreditos = false;
                bPuntos = false; 
                bInstr = false; 
                basBotonJugar.setVisible(true);
                basBotonInstr.setVisible(true);
                basBotonCreditos.setVisible(true);
            }
            if (bClickAdelante) {
                if (bInstr) {
                    bInstr = !bInstr;
                    bInstr2 = true;
                    bClickAdelante = false;
                }
                else if (bInstr2) {
                    bInstr2 = !bInstr2;
                    bInstr3 = true;
                }
            }
            
            if(bPreciobas) {
                basInfoBasica.setVisible(true);
                basInfoBasica.setX(iPosX-130);
                basInfoBasica.setY(iPosY);
            }
            else
                basInfoBasica.setVisible(false);
            if(bPrecioCleaner) {
                basInfoCleaner.setVisible(true);
                basInfoCleaner.setX(iPosX-130);
                basInfoCleaner.setY(iPosY);
            }
            else
                basInfoCleaner.setVisible(false);
            
            if(bPrecioScanner) {
                basInfoScanner.setVisible(true);
                basInfoScanner.setX(iPosX-130);
                basInfoScanner.setY(iPosY);
            }
            else
                basInfoScanner.setVisible(false);
            if(bPrecioQuarintine) {
                basInfoQuarintine.setVisible(true);
                basInfoQuarintine.setX(iPosX-130);
                basInfoQuarintine.setY(iPosY);
            }
            else
                basInfoQuarintine.setVisible(false);
            if(bPrecioSuper) {
                basInfoSuper.setVisible(true);
                basInfoSuper.setX(iPosX-130);
                basInfoSuper.setY(iPosY);
            }
            else
                basInfoSuper.setVisible(false);
        
        if (bBasicaClick) {
            if (iPuntos >= 100) {
                Basica bscTemp = new Basica(basBasica.getX(), basBasica.getY(),
                        30, 50, basBasica.getX(), basBasica.getY(),
                        Toolkit.getDefaultToolkit().getImage(urlImagenBasica));

                bscTemp.Pressed();
                menuAction();

                lklBasica.add(bscTemp);
                bBasicaClick = !bBasicaClick;
                iPuntos -= 100;
            }
        } 
        
        if (bCleanerClick) {
            if (iPuntos >= 150) {
                Cleaner clnTemp = new Cleaner(basCleaner.getX(), basCleaner.getY(),
                 40, 50, Toolkit.getDefaultToolkit().getImage(urlImagenCleaner));

                clnTemp.Pressed();
                menuAction();

                lklCleaner.add(clnTemp);
                bCleanerClick = !bCleanerClick;
                iPuntos -= 150;
            }
        }
        if (bScannerClick) {
            if (iPuntos >= 70) {
                Scanner scnTemp = new Scanner(basScanner.getX(), basScanner.getY(),
                        30, 30,
                        Toolkit.getDefaultToolkit().getImage(urlImagenScanner));

                scnTemp.Pressed();
                menuAction();

                lklScanner.add(scnTemp);
                bScannerClick = !bScannerClick;
                iPuntos -= 70;
            }
        } 
        if (bQuarintineClick) {
            if (iPuntos >= 120) {
                Quarintine qrnTemp = new Quarintine(basQuarintine.getX(), basQuarintine.getY(),
                        50, 50,
                        Toolkit.getDefaultToolkit().getImage(urlImagenQuarintine));

                qrnTemp.Pressed();
                menuAction();

                lklQuarintine.add(qrnTemp);
                bQuarintineClick = !bQuarintineClick;
                iPuntos -= 120;
            }
        } 
        if (bSuperClick) {
            if (iPuntos >= 500) {
                Super supTemp = new Super(basBasica.getX(), basBasica.getY(),
                        60, 60, basBasica.getX(), basBasica.getY(),
                        Toolkit.getDefaultToolkit().getImage(urlImagenSuper));

                supTemp.Pressed();
                menuAction();

                lklSuper.add(supTemp);
                bSuperClick = !bSuperClick;
                iPuntos -= 500;
            }
        } 
        
        for (Basica bscTemp : lklBasica) {
            if (bscTemp.isClick()) {
                bscTemp.setX(iPosX + iDeltaX);
                bscTemp.setY(iPosY + iDeltaY);
                if (!bscTemp.isPurchase()) {
                    bscTemp.pryBala.setITorX(iPosX + iDeltaX);
                    bscTemp.pryBala.setITorY(iPosY + iDeltaY);
                }
            }
        }
        
        for (Cleaner clnTemp : lklCleaner) {
            if (clnTemp.isClick()) {
                clnTemp.setX(iPosX + iDeltaX);
                clnTemp.setY(iPosY + iDeltaY);
            }
        }
        
        for (Scanner scnTemp : lklScanner) {
            if (scnTemp.isClick()) {
                scnTemp.setX(iPosX + iDeltaX);
                scnTemp.setY(iPosY + iDeltaY);
            }
        }
        
        for (Quarintine qrnTemp : lklQuarintine) {
            if (qrnTemp.isClick()) {
                qrnTemp.setX(iPosX + iDeltaX);
                qrnTemp.setY(iPosY + iDeltaY);
            }
        }
        
        for (Super supTemp : lklSuper) {
            if (supTemp.isClick()) {
                supTemp.setX(iPosX + iDeltaX);
                supTemp.setY(iPosY + iDeltaY);
            }
        }
        
        if (bClickJugar) {
            bInGame = true;          
            basBotonlvlStart.setX(getWidth() / 2 - basBotonJugar.getAncho() / 2);
            basBotonlvlStart.setY(getHeight() / 2 - basBotonJugar.getAlto() / 2);
            basBotonlvlStart.setVisible(true);
            basBotonJugar.setVisible(false);
            basBotonInstr.setVisible(false);
            basBotonCreditos.setVisible(false);
        }
        
        if (bClickStart) {
            basBotonlvlStart.setVisible(false);
            bPlay = true;
            bClickStart = !bClickStart;
            adcBGMusic.loop(LOOP_CONTINUOUSLY);
        }
        
        if (bPlay) {
            
            for (Malo malTemp : lklMalos) {
                bTrojanDiscovered = false;
                        malTemp.act();
                
                if(malTemp instanceof Trojan) {
                            Trojan trjTemp = (Trojan) malTemp;
                            for (Scanner scnTemp : lklScanner) {
                                if (distance(trjTemp, scnTemp) < 200) {
                                    bTrojanDiscovered = true;
                                }
                            }
                            if (bTrojanDiscovered) {
                                trjTemp.discovered();
                            }
                            else {
                                trjTemp.hidden();
                            }
                        }
            }

            for (Basica bscTemp : lklBasica) {
                bRange = false;
                Malo malNextEnemy = null;
                if (!bscTemp.isPurchase()) {
                    for (Malo malTemp : lklMalos) {
                        boolean bTarget = true;
                        if(malTemp instanceof Trojan) {
                            Trojan trjTemp = (Trojan) malTemp;
                            bTarget = trjTemp.isDiscover();
                        }
                        if (distance(malTemp, bscTemp) < 200 && !bRange 
                                && bTarget && malTemp.getY() > -30) {
                            bRange = true;
                            malNextEnemy = malTemp;
                        }
                    }   
                    if (malNextEnemy != null && bRange) {
                    bscTemp.pryBala.shoot(malNextEnemy.getX(), malNextEnemy.getY());
                    }
                    else if (bscTemp.pryBala.getTimer() != 0){
                        bscTemp.pryBala.act();
                    }
                }
            }

            for (Super supTemp : lklSuper) {
                bRange = false;
                Malo malNextEnemy = null;
                if (!supTemp.isPurchase()) {
                    for (Malo malTemp : lklMalos) {
                        boolean bTarget = true;
                        if(malTemp instanceof Trojan) {
                            Trojan trjTemp = (Trojan) malTemp;
                            bTarget = trjTemp.isDiscover();
                        }
                        if (distance(malTemp, supTemp) < 500 && !bRange 
                                && bTarget  && malTemp.getY() > -30) {
                            bRange = true;
                            malNextEnemy = malTemp;
                        }
                    }   
                    if (malNextEnemy != null && bRange) {
                    supTemp.shoot(malNextEnemy.getX(), malNextEnemy.getY());
                    }
                    supTemp.act();
                    
                }
            }
            
            for (Quarintine qrnTemp : lklQuarintine) {
                qrnTemp.act();
            }
            
            for (Cleaner clnTemp : lklCleaner) {
                bRange = false;
                if (!clnTemp.isPurchase()) {

                    for (Cleaner.Proyectil pryTemp : clnTemp.prySpray) {
                        for (Malo malTemp : lklMalos) {
                            boolean bTarget = true;
                            if (malTemp instanceof Trojan) {
                                Trojan trjTemp = (Trojan) malTemp;
                                bTarget = trjTemp.isDiscover();
                            }
                            if (distance(clnTemp, malTemp) < 150 && bTarget) {
                                bRange = true;
                            }
                        }
                        if (bRange || pryTemp.getTimer() != 0) {
                            pryTemp.act();
                        }
                    }
                }
            }
        } 
        if (bBotonMenuClick) {
            menuAction();
        }
        
        if (lklMalos.isEmpty()) {
            iLevel ++ ;
            nextLevel();
            adcBGMusic.stop();
            bPlay = false;
            basBotonlvlStart.setVisible(true);
        }
    }
    
    /**Metodo checaColision que se encarga de efectuar y revisar las colisiones
     * entre los objetos del juego
     * 
     */
    public void checaColision() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        
        Malo malTemp = null;
        
        for (Basica bscTemp : lklBasica) {
            for (int iI = 0; iI < lklMalos.size(); iI ++ ) {
                Malo malMalo = lklMalos.get(iI);
                if (malMalo.intersecta(bscTemp.pryBala) && bscTemp.pryBala.isVisible()) {
                    if (malMalo instanceof Polymorph) {
                        Polymorph plyTemp = (Polymorph) malMalo;
                        if (plyTemp.getState() == 0) {
                            bscTemp.pryBala.die();
                            malMalo.setDying(true);
                            iPuntos++;
                        }
                    } else if (malMalo instanceof Worm) {
                        Worm wrmTemp = (Worm) malMalo;
                        bscTemp.pryBala.die();
                        if (wrmTemp.getHit() <= 0) {
                            malMalo.setDying(true);
                        } else {
                            wrmTemp.hit();
                            iPuntos++;
                        }
                    } else {
                        bscTemp.pryBala.die();
                        malMalo.setDying(true);
                    }
                }
            }

        }   

        Super.Proyectil pryDead = null;
        for (Super supTemp : lklSuper) {
            for (int iI = 0; iI < lklMalos.size(); iI ++ ) {
                Malo malMalo = lklMalos.get(iI);
                for (Super.Proyectil pryTemp : supTemp.lklBala) {
                    if (malMalo.intersecta(pryTemp) && pryTemp.isVisible()) {
                        if (malMalo instanceof Polymorph) {
                            Polymorph plyTemp = (Polymorph) malMalo;
                            if (plyTemp.getState() == 2) {
                                pryDead = pryTemp;
                                malMalo.setDying(true);
                                iPuntos++;
                            }
                        } else if (malMalo instanceof Worm) {
                            Worm wrmTemp = (Worm) malMalo;
                            pryDead = pryTemp;
                            if (wrmTemp.getHit() <= 0) {
                                malMalo.setDying(true);
                            } else {
                                wrmTemp.hit();
                                iPuntos++;
                            }
                        } else {
                            pryDead = pryTemp;
                            malMalo.setDying(true);
                        }
                    }
                }
                }
            if (pryDead != null) {
                supTemp.lklBala.remove(pryDead);
            }
        }  
        
        for (Quarintine qrnTemp : lklQuarintine) {
            for (int iI = 0; iI < lklMalos.size();  iI ++) {
                if(lklMalos.get(iI).intersecta(qrnTemp.aurFreeze)) {
                    lklMalos.get(iI).freeze();
                } else {
                    lklMalos.get(iI).unfreeze();
                }
            }
        }
        
        for (Cleaner clnTemp : lklCleaner) {
            for (int iI = 0; iI < lklMalos.size(); iI++) {
                Malo malMalo = lklMalos.get(iI);
                for (Cleaner.Proyectil pryTemp : clnTemp.prySpray) {
                    if (malMalo.intersecta(pryTemp) && pryTemp.isVisible()) {
                        if (malMalo instanceof Polymorph) {
                            Polymorph plyTemp = (Polymorph) malMalo;
                            if (plyTemp.getState() == 1) {
                                pryTemp.die();
                                malMalo.setDying(true);
                                iPuntos++;
                            }
                        } else if (malMalo instanceof Worm) {
                        Worm wrmTemp = (Worm) malMalo;
                        pryTemp.die();
                        if (wrmTemp.getHit() <= 0) {
                            malMalo.setDying(true);
                        } else {
                            wrmTemp.hit();
                            iPuntos++;
                        }
                    } else {
                        pryTemp.die();
                        malMalo.setDying(true);
                    }
                    }
                }
                }
            
        }   
        
        removeMalo();
        
        for (int iI = 0; iI < lklMalos.size(); iI ++ ) {
            Malo malMalo = lklMalos.get(iI);
            if (malMalo.getY() > 600) {
                malTemp = malMalo;
                if (malTemp instanceof Worm) {
                    iVidas -= 3;
                }
                if (malTemp instanceof Trojan) {
                    iVidas -= 5;
                }
                if (malTemp instanceof Polymorph) {
                    iVidas -= 4;
                }
                lklMalos.remove(malTemp);
                iVidas -= 1;
            }
        }
    }
    
    /**Metodo menuAction que dice lo que pasa al oprimirse el boton del menu 
     * 
     */
    public void menuAction() {
        if (basBotonMenu.getX() == 640) {
            basBotonMenu.setX(775);
            basMenu.setX(800);
            basBasica.setVisible(false);
            basQuarintine.setVisible(false);
            basCleaner.setVisible(false);
            basScanner.setVisible(false);
            basSuper.setVisible(false);
            bBotonMenuClick = false;
        } else {
            basBotonMenu.setX(640);
            basMenu.setX(670);
            basBasica.setVisible(true);
            basQuarintine.setVisible(true);
            basCleaner.setVisible(true);
            basScanner.setVisible(true);
            basSuper.setVisible(true);
            bBotonMenuClick = false;
        }

    }
    
    /**Metodo distance que regresa la distancia entre dos objetos tipo Base 
     * 
     * @param a de tipo <Base> el objeto 1
     * @param b de tipo <Base> el objeto 2
     * @return 
     */
    public double distance(Base a, Base b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2)
                + Math.pow(a.getY() - b.getY(), 2));
    }
    
    /**
     * paint
     *
     * Metodo sobrescrito de la clase <code>Applet</code>, heredado de la clase
     * Container.<P>
     * En este metodo lo que hace es actualizar el contenedor y define cuando
     * usar ahora el paint1
     *
     * @param graGrafico es el <code>objeto grafico</code> usado para dibujar.
     *
     */
    public void paint(Graphics graGrafico) {
        // Inicializan el DoubleBuffer
        if (imaImagenApplet == null) {
            imaImagenApplet = createImage(this.getSize().width,
                    this.getSize().height);
            graGraficaApplet = imaImagenApplet.getGraphics();
        }

        // Actualiza la imagen de fondo.
        URL urlImagenFondo = this.getClass().getResource("path.png");
        Image imaImagenFondo = Toolkit.getDefaultToolkit().getImage(urlImagenFondo);
        graGraficaApplet.drawImage(imaImagenFondo, 0, 0, getWidth(), getHeight(), this);

        // Actualiza el Foreground.
        graGraficaApplet.setColor(getForeground());
        paint1(graGraficaApplet);

        // Dibuja la imagen actualizada
        graGrafico.drawImage(imaImagenApplet, 0, 0, this);
    }
    
    /**
     * paint1
     *
     * Metodo sobrescrito de la clase <code>Applet</code>, heredado de la clase
     * Container.<P>
     * En este metodo se dibuja la imagen con la posicion actualizada, ademas
     * que cuando la imagen es cargada te despliega una advertencia.
     *
     * @param graDibujo es el objeto de <code>Graphics</code> usado para
     * dibujar.
     *
     */
    public void paint1(Graphics graDibujo) {
        
        if (bLogo) {
            graDibujo.setColor(Color.green); 
            graDibujo.drawImage(imaLogo, 0, 0, getWidth(), getHeight(), this);
            graDibujo.drawString("Enter Para continuar... " , (getWidth()/2) - 10, 50);

        } else if (bInicio) {
           
            graDibujo.drawImage(imaPantallaInicio, 0, 0, getWidth(), getHeight(), this);
              
            if (basBotonJugar.isVisible()) {
                basBotonJugar.paint(graDibujo, this);
            }
            
            if (basBotonInstr.isVisible()) {
                basBotonInstr.paint(graDibujo, this);
            }
            if (basBotonCreditos.isVisible()) {
                basBotonCreditos.paint(graDibujo, this);
            }
            if (basBotonlvlStart.isVisible()) {
                basBotonlvlStart.paint(graDibujo, this);
            }


            
        }

        else if (bPuntos) {
            graDibujo.drawImage(imaPantallaPuntos, 0, 0, getWidth(), getHeight(), this);
            basBotonAtras.paint(graDibujo, this);

        }
        else if (bCreditos) {
            graDibujo.drawImage(imaPantallaAcerca, 0, 0, getWidth(), getHeight(), this);
            basBotonAtras.paint(graDibujo, this);

        }
        else if (bInstr) {
            bInicio=false;
            graDibujo.drawImage(imaPnatallaInstr, 0, 20, getWidth(), getHeight(), this);
            basBotonAtras.paint(graDibujo, this);
            basBotonAdelante.paint(graDibujo, this);

        }
        else if (bInstr2) {
            bInicio=false;
            graDibujo.drawImage(imaPnatallaInstr2, 0, 20, getWidth(), getHeight(), this);
            basBotonAtras.paint(graDibujo, this);
            basBotonAdelante.paint(graDibujo, this);

        }
        else if (bInstr3) {
            bInicio=false;
            graDibujo.drawImage(imaPnatallaInstr3, 0, 0, getWidth(), getHeight(), this);
            basBotonAtras.paint(graDibujo, this);

        }
        
        if (bInGame) {
          
                
        // si la imagen ya se cargo
        if (lklMalos != null && basBotonMenu != null) {
            URL urlImagenFondo = this.getClass().getResource("path.png");
            Image imaImagenFondo = Toolkit.getDefaultToolkit().getImage(urlImagenFondo);
                graGraficaApplet.drawImage(imaImagenFondo, 0, 0, getWidth(), getHeight(), this);
            
            for (Basica bscTemp : lklBasica) {
                bscTemp.paint(graDibujo, this);
                if (!bscTemp.isPurchase()) {
                    if (bscTemp.pryBala.isVisible()) {
                        bscTemp.pryBala.paint(graDibujo, this);
                    }
                }
            }
            for (Cleaner clnTemp : lklCleaner) {
                if (!clnTemp.isPurchase()) {
                    for (Cleaner.Proyectil pryTemp : clnTemp.prySpray) {
                        if (pryTemp.isVisible()) {
                            pryTemp.paint(graDibujo, this);
                        }
                    }
                }
                clnTemp.paint(graDibujo, this);
            }
            for (Scanner scnTemp : lklScanner) {
                scnTemp.paint(graDibujo, this);
            }
            for (Quarintine qrnTemp : lklQuarintine) {
                qrnTemp.paint(graDibujo, this);
                if(!qrnTemp.isPurchase()) {
                    if(qrnTemp.aurFreeze.isVisible()) {
                        qrnTemp.aurFreeze.paint(graDibujo, this);
                    }
                }
            }
            for (Super supTemp : lklSuper) {
                supTemp.paint(graDibujo, this);
                if (!supTemp.isPurchase()) {
                    for (int iI = 0; iI < supTemp.lklBala.size(); iI++) {
                        if (supTemp.lklBala.get(iI).isVisible()) {
                            supTemp.lklBala.get(iI).paint(graDibujo, this);
                        
                        }
                    }
                }
            }
        
            
            for (int iI = 0; iI < lklMalos.size() ; iI ++) {
                lklMalos.get(iI).paint(graDibujo, this);
            }
                

            
            basMenu.paint(graDibujo, this);
            basBotonMenu.paint(graDibujo, this);
            if(basBasica.isVisible()) {
                basBasica.paint(graDibujo, this);
            }
            if(basCleaner.isVisible()) {
                basCleaner.paint(graDibujo, this);
            }
            if(basScanner.isVisible()) {
                basScanner.paint(graDibujo, this);
            }
            if(basSuper.isVisible()) {
                basSuper.paint(graDibujo, this);
            }
            if(basQuarintine.isVisible()) {
                basQuarintine.paint(graDibujo, this);
            }
            if(basInfoBasica.isVisible()) {
                 basInfoBasica.paint(graDibujo, this);
            }           
            if(basInfoCleaner.isVisible()) {
                 basInfoCleaner.paint(graDibujo, this);
            }
            if(basInfoScanner.isVisible()) {
                 basInfoScanner.paint(graDibujo, this);
            }
            if(basInfoSuper.isVisible()) {
                 basInfoSuper.paint(graDibujo, this);
            }
            if(basInfoQuarintine.isVisible()) {
                 basInfoQuarintine.paint(graDibujo, this);
            }            
            
            Font stringFont = new Font("Comic Sans MS", Font.PLAIN, 18);
            graDibujo.setFont(stringFont);
            graDibujo.setColor(Color.cyan); 
            graDibujo.drawString("Puntos: " + iPuntos, 195, 580);
            graDibujo.drawString("Vidas: " + iVidas, 95, 580);            
            graDibujo.drawString("Level: " + iLevel, 15, 580);
            
            
            if (basBotonlvlStart.isVisible()) {
                basBotonlvlStart.paint(graDibujo, this);
            }
        }
    }
        if (bGameOver) {
                basGameOver.paint(graDibujo, this);
            }
    }
    
    @Override
    public void keyTyped(KeyEvent keyEvent) {
      
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
       if (keyEvent.getKeyCode() == KeyEvent.VK_P) {
            bPause = !bPause;
        }
       
       if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE) {
           if (basBotonlvlStart.isVisible()) {
            bPlay = true;
            bClickStart = true;
           }
        }
       if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            bLogo = false;
        }
       
       if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            if (DELAY > 6) {
                DELAY -= 6;
            }
       }
       if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            if (DELAY < 27) {
                DELAY += 6;
            }
       }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        
    }

    @Override
    public void mouseClicked(MouseEvent mseEvent) {
  
    }

    @Override
    public void mousePressed(MouseEvent mseEvent) {
        iPosX = mseEvent.getX();
        iPosY = mseEvent.getY();
        if (basBotonJugar.isVisible()) {
            bClickJugar = basBotonJugar.intersecta(iPosX, iPosY);
        }

        if (basBotonInstr.isVisible()) {
            bClickInstr = basBotonInstr.intersecta(iPosX, iPosY);
        }
        if (basBotonCreditos.isVisible()) {
            bClickAcerca = basBotonCreditos.intersecta(iPosX, iPosY);
        }
        if (basBotonAtras.isVisible()) {
            bClickAtras = basBotonAtras.intersecta(iPosX, iPosY);
        }
        if (basBotonAdelante.isVisible()) {
            bClickAdelante = basBotonAdelante.intersecta(iPosX, iPosY);
        }
        if (basBotonlvlStart.isVisible()) {
            bClickStart = basBotonlvlStart.intersecta(iPosX, iPosY);
        }
        


        if (bBasicaClick) {
            bBasicaClick = false;
        }
        for (Basica bscTemp : lklBasica) {
            if (bscTemp.isClick()) {
                if (isClear(bscTemp)) {
                    try {
                        bscTemp.Released();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        Logger.getLogger(VirusTowerDefense.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        if (bCleanerClick) {
            bCleanerClick = false;
        }
        for (Cleaner clnTemp : lklCleaner) {
            if (clnTemp.isClick()) {
                if (isClear(clnTemp)) {
                    try {
                        clnTemp.Released();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        Logger.getLogger(VirusTowerDefense.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        if (bScannerClick) {
            bScannerClick = false;
        }
        for (Scanner scnTemp : lklScanner) {
            if (scnTemp.isClick()) {
                if (isClear(scnTemp)) {
                    try {
                        scnTemp.Released();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        Logger.getLogger(VirusTowerDefense.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        if (bQuarintineClick) {
            bQuarintineClick = false;
        }
        for (Quarintine qrnTemp : lklQuarintine) {
            if (qrnTemp.isClick()) {
                if (isClear(qrnTemp)) {
                    try {
                        qrnTemp.Released();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        Logger.getLogger(VirusTowerDefense.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        if (bSuperClick) {
            bSuperClick = false;
        }
        for (Super supTemp : lklSuper) {
            if (supTemp.isClick()) {
                if (isClear(supTemp)) {
                    try {
                        supTemp.Released();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        Logger.getLogger(VirusTowerDefense.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        if (bBotonMenuClick) {
            bBotonMenuClick = false;
        }
    }


    @Override
    public void mouseReleased(MouseEvent mseEvent) {

        iPosX = mseEvent.getX();
        iPosY = mseEvent.getY();
        if (!bPause) {
            if (basBasica.isVisible()) {
                bBasicaClick = basBasica.intersecta(iPosX, iPosY);
                if (bBasicaClick) {
                    iDeltaX = basBasica.getX() - iPosX;
                    iDeltaY = basBasica.getY() - iPosY;
                }
            }
            if (basCleaner.isVisible()) {
                bCleanerClick = basCleaner.intersecta(iPosX, iPosY);
                if (bCleanerClick) {
                    iDeltaX = basCleaner.getX() - iPosX;
                    iDeltaY = basCleaner.getY() - iPosY;
                }
            }
            if (basScanner.isVisible()) {
                bScannerClick = basScanner.intersecta(iPosX, iPosY);
                if (bScannerClick) {
                    iDeltaX = basScanner.getX() - iPosX;
                    iDeltaY = basScanner.getY() - iPosY;
                }
            }
            if (basQuarintine.isVisible()) {
                bQuarintineClick = basQuarintine.intersecta(iPosX, iPosY);
                if (bQuarintineClick) {
                    iDeltaX = basQuarintine.getX() - iPosX;
                    iDeltaY = basQuarintine.getY() - iPosY;
                }
            }
            if (basSuper.isVisible()) {
                bSuperClick = basSuper.intersecta(iPosX, iPosY);
                if (bSuperClick) {
                    iDeltaX = basSuper.getX() - iPosX;
                    iDeltaY = basSuper.getY() - iPosY;
                }
            }
            for (Basica bscTemp : lklBasica) {
                if (bscTemp.isPurchase()) {
                    if (bscTemp.intersecta(iPosX, iPosY)) {
                        bscTemp.Pressed();
                        iDeltaX = bscTemp.getX() - iPosX;
                        iDeltaY = bscTemp.getY() - iPosY;
                    }
                }
                
            }
            for (Cleaner clnTemp : lklCleaner) {
                if (clnTemp.isPurchase()) {
                    if (clnTemp.intersecta(iPosX, iPosY)) {
                        clnTemp.Pressed();
                        iDeltaX = clnTemp.getX() - iPosX;
                        iDeltaY = clnTemp.getY() - iPosY;
                    }
                }
                
            }
            for (Scanner scnTemp : lklScanner) {
                if (scnTemp.isPurchase()) {
                    if (scnTemp.intersecta(iPosX, iPosY)) {
                        scnTemp.Pressed();
                        iDeltaX = scnTemp.getX() - iPosX;
                        iDeltaY = scnTemp.getY() - iPosY;
                    }
                }
                
            }
            for (Quarintine qrnTemp : lklQuarintine) {
                if (qrnTemp.isPurchase()) {
                    if (qrnTemp.intersecta(iPosX, iPosY)) {
                        qrnTemp.Pressed();
                        iDeltaX = qrnTemp.getX() - iPosX;
                        iDeltaY = qrnTemp.getY() - iPosY;
                    }
                }
                
            }
            for (Super supTemp : lklSuper) {
                if (supTemp.isPurchase()) {
                    if (supTemp.intersecta(iPosX, iPosY)) {
                        supTemp.Pressed();
                        iDeltaX = supTemp.getX() - iPosX;
                        iDeltaY = supTemp.getY() - iPosY;
                    }
                }
                
            }
            bBotonMenuClick = basBotonMenu.intersecta(iPosX, iPosY);
        }
        
        bClickJugar = false;

        bClickPuntos = false;

        bClickInstr = false;

        bClickAcerca = false;

        bClickAtras = false;

        bClickStart = false;
    }

    @Override
    public void mouseEntered(MouseEvent mseEvent) {
    
    }

    @Override
    public void mouseExited(MouseEvent mseEvent) {
     
    }

    @Override
    public void mouseDragged(MouseEvent mseEvent) {
        iPosX = mseEvent.getX();
        iPosY = mseEvent.getY();
    }

    @Override
    public void mouseMoved(MouseEvent mseEvent) {
        iPosX = mseEvent.getX();
        iPosY = mseEvent.getY();
   
        if (basBasica.isVisible()) {
            if (basBasica.intersecta(iPosX, iPosY)) {
                bPreciobas = true;
            } else {
                bPreciobas = false;
            }
        } else {
            bPreciobas = false;
        }
        if (basCleaner.isVisible()) {
            if (basCleaner.intersecta(iPosX, iPosY)) {
                bPrecioCleaner = true;
            } else {
                bPrecioCleaner = false;
            }
        } else {
            bPrecioCleaner = false;
        }
        if (basSuper.isVisible()) {
            if (basSuper.intersecta(iPosX, iPosY)) {
                bPrecioSuper = true;
            } else {
                bPrecioSuper = false;
            }
        } else {
            bPrecioSuper = false;
        }
        if (basScanner.isVisible()) {
            if (basScanner.intersecta(iPosX, iPosY)) {
                bPrecioScanner = true;
            } else {
                bPrecioScanner = false;
            }
        } else {
            bPrecioScanner = false;
        }
        if (basQuarintine.isVisible()) {
            if (basQuarintine.intersecta(iPosX, iPosY)) {
                bPrecioQuarintine = true;
            } else {
                bPrecioQuarintine = false;
            }
        } else {
            bPrecioQuarintine = false;
        }
    }

    public void creaCamino() {
        Base basTemp = new Base(87, 0, 237, 50, null);
        lklCamino.add(basTemp);
        
        basTemp = new Base(136, 187, 50, 53, null);
        lklCamino.add(basTemp);
        
        basTemp = new Base(190, 80, 157, 50, null);
        lklCamino.add(basTemp);
        
        basTemp = new Base(190, 80, 50, 549, null);
        lklCamino.add(basTemp);
        
        basTemp = new Base(690, 130, 157, 50, null);
        lklCamino.add(basTemp);
        
        basTemp = new Base(547, 238, 50, 190, null);
        lklCamino.add(basTemp);
        
        basTemp = new Base(547, 238, 122, 50 , null);
        lklCamino.add(basTemp);
        
        basTemp = new Base(596, 312, 50, 141, null);
        lklCamino.add(basTemp);
        
        basTemp = new Base(688, 361, 77, 50, null);
        lklCamino.add(basTemp);
        
        basTemp = new Base(487, 389, 50, 250, null);
        lklCamino.add(basTemp);
        
        basTemp = new Base(438, 300, 138, 50, null);
        lklCamino.add(basTemp); 
        
        basTemp = new Base(287, 250, 50, 200, null);
        lklCamino.add(basTemp);
        
        basTemp = new Base(287, 250, 187, 50, null);
        lklCamino.add(basTemp);
        
        basTemp = new Base(84, 388, 50, 252, null);
        lklCamino.add(basTemp);
        
        basTemp = new Base(84, 388, 149, 50, null);
        lklCamino.add(basTemp);
        
        basTemp = new Base(130, 488, 50, 390, null);
        lklCamino.add(basTemp);
        
        basTemp = new Base(696, 537, 70, 50, null);
        lklCamino.add(basTemp);
    }
    
    public boolean overlap(Base b1, LinkedList l1) {
        boolean bOverlap = false;
        for (Object o1 : l1)
        {
            if (o1 instanceof Base) {
                Base bTemp = (Base) o1;
                if (b1.intersecta(bTemp) && b1 != bTemp) {
                    bOverlap = true;
                }
            }
        }
        return bOverlap;
    }
    
    public boolean isClear(Base b1) {
        return (!overlap(b1, lklBasica) 
                && !overlap(b1, lklCleaner)
                && !overlap(b1, lklScanner)
                && !overlap(b1, lklQuarintine)
                && !overlap(b1, lklSuper)
                && !overlap(b1, lklCamino));
            
    }
    
    

    public void removeMalo() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        LinkedList<Malo> clone = (LinkedList<Malo>) lklMalos.clone();
        for (Malo malTemp : lklMalos) {

            if (malTemp.isDying()) {
                iPuntos ++ ;
                                    
                playMaloDie();
                clone.remove(malTemp);
            }
        }
        lklMalos = (LinkedList<Malo>) clone.clone();
    }


    
    public void playMaloDie() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Clip adcTemp = lklMaloDie.remove();
        adcTemp.start();
        AudioInputStream inputStream;
        
        int iSelector = (int) (Math.random() * 3 + 1); 
        
        switch (iSelector) {
                case 1:
                    inputStream = AudioSystem.getAudioInputStream(urlSonidoMaloDie1);
                    adcTemp = AudioSystem.getClip();
                    adcTemp.open(inputStream);
                    lklMaloDie.add(adcTemp);
                    break;
                case 2:
                    inputStream = AudioSystem.getAudioInputStream(urlSonidoMaloDie2);
                    adcTemp = AudioSystem.getClip();
                    adcTemp.open(inputStream);
                    lklMaloDie.add(adcTemp);
                    break;
                case 3:
                    inputStream = AudioSystem.getAudioInputStream(urlSonidoMaloDie3);
                    adcTemp = AudioSystem.getClip();
                    adcTemp.open(inputStream);
                    lklMaloDie.add(adcTemp);
                    break;
            }
    }

    
    public void nextLevel() {
        switch(iLevel) {
            case 1:
                level1();
                break;
            case 2:
                level2();
                break;
            case 3:
                level3();
                break;
            case 4:
                level4();
                break;
            case 5:
                level5();
                break;
            case 6:
                level6();
                break;
            case 7:
                level7();
                break;
            case 8:
                level8();
                break;
            case 9:
                level9();
                break;
            case 10:
                level10();
                break;
            case 11:
                level11();
                break;
            case 12:
                level12();
                break;
            case 13:
                level13();
                break;
            case 14:
                level14();
                break;
            case 15:
                level15();
                break;
            case 16:
                level16();
                break;
            case 17:
                level17();
                break;
            case 18:
                level18();
                break;
            case 19:
                level19();
                break;
            case 20:
                level20();
                break;
            case 21:
                level21();
                break;
            case 22:
                level22();
                break;
            case 23:
                level23();
                break;
            case 24:
                level24();
                break;
            case 25:
                level25();
                break;
            case 26:
                level26();
                break;
            case 27:
                level27();
                break;
            case 28:
                level28();
                break;
            case 29:
                level29();
                break;
            case 30:
                level30();
                break;
            case 31:
                level30();
                iLevel = 30;
                iLevel30 ++;
                break;
        }
    }
    
    public void level1() {
        
        for (int iI = 0; iI < 10; iI++) {
            Malware malTemp = new Malware (100, iI*-80, 30, 30, 1, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }

    }
    
    public void level2() {
          
        for (int iI = 0; iI < 10; iI++) {
            Malware malTemp = new Malware (100, iI*-40, 30, 30, 1, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
    
    }
    
    public void level3() {
          
        for (int iI = 0; iI < 20; iI++) {
            Malware malTemp = new Malware (100, iI*-50, 30, 30, 1, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
    
    }
    
    public void level4() {
         
        for (int iI = 0; iI < 20; iI++) {
            Malware malTemp = new Malware (100, iI*-80, 30, 30, 2, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
           
    }
    
    public void level5() {
        
        for (int iI = 0; iI < 40; iI++) {
            Malware malTemp = new Malware (100, iI*-60, 30, 30, 2, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
  
    }
    
    public void level6() {
          
        for (int iI = 0; iI < 17; iI++) {
            Malware malTemp = new Malware (100, iI*-60, 30, 30, 3, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
 
    }
    
    public void level7() {
          
        for (int iI = 0; iI < 25; iI++) {
            Malware malTemp = new Malware (100, iI*-40, 30, 30, 2, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
         
        for (int iI = 0; iI < 5; iI++) {
            Malware malTemp = new Malware (100, -300 + iI*-40, 30, 30, 3, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
         
        for (int iI = 0; iI < 10; iI++) {
            Malware malTemp = new Malware (100, -300 + iI*-40, 30, 30, 1, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
         

    }
    
    public void level8() {
         
        for (int iI = 0; iI < 30; iI++) {
            Malware malTemp = new Malware (100, iI*-50, 30, 30, 3, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }

    }
    public void level9() {
         
   
        for (int iI = 0; iI < 2; iI++) {
            Worm wrmTemp = new Worm(100, iI*-100, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenWorm));
            
            lklMalos.add(wrmTemp);
        }
        
    }
    
    public void level10() {
         
  
        for (int iI = 0; iI < 5; iI++) {
            Worm wrmTemp = new Worm(100, iI*-90, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenWorm));
            
            lklMalos.add(wrmTemp);
        }
        
    }
    public void level11() {
        
        for (int iI = 0; iI < 20; iI++) {
            Malware malTemp = new Malware (100, iI*-80, 30, 30, 2, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }

        for (int iI = 0; iI < 5; iI++) {
            Worm wrmTemp = new Worm(100, -200+iI*-100, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenWorm));
            
            lklMalos.add(wrmTemp);
        }    
    }
    
    public void level12() {
          
        for (int iI = 0; iI < 60; iI++) {
            Malware malTemp = new Malware (100, iI*-60, 30, 30, 2, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
         
    
        for (int iI = 0; iI < 5; iI++) {
            Worm wrmTemp = new Worm(100, -1000+iI*-200, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenWorm));
            
            lklMalos.add(wrmTemp);
        }      
    }
    
    public void level13() {
          
        for (int iI = 0; iI < 40; iI++) {
            Malware malTemp = new Malware (100, iI*-20, 30, 30, 3, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
          
    }
    
    public void level14() {
         
        for (int iI = 0; iI < 30; iI++) {
            Malware malTemp = new Malware (100, iI*-40, 30, 30, 3, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
     
        for (int iI = 0; iI < 6; iI++) {
            Worm wrmTemp = new Worm(100, -1350+iI*-90, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenWorm));
            
            lklMalos.add(wrmTemp);
        }
        
    }
    
    public void level15() {
  
        for (int iI = 0; iI < 10; iI++) {
            Trojan troTemp = new Trojan (100, iI*-120, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenTrojan));
            
            lklMalos.add(troTemp);
        }
        
   
    }
    
    public void level16() {
          
   
        for (int iI = 0; iI < 10; iI++) {
            Trojan troTemp = new Trojan (100, iI*-40, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenTrojan));
            
            lklMalos.add(troTemp);
        }
        
        for (int iI = 0; iI < 7; iI++) {
            Worm wrmTemp = new Worm(100, iI*-80, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenWorm));
            
            lklMalos.add(wrmTemp);
        }
        
    }
    
    public void level17() {
          
        for (int iI = 0; iI < 50; iI++) {
            Malware malTemp = new Malware (100, -2000+iI*-40, 30, 30, 2, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
         
        for (int iI = 0; iI < 10; iI++) {
            Malware malTemp = new Malware (100, -4000 + iI*-40, 30, 30, 3, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
         
        for (int iI = 0; iI < 100; iI++) {
            Malware malTemp = new Malware (100, iI*-40, 30, 30, 1, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
         
  
    }
    
    public void level18() {
         
        for (int iI = 0; iI < 30; iI++) {
            Malware malTemp = new Malware (100, iI*-50, 30, 30, 3, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
         
   
        for (int iI = 0; iI < 40; iI++) {
            Trojan troTemp = new Trojan (100, iI*-40, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenTrojan));
            
            lklMalos.add(troTemp);
        }
   
    }
    public void level19() {
         
    
        for (int iI = 0; iI < 10; iI++) {
            Worm wrmTemp = new Worm(100, iI*-100, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenWorm));
            
            lklMalos.add(wrmTemp);
        }
        
    }
    
    public void level20() {
     
        for (int iI = 0; iI < 10; iI++) {
            Polymorph plyTemp = new Polymorph (100, iI*-40, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenPoly));
            
            lklMalos.add(plyTemp);
        }
     
    }
 
    public void level21() {
        
        for (int iI = 0; iI < 30; iI++) {
            Malware malTemp = new Malware (100, iI*-80, 30, 30, 2, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
         
        for (int iI = 0; iI < 5; iI++) {
            Polymorph plyTemp = new Polymorph (100, iI*-40, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenPoly));
            
            lklMalos.add(plyTemp);
        }
        
        for (int iI = 0; iI < 10; iI++) {
            Trojan troTemp = new Trojan (100, iI*-40, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenTrojan));
            
            lklMalos.add(troTemp);
        }
        
        for (int iI = 0; iI < 5; iI++) {
            Worm wrmTemp = new Worm(100, -200+iI*-100, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenWorm));
            
            lklMalos.add(wrmTemp);
        }       
    }
    
    public void level22() {
          
        for (int iI = 0; iI < 90; iI++) {
            Malware malTemp = new Malware (100, iI*-30, 30, 30, 3, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
         
        for (int iI = 0; iI < 10; iI++) {
            Polymorph plyTemp = new Polymorph (100, iI*-60, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenPoly));
            
            lklMalos.add(plyTemp);
        }
      
        for (int iI = 0; iI < 8; iI++) {
            Worm wrmTemp = new Worm(100, -1000+iI*-150, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenWorm));
            
            lklMalos.add(wrmTemp);
        }        
    }
    
    public void level23() {
          
        for (int iI = 0; iI < 50; iI++) {
            Malware malTemp = new Malware (100, iI*-20, 30, 30, 3, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
         
        for (int iI = 0; iI < 10; iI++) {
            Polymorph plyTemp = new Polymorph (100, iI*-40, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenPoly));
            
            lklMalos.add(plyTemp);
        }
        
        for (int iI = 0; iI < 50; iI++) {
            Trojan troTemp = new Trojan (100, iI*-70, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenTrojan));
            
            lklMalos.add(troTemp);
        }
      
    }
    
    public void level24() {
         
       
        for (int iI = 0; iI < 10; iI++) {
            Worm wrmTemp = new Worm(100, iI*-40, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenWorm));
            
            lklMalos.add(wrmTemp);
        }
        
    }
    
    public void level25() {
        
      
        for (int iI = 0; iI < 20; iI++) {
            Polymorph plyTemp = new Polymorph (100, iI*-60, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenPoly));
            
            lklMalos.add(plyTemp);
        }
       
    }
    
    public void level26() {
          
      
        for (int iI = 0; iI < 30; iI++) {
            Polymorph plyTemp = new Polymorph (100, iI*-50, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenPoly));
            
            lklMalos.add(plyTemp);
        }
        
    
    }
    
    public void level27() {
          
     
        for (int iI = 0; iI < 20; iI++) {
            Worm wrmTemp = new Worm(100, iI*-40, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenWorm));
            
            lklMalos.add(wrmTemp);
        }
        
    }
    
    public void level28() {
         
        for (int iI = 0; iI < 200; iI++) {
            Malware malTemp = new Malware (100, iI*-20, 30, 30, 3, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
         
        for (int iI = 0; iI < 20; iI++) {
            Polymorph plyTemp = new Polymorph (100, -2000+iI*-50, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenPoly));
            
            lklMalos.add(plyTemp);
        }
        
       
    }
    public void level29() {
         
        for (int iI = 0; iI < 100; iI++) {
            Malware malTemp = new Malware (100, iI*-20, 30, 30, 3, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
         
        for (int iI = 0; iI < 10; iI++) {
            Polymorph plyTemp = new Polymorph (100, -2000+iI*-100, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenPoly));
            
            lklMalos.add(plyTemp);
        }
        
        for (int iI = 0; iI < 100; iI++) {
            Trojan troTemp = new Trojan (100, iI*-40, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenTrojan));
            
            lklMalos.add(troTemp);
        }
        
        for (int iI = 0; iI < 10; iI++) {
            Worm wrmTemp = new Worm(100, iI*-300, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenWorm));
            
            lklMalos.add(wrmTemp);
        }
        
    }
    
    public void level30() {
         
        for (int iI = 0; iI < 20 * iLevel30; iI++) {
            Malware malTemp = new Malware (100, iI*-40, 30, 30, 3, 2,
                Toolkit.getDefaultToolkit().getImage(urlImagenMalware));
            
            lklMalos.add(malTemp);
        }
         
        for (int iI = 0; iI < 5 * iLevel30; iI++) {
            Polymorph plyTemp = new Polymorph (100, iI*-50, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenPoly));
            
            lklMalos.add(plyTemp);
        }
        
        for (int iI = 0; iI < 10 * iLevel30; iI++) {
            Trojan troTemp = new Trojan (100, iI*-50, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenTrojan));
            
            lklMalos.add(troTemp);
        }
        
        for (int iI = 0; iI < 5 * iLevel30; iI++) {
            Worm wrmTemp = new Worm(100, iI*-100, 30, 30,
                Toolkit.getDefaultToolkit().getImage(urlImagenWorm));
            
            lklMalos.add(wrmTemp);
        }
        
    }
    
}
    
