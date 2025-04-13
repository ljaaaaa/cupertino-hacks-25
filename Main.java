import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class Main implements ActionListener {
	private MousePositionListener mpl; //Listens for the mouse position: used only during developement.
	private LoadedImages images;

	private CardLayout cards;

	//All panels here
	private JPanel mainPanel;

	private BackgroundPanel homePanel;
	private BackgroundPanel instructionsPanel;
	private BackgroundPanel enterMethodPanel;

	private BackgroundPanel resultsPanel;

	private BackgroundPanel resultsPage;

	private JButton continueBtn;
	private JButton continueBtn2;
	
	private JButton useTextBtn;
	private JButton useFileBtn;
	private JButton useCameraBtn;

	private JLabel resultInput;

	private JLabel result1Name;
	private ImageIcon result1Image;

	public Main() {
		//HOME PANEL
		homePanel = new BackgroundPanel(LoadedImages.HOME_PAGE);
		continueBtn = new JButton("Continue");
			continueBtn.setBackground(new Color(255, 188, 0));
			continueBtn.setBounds(300, 700, 400, 80);
        	continueBtn.addActionListener(this);
		homePanel.add(continueBtn);
		
		//INSTUCTIONS PANEL
		instructionsPanel = new BackgroundPanel(LoadedImages.INSTRUCTIONS_PAGE);
		continueBtn2 = new JButton("Proceed");
			continueBtn2.setBackground(new Color(255, 188, 0));
			continueBtn2.setBounds(300, 700, 400, 80);
        	continueBtn2.addActionListener(this);
		instructionsPanel.add(continueBtn2);
		
		//ENTER METHOD PANEL
		enterMethodPanel = new BackgroundPanel(LoadedImages.ENTER_INSTRUCTIONS_PAGE);

		useTextBtn = new JButton();
		useFileBtn = new JButton();
		useCameraBtn = new JButton();

		//RESULTS PAGE
		resultsPanel = new BackgroundPanel(null);
		resultInput = new JLabel("Pencil, paper");

		ImageIcon icon = new ImageIcon("images/logo_with_bg.png"); // adjust size as needed

        ProjectComponentBox box = new ProjectComponentBox("My Title", icon, "Click here", "https://www.example.com");
        box.setBounds(30, 150, 300, 370); // Position (x=50, y=50)

		ProjectComponentBox box2 = new ProjectComponentBox("My Title", icon, "Click here", "https://www.example.com");
        box2.setBounds(340, 150, 300, 370); // Position (x=50, y=50)

		ProjectComponentBox box3 = new ProjectComponentBox("My Title", icon, "Click here", "https://www.example.com");
        box3.setBounds(660, 150, 300, 370); // Position (x=50, y=50)

		resultsPanel.add(box);
		resultsPanel.add(box2);
		resultsPanel.add(box3);

		//Make the CardLayout
		cards = new CardLayout();

		//The main drawing panel, on which everything goes
		mainPanel = new JPanel();
		mainPanel.setLayout(cards);

		//Add panels to the CardLayout
		mainPanel.add("Home", homePanel);
		mainPanel.add("Instructions", instructionsPanel);
		mainPanel.add(enterMethodPanel);
		
		cards.show(mainPanel, "Home");

		setUpFrame();
	}

	public void setUpFrame(){
		JFrame f = new JFrame("Scraps to Crafts");
		//mpl = new MousePositionListener(Constants.PRINTING);

		f.setIconImage(new ImageIcon("images/logo_with_bg.png").getImage());
		f.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);

		f.setFocusable(true);

		//f.add(mainPanel);
		//StartPage startPage = new StartPage(images, cards, this);
		//f.add(startPage);

		//f.addMouseListener(mpl);

        f.add(resultsPanel);

		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() != null) {
			System.out.println(e.getActionCommand());
			switch (e.getActionCommand()) {
			case "Continue":
				//In this case, just go to the next panel, the instructions panel
				cards.next(mainPanel);
				break;
			}
		}
	}
}
