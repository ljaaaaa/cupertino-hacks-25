import java.awt.Image;
import javax.swing.ImageIcon;

//This is a bit complicated, but essentially, it loads images and has field variables for them
public class LoadedImages {
    public static Image LOGO = new ImageIcon("images/logo.png").getImage();
    public static Image HOME_PAGE = new ImageIcon("images/home_page.png").getImage();
    public static Image INSTRUCTIONS_PAGE = new ImageIcon("images/instructions_page.png").getImage();
    public static Image ENTER_INSTRUCTIONS_PAGE = new ImageIcon("images/enter_instructions_page.png").getImage();

    public static Image RESULTS_PAGE = new ImageIcon("images/results_page.png").getImage();
    public static Image SUPPLIES_LIST_PAGE = new ImageIcon("images/supplies_page.png").getImage();
}
