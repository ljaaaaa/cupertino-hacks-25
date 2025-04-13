import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPage extends JPanel{
    JButton continueButton;
    LoadedImages images;
    ImageIcon icon; //The icon for the button
    
    public StartPage(LoadedImages imagesIn)
    {
        setLayout(null);

        images = imagesIn;

        //Do all image related stuff: catch image loading related errors
        try {
            //Make our Icon
            icon = new ImageIcon(images.buttonImage);
        
            //Make our button
            continueButton = new JButton(icon);
        }
        catch(NullPointerException err) { //Image didn't load
            continueButton = new JButton();
        }

        continueButton.setBounds(50, 50, 100, 50);

        add(continueButton);
    }
}
