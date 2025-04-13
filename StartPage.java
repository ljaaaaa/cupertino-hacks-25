import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPage extends JPanel{
    JButton continueButton;
    LoadedImages images;
    ImageIcon icon; //The icon for the button
    Image background;

    public StartPage(LoadedImages imagesIn, CardLayout cards, JPanel screen)
    {
        setLayout(null);

        images = imagesIn;

        //Do all image related stuff: catch image loading related errors
        try {
            //Our background image
            background = images.homePage;
            
            //Make our Icon
            icon = new ImageIcon(images.buttonImage);
        
            //Make our button
            continueButton = new JButton(icon);
        }
        catch(NullPointerException err) { //Image didn't load
            continueButton = new JButton();
        }

        //Set bounds of the button
        continueButton.setBounds(300, 700, 400, 100);

        //continueButton.addActionListener(new ContinueListener(cards, screen));

        add(continueButton);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
        System.out.println("Not being displayed :( ...");
    }
}
