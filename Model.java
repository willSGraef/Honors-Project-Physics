//Model class

import java.util.ArrayList;
import java.util.Iterator;


public class Model
{
    public ArrayList<Sprite> sprites; 
    private int forceApplied1 = 0;
    private int forceApplied2 = 0;
    private int angle = 0;
    private double frictionK = 0.1;
    private double frictionS = 0.1;
    private int restitution = 0;
    private double mass1 = 1.0;
    private double mass2 = 1.0;
    public static long startTime;
    


	public Model()
	{
		sprites = new ArrayList<Sprite>();
	}

	//update method that checks for false updating colliders and collisions between colliders
	public void update() {
        if (Controller.START == true) {
            if (this.startTime == 0L){
                this.startTime = System.currentTimeMillis();
            }
            Iterator<Sprite> iterator1 = sprites.iterator();
            while (iterator1.hasNext()){
                Sprite sprite1 = iterator1.next();
                sprite1.update();

                Iterator<Sprite> iterator2 = sprites.iterator();
                while(iterator2.hasNext()){
                    Sprite sprite2 = iterator2.next();
                    
                    //begin collison detection, break up collision results into groups of what is colliding into what
                    if (sprite1.isCollider() && sprite2.isCollider() && (sprite1 != sprite2) && (this.colliding(sprite1, sprite2) == true)) {
                        int sprite1Magnitude = 0;
                        int sprite2Magnitude = 0;
                        int newMagnitude;
                        //Collider 2
                        if(sprite2.getAcceleration() < 0) {
                            newMagnitude = this.forceApplied2 - this.forceApplied1;
                            for (int i = 0; i < sprite2.getForces().size(); i++) {
                                Force tempForce = sprite2.getForces().get(i);
                                if (tempForce.getAxis() == 0 && newMagnitude <= 0) {
                                    System.out.println("Direction changed");
                                    if(sprite2.getS() == 0) continue;    
                                    tempForce.setDirection(tempForce.getDirection()*-1);
                                    //Change kinetic to kinetic/gravitational 
                                    if (this.angle != 0 && tempForce.getType() == 3) {
                                        tempForce.setType(4);
                                    }
                                    //Change applied/gravitational to applied
                                    else if (this.angle != 0 && tempForce.getType() == 5) {
                                        tempForce.setType(2);
                                    }
                                }
                                if ((tempForce.getType() == 2 || tempForce.getType() == 5) && !(newMagnitude == 0)) {
                                    tempForce.setAcceleration((int)((newMagnitude)/tempForce.getMass()));
                                }
                            }
                        }
                        //Collider 1
                        else {
                            newMagnitude = this.forceApplied1 - this.forceApplied2;
                            System.out.println("new mag: " + newMagnitude);
                            for (int i = 0; i < sprite2.getForces().size(); i++) {
                                Force tempForce = sprite2.getForces().get(i);
                                if (tempForce.getAxis() == 0 && newMagnitude <= 0) {
                                    System.out.println("Direction changed");
                                    if(sprite2.getS() == 0) continue;    
                                    tempForce.setDirection(tempForce.getDirection()*-1);
                                    //Change applied force to applied/gravitational 
                                    if (this.angle != 0 && tempForce.getType() == 2) {
                                        tempForce.setType(5);
                                    }
                                    //Change kinetic/gravitational to kinetic
                                    else if (this.angle != 0 && tempForce.getType() == 4) {
                                        tempForce.setType(2);
                                    }
                                }
                                if ((tempForce.getType() == 2 || tempForce.getType() == 5) && !(newMagnitude == 0)) {
                                    tempForce.setAcceleration((int)((newMagnitude)/tempForce.getMass()));
                                }
                            }
                        }
                        //Collider 1
                        if(sprite1.getAcceleration() < 0) {
                            newMagnitude = this.forceApplied2 - this.forceApplied1;
                            for (int i = 0; i < sprite1.getForces().size(); i++) {
                                Force tempForce = sprite1.getForces().get(i);
                                if (tempForce.getAxis() == 0 && newMagnitude <= 0) {
                                    System.out.println("Direction changed");
                                    if(sprite1.getS() == 0) continue;
                                    tempForce.setDirection(tempForce.getDirection()*-1);
                                    //Change kinetic to kinetic/gravitational 
                                    if (this.angle != 0 && tempForce.getType() == 3) {
                                        tempForce.setType(4);
                                    }
                                    //Change applied/gravitational to applied
                                    else if (this.angle != 0 && tempForce.getType() == 5) {
                                        tempForce.setType(2);
                                    }
                                }
                                if ((tempForce.getType() == 2 || tempForce.getType() == 5) && !(newMagnitude == 0)) {
                                    tempForce.setAcceleration((int)((newMagnitude)/tempForce.getMass()));
                                }
                            }
                        }   
                        else {
                            newMagnitude = this.forceApplied1 - this.forceApplied2;
                            System.out.println("new mag: " + newMagnitude);
                            for (int i = 0; i < sprite1.getForces().size(); i++) {
                                Force tempForce = sprite1.getForces().get(i);
                                if (tempForce.getAxis() == 0 && newMagnitude <= 0) {
                                    System.out.println("Direction changed");
                                    if(sprite1.getS() == 0) continue;
                                    tempForce.setDirection(tempForce.getDirection()*-1);
                                    //Change applied force to applied/gravitational 
                                    if (this.angle != 0 && tempForce.getType() == 2) {
                                        tempForce.setType(5);
                                    }
                                    //Change kinetic/gravitational to kinetic
                                    else if (this.angle != 0 && tempForce.getType() == 4) {
                                        tempForce.setType(2);
                                    }
                                }
                                if ((tempForce.getType() == 2 || tempForce.getType() == 5) && !(newMagnitude == 0)) {
                                    tempForce.setAcceleration((int)((newMagnitude)/tempForce.getMass()));
                                }
                            }
                        }
                        double time = (double)((System.currentTimeMillis() - Model.startTime)/1000);
                        sprite1.setAcceleration(0);
                        sprite1.update();
                        sprite2.setAcceleration(0);
                        sprite2.update();
                    }
                }
            }
        } 
        else {
            this.startTime = 0;
            this.clearSprites(this.sprites);
        }
    }

	public ArrayList<Sprite> getSprites(){
		return sprites; 
	}

    public void clearSprites(ArrayList<Sprite> spriteList){
		while (!spriteList.isEmpty()) {
			spriteList.remove(0);
		}
	}

    public void generateSprites(int f1, int f2, int a, double fk, double fs, int r, double m1, double m2){
        this.forceApplied1 = f1;
        this.forceApplied2 = f2;
        this.angle = a;
        this.frictionK = fk;
        this.frictionS = fs;
        this.restitution = r;
        this.mass1 = m1;
        this.mass2 = m2;

        Environment environment = new Environment(50, 250, 400, 2, this.angle, this.frictionK, this.frictionS, "images/Environment.png");
        Collider collider1 = new Collider(environment.getX(), environment.getY(), this.angle, this.mass1, 0, "images/Collider1.png");
        Collider collider2 = new Collider(environment.getW()-(int)(Main.SPRITE_WIDTH*this.mass2) + environment.getX(), environment.getY(), this.angle, this.mass2, 0, "images/Collider2.png");
        
        //Handle forces (no incline)
        if (this.angle == 0) {
            //normal force
            collider1.addForce(1, 0, 1, 0, 0, 0);
            //gravitational force
            collider1.addForce(-1, 1, 1, 0, 0, 0);
            //applied force
            collider1.addForce(1, 2, 0, 0, 0, this.forceApplied1);
            //Check whether it should be kinetic or static friction
            if (this.forceApplied1 <= new Force(0, 0, this.mass1, 0, this.frictionK, 0, this.angle, 1, 3, 0).getMagnitude()) {
                collider1.addForce(-1, 6, 0, 0, this.frictionS, 0);
            }
            else {
                collider1.addForce(-1, 3, 0, this.frictionK, 0, 0);
            }

            //normal force
            collider2.addForce(1, 0, 1, 0, 0, 0);
            //gravitational force
            collider2.addForce(-1, 1, 1, 0, 0, 0);
            //applied force
            collider2.addForce(-1, 2, 0, 0, 0, this.forceApplied2);
            //Check whether it should be kinetic or static friction
            if (this.forceApplied2 <= new Force(0, 0, this.mass2, 0, this.frictionK, 0, this.angle, 1, 3, 0).getMagnitude()) {
                collider2.addForce(1, 6, 0, 0, this.frictionS, 0);
            }
            else {
                collider2.addForce(1, 3, 0, this.frictionK, 0, 0);
            }
        }
        else {
            //normal force
            collider1.addForce(1, 0, 1, 0, 0, 0);
            //gravitational force
            collider1.addForce(-1, 1, 1, 0, 0, 0);
            //applied force
            collider1.addForce(1, 5, 0, 0, 0, this.forceApplied1);
            //Check whether it should be kinetic or static friction
            //Combine future magnitude of force applied and force gravity
            if (this.forceApplied1 + (10*this.mass1) <= new Force(0, 0, this.mass1, 0, this.frictionK, 0, this.angle, 1, 3, 0).getMagnitude()) {
                collider1.addForce(-1, 6, 0, 0, this.frictionS, 0);
            }
            else {
                collider1.addForce(-1, 3, 0, this.frictionK, 0, 0);
            }

            //normal force
            collider2.addForce(1, 0, 1, 0, 0, 0);
            //gravitational force
            collider2.addForce(-1, 1, 1, 0, 0, 0);
            //applied force
            collider2.addForce(-1, 2, 0, 0, 0, this.forceApplied2);
            //Check whether it should be kinetic or static friction
            //Combine future magnitude of kinetic and gravitational forces
            if (this.forceApplied2 <= new Force(0, 0, this.mass2, 0, this.frictionK, 0, this.angle, 1, 3, 0).getMagnitude() + (10*this.mass2)) {
                collider2.addForce(1, 6, 0, 0, this.frictionS, 0);
            }
            else {
                collider2.addForce(1, 4, 0, this.frictionK, 0, 0);
            }
        }

        sprites.add(collider2);
        sprites.add(collider1);
        sprites.add(environment);
        sprites.addAll(collider2.getForces());
        sprites.addAll(collider1.getForces());
    }

    public int getAngle() {
        return this.angle;
    }

    public boolean colliding(Sprite sprite1, Sprite sprite2) {
		if (sprite1.getX() + sprite1.getS() + sprite1.getW() < sprite2.getX() + sprite2.getS()) {
			return false;
		}
		if (sprite1.getX() + sprite2.getS() > sprite2.getX() + sprite2.getS() + sprite2.getW()) {
			return false;
		}
		if (sprite1.getY() + sprite1.getH() < sprite2.getY()) {
			return false;
		}
		if (sprite1.getY() > sprite2.getY() + sprite2.getH()) {
			return false;
		}
		return true;
	}
}