
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
public class Quarintine extends Torre{
    private int iTimer;
    
 //   private URL urlImagenAura = this.getClass().getResource("aura.png");
    private URL urlSonidoAura = this.getClass().getResource("auraFreezeSound.wav");
    
    private Clip adcAura;
    
    protected Aura aurFreeze;
    
    public Quarintine() {}
    
    public Quarintine(int x, int y, int alto, int ancho, Image imagen) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        iX = x;
        iY = y;
        iAncho = ancho;
        iAlto = alto;
        imaImagen = imagen;
        iTimer = 0;
        
        
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(urlSonidoAura);
        adcAura = AudioSystem.getClip();
        adcAura.open(inputStream);

        creaAura();

        bPurchase = true;
        
    }
    
    public void act() {
        iTimer++;
        if (iTimer == 150) {
            adcAura.setFramePosition(0);
            adcAura.start();
        }
        if (iTimer > 150) {
            aurFreeze.setX(iX- 130);
            aurFreeze.setY(iY - 130);
            aurFreeze.setAncho(310);
            aurFreeze.setAlto(310);
        }
        if (iTimer > 240) {
            aurFreeze.setX(iX);
            aurFreeze.setY(iY);
            aurFreeze.setAncho(0);
            aurFreeze.setAlto(0);
            iTimer = 0;
        }
    }
    
    public void creaAura()
    {
        aurFreeze = new Aura(iX, iY, 0, 0, null);
 //       Toolkit.getDefaultToolkit().getImage(urlImagenAura));
    }
     
    public void Released() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        super.Released();
        creaAura();
    }

     public class Aura extends Base {
 
         
         public Aura(int x, int y, int alto, int ancho, Image imagen) {
             super(x, y, alto, ancho, imagen);

         }
     }
}
