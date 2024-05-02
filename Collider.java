import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;


public class Collider extends Sprite {
    private BufferedImage image;
    private ArrayList<Force> forces;
    private double mass;

    //Collider constructor
    public Collider(int x, int y, int angle, double mass, int acceleration, String filename) {
        this.w = (int)(Main.SPRITE_WIDTH * mass); 
        this.h = (int)(Main.SPRITE_HEIGHT * mass);
        this.x = x;
        this.y = y - this.h;
        this.mass = mass;
        this.speed = 0;
        this.angle = angle;
        forces = new ArrayList<Force>();
        image = View.loadImage(filename);
    }

    public ArrayList<Force> getForces() {
        return forces;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double m) {
        this.mass = m;
    }

    @Override
    public void update() {
        this.speed += (int)(this.getAcceleration()*((System.currentTimeMillis() - Model.startTime)/1000));
        for (int i = 0; i < forces.size(); i++) {
            forces.get(i).setS(this.speed);
        }
        this.updateForces();
    }

    public void updateForces() {
        int forceX = 0;
        int forceY = 0;
        for (int i = 0; i < this.forces.size(); i++){
            forceX = 0;
            forceY = 0;
            Force tempForce = this.forces.get(i);
            int axis = tempForce.getAxis();
            int direction = tempForce.getDirection();
            if (axis == 0 && direction == 1) {
                forceX = this.x + this.w;
                forceY = this.y + (int)(0.5*this.h) - Main.SPRITE_WIDTH/20;
            }
            //If y axis and positive direction
            else if (axis == 1 && direction == 1) {
                forceX = this.x + (int)(0.5*this.w) - Main.SPRITE_WIDTH/20;
                forceY = this.y;
            }
            //If x axis and negative direction
            else if (axis == 0 && direction == -1) {
                forceX = this.x;
                forceY = this.y + (int)(0.5*this.h) - Main.SPRITE_WIDTH/20;
            }
            //If y axis and negative direction
            else if (axis == 1 && direction == -1) {
                forceX = this.x + (int)(0.5*this.w) - Main.SPRITE_WIDTH/20;
                forceY = this.y + this.h;
            }
            tempForce.setX(forceX);
            tempForce.setY(forceY);
        }
    }

    //Clear forces
    public void clearForces() {
        this.forces.clear();
    }

    public void addForce(int direction, int type, int axis, double frictionK, double frictionS, int magnitude) {
        int forceX = 0;
        int forceY = 0;
        //If x axis and positive direction 
        if (axis == 0 && direction == 1) {
            forceX = this.x + this.w;
            forceY = this.y + (int)(0.5*this.h) - Main.SPRITE_WIDTH/20;
        }
        //If y axis and positive direction
        else if (axis == 1 && direction == 1) {
            forceX = this.x + (int)(0.5*this.w) - Main.SPRITE_WIDTH/20;
            forceY = this.y;
        }
        //If x axis and negative direction
        else if (axis == 0 && direction == -1) {
            forceX = this.x;
            forceY = this.y + (int)(0.5*this.h) - Main.SPRITE_WIDTH/20;
        }
        //If y axis and negative direction
        else if (axis == 1 && direction == -1) {
            forceX = this.x + (int)(0.5*this.w) - Main.SPRITE_WIDTH/20;
            forceY = this.y + this.h;
        }
        if (type == 2) {
            forces.add(new Force(forceX, forceY, this.mass, ((int)(magnitude/this.mass)), frictionK, frictionS, this.angle, direction, type, axis));
        }
        else {
            forces.add(new Force(forceX, forceY, this.mass, this.acceleration, frictionK, frictionS, this.angle, direction, type, axis));
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(this.image, this.x + this.speed, this.y, this.w, this.h, null);
    }

    @Override
    public boolean isCollider() {
        return true;
    } 

    @Override
    public int getAcceleration() {
        if (this.acceleration == 0) {
            for (int i = 0; i < forces.size(); i++) {
                Force tempForce = forces.get(i);
                if (tempForce.getAxis() == 0) {
                    if (tempForce.getType() == 6 || tempForce.getType() == 3){
                        if (tempForce.getDirection() == 1 && ((this.speed + this.acceleration + (tempForce.getDirection()*tempForce.getMagnitude())/this.mass)) < 0) {
                            continue;
                        }
                        else if (tempForce.getDirection() == -1 && ((this.speed + this.acceleration + (tempForce.getDirection()*tempForce.getMagnitude())/this.mass)) > 0) {
                            continue;
                        }
                    }
                    else {
                        this.acceleration += (tempForce.getDirection()*tempForce.getMagnitude())/this.mass;
                    }
                }
            }
        }
        return this.acceleration;
    }

}