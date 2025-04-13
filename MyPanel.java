import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyPanel extends JPanel {

	public MyPanel(){
		setBounds(Constants.FRAME_BOUNDS);
	}

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		g2d.drawImage(new ImageIcon("images/image.png").getImage(), 0, 0, 100, 100, null);
	}
}
