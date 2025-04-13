import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ContinueListener implements ActionListener{
    CardLayout cards; //To change panels
    JPanel screen; //The screen to draw the panel on

    public ContinueListener(CardLayout cardsIn, JPanel screenIn) {
        cards = cardsIn;
        screen = screenIn;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cards.show(screen, "Directions");
    }
    
}
