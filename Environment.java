import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class Environment extends Sprite {
    private static BufferedImage image;
    private boolean closed; 
    private double frictionK;
    private double frictionS; 

    //Environment constructor
    public Environment(int x, int y, int w, int h, int angle, double frictionK, double frictionS, String filename) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = 0;
        this.angle = angle;
        this.frictionK = frictionK;
        this.frictionS = frictionS;
        if (image == null){
            image = View.loadImage(filename);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(this.image, this.x, this.y, this.w, this.h, null);
    }

    @Override
    public boolean isEnvironment() {
        return true;
    } 

    @Override
    public void update() {}

}