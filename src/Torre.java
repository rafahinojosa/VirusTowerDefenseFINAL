
import java.io.IOException;
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
public class Torre extends Base{
    protected boolean bClick;         //dice si el mouse esta oprimido sobre la torre
    protected boolean bPurchase;      //dice si la torre ha sido comprada
    
    private Clip adcSetTower;
    
    private URL urlAudioSetTower = this.getClass().getResource("TorreRelease.wav");
    
    /**Constructor vacío
     * 
     */
    public Torre() {}
    
    /**Metodo isPurchase que regresa el valor de bPurchase
     * 
     * @return <boolean> bPurchase
     */
    public boolean isPurchase() {
        return bPurchase;
    }
    
    /**Metodo isClick que regresa el valor de bClick
     * 
     * @return <boolean> bClick
     */
    public boolean isClick() {
        return bClick;
    }
    
    /**Metodo setPurchase que asigna un valor a bPurchase
     * 
     * @param bP tipo boolean que se asigna a bPurchase
     */
    public void setPurchase(boolean bP) {
        bPurchase = bP;
    }
    
    /**Metodo Pressed que dice si se ha seleccionado por el mouse
     * 
     */
    public void Pressed() {
        bClick = true;
    }
    
    /**Metodo Released que dice cuando se ha soltado la torre
     * 
     */
    public void Released() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        bClick = false;
        bPurchase = false;
        
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(urlAudioSetTower);
        adcSetTower = AudioSystem.getClip();
        adcSetTower.open(inputStream);
        
        adcSetTower.start();
    }
}
