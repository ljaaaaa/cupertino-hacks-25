import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MousePositionListener implements MouseListener{
    public boolean printing;
    
    public MousePositionListener(boolean printOrNot)
    {
        printing = printOrNot;        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Only print while developing
        if (printing)
        {
            int x = e.getX();
            int y = e.getY();
            System.out.printf("(%d, %d)\n", x, y);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
