import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;

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
	private BackgroundPanel suppliesPanel;

	private JButton continueBtn;
	private JButton continueBtn2;
	
	private JTextField suppliesField; //Enter in craft "scraps"
	private JTextField resultsField; //Show our "scraps"

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
		
		//ENTER METHOD PANEL
		enterMethodPanel = new BackgroundPanel(LoadedImages.ENTER_INSTRUCTIONS_PAGE);

		//SUPPLIES PANEL
		suppliesPanel = new BackgroundPanel(LoadedImages.SUPPLIES_LIST_PAGE);
		suppliesField = new JTextField();
			suppliesField.setBounds(20, 300, 960, 20);
			suppliesField.addActionListener(this);
		suppliesPanel.add(suppliesField);

		//DATASET MANAGING
		projectData = new ProjectsDataset();

		//RESULTS PAGE
		resultsPanel = new BackgroundPanel(LoadedImages.RESULTS_PAGE);
		resultsField = new JTextField();
			resultsField.setBounds(20, 0, 960, 20);
			resultsField.setEditable(false);
		resultsPanel.add(resultsField);

		ImageIcon icon = new ImageIcon("images/logo_with_bg.png"); // adjust size as needed

		//Make the CardLayout
		cards = new CardLayout();

		//The main drawing panel, on which everything goes
		mainPanel = new JPanel();
		mainPanel.setLayout(cards);

		//Add panels to the CardLayout
		mainPanel.add("Home", homePanel);
		mainPanel.add("Instructions", instructionsPanel);
		mainPanel.add("Supplies", suppliesPanel);
		mainPanel.add("Results", resultsPanel);
		
		cards.show(mainPanel, "Home");

		setUpFrame();
	}

	public void setUpFrame(){
		JFrame f = new JFrame("Scraps to Crafts");
		//f.addMouseListener(new MousePositionListener(Constants.PRINTING));

		f.setIconImage(new ImageIcon("images/logo_with_bg.png").getImage());
		f.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);

		f.setFocusable(true);

		f.add(mainPanel);
		//generateResultsPage("yarn");
		//f.add(resultsPage);

		//f.addMouseListener(mpl);

		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() != null) {
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
				generateResultsPage(e.getActionCommand()); //Just finds from dataset the info
				cards.next(mainPanel);
				break;
			}
		}
	}

	public void generateResultsPage(String inputText) {
		resultsPanel.removeAll();
		
		//Indexes of working projects
		ArrayList<Integer> indexes = projectData.findFromDataset(projectData.processInput(inputText));

		ArrayList<ProjectComponentBox> boxes = new ArrayList<ProjectComponentBox>();

		int x = 20;
		int y = 150;

		//TODO... max 3 but what if less than 3 results
		for (int n = 0; n < indexes.size(); n++){
			int index = indexes.get(n);
			String[] stuff = projectData.getRow(index);

			//Generate an ImageIcon from a link to an online image
			URL url = null;
			try {
				url = new URL(stuff[2]);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ImageIcon image = new ImageIcon(url);

			boxes.add(new ProjectComponentBox(stuff[0], image, stuff[1]));
			boxes.get(n).setBounds(x, y, 300, 370);
			x += 300 + 30;
		}

		resultsPanel.add(boxes.get(0));
		resultsPanel.add(boxes.get(1));
		resultsPanel.add(boxes.get(2));
		System.out.println("added boxes??");
	}
}