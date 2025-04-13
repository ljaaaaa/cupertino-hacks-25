import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPage extends JPanel{
    JLabel welcomeLabel;
    
    public StartPage()
    {
        setLayout(null);
    
        //Define our components
        welcomeLabel = new JLabel("Welcome to:");
    
        //Position our components
        welcomeLabel.setBounds(0, 0, 300, 50);

        //Add our components
        add(welcomeLabel);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }
}
