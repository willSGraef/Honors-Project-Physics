import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Force extends Sprite {
    private static BufferedImage baseImage;
    private BufferedImage arrowImage;
    private int magnitude;
    private int arrowX;
    private int arrowY;
    private int axis;
    private int direction;
    private double frictionK;
    private double frictionS;
    private double mass;
    private int acceleration;
    private int type;
    private int oY;
    private int oX;

    //Collider constructor
    public Force(int x, int y, double mass, int acceleration, double frictionK, double frictionS, int objAngle, int direction, int type, int axis) {

        this.x = x;
        this.oX = x;
        this.y = y;
        this.oY = y;
        this.w = Main.SPRITE_WIDTH/10;
        this.h = Main.SPRITE_HEIGHT/10;
        this.speed = 0;
        this.angle = objAngle;
        this.axis = axis;
        this.direction = direction;
        this.frictionK = frictionK;
        this.frictionS = frictionS;
        this.acceleration = acceleration;
        this.mass = mass;
        this.type = type;

        if (baseImage == null){
            baseImage = View.loadImage("images/ForceArrowBase.png");
        }
        //Handle different force types
        //Check if angle is 0
        if (this.angle == 0) {
            //If normal or gravitational force
            if (type == 0 || type == 1) {
                //Set arrow image to UpNormal
                if (type == 0) {
                    arrowImage = View.loadImage("images/ForceUpNormal.png");
                }
                //Set arrow image to DownGravity
                else {
                    arrowImage = View.loadImage("images/ForceDownGravity.png");
                }
            }
            //If applied force
            else if (type == 2) {
                //Set arrow image to RightApplied
                if (direction == 1) {
                    arrowImage = View.loadImage("images/ForceRightApplied.png");
                }
                //Set arrow image to LeftApplied
                else {
                    arrowImage = View.loadImage("images/ForceLeftApplied.png");
                }

            }
            //If kinetic force
            else if (type == 3) {
                //Set arrow image to RightKinetic
                if (direction == 1) {
                    arrowImage = View.loadImage("images/ForceRightKinetic.png");
                }
                //Set arrow image to LeftKinetic
                else {
                    arrowImage = View.loadImage("images/ForceLeftKinetic.png");
                }
            }
            else if (type == 6) {
                //Set arrow image to RightStatic
                if (direction == 1) {
                    arrowImage = View.loadImage("images/ForceRightStatic.png");
                }
                //Set arrow image to LeftStatic
                else {
                    arrowImage = View.loadImage("images/ForceLeftStatic.png");
                }
            }
        }
        else {
            //If normal or gravitational force on the y axis
            if (type == 0 || type == 1) {
                //Set arrow image to UpNormal
                if (type == 0) {
                    arrowImage = View.loadImage("images/ForceUpNormal.png");
                }
                //Set arrow image to DownGravity
                else {
                    if (axis == 1) {
                        arrowImage = View.loadImage("images/ForceDownGravity.png");
                    } 
                    else {
                        arrowImage = View.loadImage("images/ForceRightGravity.png");
                    }
                }
            }
            //If applied force (angle does not matter here because applied will only ever be by itself when direction is -1)
            else if (type == 2) {
                //Set arrow image to RightApplied
                if (direction == 1) {
                    arrowImage = View.loadImage("images/ForceRightApplied.png");
                }
                //Set arrow image to LeftApplied
                else {
                    arrowImage = View.loadImage("images/ForceLeftApplied.png");
                }
            }
            //Same for kinetic friciton
            else if (type == 3) {
                //Set arrow image to LeftKinetic
                arrowImage = View.loadImage("images/ForceLeftKinetic.png");
            }
            //If kinetic and gravitational
            else if (type == 4) {
                //Set arrow image to LeftGravityKinetic
                arrowImage = View.loadImage("images/ForceRightGravityKinetic.png");
            }
            //If gravitational and applied
            else if (type == 5) {
                //Set arrow image to RightGravityApplied
                arrowImage = View.loadImage("images/ForceRightGravityApplied.png");
            }
            //If static
            else if (type == 6) {
                if (direction == 1) {
                    arrowImage = View.loadImage("images/ForceRightStatic.png");
                }
                //Set arrow image to LeftStatic
                else {
                    arrowImage = View.loadImage("images/ForceLeftStatic.png");
                }
            }
        }   
    }

    @Override
    public void draw(Graphics g) {
        //Draw arrow base
        g.drawImage(this.baseImage, this.x + this.speed, this.y, this.w, this.h, null);
        //Draw arrow head
        g.drawImage(this.arrowImage, this.arrowX + this.speed, this.arrowY, 30, 30, null);
    }

    @Override
    public void update() {
        //Reinit class variables
        int type = this.type;
        double mass = this.mass;
        double frictionK = this.frictionK;
        double frictionS = this.frictionS;
        int acceleration = this.acceleration;
        int axis = this.axis;
        int direction = this.direction;
        this.w = Main.SPRITE_WIDTH/10;
        this.h = Main.SPRITE_HEIGHT/10;
        if (this.angle == 0) {
            //If normal or gravitational force
            if (type == 0 || type == 1) {
                //Set arrow image to UpNormal
                if (type == 0) {
                    arrowImage = View.loadImage("images/ForceUpNormal.png");
                }
                //Set arrow image to DownGravity
                else {
                    arrowImage = View.loadImage("images/ForceDownGravity.png");
                }
            }
            //If applied force
            else if (type == 2) {
                //Set arrow image to RightApplied
                if (direction == 1) {
                    arrowImage = View.loadImage("images/ForceRightApplied.png");
                }
                //Set arrow image to LeftApplied
                else {
                    arrowImage = View.loadImage("images/ForceLeftApplied.png");
                }

            }
            //If kinetic force
            else if (type == 3) {
                //Set arrow image to RightKinetic
                if (direction == 1) {
                    arrowImage = View.loadImage("images/ForceRightKinetic.png");
                }
                //Set arrow image to LeftKinetic
                else {
                    arrowImage = View.loadImage("images/ForceLeftKinetic.png");
                }
            }
            else if (type == 6) {
                //Set arrow image to RightStatic
                if (direction == 1) {
                    arrowImage = View.loadImage("images/ForceRightStatic.png");
                }
                //Set arrow image to LeftStatic
                else {
                    arrowImage = View.loadImage("images/ForceLeftStatic.png");
                }
            }
        }
        else {
            //If normal or gravitational force on the y axis
            if (type == 0 || type == 1) {
                //Set arrow image to UpNormal
                if (type == 0) {
                    arrowImage = View.loadImage("images/ForceUpNormal.png");
                }
                //Set arrow image to DownGravity
                else {
                    if (axis == 1) {
                        arrowImage = View.loadImage("images/ForceDownGravity.png");
                    } 
                    else {
                        arrowImage = View.loadImage("images/ForceRightGravity.png");
                    }
                }
            }
            else if (type == 2) {
                //Set arrow image to LeftApplied
                if (direction == 1) {
                    arrowImage = View.loadImage("images/ForceRightApplied.png");
                }
                //Set arrow image to RightApplied
                else {
                    arrowImage = View.loadImage("images/ForceLeftApplied.png");
                }
            }
            //Same for kinetic friciton
            else if (type == 3) {
                //Set arrow image to LeftKinetic
                if (direction == -1) {
                    arrowImage = View.loadImage("images/ForceLeftKinetic.png");
                }
                //Set arrow image to RightKinetic
                else {
                    arrowImage = View.loadImage("images/ForceRightKinetic.png");
                }
            }
            //If kinetic and gravitational
            else if (type == 4) {
                //Set arrow image to LeftGravityKinetic
                arrowImage = View.loadImage("images/ForceRightGravityKinetic.png");
            }
            //If gravitational and applied
            else if (type == 5) {
                //Set arrow image to RightGravityApplied
                if (direction == 1) {
                    arrowImage = View.loadImage("images/ForceRightGravityApplied.png");
                }
                //Set arrow image to LeftGravityApplied
                else {
                    arrowImage = View.loadImage("images/ForceLeftGravityApplied.png");
                }
            }
            //If static
            else if (type == 6) {
                if (direction == 1) {
                    arrowImage = View.loadImage("images/ForceRightStatic.png");
                }
                //Set arrow image to LeftStatic
                else {
                    arrowImage = View.loadImage("images/ForceLeftStatic.png");
                }
            }
        }

        if (this.angle == 0) {
            //If normal or gravitational force
            if (type == 0 || type == 1) {
                this.magnitude = (int)(10*mass);
            }
            //If applied force
            else if (type == 2) {
                this.magnitude = (int)(acceleration*mass);
            }
            //If kinetic force
            else if (type == 3) {
                this.magnitude = (int)(10*mass*frictionK);
            }
            else if (type == 6) {
                this.magnitude = (int)(10*mass*frictionS);
            }
        }
        else {
            //If normal or gravitational force on the y axis
            if (type == 0 || type == 1) {
                this.magnitude = (int)(10*mass*Math.sin(Math.toRadians(this.angle)));
            }
            //If applied force (angle does not matter here because applied will only ever be by itself when direction is -1)
            else if (type == 2) {
                this.magnitude = (int)(acceleration*mass);
            //Same for kinetic friciton
            }
            else if (type == 3) {
                this.magnitude = (int)(10*mass*frictionK);
            //If kinetic and gravitational
            }
            else if (type == 4) {
                this.magnitude = ((int)(10*mass*Math.sin(Math.toRadians(this.angle))*frictionK))+(int)(10*mass*Math.cos(Math.toRadians(this.angle)));
            }
            //If gravitational and applied
            else if (type == 5) {
                this.magnitude = (int)(acceleration*mass)+(int)(10*mass*Math.cos(Math.toRadians(this.angle)));
            }
            //If static
            else if (type == 6) {
                this.magnitude = (int)(10*mass*frictionS);
            }
        }   

        //If x axis and positive direction 
        if (axis == 0 && direction == 1) {
            this.w = this.magnitude;
            //Define arrow coordinates
            this.arrowX = this.x + this.w;
            this.arrowY = this.y - 12;
        }
        //If y axis and positive direction
        else if (axis == 1 && direction == 1) {
            this.h = this.magnitude;
            this.y = this.oY - this.magnitude;
            //Define arrow coordinates
            this.arrowX = this.x - 12;
            this.arrowY = this.y - 30;
        }
        //If x axis and negative direction
        else if (axis == 0 && direction == -1) {
            this.w = this.magnitude;
            // if (this.angle != 0) {
            //     this.x = this.oX - this.w;
            // }
            //Define arrow coordinates
            this.arrowX = this.x - 30;
            this.arrowY = this.y - 12;
        }
        //If y axis and negative direction
        else if (axis == 1 && direction == -1) {
            this.h = this.magnitude;
            //Define arrow coordinates
            this.arrowX = this.x - 12;
            this.arrowY = this.y + this.h;
        }            
    }

    public int getMagnitude() {
        return this.magnitude;
    }

    public int getDirection() {
        return this.direction;
    }

    public int getAxis() {
        return this.axis;
    }

    public double getMass() {
        return this.mass;
    }

    @Override
    public int getType() {
        return this.type;
    }

    public void setDirection(int direction) {
        this.direction = direction;
        this.update();
    }

    public void setType (int type) {
        this.type = type;
    }

    @Override
    public boolean isForce() {
        return true;
    }
}