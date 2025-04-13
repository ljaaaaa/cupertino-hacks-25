import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Main {
	MyKeyListener keyListener;
	MyMouseListener mouseListener;
	MyActionListener actionListener;
	Timer timer;
	MyPanel panel;

	public Main(){
		keyListener = new MyKeyListener();
		mouseListener = new MyMouseListener();
		actionListener = new MyActionListener();
		timer = new Timer(Constants.DELAY, actionListener);
		panel = new MyPanel();

		setUpFrame();
	}

	public void setUpFrame(){
		JFrame f = new JFrame("Swing Template");
		f.setIconImage(new ImageIcon("images/image.png").getImage());
		f.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		f.setLayout(null);

		f.addMouseListener(mouseListener);

		f.addKeyListener(keyListener);
		f.setFocusable(true);

		f.add(panel);

		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		timer.start();
	}
}
