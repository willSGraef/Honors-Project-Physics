import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.GridLayout;


public class Main extends JFrame
{
	private Model model;
	private Controller controller; 
	private View view;
	public static final int WINDOW_HEIGHT = 500;
	public static final int WINDOW_WIDTH = 1000;
	public static final int MAIN_HEIGHT = 1000;
	public static final int MAIN_WIDTH = 1500;
    public static final int SPRITE_HEIGHT = 50;
    public static final int SPRITE_WIDTH = 50;

	//Main constructor, init model, controller, and view
	public Main()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model, this.getContentPane());
		this.setTitle("2D Collision Simulator");
		this.setSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		this.setFocusable(true);
        this.getContentPane().setLayout(new GridLayout(1,2));
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args)
	{
		Main m = new Main();
		m.run();
	}
	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // This will indirectly call View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// Go to sleep for 40* milliseconds (25 fps)
			try
			{
				Thread.sleep(40);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}
