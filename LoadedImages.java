import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//This is a bit complicated, but essentially, it loads images and has field variables for them
public class LoadedImages {
    public Image buttonImage;

    public void loadImages() {
        buttonImage = getImage("startButton.png");
    }

    public Image getImage(String imageName) {
        Image imageOut = null;
        try {
            File file = new File("images/" + imageName); //TODO: make it relative path
            imageOut = ImageIO.read(file);
        }
        catch (IOException e) {
            System.err.println("Could not load image: " + imageName);
            e.printStackTrace();
        }

        return imageOut;
    }
}
