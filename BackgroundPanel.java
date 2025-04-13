import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;

public class BackgroundPanel extends JPanel {

	Image image;

	public BackgroundPanel(Image image){
		setLayout(null);
		setBounds(Constants.FRAME_BOUNDS);
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, this);
	}
}
