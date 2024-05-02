//Sprite class

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;


public abstract class Sprite {
    protected int x, y, w, h, speed, angle, acceleration;
    abstract void draw(Graphics g);
    abstract void update();
    //abstract boolean update();

    //Mutators

    //Getters

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getW(){
        return w;
    }

    public int getH(){
        return h;
    }
    public double getS(){
        return speed;
    }
    public int getA(){
        return angle;
    }
    public int getAcceleration(){
        return acceleration;
    }
    public int getType(){
        return 0;
    }

    //Setters

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setW(int y) {
        this.w = w;
    }

    public void setH(int y) {
        this.h = h;
    }

    public void setS(int s) {
        this.speed = s;
    }

    public void setAngle(int a) {
        this.angle = a;
    }

    public void setAcceleration(int a) {
        this.acceleration = a;
    }

    public void setSpeed(long time) {}


    public boolean isCollider(){
        return false;
    }

    public boolean isEnvironment(){
        return false;
    }

    public boolean isForce(){
        return false;
    }
    
    public ArrayList<Force> getForces() {
        return null;
    }
}