import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.util.ArrayList;



public class View extends JPanel
{
	private Model model;
    private JPanel panel1;
    private JPanel panel2, panel2_1, panel2_1_1, panel2_1_2, panel2_2, panel2_2_1, panel2_2_2;
    private JPanel panel3, panel3_1, panel3_1_1, panel3_1_2, panel3_2, panel3_2_1, panel3_2_2;
    private TextField mass1TF, mass2TF, appliedForce1TF, appliedForce2TF, angleTF, frictionSTF, frictionKTF;
    private Button startButton;


	//view constructor
	public View(Controller c, Model m, Container content)
	{
		model = m;

        //Build UI
        this.setLayout(new GridLayout(1,2));
        panel1 = new JPanel(new GridLayout(2,1));
        //Inside panel1
        panel2 = new JPanel(new GridLayout(1,2));
        //Inside panel2
        panel2_1 = new JPanel(new GridLayout(3,1));
        //Inside panel2_1
        panel2_1_1 = new JPanel(new GridLayout(1,2));
        panel2_1_2 = new JPanel(new GridLayout(1,2));

        panel2_2 = new JPanel(new GridLayout(3,1));
        //Inside panel2_2
        panel2_2_1 = new JPanel(new GridLayout(1,2));
        panel2_2_2 = new JPanel(new GridLayout(1,2));

        panel3 = new JPanel(new GridLayout(4,1));
        //Inside panel3
        panel3_1 = new JPanel(new GridLayout(1,2));
        //Inside panel3_1
        panel3_1_1 = new JPanel(new GridLayout(1,2));
        panel3_1_2 = new JPanel(new GridLayout(1,2));

        panel3_2 = new JPanel(new GridLayout(1,2));
        //Inside panel3_2
        panel3_2_1 = new JPanel(new GridLayout(1,2));
        panel3_2_2 = new JPanel(new GridLayout(1,2));

        content.add(panel1);
        panel1.add(panel2);
        panel1.add(panel3);
        panel2.add(panel2_1);
        panel2.add(panel2_2);
        panel3.add(new Label("ENVIRONMENT", 1));
        panel3.add(panel3_1);
        panel3.add(panel3_2);
        panel3.add(this.startButton = new Button("START"));
        //Object 1 panel
        panel2_1.add(new Label("OBJ 1", 1));
        panel2_1.add(panel2_1_1);
        panel2_1_1.add(new Label("APPLIED FORCE", 1));
        panel2_1_1.add(this.appliedForce1TF = new TextField("1"));
        panel2_1.add(panel2_1_2);
        panel2_1_2.add(new Label("MASS", 1));
        panel2_1_2.add(this.mass1TF = new TextField("1"));
        //Object 2 panel
        panel2_2.add(new Label("OBJ 2", 1));
        panel2_2.add(panel2_2_1);
        panel2_2_1.add(new Label("APPLIED FORCE", 1));
        panel2_2_1.add(this.appliedForce2TF = new TextField("2"));
        panel2_2.add(panel2_2_2);
        panel2_2_2.add(new Label("MASS", 1));
        panel2_2_2.add(this.mass2TF = new TextField("2"));
        //Environment Condtions
        //3_1
        panel3_1.add(panel3_1_1);
        panel3_1_1.add(new Label("ANGLE", 1));
        panel3_1_1.add(this.angleTF = new TextField("0"));
        panel3_1.add(panel3_1_2);
        panel3_1_2.add(new Label("STATIC F", 1));
        panel3_1_2.add(this.frictionSTF = new TextField("0"));
        //3_2
        panel3_2.add(panel3_2_1);
        panel3_2_1.add(new Label("KINETIC F", 1));
        panel3_2_1.add(this.frictionKTF = new TextField("0"));
        panel3_2.add(panel3_2_2);
        panel3_2_2.add(new Label("", 1));
        panel3_2_2.add(new Label(""));

        //Set view in controller
        c.setView(this);

	}

    public Button getButton() {
        return this.startButton;
    }

	//paint component method
	public void paintComponent(Graphics g)
	{
        Graphics2D g2 = (Graphics2D)g;
		//set color to black
        g2.rotate((Math.toRadians(model.getAngle())), 250.0, 250.0);
		g.setColor(new Color(245, 255, 250));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//draw sprites
		for (int i = 0; i < model.sprites.size(); i++){
			model.sprites.get(i).draw(g);
		}
	}

	public static BufferedImage loadImage(String filename) {
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(new File(filename));
		}
		catch(Exception e) 
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return image;
	}

    //Set values method
    public void setValues() {
        //Set model values to stored values in text boxes
        int f1 = Integer.parseInt(this.appliedForce1TF.getText());

        int f2 = Integer.parseInt(this.appliedForce2TF.getText());

        int a = Integer.parseInt(this.angleTF.getText());

        double fk = Double.parseDouble(this.frictionKTF.getText());

        double fs = Double.parseDouble(this.frictionSTF.getText());

        double m1 = Double.parseDouble(this.mass1TF.getText());
        
        double m2 = Double.parseDouble(this.mass2TF.getText());

        //Generate values for model
        this.model.generateSprites(f1, f2, a, fk, fs, 0, m1, m2);
    }
}

