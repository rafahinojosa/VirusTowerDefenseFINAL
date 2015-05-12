/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;



/**Clase Base
 * 
 * Clase usada para manejar objetos del juego
 *
 * @author Milky Games
 */
public class Base {
    
    protected int iAlto;              //la altura del objeto
    protected int iAncho;             //el ancho del objeto
    protected int iX;                 //la posicion en X del objeto
    protected int iY;                 //la posicion en Y del objeto
    protected int iDx;                //un diferencial en X del objeto
    protected int iDy;                //un diferencial en Y del objeto
    
    protected boolean bolVisible;      //dice si esta visible el objeto
    protected boolean bolDying;       //dice si esta "muriendo" el objeto
    
    protected Image imaImagen;        //la imagen del objeto
  
    /**Metodo que inicializa la clase Base
     *
     */
    public Base() {
    
    }
    
    /**Meteodo que inicializa la clase Base
     *
     * @param x de tipo <int> da la posicion X
     * @param y de tipo <int> da la posicion Y
     * @param alto de tipo <int> da la altura
     * @param ancho de tipo <int> da el ancho
     * @param imagen de tipo <Image> defina la imagen
     */
    public Base(int x, int y, int alto, int ancho, Image imagen) {
        iX = x;
        iY = y;
        iAncho = ancho;
        iAlto = alto;
        imaImagen = imagen;
        bolVisible = true;
    }
    
    /**Metodo para matar el objeto
     *
     */
    public void die() {
        bolVisible = false;
    }
    
    /**Metodo que regresa el valor <boolean> de bolVisible
     *
     * @return <boolean> bolVisible
     */
    public boolean isVisible() {
        return bolVisible;
    }
    
    /**Metodo que asigna un valor a bolVisible
     * 
     * @param v del tipo <boolean> se asigna a bolVisible
     */
    public void setVisible(boolean v) {
        bolVisible = v;
    }
    
    /**Metodo que asigna un valor a imaImagen
     * 
     * @param imagen del tipo <Image> se asigna a imaImagen
     */
    public void setImagen(Image imagen) {
        imaImagen = imagen;
    }
    
    /**Metodo que regresa la variable imaImagen
     * 
     * @return <Image> imaImagen
     */
    public Image getImagen() {
        return imaImagen;
    }
    
    /**Metodo que asigna un valor a iX
     * 
     * @param x del tipo <int> se asigna a iX
     */
    public void setX(int x) {
        iX = x;
    }

    /**Metodo que asigna un valor a iY
     * 
     * @param y del tipo <int> se asigna a iY
     */
    public void setY(int y) {
        iY = y;
    }
    
    /**Metodo que regresa el valor de iY
     * 
     * @return <int> iY
     */
    public int getY() {
        return iY;
    }

    /**Metodo que regresa el valor de iX
     * 
     * @return <int> iX
     */
    public int getX() {
        return iX;
    }

    /**Metodo que asigna un valor a iAncho
     * 
     * @param ancho del tipo <int> se asigna a iAncho
     */
    public void setAncho(int ancho) {
        iAncho = ancho;
    }

    /**Metodo que asigna un valor a iAlto
     * 
     * @param alto del tipo <int> se asigna a iAlto
     */
    public void setAlto(int alto) {
        iAlto = alto;
    }

    /**Metodo que regresa el valor de iAncho
     * 
     * @return <int> iAncho
     */
    public int getAncho() {
        return iAncho;
    }

    /**Metodo que regresa el valor de iAlto
     * 
     * @return <int> iAlto
     */
    public int getAlto() {
        return iAlto;
    }
    
    /**Metodo que asigna un valor a bolDying
     * 
     * @param dying tipo <boolean> se asigna a bolDying
     */
    public void setDying(boolean dying) {
        this.bolDying = dying;
    }

    /**Metodo que regresa el valor de bolDying
     * 
     * @return <boolean> bolDying
     */
    public boolean isDying() {
        return this.bolDying;
    }

    /**<Metodo que pinta el objeto base en pantalla
     * 
     * @param graGrafico tipo <Graphics> de la clase principal
     * @param imoObserver tipo <ImageObserver> de la pantalla principal
     */
    public void paint(Graphics graGrafico, ImageObserver imoObserver) {
    graGrafico.drawImage(getImagen(), getX(), getY(), getAncho(), getAlto(),
                            imoObserver);
    }

    /**Metodo que dice si se intersecta un objeto Base con otro objeto Base
     * 
     * @param objObjeto objeto tipo <Object> con quien se checa interseccion
     * @return <boolean> que es true si se intersectan y false si no
     */
    public boolean intersecta(Object objObjeto) {
        if (objObjeto instanceof Base) {
            Rectangle rctEste = new Rectangle(this.getX(), this.getY(), 
                                this.getAncho(), this.getAlto());
            Base sprTemp = (Base) objObjeto;
            Rectangle rctParam = new Rectangle(sprTemp.getX(),sprTemp.getY(),
                                sprTemp.getAncho(), sprTemp.getAlto());

            return rctEste.intersects(rctParam);
        }
        return false;
    }

    /**Metodo sobreescrito intersecta que dice si el objeto Base intersecta con
     * una posicion en x y y
     * 
     * @param iX tipo <int> la posicion en x
     * @param iY tipo <int> la posicion en y
     * @return <boolean> si el objeto incluye esa coordenada
     */
    public boolean intersecta(int iX, int iY) {

    Rectangle rctEste = new Rectangle(this.getX(), this.getY(), 
                        this.getAncho(), this.getAlto());

    return rctEste.contains(iX, iY);
    }
}
