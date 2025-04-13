import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Main implements ActionListener {
	private MousePositionListener mpl; //Listens for the mouse position: used only during developement.
	private LoadedImages images;

	private CardLayout cards;

	//All panels here
	private JPanel mainPanel;

	private BackgroundPanel homePanel;
	private BackgroundPanel instructionsPanel;
	private BackgroundPanel suppliesPanel;

	private JButton continueBtn;
	private JButton continueBtn2;
	
	private JTextField suppliesField; //Enter in craft "scraps"

	//Our Dataset manager
	private ProjectsDataset projectData;

	//Only if we really have time to implement multiple methods of item selection
	/*
	private JButton useTextBtn;
	private JButton useFileBtn;
	private JButton useCameraBtn;
	*/

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
		
		//SUPPLIES PANEL
		suppliesPanel = new BackgroundPanel(LoadedImages.SUPPLIES_LIST_PAGE);
		suppliesField = new JTextField();
			suppliesField.setBounds(20, 100, 900, 20);
			suppliesField.addActionListener(this);
		suppliesPanel.add(suppliesField);

		//DATASET MANAGING
		projectData = new ProjectsDataset();

		//Make the CardLayout
		cards = new CardLayout();

		//The main drawing panel, on which everything goes
		mainPanel = new JPanel();
		mainPanel.setLayout(cards);

		//Add panels to the CardLayout
		mainPanel.add("Home", homePanel);
		mainPanel.add("Instructions", instructionsPanel);
		mainPanel.add(suppliesPanel);
		
		cards.show(mainPanel, "Home");

		setUpFrame();
	}

	public void setUpFrame(){
		JFrame f = new JFrame("Scraps to Crafts");
		//mpl = new MousePositionListener(Constants.PRINTING);

		f.setIconImage(new ImageIcon("images/logo_with_bg.png").getImage());
		f.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);

		f.setFocusable(true);

		f.add(mainPanel);
		//StartPage startPage = new StartPage(images, cards, this);
		//f.add(startPage);

		//f.addMouseListener(mpl);

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
			case "Proceed":
				//Again, just go to the next panel
				cards.next(mainPanel);
				break;
			default: //In this case, we are using the Text Field
				projectData.findFromDataset(projectData.processInput(e.getActionCommand()));
			}
		}
	}
}
