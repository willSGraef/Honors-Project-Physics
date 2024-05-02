import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class Controller
{
    public static boolean START = false;
    private Button startButton;
	//View and model
	private View view;
	private Model model;

	//Constructor
	public Controller(Model m)
	{
		model = m;
	}

	//Set view method
	void setView(View v)
	{
    	view = v;
        this.startButton = view.getButton();
        this.startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Controller.START = !Controller.START;
                System.out.println(Controller.START);
            }
        } );
	}

	//Update method to update controller
	public void update() {
        if (this.START == true) {
            //Change button name
            if (this.startButton.getLabel().equals("START")) {
                    this.startButton.setLabel("STOP");
            }
            if (this.model.getSprites().isEmpty()) {
                this.view.setValues();
            }
        }
        else {
            if (this.startButton.getLabel().equals("STOP")) {
                    this.startButton.setLabel("START");
            }
        }
    }
}