import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
	private BackgroundPanel noResultsPanel; //when there are no results
	private BackgroundPanel suppliesPanel;

	private JButton continueBtn;
	private JButton continueBtn2;

	private JButton backButton;
	private JButton backButton2; //Same button can't be used on multiple panels
	private JButton backButton3; //For the noResults page

	private JButton nextButton; //Go to the next options

	private JTextField suppliesField; //Enter in craft "scraps"
	private JTextField resultsField; //Show our "scraps"
	private JTextField noResultsField; //Also shows our "scraps"

	private JLabel noResultsLabel;
	
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
		backButton = new JButton("Back to Directions");
			backButton.setBounds(750, 815, 230, 40);
			backButton.addActionListener(this);
		suppliesPanel.add(suppliesField);
		suppliesPanel.add(backButton);

		//DATASET MANAGING
		projectData = new ProjectsDataset();

		//RESULTS PAGE
		resultsPanel = new BackgroundPanel(LoadedImages.RESULTS_PAGE);
		resultsField = new JTextField();
			resultsField.setBounds(320, 60, 660, 20);
			resultsField.setEditable(false);
		backButton2 = new JButton("Back to Directions");
			backButton2.setBounds(750, 750, 230, 40);
			backButton2.addActionListener(this);
		nextButton = new JButton("Next");
			nextButton.setBounds(750, 815, 230, 40); 
			nextButton.addActionListener(this);		
		resultsPanel.add(resultsField);
		resultsPanel.add(backButton2);
		resultsPanel.add(nextButton);

		//NO RESULTS PAGE: QUITE SIMILAR
		noResultsPanel = new BackgroundPanel(LoadedImages.RESULTS_PAGE);
		noResultsField = new JTextField();
			noResultsField.setBounds(320, 60, 660, 20);
			noResultsField.setEditable(false);
		backButton3 = new JButton("Back to Directions");
			backButton3.setBounds(750, 800, 230, 40);
			backButton3.addActionListener(this);
		noResultsLabel = new JLabel("No Results");
			noResultsLabel.setFont(new Font("Parkinsans", Font.BOLD, 25));
			noResultsLabel.setBounds(300, 400, 400, 75);
			noResultsLabel.setForeground(new Color(212, 83, 108));
		noResultsPanel.add(noResultsField);
		noResultsPanel.add(backButton3);
		noResultsPanel.add(noResultsLabel);

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
		mainPanel.add("No Results", noResultsPanel);

		cards.show(mainPanel, "Home");

		setUpFrame();
	}

	public void setUpFrame(){
		JFrame f = new JFrame("Scraps to Crafts");
		f.addMouseListener(new MousePositionListener(Constants.PRINTING));

		f.setIconImage(new ImageIcon("images/logo_with_bg.png").getImage());
		f.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);

		f.setFocusable(true);

		f.add(mainPanel);

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
			case "Back to Directions":
				//Show this panel
				cards.show(mainPanel, "Instructions");
				break;
			case "Next":
				break;
			case "Prev.":
				break;
			default: //In this case, we are using the Text Field
				resultsField.setText(e.getActionCommand()); //Set the field's text
				noResultsField.setText(e.getActionCommand()); //Also this one's
				generateResultsPage(e.getActionCommand()); //Just finds from dataset the info
				//Panel is changed in ^ that method
			}
		}
	}

	public void generateResultsPage(String inputText) {
		
		//Indexes of working projects
		ArrayList<Integer> indexes = projectData.findFromDataset(projectData.processInput(inputText));

		ArrayList<ProjectComponentBox> boxes = new ArrayList<ProjectComponentBox>();

		int x = 20;
		int y = 150;

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

			Image image = new ImageIcon(url).getImage();

			boxes.add(new ProjectComponentBox(stuff[0], new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_DEFAULT)), stuff[1]));
			boxes.get(n).setBounds(x, y, 300, 370);
			x += 300 + 30;
		}
		int i = 0;
		try {
			for (; i < 3; i++) {
				resultsPanel.add(boxes.get(0));
				resultsPanel.add(boxes.get(1));
				resultsPanel.add(boxes.get(2));
			}
			cards.show(mainPanel, "Results");
		}
		catch (IndexOutOfBoundsException e) { //Ran out of indeces
			if (i == 0) {
				cards.show(mainPanel, "No Results");
			}
			else {
				JPanel blankPanel = new JPanel();
				blankPanel.setOpaque(false);
				for (; i < 3; i++) {
					resultsPanel.add(blankPanel);
				}
				cards.show(mainPanel, "Results");
			}
		}
	}
}