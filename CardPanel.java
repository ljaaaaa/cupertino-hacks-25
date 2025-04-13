import java.awt.CardLayout;

import javax.swing.JPanel;

public class CardPanel extends JPanel{
    LoadedImages images;
    CardLayout cards;
    StartPage startPage; //Our very first page
    InstructionsPage instPage; //Page which has the instructions

    public CardPanel(LoadedImages imagesIn) {
        images = imagesIn;
        cards = new CardLayout(0, 0);
        
        startPage = new StartPage(images, cards, this);

        add(startPage);
        
        /*
        images = imagesIn; //Get access to all loaded images: pass them to contained panels
        
        cards = new CardLayout(0, 0);

        //Make all our panels
        startPage = new StartPage(images, cards, this);
        instPage = new InstructionsPage(images, cards, this);

        //Add all our panels
        add("Start", startPage);
        add("Instructions", instPage);
        */
    }
}
