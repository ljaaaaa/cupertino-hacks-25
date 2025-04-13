import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
	private StartPage panel;
	private MousePositionListener mpl; //Listens for the mouse position: used only during developement.
	private LoadedImages images;
	public Main(){

		//Prepare the images
		images = new LoadedImages();
		images.loadImages();

		//Make our start page
		panel = new StartPage(images);
		images = new LoadedImages(); //Load all our images before everything
		setUpFrame();
	}

	public void setUpFrame(){
		JFrame f = new JFrame("Swing Template");
		mpl = new MousePositionListener(Constants.PRINTING);

		f.setIconImage(new ImageIcon("images/logo_with_bg.png").getImage());
		f.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);

		f.setFocusable(true);

		f.add(panel);

		f.addMouseListener(mpl);

		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
