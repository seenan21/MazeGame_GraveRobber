package Map;

import java.awt.image.BufferedImage;


public class Wall {
    private int posX;
    private int posY;
    protected BufferedImage sprite;

    public Wall(int posX, int posY, BufferedImage sprite){
        this.posX = posX;
        this.posY = posY;
        this.sprite = sprite;

    }

    public int getPosX() {
        return posX;
    }

    public int getPosY(){return posY;}

    public void render(){} //Must implement this

}
